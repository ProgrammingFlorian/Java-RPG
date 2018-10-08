package florian.rpg.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import florian.rpg.battle.Attack;
import florian.rpg.game.Handler;
import florian.rpg.world.World;

public abstract class Entity {
	
	protected Handler handler;
	
	protected boolean alive = true;
	protected int health, maxHealth;
	protected int mana, maxMana;
	protected int posX, posY;
	protected float moveX, moveY;
	protected int width, height;
	protected BufferedImage image;
	protected Rectangle bounds;
	
	protected Attack[] attacks;
	protected boolean isFighting = false;
	
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
		
		this.bounds = new Rectangle(0, 0, width, height);
	}
	
	public abstract void tick();
	
	public void render(Graphics g) {
		g.drawImage(image, (int) (this.posX - handler.getCamera().getxOffset()), (int) (this.posY - handler.getCamera().getyOffset()), width, height, null);
		if(isFighting) {
			g.setColor(Color.GRAY);
			g.fillRect((int) (this.posX - handler.getCamera().getxOffset()), (int) (this.posY - handler.getCamera().getyOffset()), width, 10);
			g.setColor(Color.RED);
			g.fillRect((int) (this.posX - handler.getCamera().getxOffset()), (int) (this.posY - handler.getCamera().getyOffset()), (int) (width * (1f / ((float) maxHealth / (float) health))), 10);
		}
		
		/*g.setColor(Color.RED); --- Collision Box ---
		g.fillRect((int) (posX + bounds.x - handler.getCamera().getxOffset()), (int) (posY + bounds.y - handler.getCamera().getyOffset()), bounds.width, bounds.height);*/
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
	
	public int getHealth(){
		return health;
	}
	
	public boolean isAlive(){
		return alive;
	}
	
	public boolean isFighting() {
		return isFighting;
	}

	public void setFighting(boolean isFighting) {
		this.isFighting = isFighting;
	}
	
	public void move() {
		moveX();
		moveY();
	}
	
	public void moveX() {
		if(moveX > 0) {
			int tx = (int) (posX + moveX + bounds.x + bounds.width) / World.TILE_SIZE;
			if(!collisionWithTile(tx, (int) (posY + bounds.y) / World.TILE_SIZE) && !collisionWithTile(tx, (int) (posY + bounds.y + bounds.height) / World.TILE_SIZE))
				posX += moveX;
			else
				posX = tx * World.TILE_SIZE - bounds.x - bounds.width - 1;
		}else if(moveX < 0) {
			int tx = (int) (posX + moveX + bounds.x) / World.TILE_SIZE;
			if(!collisionWithTile(tx, (int) (posY + bounds.y) / World.TILE_SIZE) && !collisionWithTile(tx, (int) (posY + bounds.y + bounds.height) / World.TILE_SIZE))
				posX += moveX;
			else
				posX = tx * World.TILE_SIZE + World.TILE_SIZE - bounds.x;
		}
	}
	
	public void moveY() {
		if(moveY > 0) {
			int ty = (int) (posY + moveY + bounds.y + bounds.height) / World.TILE_SIZE;
			if(!collisionWithTile((int) (posX + bounds.x) / World.TILE_SIZE, ty) && !collisionWithTile((int) (posX + bounds.x + bounds.width) / World.TILE_SIZE, ty))
				posY += moveY;
			else
				posY = ty * World.TILE_SIZE - bounds.y - bounds.height - 1;
		}else if(moveY < 0) {
			int ty = (int) (posY + moveY + bounds.y) / World.TILE_SIZE;
			if(!collisionWithTile((int) (posX + bounds.x) / World.TILE_SIZE, ty) && !collisionWithTile((int) (posX + bounds.x + bounds.width) / World.TILE_SIZE, ty))
				posY += moveY;
			else
				posY = ty * World.TILE_SIZE + World.TILE_SIZE - bounds.y;
		}
	}
	
	protected boolean collisionWithTile(int x, int y) {
		return handler.getWorld().isSolid(x, y);
	}

	public void attack(int damage){
		this.health -= damage;
		if(this.health <= 0){
			this.die();
		}
	}
	
	private void die(){
		this.alive = false;
	}

}
