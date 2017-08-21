package sig.megamon;

import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.LinkedList;

import sig.megamon.creature.Category;
import sig.megamon.creature.CreatureMove;
import sig.megamon.creature.CreatureMoveLinker;
import sig.megamon.creature.CreatureType;
import sig.megamon.creature.Evolution;
import sig.megamon.creature.ExperienceRate;
import sig.megamon.ref.RoomRef;
import sig.megamon.ref.SignRef;

public class Database {

	public static void SetupDoorDatabase() {
		new RoomRef(
				new Point2D.Double(9, 95),
				"Test Map",
				new Point2D.Double(7, 98),
				"Test Map 2"
				).addInverseDoorLink();
	}
	
	
	public static void SetupInfoDatabase() {
		new SignRef(
				new Point2D.Double(9,99),
				"Test Map",
				new String[]{
						"Hello there! You have found the invisible space that happens to be a dialog box.",
						"\n\nCongratulations~!"
				}
				);
	}
	
	public static void SetupMoveDatabase() {
		String moveName = "M.Buster";Megamon.moveDatabase.put(moveName, new CreatureMove(
				moveName,"Damages the target. Exclusive to Mega Man.",
				CreatureType.NORMAL, Category.SPECIAL,
				35,35,95));
		Megamon.moveDatabase.put(moveName="P.Buster", new CreatureMove(
				moveName,"Damages the target. Exclusive to Proto Man.",
				CreatureType.NORMAL, Category.SPECIAL,
				35,35,95));
		Megamon.moveDatabase.put(moveName="B.Buster", new CreatureMove(
				moveName,"Damages the target. Exclusive to Bass.",
				CreatureType.NORMAL, Category.SPECIAL,
				35,35,95));
		Megamon.moveDatabase.put(moveName="Bubble Lead", new CreatureMove(
				moveName,"Damages the target with bubbles. Exclusive to Mega Man.",
				CreatureType.WATER, Category.SPECIAL,
				25,40,100));
		Megamon.moveDatabase.put(moveName="Magma Bazooka", new CreatureMove(
				moveName,"Has a 10% chance to burn the target. Exclusive to Proto Man.",
				CreatureType.FIRE, Category.SPECIAL,
				15,95,100));
		Megamon.moveDatabase.put(moveName="Slide", new CreatureMove(
				moveName,"Increases the user's evasion by one stage.",
				CreatureType.NORMAL, Category.STATUS,
				15,0,100));
		Megamon.moveDatabase.put(moveName="Water Balloon", new CreatureMove(
				moveName,"Damages the target with a large water baloon. Exclusive to Mega Man.",
				CreatureType.WATER, Category.SPECIAL,
				5,120,80));
		Megamon.moveDatabase.put(moveName="Quick Jab", new CreatureMove(
				moveName,"Damages the target, and is an increased priority move. Will go first regardless of the user's or targets speed.",
				CreatureType.STRENGTH, Category.PHYSICAL,
				30,60,100));
		Megamon.moveDatabase.put(moveName="Haymaker", new CreatureMove(
				moveName,"Damages the target, and has a 10% chance to paralyze the target.",
				CreatureType.STRENGTH, Category.PHYSICAL,
				15,90,100));
		Megamon.moveDatabase.put(moveName="Proto Shield", new CreatureMove(
				moveName,"If the target uses a damaging move you block 75% of the damage. If the move is non damaging the user's defense falls 1 stage.",
				CreatureType.SHIELD, Category.STATUS,
				25,0,100));
		Megamon.moveDatabase.put(moveName="Shield Charge", new CreatureMove(
				moveName,"Damages the target, and has a 10% chance to paralyze the target.",
				CreatureType.SHIELD, Category.PHYSICAL,
				15,90,100));
		Megamon.moveDatabase.put(moveName="Jamming", new CreatureMove(
				moveName,"Lowers the target's attack by 1 stage",
				CreatureType.NORMAL, Category.STATUS,
				40,0,100));
		Megamon.moveDatabase.put(moveName="Degrade", new CreatureMove(
				moveName,"Lowers the target's defense by 1 stage",
				CreatureType.NORMAL, Category.STATUS,
				40,0,100));
		Megamon.moveDatabase.put(moveName="Shadow Punch", new CreatureMove(
				moveName,"Inflicts damage and is unaffected by ACC & EVA stat mods. Will not hit targets using Fly or Dig.",
				CreatureType.DARK, Category.PHYSICAL,
				20,60,100));
		Megamon.moveDatabase.put(moveName="Evil Energy", new CreatureMove(
				moveName,"Increases the Attack by 2 Stages. Cannot Stack.",
				CreatureType.DARK, Category.STATUS,
				10,0,100));
		Megamon.moveDatabase.put(moveName="Octagon Shot", new CreatureMove(
				moveName,"Attacks 3-5 times in one turn; if one of these attacks breaks a target's Substitute, the target will take damage for the rest",
				CreatureType.DARK, Category.PHYSICAL,
				20,25,100));
		Megamon.moveDatabase.put(moveName="Aerial Barrage", new CreatureMove(
				moveName,"A powerful aerial strike.",
				CreatureType.FLYING, Category.PHYSICAL,
				15,95,100));
		Megamon.moveDatabase.put(moveName="Beat Plane", new CreatureMove(
				moveName,"Beats the target, and has a 10% chance to confuse the target.",
				CreatureType.FLYING, Category.PHYSICAL,
				15,75,95));
		Megamon.moveDatabase.put(moveName="Double Jump", new CreatureMove(
				moveName,"Increases the user's evasion by 1 stage.",
				CreatureType.NORMAL, Category.STATUS,
				15,0,100));
		Megamon.moveDatabase.put(moveName="Double Jump", new CreatureMove(
				moveName,"First turn, the user jumps into the air, becoming uncontrollable, and evades most attacks. Swift may still hit. The second turn, the user slams into the ground dealing heavy damage.",
				CreatureType.NORMAL, Category.PHYSICAL,
				15,70,95));
		Megamon.moveDatabase.put(moveName="Spread Shot", new CreatureMove(
				moveName,"Damages the target. Exclusive to Mets.",
				CreatureType.NORMAL, Category.PHYSICAL,
				30,35,95));
		Megamon.moveDatabase.put(moveName="Taunt", new CreatureMove(
				moveName,"Raises the target's attack by 2 stages and confuses it.",
				CreatureType.NORMAL, Category.STATUS,
				15,0,90));
		Megamon.moveDatabase.put(moveName="Spark Shock", new CreatureMove(
				moveName,"Damages the target, and has a 50% chance to paralyze the target. Exclusive to Spark Man.",
				CreatureType.ELECTRIC, Category.SPECIAL,
				10,50,85));
		Megamon.moveDatabase.put(moveName="Charge", new CreatureMove(
				moveName,"Raises the user's Special Attack by 1 stage.",
				CreatureType.ELECTRIC, Category.STATUS,
				15,0,100));
		Megamon.moveDatabase.put(moveName="Rain Flush", new CreatureMove(
				moveName,"Damages the target. Has a 25% chance to poison the target. Exclusive to Toad Man.",
				CreatureType.WATER, Category.SPECIAL,
				15,75,90));
		Megamon.moveDatabase.put(moveName="Fade to Black", new CreatureMove(
				moveName,"Raises the user's evasion by 1 stage.",
				CreatureType.DARK, Category.STATUS,
				20,0,0));
		Megamon.moveDatabase.put(moveName="Dive Bomb", new CreatureMove(
				moveName,"Flies up and dives straight for the target.",
				CreatureType.FLYING, Category.PHYSICAL,
				15,60,100));
		Megamon.moveDatabase.put(moveName="Shield & Burst", new CreatureMove(
				moveName,"On turn 1 a shield is put up and will absorb 25% of the attack damage. On Turn 2 the shield bursts into shrapnel-like pieces.",
				CreatureType.SHIELD, Category.SPECIAL,
				15,60,100));
		Megamon.moveDatabase.put(moveName="Pharaoh Shot", new CreatureMove(
				moveName,"Damages the target. Has a 10% chance to burn the target. Exclusive to Pharoah Man.",
				CreatureType.FIRE, Category.SPECIAL,
				15,95,100));
		Megamon.moveDatabase.put(moveName="Pharaoh Shot", new CreatureMove(
				moveName,"Damages the target. Has a 10% chance to burn the target. Exclusive to Pharoah Man.",
				CreatureType.FIRE, Category.SPECIAL,
				15,95,100));
		Megamon.moveDatabase.put(moveName="Yamato Spear", new CreatureMove(
				moveName,"Damages the target.",
				CreatureType.NORMAL, Category.PHYSICAL,
				15,80,100));
		Megamon.moveDatabase.put(moveName="Burn", new CreatureMove(
				moveName,"Inflicts a burn damage to the target. It has no effect on Fire-types.",
				CreatureType.FIRE, Category.SPECIAL,
				15,0,75));
		Megamon.moveDatabase.put(moveName="Fire Storm", new CreatureMove(
				moveName,"Damages the target. Has a 10% chance to Burn the target. Also increases Special & Attack by 1 stage to the user.",
				CreatureType.FIRE, Category.SPECIAL,
				15,60,100));
		Megamon.moveDatabase.put(moveName="Bug Bite", new CreatureMove(
				moveName,"Damages the target. Has a 20% chance to poison the target.",
				CreatureType.BUG, Category.PHYSICAL,
				15,65,85));
		Megamon.moveDatabase.put(moveName="Air Shooter", new CreatureMove(
				moveName,"Damages the target with air waves. Exclusive to Air Man.",
				CreatureType.FLYING, Category.SPECIAL,
				15,95,100));
		Megamon.moveDatabase.put(moveName="Plant Barrier", new CreatureMove(
				moveName,"It blocks any attack made by the enemy. Will always go first.",
				CreatureType.SHIELD, Category.STATUS,
				5,0,100));
		Megamon.moveDatabase.put(moveName="Noise Crush", new CreatureMove(
				moveName,"Damages the Target. If used on an enemy that has barrier, light screen, or reflect active the damage doubles.",
				CreatureType.DARK, Category.PHYSICAL,
				15,85,100));
		Megamon.moveDatabase.put(moveName="Crystal Eye", new CreatureMove(
				moveName,"Damages the target. Excluse to Crystal Man.",
				CreatureType.NORMAL, Category.SPECIAL,
				15,90,100));
		Megamon.moveDatabase.put(moveName="Proto Song", new CreatureMove(
				moveName,"Puts the target to sleep. Exclusive to Proto Man.",
				CreatureType.NORMAL, Category.SPECIAL,
				15,0,75));
		Megamon.moveDatabase.put(moveName="Last Round", new CreatureMove(
				moveName,"Lowers the user's speed by 1 stage, but increases the user's attack and defense by 1 stage.",
				CreatureType.STRENGTH, Category.STATUS,
				5,0,100));
		Megamon.moveDatabase.put(moveName="Fire Armor", new CreatureMove(
				moveName,"Damages the target and increases the users defense by 1 stage.",
				CreatureType.FIRE, Category.SPECIAL,
				20,50,100));
		Megamon.moveDatabase.put(moveName="Ultimate Defense", new CreatureMove(
				moveName,"If successful, the user's defense increases by 2 stages, If not successful the user suffers from a random status condition.",
				CreatureType.FIRE, Category.SPECIAL,
				5,0,50));
	}
	
	public static void SetupMegamonDatabase() {
		Megamon.megamonDatabase = new LinkedList<MegamonCreature>(Arrays.asList(
					new MegamonCreature("1_icon.png","1.png","1_back.png",
							"Basic M", "Basic Megaman", 1, 1,
							CreatureType.WATER, 44, 48, 65, 50, 43,
							3, 64, ExperienceRate.MEDIUM_SLOW,
							new CreatureMoveLinker[]{
								new CreatureMoveLinker(Megamon.moveDatabase.get("M.Buster"),1),
								new CreatureMoveLinker(Megamon.moveDatabase.get("Degrade"),1),
								new CreatureMoveLinker(Megamon.moveDatabase.get("Bubble Lead"),8),
								new CreatureMoveLinker(Megamon.moveDatabase.get("Slide"),15),
								new CreatureMoveLinker(Megamon.moveDatabase.get("Bubble Beam"),22),
								new CreatureMoveLinker(Megamon.moveDatabase.get("Water Shield"),28),
								new CreatureMoveLinker(Megamon.moveDatabase.get("Water Balloon"),38),
							})
				));
	}
}
