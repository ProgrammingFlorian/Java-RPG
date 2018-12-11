package florian.rpg.inventory.items;

import florian.rpg.assets.Assets;
import florian.rpg.entities.Player;
import florian.rpg.inventory.Item;

public class Item_GoldenSword extends Item {

	public Item_GoldenSword() {
		super(1, "Golden Sword", "This Sword is golden", Assets.getAttackIcon(1));
	}

	@Override
	public void use(Player p) {
		
	}
	
}
