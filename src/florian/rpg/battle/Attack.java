package florian.rpg.battle;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import florian.rpg.entities.Entity;
import florian.rpg.game.Handler;
import florian.rpg.utils.Maths.Vector2;

public abstract class Attack {

	protected int damage;
	protected int manaCost;
	protected String name;
	protected float length;
	protected int cooldown = 0, remainingCooldown = 0;
	protected int speed;
	
	protected BufferedImage icon;
	
	protected Entity me;
	
	protected Handler handler;
	
	public Attack(Handler handler, Entity me) {
		this.handler = handler;
		this.me = me;
	}
	
	public int getDamage() {
		return this.damage;
	}
	
	public int getManaCost() {
		return this.manaCost;
	}
	
	public String getName() {
		return this.name;
	}
	
	public BufferedImage getIcon() {
		return this.icon;
	}
	
	public void trySpawn(Vector2 pos) {
		if(remainingCooldown > 0 || !me.costMana(manaCost))
			return;
		else
			spawn(pos);
	}
	
	private void spawn(Vector2 pos) {
		System.out.println("Spawning " + this.name);
		remainingCooldown = cooldown;
		int mousePosX = (int) (pos.x + handler.getCamera().getxOffset());
		int mousePosY = (int) (pos.y + handler.getCamera().getyOffset());;
		Vector2 direction = new Vector2(mousePosX - me.getMidX(), mousePosY - me.getMidY());
		handler.getOpenWorldState().spawnObject(new Projectile(this, me.getMidPos(), direction.normalized(), length));
	}
	
	public void tick(float delta) {
		if(remainingCooldown > 0) {
			remainingCooldown -= delta;
		}
	}
	
	public boolean canActivate() {
		return remainingCooldown <= 0;
	}
	
	public float getCooldownPercentage() {
		float c = ((float) remainingCooldown / (float) cooldown);
		return c;
	}
	
	public void reset() {
		remainingCooldown = 0;
	}
	
	public abstract void render(Graphics g, int x, int y);
	
	public Handler getHandler() {
		return handler;
	}
	
}
