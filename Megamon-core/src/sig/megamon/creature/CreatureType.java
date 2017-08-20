package sig.megamon.creature;

import java.lang.reflect.Field;

public enum CreatureType {
	NORMAL,
	FIRE,
	WATER,
	ELECTRIC,
	ICE,
	STRENGTH,
	GROUND,
	FLYING,
	BUG,
	DRAGON,
	DARK,
	SHIELD,
	NONE;
	
	public CreatureType[] getSuperEffectiveTypes() {
		switch (this) {
		case BUG:
				return new CreatureType[]{DARK,SHIELD};
		case DARK:
				return new CreatureType[]{STRENGTH,FLYING};
		case DRAGON:
			return new CreatureType[]{DRAGON};
		case ELECTRIC:
			return new CreatureType[]{WATER,FLYING,DARK};
		case FIRE:
			return new CreatureType[]{BUG,DARK};
		case FLYING:
			return new CreatureType[]{STRENGTH,BUG};
		case GROUND:
			return new CreatureType[]{FIRE,ELECTRIC};
		case ICE:
			return new CreatureType[]{FLYING,DRAGON};
		case NORMAL:
			return new CreatureType[]{};
		case SHIELD:
			return new CreatureType[]{ICE,GROUND};
		case STRENGTH:
			return new CreatureType[]{NORMAL,ICE};
		case WATER:
			return new CreatureType[]{FIRE,GROUND};
		case NONE:
			return new CreatureType[]{};
		default:
			System.out.println("WARNING! Could not find proper effectiveness table for type "+this.name()+"! This should not be happening!");
			return new CreatureType[]{};
		}
	}
	
	public CreatureType[] getWeakTypes() {
		switch (this) {
		case BUG:
				return new CreatureType[]{FIRE,STRENGTH,FLYING};
		case DARK:
				return new CreatureType[]{FIRE,ELECTRIC};
		case DRAGON:
			return new CreatureType[]{};
		case ELECTRIC:
			return new CreatureType[]{ELECTRIC,DRAGON,SHIELD};
		case FIRE:
			return new CreatureType[]{FIRE,WATER,DRAGON,SHIELD};
		case FLYING:
			return new CreatureType[]{ELECTRIC,DARK};
		case GROUND:
			return new CreatureType[]{BUG};
		case ICE:
			return new CreatureType[]{WATER,ICE};
		case NORMAL:
			return new CreatureType[]{};
		case SHIELD:
			return new CreatureType[]{STRENGTH,FLYING};
		case STRENGTH:
			return new CreatureType[]{FLYING,BUG};
		case WATER:
			return new CreatureType[]{WATER,DRAGON,SHIELD};
		case NONE:
			return new CreatureType[]{};
		default:
			System.out.println("WARNING! Could not find proper effectiveness table for type "+this.name()+"! This should not be happening!");
			return new CreatureType[]{};
		}
	}
	
	public CreatureType[] getUnaffectedTypes() {
		switch (this) {
		case BUG:
				return new CreatureType[]{};
		case DARK:
				return new CreatureType[]{};
		case DRAGON:
			return new CreatureType[]{};
		case ELECTRIC:
			return new CreatureType[]{GROUND};
		case FIRE:
			return new CreatureType[]{};
		case FLYING:
			return new CreatureType[]{};
		case GROUND:
			return new CreatureType[]{FLYING};
		case ICE:
			return new CreatureType[]{};
		case NORMAL:
			return new CreatureType[]{};
		case SHIELD:
			return new CreatureType[]{};
		case STRENGTH:
			return new CreatureType[]{};
		case WATER:
			return new CreatureType[]{};
		case NONE:
			return new CreatureType[]{};
		default:
			System.out.println("WARNING! Could not find proper effectiveness table for type "+this.name()+"! This should not be happening!");
			return new CreatureType[]{};
		}
	}
}
