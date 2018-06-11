package edu.ricm3.game.tomatower.automaton;

import edu.ricm3.game.tomatower.entities.Entity;

public class A_Transition {
	
	A_Condition condition;
	A_Action action;
	String state;
	
	public A_Transition(A_Condition c, A_Action a, String s) {
		this.condition = c;
		this.action = a;
		this.state = s;
	}
	
	
	/*
	 * Execute the transition (even if the condition if false, so it necessary to check before)
	 */
	public void exec(A_Automaton a, Entity e) throws Exception {
		
		this.action.exec(e);
		a.state = state;
		
		if(a.state.equals("X")) {
			System.out.println(a.state);
			e.removeAutomaton();
		}
		
		
	}

}
