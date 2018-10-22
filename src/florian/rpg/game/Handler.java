package florian.rpg.game;

import javax.swing.JFrame;

import florian.rpg.display.Camera;
import florian.rpg.display.Display;
import florian.rpg.entities.Player;
import florian.rpg.inventory.Inventory;
import florian.rpg.manager.KeyManager;
import florian.rpg.manager.MouseManager;
import florian.rpg.states.OpenWorldState;
import florian.rpg.world.World;

public class Handler {

	private World world;
	private Game game;
	private Display display;
	private KeyManager keyManager;
	private MouseManager mouseManager;
	private Camera camera;
	private OpenWorldState openWorldState;
	private Player player;
	private Inventory inventory;
	
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

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public MouseManager getMouse() {
		return mouseManager;
	}

	public void setMouse(MouseManager mouseManager) {
		this.mouseManager = mouseManager;
	}
	
	
	
}
