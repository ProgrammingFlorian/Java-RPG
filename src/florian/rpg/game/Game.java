package florian.rpg.game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;

import florian.rpg.config.CONF_Display;
import florian.rpg.display.Display;
import florian.rpg.manager.KeyManager;
import florian.rpg.manager.MouseManager;
import florian.rpg.states.OpenWorldState;
import florian.rpg.states.State;

public class Game implements Runnable {

	private Handler handler;
	
	private Thread thread;
	
	private boolean running = false;
	private String title;
	private int fps = 60;
	
	public Game(String title){
		this.title = title;
	}
	
	private void init() {
		handler = new Handler();
		handler.setDisplay(new Display(CONF_Display.width, CONF_Display.height, title));
		handler.setGame(this);;
		handler.setKeys(new KeyManager());
		handler.getFrame().addKeyListener(handler.getKeys());
		handler.setMouse(new MouseManager());
		handler.getFrame().addMouseListener(handler.getMouse());
		handler.getFrame().addMouseMotionListener(handler.getMouse());
		handler.getDisplay().getCanvas().addMouseListener(handler.getMouse());
		handler.getDisplay().getCanvas().addMouseMotionListener(handler.getMouse());
		
		handler.setOpenWorldState(new OpenWorldState(handler));
		State.setState(handler.getOpenWorldState());
	}
	
	public void run(){
		init();
		
		start();

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
		handler.getKeys().tick();
		handler.getMouse().tick();
	}
	
	private void render() {
		BufferStrategy bs = handler.getDisplay().getCanvas().getBufferStrategy();
		if(bs == null) {
			handler.getDisplay().getCanvas().createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
		g.clearRect(0, 0, CONF_Display.width, CONF_Display.height);
		
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
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
