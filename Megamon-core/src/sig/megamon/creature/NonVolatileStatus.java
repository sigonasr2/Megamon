package sig.megamon.creature;

import java.lang.reflect.Field;

public enum NonVolatileStatus {
	BURN("BRN"),
	FREEZE("FRZ"),
	PARALYSIS("BRN"),
	POISON("PSN"),
	BAD_POISON("PSN"),
	SLEEP("SLP");
	
	String abbreviation;
	
	NonVolatileStatus(String abbreviation) {
		this.abbreviation=abbreviation;
	}
}
