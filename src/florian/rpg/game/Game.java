package florian.rpg.game;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import florian.rpg.display.Display;
import florian.rpg.manager.KeyManager;
import florian.rpg.states.OpenWorldState;
import florian.rpg.states.State;

public class Game implements Runnable {

	private Handler handler;
	
	private Thread thread;
	
	private boolean running = false;
	private int width, height;
	private String title;
	private int fps;
	
	public Game(int width, int height, String title){
		this.width = width;
		this.height = height;
		this.title = title;
	}
	
	private void init() {
		handler = new Handler();
		handler.setDisplay(new Display(width, height, title));
		handler.setGame(this);;
		handler.setKeys(new KeyManager());
		handler.getFrame().addKeyListener(handler.getKeys());
		
		handler.setOpenWorldState(new OpenWorldState(handler));
		State.setState(handler.getOpenWorldState());
	}
	
	public void run(){
		init();
		
		start();

		fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while(running){
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1){
				tick();
				render();
				delta--;
				ticks++;
			}
			
			if(timer >= 1000000000){
				System.out.println("FPS: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}
		
		stop();
	}
	
	private void tick() {
		State.getState().tick();
	}
	
	private void render() {
		BufferStrategy bs = handler.getDisplay().getCanvas().getBufferStrategy();
		if(bs == null) {
			handler.getDisplay().getCanvas().createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
		g.clearRect(0, 0, width, height);
		
		State.getState().render(g);
		
		bs.show();
		g.dispose();
	}
	
	public synchronized void start(){
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop(){
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public int getFPS() {
		return fps;
	}
}
