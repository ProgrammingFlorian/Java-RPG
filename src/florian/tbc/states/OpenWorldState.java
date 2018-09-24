package florian.tbc.states;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import florian.tbc.battle.Battle;
import florian.tbc.entities.Bob;
import florian.tbc.entities.Entity;
import florian.tbc.entities.Player;
import florian.tbc.game.Handler;
import florian.tbc.utils.Utilities;
import florian.tbc.world.World;

public class OpenWorldState extends State {
	
	private Handler handler;
	private World world;
	
	private Entity player;
	private List<Entity> entities = new ArrayList<Entity>();
	
	public OpenWorldState(Handler handler) {
		this.handler = handler;
		
		player = new Player(100, 100, 0, 0, 3, 64, 64, Utilities.loadImage("/textures/player.png"), handler);
		entities.add(new Bob(100, 100, 200, 100, 64, 64, Utilities.loadImage("/textures/bob.png")));
		world = new World(6, 6, handler);
		handler.setWorld(world);
	}
	
	public void tick() {
		if(handler.getKeys().p) {
			State.setState(new BattleState(new Battle(player, entities.get(0), handler), handler));
		}
		world.tick();
		for(Entity e : entities) {
			e.tick();
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
	
}
