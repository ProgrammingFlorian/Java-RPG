package florian.tbc.world;

import java.awt.Graphics;

import florian.tbc.assets.Assets;
import florian.tbc.game.Handler;

public class World {

	private int width, height;
	static int TILE_SIZE = 64;
	
	private Assets sprites;
	private Handler handler;
	
	private int[][] tiles;
	
	public World(int width, int height, Handler handler) {
		this.width = width;
		this.height = height;
		this.handler = handler;
		
		init();
	}
	
	private void init() {
		sprites = new Assets(6, 10);
		handler.setSprites(sprites);
		
		tiles = new int[][] {
			{2, 3, 4, 5, 5, 17},
			{8, 9, 10, 5, 5, 17},
			{14, 15, 16, 5, 5, 17},
			{1, 2, 3, 4, 5, 6},
			{7, 8, 9, 10, 11, 12},
			{13, 14, 15, 16, 17, 18}
		};
	}
	
	public void tick() {
		handler.getKeys().tick();
	}
	
	public void render(Graphics g) {
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				g.drawImage(sprites.getSprite(tiles[y][x]), x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE, null);
			}
		}
	}
}
