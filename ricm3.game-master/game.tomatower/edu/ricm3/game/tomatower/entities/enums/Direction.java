package edu.ricm3.game.tomatower.entities.enums;

public enum Direction {
	SOUTH(0), NORTH(1), EAST(2), WEST(3), FRONT(-1), BACK(-1), ONTHERIGHT(-1), ONTHELEFT(-1), NONE(-1);

	int value;

	Direction(int val) {
		this.value = val;
	}

	public int getValue() {
		return value;
	}
}
