package edu.ricm3.game.tomatower.automaton;

import edu.ricm3.game.tomatower.entities.Entity;

public class A_Condition {
	
	A_Expression a_Expression;
	
	public A_Condition(A_Expression e) {
		this.a_Expression = e;
	}
	
	
	
	public boolean eval(Entity e) throws Exception{
		return a_Expression.eval(e);
	}

}
