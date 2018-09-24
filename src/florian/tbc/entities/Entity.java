package florian.tbc.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import florian.tbc.battle.Attack;
import florian.tbc.game.Handler;

public abstract class Entity {
	
	protected Handler handler;
	
	protected int health, maxHealth;
	protected int mana, maxMana;
	protected int posX, posY;
	protected int width, height;
	protected BufferedImage image;
	
	protected Attack[] attacks;
	
	public Entity(int health, int mana, int posX, int posY, int width, int height, BufferedImage image, Handler handler) {
		this.handler = handler;
		this.maxHealth = health;
		this.health = health;
		this.maxMana = mana;
		this.mana = mana;
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
		this.image = image;
	}
	
	public abstract void tick();
	
	public void render(Graphics g) {
		g.drawImage(image, (int) (this.posX - handler.getCamera().getxOffset()), (int) (this.posY - handler.getCamera().getyOffset()), width, height, null);
	}
	
	public int getX() {
		return this.posX;
	}
	
	public int getMidX() {
		return this.posX + (this.width/ 2);
	}
	
	public int getY() {
		return this.posY;
	}
	
	public int getMidY() {
		return this.posY + (this.height / 2);
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public Attack getAttack(int ID) {
		return attacks[ID];
	}
	
	public int attackCount() {
		if(attacks != null)
			return attacks.length;
		else
			return 0;
	}

}
