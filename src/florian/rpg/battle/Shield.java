package florian.rpg.battle;

import java.awt.Color;
import java.awt.Graphics;

import florian.rpg.assets.Assets;
import florian.rpg.game.Handler;

public class Shield extends Attack {
	
	public Shield(Handler handler) {
		super(handler);
		this.name = "Shield";
		this.damage = 50;
		this.isShield = true;
		this.length = 0.5f;
		this.cooldown = 5 * handler.getGame().getFPS();
		this.icon = Assets.getAttackIcon(2);
	}

	@Override
	public void render(Graphics g, int x1, int x2, int y1, int y2) {
		if(active){
			g.setColor(new Color(0, 0, 255, (int) (this.percentage * 100)));
			g.drawOval((int) (x1 - 50 - this.handler.getCamera().getxOffset()), (int) (y1 - 50 - this.handler.getCamera().getyOffset()), 100, 100);
		}
	}

}
