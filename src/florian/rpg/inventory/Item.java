package florian.rpg.inventory;

import java.awt.image.BufferedImage;

import florian.rpg.entities.Player;

public abstract class Item {

	private int ID;
	private String name;
	private String description;
	private BufferedImage icon;
	
	public Item(int ID, String name, String description, BufferedImage icon) {
		this.ID = ID;
		this.name = name;
		this.description = description;
		this.icon = icon;
	}
	
	public int getID() {
		return ID;
	}
	
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public BufferedImage getIcon() {
		return icon;
	}
	
	public abstract void use(Player p);
	
}
