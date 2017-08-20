package sig.megamon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import sig.megamon.creature.CreatureMove;
import sig.megamon.creature.CreatureType;
import sig.megamon.creature.SpriteCollection;

public class MegamonCreature {
	SpriteCollection sprites;
	String name;
	String bio;
	CreatureType type1;
	CreatureType type2;
	int hp;
	int atk;
	int def;
	int spd;
	int spc;
	List<CreatureMove> moveset;
	
	public MegamonCreature(String mini_icon, String sprite, String back_sprite,
			String name, String bio, CreatureType type1, int base_hp, int base_atk, int base_def, int base_spd, int base_spc,
			CreatureMove...moveset) {
		this(mini_icon,sprite,back_sprite,name,bio,type1,CreatureType.NONE,base_hp,base_atk,base_def,base_spd,base_spc,moveset);
	}

	public MegamonCreature(String mini_icon, String sprite, String back_sprite,
			String name, String bio, CreatureType type1, CreatureType type2, int base_hp, int base_atk, int base_def, int base_spd, int base_spc,
			CreatureMove...moveset) {
		this.sprites = new SpriteCollection(mini_icon,sprite,back_sprite);
		this.name = name;
		this.bio = bio;
		this.type1 = type1;
		this.type2 = type2;
		this.hp = base_hp;
		this.atk = base_atk;
		this.def = base_def;
		this.spd = base_spd;
		this.spc = base_spc;
		this.moveset = new LinkedList(Arrays.asList(moveset));
	}
}
