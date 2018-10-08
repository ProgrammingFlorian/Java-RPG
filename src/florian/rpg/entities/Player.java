package florian.rpg.entities;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import florian.rpg.battle.Attack;
import florian.rpg.battle.Battle;
import florian.rpg.battle.Claw;
import florian.rpg.battle.Pow;
import florian.rpg.game.Handler;
import florian.rpg.states.BattleState;
import florian.rpg.states.State;

public class Player extends Entity {

	private int speed;
	
	public Player(int health, int mana, int posX, int posY, int speed, int width, int height, BufferedImage image, Handler handler) {
		super(health, mana, posX, posY, width, height, image, handler);
		this.speed = speed;
		this.bounds = new Rectangle(12, 20, 39, 39);
		
		this.attacks = new Attack[3];
		this.attacks[0] = new Claw(handler);
		this.attacks[1] = new Pow(handler);
		this.attacks[2] = new Claw(handler);
	}

	@Override
	public void tick() {
		super.move();
		if(handler.getKeys().up) {
			moveY = -speed;
		}else if(handler.getKeys().down) {
			moveY = speed;
		}else {
			moveY = 0;
		}
		if(handler.getKeys().right) {
			this.moveX = speed;
		}else if(handler.getKeys().left) {
			this.moveX = -speed;
		}else {
			this.moveX = 0;
		}
		handler.getCamera().centerOnEntity(this);
		for(Entity e : handler.getOpenWorldState().getEntities()){
			if(Math.abs(e.getX() - this.posX) < 120 && Math.abs(e.getY() - this.posY) < 120){
				this.setFighting(true);
				e.setFighting(true);
				State.setState(new BattleState(new Battle(this, e, handler), handler));
			}
		}
	}
	
}
