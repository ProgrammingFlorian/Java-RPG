package florian.rpg.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import florian.rpg.game.Handler;
import florian.rpg.utils.Maths;
import florian.rpg.utils.Maths.Vector2;
import florian.rpg.world.World;

public abstract class CollidingObject extends Object {
	
	protected int width, height;
	protected BufferedImage image;
	protected Rectangle bounds;
	
	public CollidingObject(int width, int height, BufferedImage image, Handler handler) {
		super(handler);
		
		this.width = width;
		this.height = height;
		this.image = image;
		this.handler = handler;
		
		this.pos = new Vector2();
		this.direction = new Vector2();
		this.speed = 1;
		this.bounds = new Rectangle(0, 0, width, height);
	}
	
	public CollidingObject(Vector2 pos, int width, int height, BufferedImage image, Handler handler) {
		super(pos, handler);
		
		this.width = width;
		this.height = height;
		this.image = image;
		
		this.direction = new Vector2();
		this.speed = 1;
		this.bounds = new Rectangle(0, 0, width, height);
	}
	
	public CollidingObject(Vector2 pos, Vector2 direction, float speed, int width, int height, BufferedImage image, Rectangle bounds, Handler handler) {
		super(pos, direction, speed, handler);
		
		this.width = width;
		this.height = height;
		this.image = image;
		this.bounds = bounds;
	}
	
	public abstract void tick(float delta);
	
	@Override
	public void render(Graphics g) {
		if(image == null)
			return;
		
		g.drawImage(image, (int) (pos.x - handler.getCamera().getxOffset()), (int) (pos.y - handler.getCamera().getyOffset()), width, height, null);
	}
	
	public int getMidX() {
		return (int) (pos.x + (this.width / 2));
	}
	
	public int getMidY() {
		return (int) (pos.y + (this.height / 2));
	}
	
	public Vector2 getMidPos() {
		return new Vector2(getMidX(), getMidY());
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	@Override
	public void move(float delta) {
		moveX(delta);
		moveY(delta);
		return;
	}
	
	private void moveX(float delta) {
		if(direction.x > 0) {
			int tx = Maths.worldToTileCoord(pos.x + direction.x + bounds.x + bounds.width);
			if(!collisionWithTile(tx, Maths.worldToTileCoord(pos.y + bounds.y)) && !collisionWithTile(tx, Maths.worldToTileCoord(pos.y + bounds.y + bounds.height)))
				pos.x += direction.x;
			else
				pos.x = Maths.tileToWorldCoord(tx) - bounds.x - bounds.width - 1;
		}else if(direction.x < 0) {
			int tx = Maths.worldToTileCoord(pos.x + direction.x + bounds.x);
			if(!collisionWithTile(tx, Maths.worldToTileCoord(pos.y + bounds.y)) && !collisionWithTile(tx, Maths.worldToTileCoord(pos.y + bounds.y + bounds.height)))
				pos.x += direction.x;
			else
				pos.x = Maths.tileToWorldCoord(tx) + World.TILE_SIZE - bounds.x;
		}
	}
	
	private void moveY(float delta) {
		if(direction.y > 0) {
			int ty = Maths.worldToTileCoord(pos.y + direction.y + bounds.y + bounds.height);
			if(!collisionWithTile(Maths.worldToTileCoord(pos.x + bounds.x), ty) && !collisionWithTile(Maths.worldToTileCoord(pos.x + bounds.x + bounds.width), ty))
				pos.y += direction.y;
			else
				pos.y = Maths.tileToWorldCoord(ty) - bounds.y - bounds.height - 1;
		}else if(direction.y < 0) {
			int ty = Maths.worldToTileCoord(pos.y + direction.y + bounds.y);
			if(!collisionWithTile(Maths.worldToTileCoord(pos.x + bounds.x), ty) && !collisionWithTile(Maths.worldToTileCoord(pos.x + bounds.x + bounds.width), ty))
				pos.y += direction.y;
			else
				pos.y = Maths.tileToWorldCoord(ty) + World.TILE_SIZE - bounds.y;
		}
	}
	
	protected boolean collisionWithTile(int x, int y) {
		return handler.getWorld().isSolid(x, y);
	}

}
