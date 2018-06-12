package edu.ricm3.game.tomatower.automaton;

import edu.ricm3.game.tomatower.entities.Entity;

public abstract class A_Expression {
	
	public abstract boolean eval(Entity e) throws Exception;
	
	public abstract void exec(Entity entity) throws Exception;

}
