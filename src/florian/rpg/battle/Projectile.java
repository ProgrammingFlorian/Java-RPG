package florian.rpg.battle;

import java.awt.Graphics;

import florian.rpg.entities.Entity;
import florian.rpg.objects.Object;
import florian.rpg.utils.Maths.Vector2;

public class Projectile extends Object {

	private Attack attack;
	private float time;
	
	public Projectile(Attack attack, Vector2 pos, Vector2 direction, float time) {
		super(pos, direction, attack.speed, attack.getHandler());
		this.attack = attack;
		this.time = time;
	}

	@Override
	public void tick(float delta) {
		time -= delta;
		if(time <= 0) {
			this.die();
			return;
		}
		
		this.move(delta);
		checkForCollision();
	}

	@Override
	public void render(Graphics g) {
		attack.render(g, (int) this.pos.x, (int) this.pos.y);
	}
	
	private void checkForCollision() {
		Entity e = attack.handler.getOpenWorldState().getEntityOnPos(this.pos);
		if(e != null) {
			e.attack(attack.damage);
			this.die();
		}
	}
	
}
