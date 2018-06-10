package edu.ricm3.game.tomatower.automaton;

import java.util.ArrayList;
import java.util.Iterator;

import edu.ricm3.game.tomatower.entities.Entity;

public class A_Behavior {
	
	String state;
	ArrayList<A_Transition> a_Transitions;
	
	public A_Behavior(String s, ArrayList<A_Transition> t) {
		this.state = s;
		this.a_Transitions = t;
	}
	
	public void exec(A_Automaton a, Entity e) throws Exception {
		
		boolean transition_executed = false;
		Iterator<A_Transition> iter_transition = this.a_Transitions.iterator();
		while(iter_transition.hasNext() && !transition_executed) {
			transition_executed = iter_transition.next().exec(a, e);
		}
		
		if(!transition_executed)
			throw new Exception("Unable to find a transition to execute");
		
		
	}
	

}
