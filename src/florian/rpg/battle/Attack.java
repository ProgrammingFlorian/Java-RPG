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
	protected int cooldown = 0, remainingCooldown = 0;
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
		if(!handler.getPlayer().costMana(manaCost))
			return;
		System.out.println("Activated " + this.name);
		this.active = true;
	}
	
	public void tick(Entity enemy){
		if(remainingCooldown > 0){
			remainingCooldown -= 1;
			this.active = false;
		}else if(this.active) {
			this.delta++;
			this.percentage = 1f / ((float) this.length * handler.getGame().getFPS() / (float) this.delta);
			if(this.percentage >= 1) {
				enemy.attack(damage);
				deactivate();
			}
		}
	}
	
	public void deactivate(){
		active = false;
		delta = 0;
		remainingCooldown = cooldown; 
	}
	
	public boolean canActivate(){
		return remainingCooldown <= 0;
	}
	
	public abstract void render(Graphics g, int x1, int x2, int y1, int y2);
	
}
