package edu.ricm3.game.tomatower.automaton;

import edu.ricm3.game.tomatower.entities.Entity;

public class A_Action {
	
	A_Expression a_Expression;
	
	public A_Action(A_Expression e) {
		this.a_Expression = e;	
	}
	
	public void exec(Entity e) throws Exception {
		if(!(a_Expression instanceof A_FunctionCall))
			throw new Exception("Illegal action");
		
		((A_FunctionCall)a_Expression).exec(e);
	}

}
