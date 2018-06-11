package edu.ricm3.game.tomatower.automaton;

import edu.ricm3.game.tomatower.entities.enums.Direction;

public class A_DirectionParameter extends A_Parameter{
	
	Direction value;
	
	public A_DirectionParameter(Direction v) {
		this.value = v;
	}

}
