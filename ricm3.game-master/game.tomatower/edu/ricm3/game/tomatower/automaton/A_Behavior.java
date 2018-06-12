package edu.ricm3.game.tomatower.automaton;

import java.util.ArrayList;
import java.util.Iterator;

import edu.ricm3.game.tomatower.Options;
import edu.ricm3.game.tomatower.entities.Entity;

public class A_Behavior {
	
	String state;
	ArrayList<A_Transition> a_Transitions;
	
	public A_Behavior(String s, ArrayList<A_Transition> t) {
		this.state = s;
		this.a_Transitions = t;
	}
	
	public boolean exec(A_Automaton a, Entity e) throws Exception {
		
		
		boolean transition_executed = false;
		Iterator<A_Transition> iter_transition = this.a_Transitions.iterator();
		while(iter_transition.hasNext() && !transition_executed) {
			A_Transition t = iter_transition.next();
			if(t.condition.eval(e)) {
				t.exec(a, e);
				transition_executed = true;
			}
		}
		
		return transition_executed; // Si aucune transition n'a pu être executé on renvoie faux
		
	}
	

}
