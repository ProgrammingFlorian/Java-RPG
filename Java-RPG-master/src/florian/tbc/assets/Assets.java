package florian.tbc.assets;

import java.awt.image.BufferedImage;

import florian.tbc.utils.Utilities;

public class Assets {
	
	private BufferedImage spritesheet, uiSpritesheet;
	private BufferedImage[] sprites, uiSprites;
	
	public Assets() {
		init();
	}
	
	private void init() {
		int xCount = 57;
		int yCount = 31;
		spritesheet = Utilities.loadImage("/textures/spritesheet.png");
		uiSpritesheet = Utilities.loadImage("/textures/uiSpritesheet.png");
		sprites = new BufferedImage[xCount * yCount];
		uiSprites = new BufferedImage[2];
		
		for(int y = 0; y < yCount; y++) {
			for(int x = 0; x < xCount; x++) {
				sprites[y * xCount + x] = spritesheet.getSubimage(x * 17, y * 17, 16, 16);
			}
		}
		uiSprites[0] = uiSpritesheet.getSubimage(0, 237, 190, 45);
		uiSprites[1] = uiSpritesheet.getSubimage(0, 49, 190, 45);
	}
	
	public BufferedImage getSprite(int ID) {
		return sprites[ID];
	}
	
	public BufferedImage getUiSprite(int ID) {
		return uiSprites[ID];
	}

}
