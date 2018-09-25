package florian.tbc.entities;

import java.awt.image.BufferedImage;

import florian.tbc.battle.Attack;
import florian.tbc.battle.Battle;
import florian.tbc.battle.Claw;
import florian.tbc.battle.Pow;
import florian.tbc.game.Handler;
import florian.tbc.states.BattleState;
import florian.tbc.states.State;

public class Player extends Entity {

	private int speed;
	
	public Player(int health, int mana, int posX, int posY, int speed, int width, int height, BufferedImage image, Handler handler) {
		super(health, mana, posX, posY, width, height, image, handler);
		this.speed = speed;
		
		this.attacks = new Attack[3];
		this.attacks[0] = new Claw(handler);
		this.attacks[1] = new Pow(handler);
		this.attacks[2] = new Claw(handler);
	}

	@Override
	public void tick() {
		if(handler.getKeys().up) {
			this.posY -= speed;
		}else if(handler.getKeys().down) {
			this.posY += speed;
		}
		if(handler.getKeys().right) {
			this.posX += speed;
		}else if(handler.getKeys().left) {
			this.posX -= speed;
		}
		handler.getCamera().centerOnEntity(this);
		for(Entity e : handler.getOpenWorldState().getEntities()){
			if(Math.abs(e.getX() - this.posX) < 50 && Math.abs(e.getY() - this.posY) < 50){
				State.setState(new BattleState(new Battle(this, e, handler), handler));
			}
		}
	}
	
}