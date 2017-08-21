package sig.megamon;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import sig.megamon.creature.CreatureMove;
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
	List<CreatureMove> moveset = new ArrayList<CreatureMove>();
	
	public MegamonPet(String nickname, MegamonCreature creature_type, Integer level) {
		this(nickname,creature_type,level,Megamon.mainP.trainer_id,Megamon.mainP.name);
	}
	
	public MegamonPet(String nickname, MegamonCreature creature_type, Integer level,
			Integer original_trainer, String original_trainer_name) {
		this(nickname,creature_type,level,original_trainer,original_trainer_name, new ArrayList<CreatureMove>(),
				new Integer[]{}, new Integer[]{});
	}
	
	public MegamonPet(String nickname, MegamonCreature creature_type, Integer level,
			Integer original_trainer, String original_trainer_name, List<CreatureMove> moveset) {
		this(nickname,creature_type,level,original_trainer,original_trainer_name, moveset,
				new Integer[]{}, new Integer[]{});
	}
	
	public MegamonPet(String nickname, MegamonCreature creature_type, Integer level,
			Integer original_trainer, String original_trainer_name, List<CreatureMove> moveset, 
			Integer[] stats_collection, Integer...IV_EV_Collection) {
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
		LearnDefaultMoves();
	}
	
	private void LearnDefaultMoves() {
		// TODO Auto-generated method stub
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
	
	public MegamonCreature getCreature() {
		return creature;
	}
	
	public NonVolatileStatus getStatus() {
		return status;
	}

	public VolatileStatus getBattleStatus() {
		return battlestatus;
	}

	public Integer getATKModifier() {
		return atk_modifier;
	}

	public Integer getDEFModifier() {
		return def_modifier;
	}

	public Integer getSPDModifier() {
		return spd_modifier;
	}

	public Integer getSPCModifier() {
		return spc_modifier;
	}

	public Integer getEVAModifier() {
		return eva_modifier;
	}

	public Integer getACCModifier() {
		return acc_modifier;
	}

	public Integer getATKIV() {
		return atk_iv;
	}

	public Integer getDEFIV() {
		return def_iv;
	}

	public Integer getSPDIV() {
		return spd_iv;
	}

	public Integer getSPCIV() {
		return spc_iv;
	}

	public Integer getATKEV() {
		return atk_ev;
	}

	public Integer getDEFEV() {
		return def_ev;
	}

	public Integer getSPDEV() {
		return spd_ev;
	}

	public Integer getSPCEV() {
		return spc_ev;
	}

	public Integer getHPEV() {
		return hp_ev;
	}

	public Integer getHP() {
		return hp;
	}

	public Integer getMaxHP() {
		return maxhp;
	}

	public Integer getATK() {
		return atk;
	}

	public Integer getDEF() {
		return def;
	}

	public Integer getSPD() {
		return spd;
	}

	public Integer getSPC() {
		return spc;
	}

	public String getNickname() {
		return nickname;
	}

	public Integer getOriginalTrainerID() {
		return original_trainer;
	}

	public String getOriginalTrainerName() {
		return original_trainer_name;
	}

	public Integer getLevel() {
		return level;
	}

	public Integer getHPIV() {
		return hp_iv;
	}

	public void setCreature(MegamonCreature creature) {
		this.creature = creature;
	}

	public void setStatus(NonVolatileStatus status) {
		this.status = status;
	}

	public void setBattleStatus(VolatileStatus battlestatus) {
		this.battlestatus = battlestatus;
	}

	public void setATKModifier(Integer atk_modifier) {
		this.atk_modifier = atk_modifier;
	}

	public void setDEFModifier(Integer def_modifier) {
		this.def_modifier = def_modifier;
	}

	public void setSPDModifier(Integer spd_modifier) {
		this.spd_modifier = spd_modifier;
	}

	public void setSPCModifier(Integer spc_modifier) {
		this.spc_modifier = spc_modifier;
	}

	public void setEVAModifier(Integer eva_modifier) {
		this.eva_modifier = eva_modifier;
	}

	public void setACCModifier(Integer acc_modifier) {
		this.acc_modifier = acc_modifier;
	}

	public void setATKIV(Integer atk_iv) {
		this.atk_iv = atk_iv;
	}

	public void setDEFIV(Integer def_iv) {
		this.def_iv = def_iv;
	}

	public void setSPDIV(Integer spd_iv) {
		this.spd_iv = spd_iv;
	}

	public void setSPCIV(Integer spc_iv) {
		this.spc_iv = spc_iv;
	}

	public void setATKEV(Integer atk_ev) {
		this.atk_ev = atk_ev;
	}

	public void setDEFEV(Integer def_ev) {
		this.def_ev = def_ev;
	}

	public void setSPDEV(Integer spd_ev) {
		this.spd_ev = spd_ev;
	}

	public void setSPCEV(Integer spc_ev) {
		this.spc_ev = spc_ev;
	}

	public void setHPEV(Integer hp_ev) {
		this.hp_ev = hp_ev;
	}

	public void setHP(Integer hp) {
		this.hp = hp;
	}

	public void setMaxHP(Integer maxhp) {
		this.maxhp = maxhp;
	}

	public void setATK(Integer atk) {
		this.atk = atk;
	}

	public void setDEF(Integer def) {
		this.def = def;
	}

	public void setSPD(Integer spd) {
		this.spd = spd;
	}

	public void setSPC(Integer spc) {
		this.spc = spc;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setOriginalTrainerID(Integer original_trainer) {
		this.original_trainer = original_trainer;
	}

	public void setOriginalTrainerName(String original_trainer_name) {
		this.original_trainer_name = original_trainer_name;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public void setHPIV(Integer hp_iv) {
		this.hp_iv = hp_iv;
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
