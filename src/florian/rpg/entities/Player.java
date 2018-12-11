package florian.rpg.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import florian.rpg.assets.Animation;
import florian.rpg.assets.Assets;
import florian.rpg.battle.Attack;
import florian.rpg.battle.Claw;
import florian.rpg.battle.Pow;
import florian.rpg.game.Handler;
import florian.rpg.utils.Maths.Vector2;

public class Player extends Entity {

	private int speed;
	
	private Animation animUp, animDown, animLeft, animRight;
	private BufferedImage imgIdle;
	private boolean idle = true;
	
	public Player(int health, Vector2 pos, int speed, int width, int height, Handler handler) {
		super(health, 200, 30, pos, width, height, null, handler);
		this.speed = speed;
		this.bounds = new Rectangle(12, 20, 39, 39);
		
		this.maxMana = 500;
		this.mana = 200;
		
		this.attacks = new Attack[2];
		this.attacks[0] = new Claw(handler, this);
		this.attacks[1] = new Pow(handler, this);
		
		int fps = handler.getGame().getFPS();
		animUp = new Animation(Assets.getPlayerUp(), fps, 10);
		animDown = new Animation(Assets.getPlayerDown(), fps, 10);
		animLeft = new Animation(Assets.getPlayerLeft(), fps, 10);
		animRight = new Animation(Assets.getPlayerRight(), fps, 10);
		imgIdle = animDown.getFrame(0);
	}

	@Override
	public void tick(float delta) {
		super.tick(delta);
		if(!isFighting) {
			updateMovement();
			super.move(delta);
			handler.getCamera().centerOnEntity(this);
		}
	}
	
	private void updateMovement() {
		if(handler.getKeys().up) {
			direction.y = -speed;
			if(idle) {
				animUp.reset();
				idle = false;
			}else
				animUp.tick();
			this.image = animUp.getCurrentFrame();
		} else if(handler.getKeys().down) {
			direction.y = speed;
			if(idle) {
				animDown.reset();
				idle = false;
			}else
				animDown.tick();
			this.image = animDown.getCurrentFrame();
		} else {
			this.direction.y = 0;
		}
		if(handler.getKeys().right) {
			this.direction.x = speed;
			if(idle) {
				animRight.reset();
				idle = false;
			}else
				animRight.tick();
			this.image = animRight.getCurrentFrame();
		} else if(handler.getKeys().left) {
			this.direction.x = -speed;
			if(idle) {
				animLeft.reset();
				idle = false;
			}else
				animLeft.tick();
			this.image = animLeft.getCurrentFrame();
		} else {
			this.direction.x = 0;
		}
		if(this.direction.x == 0 && this.direction.y == 0) {
			this.image = imgIdle;
			idle = true;
		}
	} 
	
	@Override
	public void render(Graphics g) {
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
	
}
