package sig.megamon.creature;

import java.lang.reflect.Field;

public class CreatureMove {
	String name="";
	String desc="";
	CreatureType type;
	Category cat;
	int pp;
	int accuracy;
	int power;
	
	public CreatureMove(String name, String description, CreatureType type, Category category,
			int initialPP, int power, int accuracy) {
		this.name=name;
		this.desc=description;
		this.type=type;
		this.cat=category;
		this.pp=initialPP;
		this.power = power;
		this.accuracy = accuracy;
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
