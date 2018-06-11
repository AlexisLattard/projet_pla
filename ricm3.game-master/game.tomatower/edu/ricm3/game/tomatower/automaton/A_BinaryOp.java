package edu.ricm3.game.tomatower.automaton;

import edu.ricm3.game.tomatower.entities.Entity;

public abstract class A_BinaryOp extends A_Expression{
	
	A_Expression left_expression;
	A_Expression right_expression;
	
	public A_BinaryOp(A_Expression l, A_Expression r) {
		this.left_expression = l;
		this.right_expression = r;
	}

	@Override
	public abstract boolean eval(Entity e) throws Exception;
	
	@Override
	public abstract void exec(Entity e) throws Exception;
	
	
	


}
