package florian.rpg.battle;

import java.awt.Color;
import java.awt.Graphics;

import florian.rpg.assets.Assets;
import florian.rpg.entities.Entity;
import florian.rpg.game.Handler;

/*public class Shield extends Attack {
	
	public Shield(Handler handler, Entity me) { //TODO
		super(handler, me);
		this.name = "Shield";
		this.damage = 50;
		this.isShield = true;
		this.length = 2f;
		this.cooldown = 5 * handler.getGame().getFPS();
		this.icon = Assets.getAttackIcon(2);
	}

	@Override
	public void render(Graphics g, int x, int y) {
		if(active) {
			int color = (int) ((-4 * Math.pow((this.percentage - 0.5), 2) + 1) * 255);
			g.setColor(new Color(0, 0, 255, color));
			g.drawOval((int) (x1 - 50 - this.handler.getCamera().getxOffset()), (int) (y1 - 50 - this.handler.getCamera().getyOffset()), 100, 100);
		}
	}

}*/