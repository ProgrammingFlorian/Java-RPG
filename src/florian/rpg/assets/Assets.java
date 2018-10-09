package florian.rpg.assets;

import java.awt.image.BufferedImage;

import florian.rpg.utils.Utilities;

public class Assets {
	
	private BufferedImage spritesheet, uiSpritesheet, playerSpritesheet;
	private BufferedImage[] sprites, uiSprites;
	private BufferedImage[] attackIcons;
	private BufferedImage[] playerUp, playerDown, playerLeft, playerRight;
	
	public Assets() {
		init();
	}
	
	private void init() {
		int xCount = 57;
		int yCount = 31;
		spritesheet = Utilities.loadImage("/textures/spritesheet.png");
		uiSpritesheet = Utilities.loadImage("/textures/uiSpritesheet.png");
		playerSpritesheet = Utilities.loadImage("/textures/player2.png");
		sprites = new BufferedImage[xCount * yCount];
		uiSprites = new BufferedImage[5];
		attackIcons = new BufferedImage[3];
		playerUp = new BufferedImage[4];
		playerDown = new BufferedImage[4];
		playerLeft = new BufferedImage[4];
		playerRight = new BufferedImage[4];
		
		for(int y = 0; y < yCount; y++) {
			for(int x = 0; x < xCount; x++) {
				sprites[y * xCount + x] = spritesheet.getSubimage(x * 17, y * 17, 16, 16);
			}
		}
		
		for(int i = 0; i < 4; i++){
			playerDown[i] = playerSpritesheet.getSubimage(i * 16, 0, 16, 16);
		}
		for(int i = 0; i < 4; i++){
			playerUp[i] = playerSpritesheet.getSubimage(i * 16, 16, 16, 16);
		}
		for(int i = 0; i < 4; i++){
			playerRight[i] = playerSpritesheet.getSubimage(i * 16, 32, 16, 16);
		}
		for(int i = 0; i < 4; i++){
			playerLeft[i] = playerSpritesheet.getSubimage(i * 16, 48, 16, 16);
		}
		
		uiSprites[0] = uiSpritesheet.getSubimage(290, 94, 45, 45); //Light Brown
		uiSprites[1] = uiSpritesheet.getSubimage(293, 294, 45, 49); //Light Brown clicked
		uiSprites[2] = uiSpritesheet.getSubimage(293, 392, 45, 45); //Brown
		uiSprites[3] = uiSpritesheet.getSubimage(293, 343, 45, 49); //Brown clicked
		uiSprites[4] = uiSpritesheet.getSubimage(290, 49, 45, 44); //White
		attackIcons[0] = uiSpritesheet.getSubimage(335, 152, 34, 34);
		attackIcons[1] = uiSpritesheet.getSubimage(338, 294, 34, 34);
		attackIcons[2] = uiSpritesheet.getSubimage(338, 331, 34, 34);
	}
	
	public BufferedImage getSprite(int ID) {
		return sprites[ID];
	}
	
	public BufferedImage getUiSprite(int ID) {
		return uiSprites[ID];
	}
	
	public BufferedImage getAttackIcon(int ID) {
		return attackIcons[ID];
	}

	public BufferedImage[] getPlayerUp() {
		return playerUp;
	}

	public BufferedImage[] getPlayerDown() {
		return playerDown;
	}

	public BufferedImage[] getPlayerLeft() {
		return playerLeft;
	}

	public BufferedImage[] getPlayerRight() {
		return playerRight;
	}

}
