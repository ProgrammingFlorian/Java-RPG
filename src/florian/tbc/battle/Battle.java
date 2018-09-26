package florian.tbc.battle;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import florian.tbc.entities.Entity;
import florian.tbc.game.Handler;
import florian.tbc.states.State;
import florian.tbc.utils.Utilities;

public class Battle {
	
	private Handler handler;
	
	private Entity player;
	private Entity enemy;
	
	private int currentAttack = 0;
	
	public Battle(Entity player, Entity enemy, Handler handler) {
		this.handler = handler;
		this.player = player;
		this.enemy = enemy;
	}
	
	public void tick() {
		if(!player.isAlive() || !enemy.isAlive()){
			this.EndBattle();
		}
		
		if(handler.getKeys().keyJustPressed(KeyEvent.VK_DOWN))
			currentAttack++;

		if(handler.getKeys().keyJustPressed(KeyEvent.VK_UP))
			currentAttack--;
		
		if(currentAttack >= player.attackCount())
			currentAttack = 0;
		if(currentAttack < 0)
			currentAttack = player.attackCount() - 1;
		
		if(handler.getKeys().keyJustPressed(KeyEvent.VK_ENTER)) {
			player.getAttack(currentAttack).activate();
		}
		
		for(int a = 0; a < player.attackCount(); a++){
			player.getAttack(a).tick(enemy);
		}
	}
	
	public void render(Graphics g) {
		enemy.render(g);
		player.render(g);
		for(int i = 0; i < player.attackCount(); i++)
			player.getAttack(i).render(g, player.getMidX(), enemy.getMidX(), player.getMidY(), enemy.getMidY());
		drawAttackSelection(g);
	}
	
	private void drawAttackSelection(Graphics g) {
		for(int i = 0; i < player.attackCount(); i++) {
			g.drawImage(handler.getSprites().getUiSprite(currentAttack == i ? 1 : 0), (handler.getDisplay().getWidth() / 2) - 50, (handler.getDisplay().getHeight()) - 80 - (50 * (player.attackCount() - i)), 100, 40, null);
			Utilities.drawString(g, player.getAttack(i).getName(), handler.getDisplay().getWidth() / 2, (handler.getDisplay().getHeight()) - 60 - (50 * (player.attackCount() - i)), true, Color.BLACK, new Font("Arial", 0, 14)); 
		}
	}
	
	private void EndBattle(){
		if(!enemy.isAlive()){
			State.setState(handler.getOpenWorldState());
		}else{
			
		}
	}

}
