package edu.ricm3.game.tomatower.automaton;

import edu.ricm3.game.tomatower.entities.Entity;

public class A_Transition {
	
	A_Condition a_Condition;
	A_Action a_Action;
	String state;
	
	public A_Transition(A_Condition c, A_Action a, String s) {
		this.a_Condition = c;
		this.a_Action = a;
		this.state = s;
	}
	
	
	/*
	 * Execute the transition
	 */
	public boolean exec(A_Automaton a, Entity e) throws Exception {
		if(this.a_Condition.eval(e)) {
			this.a_Action.exec(e);
			a.state = state;
			return true;
		}
		return false;
	}

}
