package florian.rpg.display;

import florian.rpg.entities.Entity;
import florian.rpg.game.Handler;

public class Camera {
	
	private Handler handler;
	private float xOffset, yOffset;
	
	public Camera(Handler handler, float xOffset, float yOffset) {
		this.handler = handler;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	public void centerOnEntity(Entity e) {
		xOffset = e.getX() - handler.getDisplay().getWidth() / 2 + e.getWidth() / 2;
		yOffset = e.getY() - handler.getDisplay().getHeight() / 2 + e.getHeight() / 2;
	}
	
	public void move(float xAmt, float yAmt) {
		xOffset += xAmt;
		yOffset += yAmt;
	}

	public float getxOffset() {
		return Math.max(0, xOffset);
	}

	public void setxOffset(float xOffset) {
		this.xOffset = xOffset;
	}

	public float getyOffset() {
		return Math.max(0, yOffset);
	}

	public void setyOffset(float yOffset) {
		this.yOffset = yOffset;
	}

}
