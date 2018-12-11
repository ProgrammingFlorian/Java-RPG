package florian.rpg.entities;

import java.awt.image.BufferedImage;
import java.util.Random;

import florian.rpg.battle.Attack;
import florian.rpg.battle.Pow;
import florian.rpg.game.Handler;
import florian.rpg.utils.Maths.Vector2;

public class Bob extends Entity {
	
	private int attackCooldown = 0;
	
	public Bob(int health, Vector2 pos, int width, int height, BufferedImage image, Handler handler) {
		super(health, 100, 10, pos, width, height, image, handler);
		this.maxMana = 100;
		this.mana = 100;
		this.attacks = new Attack[1];
		this.attacks[0] = new Pow(handler, this);
	}
	
	@Override
	public void tick(float delta) {
		super.tick(delta);
		if(isFighting) {
			attackCooldown++;
			if(attackCooldown > 10) {
				attackCooldown = 0;
				attacks[new Random().nextInt(attacks.length)].trySpawn(handler.getPlayer().getPos());
			}
		}
	}
	
}
