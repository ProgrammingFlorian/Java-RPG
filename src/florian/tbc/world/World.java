package florian.tbc.world;

import java.awt.Graphics;

import florian.tbc.assets.Assets;
import florian.tbc.display.Camera;
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
		handler.setCamera(new Camera(handler, 10, 10));
		handler.getCamera().move(200, 100);
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
		int xStart = (int) Math.max(0, handler.getCamera().getxOffset() / TILE_SIZE);
		int xEnd = (int) Math.min(width, (handler.getCamera().getxOffset() + handler.getDisplay().getWidth()) / TILE_SIZE + 1);
		int yStart = (int) Math.max(0, handler.getCamera().getyOffset() / TILE_SIZE);
		int yEnd = (int) Math.min(height, (handler.getCamera().getyOffset() + handler.getDisplay().getHeight()) / TILE_SIZE + 1);
		
		for(int y = yStart;y < yEnd;y++){
			for(int x = xStart;x < xEnd;x++){
				g.drawImage(sprites.getSprite(tiles[y][x]), (int) (x * TILE_SIZE - handler.getCamera().getxOffset()),(int) (y * TILE_SIZE - handler.getCamera().getyOffset()), TILE_SIZE, TILE_SIZE, null);
			}
		}
	}
}
