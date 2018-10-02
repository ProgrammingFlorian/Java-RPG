package florian.rpg.entities;

import java.awt.image.BufferedImage;

import florian.rpg.game.Handler;

public class Bob extends Entity {

	public Bob(int health, int mana, int posX, int poY, int width, int height, BufferedImage image, Handler handler) {
		super(health, mana, posX, poY, width, height, image, handler);
	}

	@Override
	public void tick() {
		
	}
	
}
