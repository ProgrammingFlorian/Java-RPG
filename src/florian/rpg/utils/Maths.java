package florian.rpg.utils;

import florian.rpg.world.World;

public class Maths {

	public static class Vector2 {
		public float x;
		public float y;
		
		public Vector2() {
			this.x = 0;
			this.y = 0;
		}
		
		public Vector2(float x, float y) {
			this.x = x;
			this.y = y;
		}
		
		public Vector2(Vector2 vec) {
			this.x = vec.x;
			this.y = vec.y;
		}
		
		public Vector2 normalized() {
			return Maths.Normalize(this);
		}
		
		public void normalize() {
			Vector2 normalized = Maths.Normalize(this);
			x = normalized.x;
			y = normalized.y;
		}
		
		public Vector2 clone() {
			return new Vector2(this);
		}
		
	}
	
	public static Vector2 Normalize(Vector2 vec) {
		double length = Math.sqrt(vec.x * vec.x + vec.y * vec.y);
		vec.x /= length;
		vec.y /= length;
		return vec;
	}
	
	public static int tileToWorldCoord(int tileCoord) {
		return tileCoord * World.TILE_SIZE;
	}
	
	public static int tileToWorldCoord(float tileCoord) {
		return tileToWorldCoord((int) tileCoord);
	}
	
	public static int worldToTileCoord(int worldCoord) {
		return worldCoord / World.TILE_SIZE;
	}
	
	public static int worldToTileCoord(float worldCoord) {
		return worldToTileCoord((int) worldCoord);
	}
	
}
