package edu.ricm3.game.tomatower;

public final class LevelDesign {
	// Action time
	public static final long ACTION_TIME_PLAYER = 200L; //
	public static final long ACTION_TIME_TOWER = 1000L; //
	public static final long ACTION_TIME_MOBS = 1000L; //
	public static final long ACTION_TIME_MOBSPAWN = 20000L; //
	public static final long ACTION_TIME_SPAWN_SAME_WAVE = 100L; 
	
	// HP
	public static final int MAX_LIFE_PLAYER = 1000; //
	public static final int MAX_LIFE_MOB_PLUG = 40; 
	public static final int MAX_LIFE_MOB_GHOST = 20; 
	public static final int MAX_LIFE_MOB_LANTERN = 30; 
	public static final int MAX_LIFE_MOB_HUNGRY = 10; 
	public static final int MAX_LIFE_TOWER = 500; //
	public static final int MAX_LIFE_CRYSTAL = 5000; //
	
	// Money
	public static final int MONEY_PLAYER = 5000; //
	public static final int PRICE_TOWER_PRODUCT = 500; //
	public static final int PRICE_TOWER_UPGRADE = 200; //
	public static final int INCREASES_TOWER_UPGRADE_AMOUNT = 100; //
	public static final int INCREASES_TOWER_PRODUCT_AMOUNT = 200; //
	public static final int INCREASES_TOWER_AMOUNT_MONSTER = 50; //
	public static final int EARNED_MONEY_WHEN_MOB_DIED = 100; //
	public static final int PRICE_BEHAVIOR_CHANGEMENT = 200; // 
	
	// Hit
	public static final int HIT_PLAYER = 20; //
	public static final int HIT_MOB_PLUG = 10; //
	public static final int HIT_MOB_HUNGRY = 40; //
	public static final int HIT_MOB_LANTERN = 30; //
	public static final int HIT_MOB_GHOST = 20; //
	public static final int HIT_TOWER_RED = 80; // 
	public static final int HIT_TOWER_BLUE = 60; //
	public static final int HIT_TOWER_YELLOW = 40; // 
	public static final int HIT_TOWER_PURPLE = 20; //
	
	// Range
	public static final int RANGE_PLAYER = 1; 
	public static final int RANGE_MOB_PLUG = 1; //
	public static final int RANGE_MOB_HUNGRY = 1; //
	public static final int RANGE_MOB_LANTERN = 1; //
	public static final int RANGE_MOB_GHOST = 1; //
	public static final int RANGE_TOWER_RED = 1; //
	public static final int RANGE_TOWER_BLUE = 2; //
	public static final int RANGE_TOWER_YELLOW = 3; //
	public static final int RANGE_TOWER_PURPLE = 4; //
	
	// OTHER
	public static final int DAMAGE_DESTRUCTION_MOB = 100; //
	public static final int DAMAGE_DESTRUCTION_TOWER = 100; //

	
}
