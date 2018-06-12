package edu.ricm3.game.tomatower.automaton;

import edu.ricm3.game.tomatower.entities.Entity;

public class A_Action {
	
	A_Expression expression;
	
	public A_Action(A_Expression e) {
		this.expression = e;	
	}
	
	public void exec(Entity e) throws Exception {
			
		
		if(expression instanceof A_FunctionCall || expression instanceof A_OrOp) {
			expression.exec(e);
		} else {
			throw new Exception("Illegal action");
		}
		
		
		
	}

}
