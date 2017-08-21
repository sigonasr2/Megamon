package sig.megamon.creature;

public class CreatureMoveLinker {
	CreatureMove move;
	int lvLearned=0;
	
	public CreatureMoveLinker(CreatureMove move, int lvLearnedAt) {
		this.move=move;
		this.lvLearned=lvLearnedAt;
	}
}
