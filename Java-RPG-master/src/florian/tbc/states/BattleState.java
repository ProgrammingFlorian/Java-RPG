package florian.tbc.states;

import java.awt.Graphics;

import florian.tbc.battle.Battle;
import florian.tbc.game.Handler;

public class BattleState extends State {
	
	private Handler handler;
	private Battle battle;
	
	public BattleState(Battle battle, Handler handler) {
		this.handler = handler;
		this.battle = battle;
	}
	
	public void tick() {
		handler.getWorld().tick();
		battle.tick();
	}
	
	public void render(Graphics g) {
		handler.getWorld().render(g);
		battle.render(g);
	}
	
}
