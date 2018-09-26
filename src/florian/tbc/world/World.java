package florian.tbc.world;

import java.awt.Color;
import java.awt.Graphics;
import java.lang.reflect.Array;
import java.util.Arrays;

import florian.tbc.assets.Assets;
import florian.tbc.display.Camera;
import florian.tbc.game.Handler;
import florian.tbc.utils.Utilities;

public class World {

	private int width, height, layers;
	private int spawnX, spawnY;
	static int TILE_SIZE = 64;
	
	private Assets sprites;
	private Handler handler;
	
	private int[][][] tiles;
	
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
		loadWorld("/worlds/layers");
	}
	
	private void loadWorld(String path) {
		String[] file = Utilities.loadFileAsArray(path);
		for(int y = 0; y < file.length; y++) {
			String[] data = file[y].split(","); 
			if(y == 0) {
				this.width = Integer.parseInt(data[0]);
				this.height = Integer.parseInt(data[1]);
				this.layers = Integer.parseInt(data[2]);
				tiles = new int[this.layers][this.height][this.width];
			}else if(y == 1) {
				this.spawnX = Integer.parseInt(data[0]);
				this.spawnY = Integer.parseInt(data[1]);
			}else {
				System.out.println("Layer: " + (y / this.height));
				System.out.println("y: " + (y - (((y - 2) / this.height) * this.height) - 2));
				for(int x = 0; x < data.length; x++) {
					tiles[((y - 2) / this.height)][y - (((y - 2) / this.height) * this.height) - 2][x] = Integer.parseInt(data[x]);
				}
			}
		}
		String output = "";
		for(int l = 0; l < this.layers; l++) {
			for(int y = 0; y < this.height; y++) {
				for(int x = 0; x < this.width; x++) {
						output += tiles[l][y][x] + ",";
				}
				output += "\n";
			}
			output += "\n";
		}
		Utilities.saveToFile("D:/Users/Florian/tiled2.txt", output);
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
		
		for(int l = 0; l < this.layers; l++) {
			for(int y = yStart; y < yEnd; y++){
				for(int x = xStart; x < xEnd; x++){
					if(tiles[l][y][x] != -1)
						g.drawImage(sprites.getSprite(tiles[l][y][x]), (int) (x * TILE_SIZE - handler.getCamera().getxOffset()),(int) (y * TILE_SIZE - handler.getCamera().getyOffset()), TILE_SIZE, TILE_SIZE, null);
					else if(l == 0) {
						g.setColor(Color.BLACK);
						g.fillRect((int) (x * TILE_SIZE - handler.getCamera().getxOffset()),(int) (y * TILE_SIZE - handler.getCamera().getyOffset()), TILE_SIZE, TILE_SIZE);
					}
				}
			}
		}
	}
	
	private boolean isSolid(int ID) {
		int[] solid = {697, 698, 699};
		return Arrays.asList(solid).contains(ID);
	}
}
