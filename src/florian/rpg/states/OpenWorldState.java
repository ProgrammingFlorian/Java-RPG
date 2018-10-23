package florian.rpg.states;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import florian.rpg.entities.Bob;
import florian.rpg.entities.Entity;
import florian.rpg.entities.Player;
import florian.rpg.game.Handler;
import florian.rpg.inventory.Inventory;
import florian.rpg.utils.Utilities;
import florian.rpg.world.World;

public class OpenWorldState extends State {

	private Handler handler;
	private World world;
	private Inventory inventory;
	
	private Player player;
	private List<Entity> entities = new ArrayList<Entity>();
	
	public OpenWorldState(Handler handler) {
		this.handler = handler;
		
		world = new World(6, 6, handler);
		handler.setWorld(world);
		
		inventory = new Inventory(handler);
		handler.setInventory(inventory);
		
		player = new Player(100, 100, handler.getWorld().getSpawnX() * World.TILE_SIZE, handler.getWorld().getSpawnY() * World.TILE_SIZE, 4, 64, 64, handler);
		entities.add(new Bob(100, 100, 21 * World.TILE_SIZE, 15 * World.TILE_SIZE, 64, 64, Utilities.loadImage("/textures/bob.png"), handler));
		handler.setPlayer(player);
	}
	
	public void tick() {
		world.tick();
		if(handler.getKeys().keyJustPressed(KeyEvent.VK_E))
			inventory.toggleActive();
		
		if(!inventory.isActive()) {
			for(int i = 0; i < entities.size(); i++) {
				if(!entities.get(i).isAlive()){
					entities.remove(i);
				}else{
					entities.get(i).tick();
				}
			}
			player.tick();
		}else {
			inventory.tick();
		}
	}
	
	public void render(Graphics g) {
		world.render(g);
		for(Entity e : entities) {
			e.render(g);
		}
		player.render(g);
		if(inventory.isActive())
			inventory.renderItems(g);
	}
	
	public List<Entity> getEntities(){
		return this.entities;
	}
	
}
