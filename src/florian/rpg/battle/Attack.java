package florian.rpg.battle;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import florian.rpg.entities.Entity;
import florian.rpg.game.Handler;

public abstract class Attack {

	protected int damage;
	protected int manaCost;
	protected String name;
	protected boolean active = false;
	protected float length;
	protected Handler handler;
	
	protected float percentage = 0;
	protected int delta;
	
	protected BufferedImage icon;
	
	public Attack(Handler handler) {
		this.handler = handler;
	}
	
	public int getDamage() {
		return this.damage;
	}
	
	public int getManaCost() {
		return this.manaCost;
	}
	
	public String getName() {
		return this.name;
	}
	
	public BufferedImage getIcon(){
		return this.icon;
	}
	
	public void activate() {
		System.out.println("Activated " + this.name);
		this.active = true;
	}
	
	public void tick(Entity enemy){
		if(this.active) {
			this.delta++;
			this.percentage = 1f / ((float) this.length * handler.getGame().getFPS() / (float) this.delta);
			if(this.percentage >= 1) {
				this.active = false;
				this.delta = 0;
				enemy.attack(damage);
			}
		}
	}
	
	public abstract void render(Graphics g, int x1, int x2, int y1, int y2);
	
}
