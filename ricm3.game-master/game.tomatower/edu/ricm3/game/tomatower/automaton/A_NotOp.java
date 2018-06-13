package edu.ricm3.game.tomatower.automaton;

import edu.ricm3.game.tomatower.entities.Entity;

public class A_NotOp extends A_UnaryOp {

	public A_NotOp(A_Expression e) {
		super(e);
	}

	@Override
	public boolean eval(Entity e) throws Exception {
		return !this.expression.eval(e);
	}

	@Override
	public void exec(Entity entity) throws Exception {
		throw new Exception("Cannot execute a not expression");
	}

}
