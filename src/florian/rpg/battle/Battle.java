package florian.rpg.battle;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import florian.rpg.assets.Assets;
import florian.rpg.entities.Entity;
import florian.rpg.entities.Player;
import florian.rpg.game.Handler;
import florian.rpg.states.State;

public class Battle {
	
	private Handler handler;
	
	private Player player;
	private Entity enemy;
	
	public Battle(Player player, Entity enemy, Handler handler) {
		this.handler = handler;
		this.player = player;
		this.enemy = enemy;
	}
	
	public void tick() {
		if(!player.isAlive() || !enemy.isAlive()){
			this.EndBattle();
			return;
		}
		
		if(handler.getKeys().keyJustPressed(KeyEvent.VK_1)){
			player.getAttack(0).activate();
		}else if(handler.getKeys().keyJustPressed(KeyEvent.VK_2)){
			player.getAttack(1).activate();
		}else if(handler.getKeys().keyJustPressed(KeyEvent.VK_3)){
			player.getAttack(2).activate();
		}
		
		player.tick();
		for(int a = 0; a < player.attackCount(); a++){
			player.getAttack(a).tick(player, enemy);
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
			g.drawImage(Assets.getUiSprite(0), 100 + i * 100, (handler.getDisplay().getHeight()) - 80, 75, 75, null);
			if(!player.getAttack(i).canActivate())
				g.drawArc(100 + i * 100, (handler.getDisplay().getHeight()) - 80, 75, 75, 0, (int) (player.getAttack(0).getCooldownPercentage() * 360));
			g.drawImage(player.getAttack(i).getIcon(), 110 + i * 100, (handler.getDisplay().getHeight()) - 70, 55, 55, null); 
		}
	}
	
	private void EndBattle(){
		player.setFighting(false);
		enemy.setFighting(false);
		State.setState(handler.getOpenWorldState());
	}

}
