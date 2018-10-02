package florian.rpg.game;

import javax.swing.JFrame;

import florian.rpg.assets.Assets;
import florian.rpg.display.Camera;
import florian.rpg.display.Display;
import florian.rpg.manager.KeyManager;
import florian.rpg.states.OpenWorldState;
import florian.rpg.world.World;

public class Handler {

	private World world;
	private Game game;
	private Display display;
	private KeyManager keyManager;
	private Assets sprites;
	private Camera camera;
	private OpenWorldState openWorldState;
	
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

	public Camera getCamera() {
		return camera;
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	public OpenWorldState getOpenWorldState() {
		return openWorldState;
	}

	public void setOpenWorldState(OpenWorldState openWorldState) {
		this.openWorldState = openWorldState;
	}
	
}
