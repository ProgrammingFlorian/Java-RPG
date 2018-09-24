package florian.tbc.world;

import java.awt.Graphics;

import florian.tbc.assets.Assets;
import florian.tbc.game.Handler;
import florian.tbc.utils.Utilities;

public class World {

	private int width, height;
	private int spawnX, spawnY;
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
		sprites = new Assets();
		handler.setSprites(sprites);
		
		/*tiles = new int[][] {
			{2, 3, 4, 5, 5, 17},
			{8, 9, 10, 5, 5, 17},
			{14, 15, 16, 5, 5, 17},
			{1, 2, 3, 4, 5, 6},
			{7, 8, 9, 10, 11, 12},
			{13, 14, 15, 16, 17, 18}
		};*/
		loadWorld("/worlds/start");
	}
	
	private void loadWorld(String path) {
		String[] data = Utilities.loadFileAsString(path).split(" ");
		this.width = Integer.parseInt(data[0]);
		this.height = Integer.parseInt(data[1]);
		this.spawnX = Integer.parseInt(data[2]);
		this.spawnY = Integer.parseInt(data[3]);
		tiles = new int[this.height][this.width];
		for(int i = 4; i < data.length; i++) {
			tiles[(i - 4) / this.width][(i - 4) - ((i - 4) / this.width * 20)] = Integer.parseInt(data[i]);
		}
	}
	
	public int getSpawnX() {
		return this.spawnX;
	}
	
	public int getSpawnY() {
		return this.spawnY;
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
