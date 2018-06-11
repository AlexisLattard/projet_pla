package edu.ricm3.game.tomatower.automaton;

import edu.ricm3.game.tomatower.entities.Entity;

public class A_AndOp extends A_BinaryOp {
	
	
	
	
	public A_AndOp(A_Expression l, A_Expression r) {
		super(l, r);
	}

	public boolean eval(Entity e) throws Exception {
		return this.left_expression.eval(e) && this.right_expression.eval(e);
	}
	
	public void exec(Entity e) throws Exception {
		throw new Exception("Cannot execute a double action");
	}

}
