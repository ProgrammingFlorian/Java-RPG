package florian.tbc.battle;

import java.awt.Graphics;

import florian.tbc.game.Handler;

public class Pow extends Attack {

	public Pow(Handler handler) {
		super(handler);
		this.damage = 50;
		this.manaCost = 50;
		this.name = "Pow";
		this.length = 0.5f;
	}
	
	public void draw(Graphics g, int x1, int x2, int y1, int y2, float percentage) {
		g.drawOval((int) (x1 + (x2 - x1) * percentage), (int) (y1 + (y2 - y1) * percentage), 10, 10);
	}
	
}
