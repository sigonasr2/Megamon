package sig.megamon.creature;

import sig.megamon.MegamonCreature;

public class Evolution {
	MegamonCreature creature;
	int level;
	boolean trade;
	
	public Evolution(MegamonCreature creatureEvolvesInto, int levelOfEvolution) {
		this(creatureEvolvesInto,levelOfEvolution,false);
	}
	
	public Evolution(MegamonCreature creatureEvolvesInto, int levelOfEvolution, boolean tradeRequired) {
		this.creature = creatureEvolvesInto;
		this.level = levelOfEvolution;
		this.trade = tradeRequired;
	}
}
