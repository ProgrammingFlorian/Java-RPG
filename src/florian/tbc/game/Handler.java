package florian.tbc.game;

import javax.swing.JFrame;

import florian.tbc.assets.Assets;
import florian.tbc.display.Display;
import florian.tbc.manager.KeyManager;
import florian.tbc.world.World;

public class Handler {

	private World world;
	private Game game;
	private Display display;
	private KeyManager keyManager;
	private Assets sprites;
	
	public World getWorld() {
		return world;
	}
	
	public void setWorld(World world) {
		this.world = world;
	}
	
	public Game getGame() {
		return game;
	}
	
	public void setGame(Game game) {
		this.game = game;
	}
	
	public Display getDisplay() {
		return display;
	}
	
	public void setDisplay(Display display) {
		this.display = display;
	}
	
	public JFrame getFrame() {
		return display.getFrame();
	}

	public KeyManager getKeys() {
		return keyManager;
	}

	public void setKeys(KeyManager keyManager) {
		this.keyManager = keyManager;
	}

	public Assets getSprites() {
		return sprites;
	}

	public void setSprites(Assets sprites) {
		this.sprites = sprites;
	}
}
