package florian.rpg.objects;

import java.awt.Graphics;

import florian.rpg.game.Handler;
import florian.rpg.utils.Maths.Vector2;

public abstract class Object {
	
	protected Vector2 pos;
	protected Vector2 direction;
	protected float speed;

	protected boolean alive = true;
	
	protected Handler handler;
	
	public Object(Handler handler) {
		this.handler = handler;
		
		this.pos = new Vector2();
		this.direction = new Vector2();
		this.speed = 1;
	}
	
	public Object(Vector2 pos, Handler handler) {
		this.pos = pos.clone();
		this.handler = handler;
		
		this.direction = new Vector2();
		this.speed = 1;
	}
	
	public Object(Vector2 pos, Vector2 direction, float speed, Handler handler) {
		this.pos = pos.clone();
		this.direction = direction.clone();
		this.speed = speed;
		this.handler = handler;
	}

	public abstract void tick(float delta);
	public abstract void render(Graphics g);
	
	public int getX() {
		return (int) pos.x;
	}
	
	public int getY() {
		return (int) pos.y;
	}
	
	public Vector2 getPos() {
		return pos;
	}
	
	public void move(float delta) {
		moveX(delta);
		moveY(delta);
		return;
	}
	
	private void moveX(float delta) {
		pos.x += direction.x * speed * delta;
	}
	
	private void moveY(float delta) {
		pos.y += direction.y * speed * delta;
	}

	public boolean isAlive() {
		return alive;
	}

	public void die() {
		System.out.println("someone died!!");
		alive = false;
	}
	
}
