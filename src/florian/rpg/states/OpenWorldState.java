package florian.rpg.states;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import florian.rpg.assets.Assets;
import florian.rpg.entities.Bob;
import florian.rpg.entities.Entity;
import florian.rpg.entities.Josef;
import florian.rpg.entities.Player;
import florian.rpg.game.Handler;
import florian.rpg.inventory.Inventory;
import florian.rpg.utils.Maths.Vector2;
import florian.rpg.utils.Maths;
import florian.rpg.utils.Utilities;
import florian.rpg.world.World;
import florian.rpg.objects.Object;

public class OpenWorldState extends State {

	private Handler handler;
	private World world;
	private Inventory inventory;
	
	private Player player;
	private List<Entity> entities = new ArrayList<Entity>();
	private List<Object> objects = new ArrayList<Object>();
	
	public OpenWorldState(Handler handler) {
		this.handler = handler;
		
		world = new World(6, 6, handler);
		handler.setWorld(world);
		
		inventory = new Inventory(handler);
		handler.setInventory(inventory);
		
		player = new Player(100, new Vector2(Maths.tileToWorldCoord(handler.getWorld().getSpawnX()), Maths.tileToWorldCoord(handler.getWorld().getSpawnY())), 4, 64, 64, handler);
		entities.add(new Bob(100, new Vector2(Maths.tileToWorldCoord(15), Maths.tileToWorldCoord(15)), 64, 64, Utilities.loadImage("/textures/bob.png"), handler));
		entities.add(new Josef(100, new Vector2(Maths.tileToWorldCoord(20), Maths.tileToWorldCoord(10)), 64, 64, Utilities.loadImage("/textures/bob.png"), handler));
		handler.setPlayer(player);
	}
	
	public void tick(float delta) {
		if(!player.isAlive()) {
			handler.setOpenWorldState(new OpenWorldState(handler));
			return;
		}
		
		world.tick();
		if(handler.getKeys().keyJustPressed(KeyEvent.VK_E))
			inventory.toggleActive();
		
		if(!inventory.isActive()) {
			for(int i = 0; i < entities.size(); i++) {
				if(!entities.get(i).isAlive()) {
					entities.remove(i);
				} else{ 
					entities.get(i).tick(delta);
				}
			}
			for(int i = 0; i < objects.size(); i++) {
				if(!objects.get(i).isAlive()) {
					objects.remove(i);
				} else {
					objects.get(i).tick(delta);
				}
			}
			player.tick(delta);
		}else {
			inventory.tick();
		}
	}
	
	public void render(Graphics g) {
		world.render(g);
		
		for(Entity e : entities) {
			e.render(g);
		}
		for(Object o : objects) {
			o.render(g);
		}
		player.render(g);
		
		if(inventory.isActive())
			inventory.renderItems(g);
		
		checkForAttacks();
		drawAttacksBar(g);
	}
	
	public List<Entity> getEntities() {
		return this.entities;
	}
	
	public List<Object> getObjects() {
		return this.objects;
	}
	
	public void spawnObject(Object object) {
		this.objects.add(object);
	}
	
	private void checkForAttacks() {
		if(handler.getKeys().keyJustPressed(KeyEvent.VK_1)) {
			player.getAttack(0).trySpawn(handler.getMouse().getPos());
		} else if(handler.getKeys().keyJustPressed(KeyEvent.VK_2)) {
			player.getAttack(1).trySpawn(handler.getMouse().getPos());
		} else if(handler.getKeys().keyJustPressed(KeyEvent.VK_3)) {
			player.getAttack(2).trySpawn(handler.getMouse().getPos());
		}
	}
	
	private void drawAttacksBar(Graphics g) {
		for(int i = 0; i < player.attackCount(); i++) {
			int height = (int) (75 * ((player.getAttack(i).canActivate()) ? 1 : 1 - player.getAttack(i).getCooldownPercentage()));
			g.drawImage(Assets.getUiSprite(0), 100 + i * 100, (handler.getDisplay().getHeight()) - height - 5, 75, height, null);
			/*if(!player.getAttack(i).canActivate()) {
				//g.drawArc(100 + i * 100, (handler.getDisplay().getHeight()) - 80, 75, 75, 0, (int) (player.getAttack(0).getCooldownPercentage() * 360));
				g.setColor(Color.BLACK);
				g.fillRect(100 + i * 100, (handler.getDisplay().getHeight()) - 80, 75, (int) (player.getAttack(i).getCooldownPercentage() * 75));
			}*/
			g.drawImage(player.getAttack(i).getIcon(), 110 + i * 100, (handler.getDisplay().getHeight()) - 70, 55, 55, null); 
		}
	}
	
	public Entity getEntityOnPos(Vector2 pos) {
		for(Entity e : entities)
			if(pos.x >= e.getX() && pos.x <= e.getX() + e.getWidth() && pos.y >= e.getY() && pos.y <= e.getY() + e.getHeight())
				return e;
		return null;
	}
	
}
