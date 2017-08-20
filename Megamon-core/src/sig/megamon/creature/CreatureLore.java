package sig.megamon.creature;

import java.lang.reflect.Field;

public class CreatureLore {
	final String bio;
	final int size; //In inches.
	final float weight; //In lbs.
	
	public CreatureLore(String bio, int size, float weight) {
		this.bio = bio;
		this.size = size;
		this.weight = weight;
	}
}
