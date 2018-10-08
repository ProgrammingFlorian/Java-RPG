package florian.rpg.states;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import florian.rpg.entities.Bob;
import florian.rpg.entities.Entity;
import florian.rpg.entities.Player;
import florian.rpg.game.Handler;
import florian.rpg.utils.Utilities;
import florian.rpg.world.World;

public class OpenWorldState extends State {
	
	@SuppressWarnings("unused")
	private Handler handler;
	private World world;
	
	private Entity player;
	private List<Entity> entities = new ArrayList<Entity>();
	
	public OpenWorldState(Handler handler) {
		this.handler = handler;
		
		world = new World(6, 6, handler);
		handler.setWorld(world);
		
		player = new Player(100, 100, handler.getWorld().getSpawnX() * World.TILE_SIZE, handler.getWorld().getSpawnY() * World.TILE_SIZE, 3, 64, 64, Utilities.loadImage("/textures/player.png"), handler);
		entities.add(new Bob(100, 100, 21 * World.TILE_SIZE, 15 * World.TILE_SIZE, 64, 64, Utilities.loadImage("/textures/bob.png"), handler));
	}
	
	public void tick() {
		world.tick();
		for(int i = 0; i < entities.size(); i++) {
			if(!entities.get(i).isAlive()){
				entities.remove(i);
			}else{
				entities.get(i).tick();
			}
		}
		player.tick();
	}
	
	public void render(Graphics g) {
		world.render(g);
		for(Entity e : entities) {
			e.render(g);
		}
		player.render(g);
	}
	
	public List<Entity> getEntities(){
		return this.entities;
	}
	
}
