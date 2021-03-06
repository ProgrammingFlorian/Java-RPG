package florian.rpg.states;

import java.awt.Graphics;

public abstract class State {
	
	private static State currentState = null;
	
	public static void setState(State state) {
		currentState = state;
		System.out.println("State: " + state);
	}
	
	public static State getState() {
		return currentState;
	}
	
	public abstract void render(Graphics g);
	public abstract void tick(float delta);

}
