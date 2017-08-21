package sig.megamon.menu;

public enum MenuItem {
	MEGADEX("Megadex"),
	MEGAMON("Megamon"),
	BAG("Bag"),
	TRAINER("<Trainer>"),
	SAVE("Save"),
	OPTIONS("Options"),
	EXIT("Exit");
	
	String displayName;
	
	MenuItem(String displayName) {
		this.displayName = displayName;
	}
	
	public String getDisplayText() {
		return this.displayName;
	}
}
