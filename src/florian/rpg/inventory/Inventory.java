package florian.rpg.inventory;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import florian.rpg.assets.Assets;
import florian.rpg.config.CONF_Inventory;
import florian.rpg.game.Handler;

public class Inventory {
	
	private Handler handler;
	
	private Item[] items;
	private int[] amounts;
	private boolean active;
	private int activeSlot = -1;
	private boolean clickHold = false;
	
	public Inventory(Handler handler){
		this.handler = handler;
		
		items = new Item[CONF_Inventory.SLOT_COUNT];
		amounts = new int[CONF_Inventory.SLOT_COUNT];
		for (int i = 0; i < items.length; i++) {
			items[i] = new Item(0, "Item " + i, "test", Assets.getAttackIcon(0));
			amounts[i] = i;
		}
		items[5] = new Item(1, "Other Item", "test", Assets.getAttackIcon(1));
		items[7] = new Item(1, "Other Item", "test", Assets.getAttackIcon(1));
		items[10] = new Item(2, "Another Item", "test", Assets.getAttackIcon(2));
	}
	
	public Item getItem(int slot){
		return items[slot];
	}
	
	public void tick() {
		drag();
	}
	
	public void renderItems(Graphics g) {
		//Darker Background
		g.setColor(new Color(0, 0, 0, 0.3f));
		g.fillRect(0, 0, handler.getDisplay().getWidth(), handler.getDisplay().getHeight());
		//Box
		g.setColor(new Color(151, 113, 74));
		g.fillRoundRect(100, 100, handler.getDisplay().getWidth() - 200, handler.getDisplay().getHeight() - 250, 5, 5);
		//Title Box
		g.setColor(new Color(211, 191, 143));
		g.fillRoundRect(handler.getDisplay().getWidth() / 2 - 150, 70, 300, 40, 5, 5);
		//Title Title
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", 0, 20));
		g.drawString("Inventory", handler.getDisplay().getWidth() / 2 - g.getFontMetrics().stringWidth("Inventory") / 2, 95);
		//Slots
		for (int i = 0; i < CONF_Inventory.SLOT_COUNT; i++) {
			int x = SlotPlacement.getX(i);
			int y = SlotPlacement.getY(i);
			g.drawImage(Assets.getUiSprite(0), x, y, CONF_Inventory.SLOT_SIZE, CONF_Inventory.SLOT_SIZE, null);
			//Item in Slot
			if(i != activeSlot && items[i] != null) {
				g.drawImage(items[i].getIcon(), x + 10, y + 10, CONF_Inventory.SLOT_SIZE - 20, CONF_Inventory.SLOT_SIZE - 20, null);
				g.setFont(new Font("Arial", 0, 12));
				g.drawString("" + amounts[i], x + CONF_Inventory.TEXT_AMOUNT_PADDING, y + CONF_Inventory.SLOT_SIZE - CONF_Inventory.TEXT_AMOUNT_PADDING);
			}
		}
		//Item dragged
		if(activeSlot != -1 && items[activeSlot] != null)
			g.drawImage(items[activeSlot].getIcon(), handler.getMouse().getMouseX() - ((CONF_Inventory.SLOT_SIZE - 20) / 2), handler.getMouse().getMouseY() - ((CONF_Inventory.SLOT_SIZE - 20) / 2), CONF_Inventory.SLOT_SIZE - 20, CONF_Inventory.SLOT_SIZE - 20, null);
	}
	
	public void renderBar(Graphics g) {
		for(int i = 0; i < CONF_Inventory.HOT_SLOT_COUNT; i++){
			g.drawImage(Assets.getUiSprite(0), HotbarPlacement.getX(i), HotbarPlacement.getY(), CONF_Inventory.HOT_SLOT_SIZE, CONF_Inventory.HOT_SLOT_SIZE, null);
		}
	}
	
	public void activate() {
		this.active = true;
	}
	
	public void deactivate() {
		this.active = false;
	}
	
	public void toggleActive() {
		this.active = !this.active;
	}
	
	public boolean isActive() {
		return this.active;
	}
	
	public void drag() {
		if(handler.getMouse().leftPressed()) { //Drag
			if(!clickHold) {
				clickHold = true;
				int slot = florian.rpg.inventory.SlotPlacement.getSlot(handler.getMouse().getMouseX(), handler.getMouse().getMouseY());
				if(slot != -1 && activeSlot == -1  && items[slot] != null) {
					activeSlot = slot;
				}
			}
		} else {
			clickHold = false;
			if(activeSlot != -1) { //Drop
				int slot = SlotPlacement.getSlot(handler.getMouse().getMouseX(), handler.getMouse().getMouseY());
				if(slot != -1 && slot != activeSlot) {
					if(items[slot] == null) {
						items[slot] = items[activeSlot];
						amounts[slot] = amounts[activeSlot];
						items[activeSlot] = null;
						amounts[activeSlot] = 0;
					}else if(items[activeSlot].getID() == items[slot].getID()) {
						amounts[slot] += amounts[activeSlot];
						items[activeSlot] = null;
						amounts[activeSlot] = 0;
					}else {
						System.out.println("other");
						Item item = items[activeSlot];
						int amount = amounts[activeSlot];
						items[activeSlot] = items[slot];
						amounts[activeSlot] = amounts[slot];
						items[slot] = item;
						amounts[slot] = amount;
					}
				}
				activeSlot = -1;
			}
		}
	}

}
