package florian.tbc.battle;

import java.awt.Graphics;

import florian.tbc.game.Handler;

public class Pow extends Attack {

	public Pow(Handler handler) {
		super(handler);
		this.damage = 40;
		this.manaCost = 50;
		this.name = "Pow";
		this.length = 0.5f;
	}
	
	public void render(Graphics g, int x1, int x2, int y1, int y2) {
		if(active)
			g.drawOval((int) ((x1 + (x2 - x1) * this.percentage)- this.handler.getCamera().getxOffset()), (int) ((y1 + (y2 - y1) * this.percentage) - this.handler.getCamera().getyOffset()), 10, 10);
	}
	
}
