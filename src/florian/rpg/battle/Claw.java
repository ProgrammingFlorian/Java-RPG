package florian.rpg.battle;

import java.awt.Graphics;

import florian.rpg.game.Handler;

public class Claw extends Attack {

	public Claw(Handler handler) {
		super(handler);
		this.damage = 20;
		this.manaCost = 10;
		this.name = "Claw";
		this.icon = handler.getSprites().getAttackIcon(1);
	}

	public void render(Graphics g, int x1, int x2, int y1, int y2) {
		
	}

}
