package florian.tbc.battle;

import java.awt.Graphics;

import florian.tbc.game.Handler;

public abstract class Attack {

	protected int damage;
	protected int manaCost;
	protected String name;
	protected boolean active = false;
	protected int delta;
	protected float length;
	protected Handler handler;
	
	public Attack(Handler handler) {
		this.handler = handler;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public int getManaCost() {
		return manaCost;
	}
	
	public String getName() {
		return name;
	}
	
	public void activate() {
		System.out.println("Activated " + this.name);
		this.active = true;
	}
	
	public void render(Graphics g, int x1, int x2, int y1, int y2) {
		if(this.active) {
			delta++;
			float percentage = 1f / ((float) length * handler.getGame().getFPS() / (float) delta);
			draw(g, x1, x2, y1, y2, percentage);
			if(percentage >= 1) {
				this.active = false;
				delta = 0;
			}
		}
	}
	
	public abstract void draw(Graphics g, int x1, int x2, int y1, int y2, float percentage);
	
}
