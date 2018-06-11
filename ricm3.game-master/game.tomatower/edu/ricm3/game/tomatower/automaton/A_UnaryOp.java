package edu.ricm3.game.tomatower.automaton;

import edu.ricm3.game.tomatower.entities.Entity;

public abstract class A_UnaryOp extends A_Expression{
	
	A_Expression expression;
	
	public A_UnaryOp(A_Expression e) {
		this.expression = e;
	}

	@Override
	public abstract boolean eval(Entity e) throws Exception ;

	@Override
	public abstract void exec(Entity entity) throws Exception;

}
