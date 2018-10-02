package florian.rpg.inventory;

public class Inventory {
	
	private Item[] items;
	
	public static int SLOT_COUNT = 50;
	
	public Inventory(){
		items = new Item[50];
	}
	
	public Item getItem(int slot){
		return items[slot];
	}

}
