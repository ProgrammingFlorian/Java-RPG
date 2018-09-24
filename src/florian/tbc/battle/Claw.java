package florian.tbc.battle;

import java.awt.Graphics;

import florian.tbc.game.Handler;

public class Claw extends Attack {

	public Claw(Handler handler) {
		super(handler);
		this.damage = 20;
		this.manaCost = 10;
		this.name = "Claw";
	}

	public void draw(Graphics g, int x1, int x2, int y1, int y2, float percentage) {
		
	}

}
