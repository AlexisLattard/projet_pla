package edu.ricm3.game.tomatower.entities.enums;

public enum Direction {
	DOWN(0), UP(1), RIGHT(2), LEFT(3);

	int value;

	Direction(int val) {
		this.value = val;
	}

	public int getValue() {
		return value;
	}
}
