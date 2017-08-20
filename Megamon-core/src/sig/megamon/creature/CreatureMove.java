package sig.megamon.creature;

import java.lang.reflect.Field;

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
	
	public String toString() {
		StringBuilder sb = new StringBuilder(this.getClass().getSimpleName()+"(");
		boolean first=true;
		for (Field f : this.getClass().getDeclaredFields()) {
			try {
				if (!first) {
					sb.append(",");
				} else {
					first=false;
				}
				sb.append(f.getName()+"="+this.getClass().getDeclaredField(f.getName()).get(this));
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}
		}
		sb.append(")");
		return sb.toString();
	}
}
