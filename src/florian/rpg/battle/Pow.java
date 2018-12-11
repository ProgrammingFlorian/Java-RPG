package florian.rpg.battle;

import java.awt.Graphics;

import florian.rpg.assets.Assets;
import florian.rpg.entities.Entity;
import florian.rpg.game.Handler;

public class Pow extends Attack {

	public Pow(Handler handler, Entity me) {
		super(handler, me);
		this.damage = 40;
		this.manaCost = 50;
		this.name = "Pow";
		this.length = 120f;
		this.cooldown = 10;
		this.icon = Assets.getAttackIcon(0);
		this.speed = 10;
	}
	
	public void render(Graphics g, int x, int y) {
		//g.drawOval(x, y, 10, 10);
		g.drawOval((int) (x - this.handler.getCamera().getxOffset()), (int) (y - this.handler.getCamera().getyOffset()), 10, 10);
	}
	
}
