package florian.rpg.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import florian.rpg.battle.Attack;
import florian.rpg.game.Handler;
import florian.rpg.objects.CollidingObject;
import florian.rpg.utils.Maths.Vector2;

public abstract class Entity extends CollidingObject {
	
	protected int health, maxHealth;
	protected int mana, maxMana;
	protected int shield = 0;
	
	protected Attack[] attacks;
	protected boolean isFighting = false;
	
	private int manaPerSecond;
	private int manaCooldown = 0;
	
	public Entity(int health, int mana, int manaPerSecond, int width, int height, BufferedImage image, Handler handler) {
		super(width, height, image, handler);
		
		this.health = health;
		this.maxHealth = health;
		this.mana = mana;
		this.manaPerSecond = manaPerSecond;
	}
	
	public Entity(int health, int mana, int manaPerSecond, Vector2 pos, int width, int height, BufferedImage image, Handler handler) {
		super(pos, width, height, image, handler);
		this.maxHealth = health;
		this.manaPerSecond = manaPerSecond;
		this.health = health;
		this.maxMana = mana;
		this.mana = mana;
	}
	
	@Override
	public void tick(float delta) {
		manaCooldown++;
		if(manaCooldown % (this.handler.getGame().getFPS() / manaPerSecond) == 0) {
			mana += manaPerSecond;
			if(mana > maxMana)
				mana = maxMana;
		}
		
		if(manaCooldown >= this.handler.getGame().getFPS())
			manaCooldown -= this.handler.getGame().getFPS();
		
		for(Attack a : attacks)
			a.tick(delta);
	}
	
	@Override
	public void render(Graphics g) {
		super.render(g);
		g.setColor(Color.GRAY);
		g.fillRect((int) (this.pos.x - this.handler.getCamera().getxOffset()), (int) (this.pos.y - this.handler.getCamera().getyOffset()), this.width, 10);
		g.setColor(Color.RED);
		g.fillRect((int) (this.pos.x - this.handler.getCamera().getxOffset()), (int) (this.pos.y - this.handler.getCamera().getyOffset()), (int) (this.width * (1f / ((float) maxHealth / (float) health))), 10);
		
		/*g.setColor(Color.RED); //--- Collision Box ---
		g.fillRect((int) (x + bounds.x - handler.getCamera().getxOffset()), (int) (y + bounds.y - handler.getCamera().getyOffset()), bounds.width, bounds.height);*/
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
	
	public int getHealth() {
		return health;
	}
	
	public void heal(int amount) {
		health += amount;
		if(health > maxHealth)
			health = maxHealth;
	}
	
	public boolean isFighting() {
		return isFighting;
	}

	public void setFighting(boolean isFighting) {
		this.isFighting = isFighting;
	}

	public void attack(int damage) {
		damage -= this.shield;
		if(damage > 0) {
			this.health -= damage;
			if(this.health <= 0) {
				this.die();
			}
		}
	}
	
	public int getShield() {
		return shield;
	}

	public void setShield(int shield) {
		this.shield = shield;
	}
	
	public int getManaLevel() {
		return mana;
	}
	
	public boolean costMana(int amount) {
		if(mana > amount) {
			mana -= amount;
			return true;
		} else
			return false;
	}

}
