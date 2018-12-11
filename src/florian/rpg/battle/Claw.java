package florian.rpg.battle;

import java.awt.Graphics;

import florian.rpg.assets.Assets;
import florian.rpg.entities.Entity;
import florian.rpg.game.Handler;

public class Claw extends Attack {

	public Claw(Handler handler, Entity me) {
		super(handler, me);
		this.damage = 20;
		this.manaCost = 10;
		this.name = "Claw";
		this.cooldown = 1 * handler.getGame().getFPS();
		this.icon = Assets.getAttackIcon(1);
	}

	public void render(Graphics g, int x1, int x2) {
		
	}

}
