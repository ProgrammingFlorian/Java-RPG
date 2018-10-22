package florian.rpg.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import florian.rpg.assets.Animation;
import florian.rpg.assets.Assets;
import florian.rpg.battle.Attack;
import florian.rpg.battle.Battle;
import florian.rpg.battle.Claw;
import florian.rpg.battle.Pow;
import florian.rpg.game.Handler;
import florian.rpg.states.BattleState;
import florian.rpg.states.State;

public class Player extends Entity {

	private int speed;
	
	private Animation animUp, animDown, animLeft, animRight;
	private BufferedImage imgIdle;
	private boolean idle = true;
	
	private int manaPerSecond = 10;
	private int delta;
	private int shield = 0;
	
	public Player(int health, int mana, int posX, int posY, int speed, int width, int height, Handler handler) {
		super(health, mana, posX, posY, width, height, null, handler);
		this.speed = speed;
		this.bounds = new Rectangle(12, 20, 39, 39);
		
		this.maxMana = 500;
		this.mana = 200;
		
		this.attacks = new Attack[3];
		this.attacks[0] = new Claw(handler);
		this.attacks[1] = new Pow(handler);
		this.attacks[2] = new Claw(handler);
		
		int fps = handler.getGame().getFPS();
		animUp = new Animation(Assets.getPlayerUp(), fps, 10);
		animDown = new Animation(Assets.getPlayerDown(), fps, 10);
		animLeft = new Animation(Assets.getPlayerLeft(), fps, 10);
		animRight = new Animation(Assets.getPlayerRight(), fps, 10);
		imgIdle = animDown.getFrame(0);
	}

	@Override
	public void tick() {
		if(!isFighting){
			updateMovement();
			super.move();
			checkForFight();
			handler.getCamera().centerOnEntity(this);
		}
		delta++;
		if(delta % (handler.getGame().getFPS() / manaPerSecond) == 0){
			mana += manaPerSecond;
			if(mana > maxMana)
				mana = maxMana;
		}
		
		if(delta >= handler.getGame().getFPS())
			delta -= handler.getGame().getFPS();
	}
	
	private void updateMovement(){
		if(handler.getKeys().up) {
			moveY = -speed;
			if(idle){
				animUp.reset();
				idle = false;
			}else
				animUp.tick();
			this.image = animUp.getCurrentFrame();
		}else if(handler.getKeys().down) {
			moveY = speed;
			if(idle){
				animDown.reset();
				idle = false;
			}else
				animDown.tick();
			this.image = animDown.getCurrentFrame();
		}else {
			moveY = 0;
		}
		if(handler.getKeys().right) {
			this.moveX = speed;
			if(idle){
				animRight.reset();
				idle = false;
			}else
				animRight.tick();
			this.image = animRight.getCurrentFrame();
		}else if(handler.getKeys().left) {
			this.moveX = -speed;
			if(idle){
				animLeft.reset();
				idle = false;
			}else
				animLeft.tick();
			this.image = animLeft.getCurrentFrame();
		}else {
			moveX = 0;
		}
		if(moveX == 0 && moveY == 0){
			this.image = imgIdle;
			idle = true;
		}
	}
	
	private void checkForFight(){
		for(Entity e : handler.getOpenWorldState().getEntities()){
			if(Math.abs(e.getX() - this.posX) < 120 && Math.abs(e.getY() - this.posY) < 120){
				this.setFighting(true);
				e.setFighting(true);
				State.setState(new BattleState(new Battle(this, e, handler), handler));
			}
		}
	}
	
	@Override
	public void render(Graphics g){
		super.render(g);
		//Health
		g.setColor(Color.GRAY);
		g.fillRect(10, 10, 200, 20);
		g.setColor(Color.RED);
		g.fillRect(10, 10, (int) (200 * (1f / ((float) maxHealth / (float) health))), 20);
		g.setColor(Color.BLACK);
		g.drawString("Health: " + health, 15, 25);
		//Mana
		g.setColor(Color.GRAY);
		g.fillRect(10, 30, 200, 20);
		g.setColor(Color.BLUE);
		g.fillRect(10, 30, (int) (200 * (1f / ((float) maxMana / (float) mana))), 20);
		g.setColor(Color.BLACK);
		g.drawString("Mana: " + mana, 15, 45);
	}
	
	public int getManaLevel(){
		return mana;
	}
	
	public boolean costMana(int amount){
		if(mana > amount){
			mana -= amount;
			return true;
		}else
			return false;
	}

	public int getShield() {
		return shield;
	}

	public void setShield(int shield) {
		this.shield = shield;
	}
	
	@Override
	public void attack(int damage){
		damage -= shield;
		if(damage > 0)
			super.attack(damage);
	}
	
}
