package florian.rpg.inventory.items;

import florian.rpg.assets.Assets;
import florian.rpg.entities.Player;
import florian.rpg.inventory.Item;

public class Item_Apple extends Item {

	public Item_Apple() {
		super(0, "Apple", "Yummy", Assets.getAttackIcon(2));
	}

	@Override
	public void use(Player p) {
		p.heal(10);
	}
	
}
