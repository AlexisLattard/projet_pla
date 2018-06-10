package edu.ricm3.game.tomatower.automaton;

import java.util.Random;

import edu.ricm3.game.tomatower.entities.Entity;

public class A_OrOp extends A_BinaryOp{

	
	public A_OrOp(A_Expression l, A_Expression r) {
		super(l, r);
	}


	@Override
	public boolean eval(Entity e) throws Exception {
		return this.left_expression.eval(e) || this.right_expression.eval(e);
	}
	
	
	@Override
	public void exec(Entity e) throws Exception{
		Random rand = new Random();
		int n = rand.nextInt(2);
		if(n == 0)
			this.left_expression.exec(e);
		else
			this.right_expression.exec(e);
	}

}
