package sig.megamon;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import sig.megamon.creature.CreatureLore;
import sig.megamon.creature.CreatureMove;
import sig.megamon.creature.CreatureType;
import sig.megamon.creature.ExperienceRate;
import sig.megamon.creature.SpriteCollection;

public class MegamonCreature {
	final SpriteCollection sprites;
	final String name;
	final CreatureLore bio;
	final CreatureType type1;
	final CreatureType type2;
	final int hp;
	final int atk;
	final int def;
	final int spd;
	final int spc;
	final int catch_rate;
	final int base_exp;
	final ExperienceRate exp_rate;
	final List<CreatureMove> moveset;
	boolean seenByPlayer=false;
	
	public MegamonCreature(String mini_icon, String sprite, String back_sprite,
			String name, String bio, int size, float weight, CreatureType type1, 
			int base_hp, int base_atk, int base_def, int base_spd, int base_spc,
			int catch_rate, int base_exp, ExperienceRate exp_rate,
			CreatureMove...moveset) {
		this(mini_icon,sprite,back_sprite,name,bio,size, weight,type1,CreatureType.NONE,base_hp,base_atk,base_def,base_spd,base_spc,catch_rate,base_exp,exp_rate,moveset);
	}

	public MegamonCreature(String mini_icon, String sprite, String back_sprite,
			String name, String bio, int size, float weight, CreatureType type1, 
			CreatureType type2, 
			int base_hp, int base_atk, int base_def, int base_spd, int base_spc,
			int catch_rate, int base_exp, ExperienceRate exp_rate,
			CreatureMove...moveset) {
		this.sprites = new SpriteCollection(mini_icon,sprite,back_sprite);
		this.name = name;
		this.bio = new CreatureLore(bio,size,weight);
		this.type1 = type1;
		this.type2 = type2;
		this.hp = base_hp;
		this.atk = base_atk;
		this.def = base_def;
		this.spd = base_spd;
		this.spc = base_spc;
		this.catch_rate=catch_rate;
		this.base_exp=base_exp;
		this.exp_rate = exp_rate;
		this.moveset = new LinkedList(Arrays.asList(moveset));
	}
	
	public void setSeen(boolean seenByPlayer) {
		this.seenByPlayer=seenByPlayer;
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
