package florian.rpg.inventory;

import florian.rpg.config.CONF_Display;
import florian.rpg.config.CONF_Inventory;

public class HotbarPlacement {

	public static int getY() {
		return CONF_Display.height - 100;
	}
	
	public static int getX(int ID) {
		return CONF_Inventory.HOT_SLOT_BORDER + ID * (CONF_Inventory.HOT_SLOT_SIZE + CONF_Inventory.HOT_SLOT_SPACE);
	}
	
}
