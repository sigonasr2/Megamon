package sig.megamon.creature;

import sig.megamon.Megamon;

public class CreatureMoveLinker {
	CreatureMove move;
	int lvLearned=0;
	
	public CreatureMoveLinker(String moveKeyName, int lvLearnedAt) {
		if (Megamon.moveDatabase.containsKey(moveKeyName)) {
			this.move = Megamon.moveDatabase.get(moveKeyName);
		} else {
			System.out.println("WARNING! Move \""+moveKeyName+"\" does not exist! Adding default move.");
			this.move = Megamon.moveDatabase.get("M.Buster");
		}
		this.lvLearned=lvLearnedAt;
	}
}
