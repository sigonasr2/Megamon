package sig.megamon;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import sig.megamon.creature.NonVolatileStatus;
import sig.megamon.creature.VolatileStatus;

public class MegamonPet {
	MegamonCreature creature;
	NonVolatileStatus status;
	VolatileStatus battlestatus;
	Integer atk_modifier;
	Integer def_modifier;
	Integer spd_modifier;
	Integer spc_modifier;
	Integer eva_modifier;
	Integer acc_modifier;
	Integer atk_iv;
	Integer def_iv;
	Integer spd_iv;
	Integer spc_iv;
	Integer atk_ev;
	Integer def_ev;
	Integer spd_ev;
	Integer spc_ev;
	Integer hp_ev;
	Integer hp;
	Integer maxhp;
	Integer atk;
	Integer def;
	Integer spd;
	Integer spc;
	String nickname;
	Integer original_trainer;
	String original_trainer_name;
	Integer level;
	Integer hp_iv;
	
	public MegamonPet(String nickname, MegamonCreature creature_type, Integer level) {
		this(nickname,creature_type,level,Megamon.mainP.trainer_id,Megamon.mainP.name);
	}
	
	public MegamonPet(String nickname, MegamonCreature creature_type, Integer level,
			Integer original_trainer, String original_trainer_name) {
		this(nickname,creature_type,level,original_trainer,original_trainer_name,
				new Integer[]{}, new Integer[]{});
	}
	
	public MegamonPet(String nickname, MegamonCreature creature_type, Integer level,
			Integer original_trainer, String original_trainer_name, Integer[] stats_collection, 
			Integer...IV_EV_Collection) {
		this.creature = creature_type;
		if (nickname.length()>0) {
			this.nickname = nickname;
		} else {
			this.nickname = creature_type.name;
		}
		this.level = level;
		this.original_trainer = original_trainer;
		this.original_trainer_name = original_trainer_name;
		InitializeStats(stats_collection,IV_EV_Collection);
	}
	
	private void InitializeStats(Integer[] stats_collection, Integer[] IV_EV_Collection) {
		/*if (stats_collection.length<6) {
			//System.out.println("WARNING! Malformed stats array! Will fill in with defaults.");
		}
		if (IV_EV_Collection.length<9) {
			//System.out.println("WARNING! Malformed IV_EV array! Will fill in with defaults.");
		}*/

		int i=0;
		atk_iv = UseStatOrDefault(IV_EV_Collection,i++,(int)(Math.random()*16));
		def_iv = UseStatOrDefault(IV_EV_Collection,i++,(int)(Math.random()*16));
		spd_iv = UseStatOrDefault(IV_EV_Collection,i++,(int)(Math.random()*16));
		spc_iv = UseStatOrDefault(IV_EV_Collection,i++,(int)(Math.random()*16));
		hp_iv = CalculateHPIV();
		atk_ev = UseStatOrDefault(IV_EV_Collection,i++,0);
		def_ev = UseStatOrDefault(IV_EV_Collection,i++,0);
		spd_ev = UseStatOrDefault(IV_EV_Collection,i++,0);
		spc_ev = UseStatOrDefault(IV_EV_Collection,i++,0);
		hp_ev = UseStatOrDefault(IV_EV_Collection,i++,0);
		i=0;
		hp = UseStatOrDefault(stats_collection,i++,CalculateHealth(level));
		maxhp = UseStatOrDefault(stats_collection,i++,CalculateHealth(level));
		atk = UseStatOrDefault(stats_collection,i++,CalculateStat(level,creature.atk,atk_iv,atk_ev));
		def = UseStatOrDefault(stats_collection,i++,CalculateStat(level,creature.def,def_iv,def_ev));
		spd = UseStatOrDefault(stats_collection,i++,CalculateStat(level,creature.spd,spd_iv,spd_ev));
		spc = UseStatOrDefault(stats_collection,i++,CalculateStat(level,creature.spc,spc_iv,spc_ev));
	}
	
	private boolean isOdd(Integer val) {
		return val % 2 == 0;
	}

	private Integer CalculateHPIV() {
		int lsb_total = ((isOdd(atk_iv))?8:0)+
				((isOdd(def_iv))?4:0)+
				((isOdd(spd_iv))?2:0)+
				((isOdd(spc_iv))?1:0);
		return lsb_total;
	}

	private Integer UseStatOrDefault(Integer[] stat_array, int index, int default_value) {
		if (stat_array.length>index) {
			return stat_array[index];
		} else {
			return default_value;
		}
	}

	private int CalculateHealth(Integer lv) {
		return (int)(((((creature.hp+hp_iv)*2)+(Math.sqrt(hp_ev)/4))*lv)/100)+lv+10;
	}

	private int CalculateStat(Integer lv, int base, Integer iv, Integer ev) {
		return (int)(((((base+iv)*2)+(Math.sqrt(ev)/4))*lv)/100)+5;
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
