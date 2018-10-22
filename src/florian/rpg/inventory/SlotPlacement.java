package florian.rpg.inventory;

import florian.rpg.config.CONF_Inventory;
import florian.rpg.game.Handler;

public class SlotPlacement {

	private Handler handler;
	
	public SlotPlacement(Handler handler) {
		this.handler = handler;
	}
	
	public int getSlot(int x, int y) {
		for(int i = 0; i < CONF_Inventory.SLOT_COUNT; i++) {
			int _x = getX(i);
			int _y = getY(i);
			if(x >= _x && x <= _x + CONF_Inventory.SLOT_SIZE && y >= _y && y <= _y + CONF_Inventory.SLOT_SIZE)
				return i;
		}
		return -1;
	}
	
	public int getX(int id) {
		return CONF_Inventory.SLOT_BORDER + (id % rowCount()) * (CONF_Inventory.SLOT_SIZE + CONF_Inventory.SLOT_SPACE);
	}
	
	public int getY(int id) {
		return CONF_Inventory.SLOT_BORDER + (1 + getRow(id) * (CONF_Inventory.SLOT_SIZE + CONF_Inventory.SLOT_SPACE));
	}
	
	private int getRow(int id) {
		return id / rowCount();
	}
	
	private int rowCount() {
		return (int) ((handler.getDisplay().getWidth() - CONF_Inventory.SLOT_BORDER * 2 + CONF_Inventory.SLOT_SPACE) / (CONF_Inventory.SLOT_SIZE + CONF_Inventory.SLOT_SPACE)); 
	}
	
}
