package sig.megamon.creature;

public class CreatureMove {
	int lvLearned=0;
	String name="";
	String desc="";
	CreatureType type;
	Category cat;
	
	public CreatureMove(String name, String description, int levelLearned, CreatureType type, Category category) {
		this.name=name;
		this.desc=description;
		this.lvLearned=levelLearned;
		this.type=type;
		this.cat=category;
	}
}
