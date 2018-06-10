package edu.ricm3.game.tomatower.automaton;

import java.util.ArrayList;
import java.util.Iterator;

import edu.ricm3.game.parser.Ast;
import edu.ricm3.game.tomatower.entities.Entity;


public class A_Automaton {
	
	String name;
	ArrayList<A_Behavior> a_Behaviors;
	String state;
	
	public A_Automaton(String n, ArrayList<A_Behavior> b, String s) {
		this.name = n;
		this.a_Behaviors = b;
		this.state = s;
	}
	
	
	
	public boolean step(Entity e) {
		try {
			A_Behavior a_Behavior = null;
			Iterator<A_Behavior> iter_behavior = this.a_Behaviors.iterator();
			while(iter_behavior.hasNext() && a_Behavior == null) {
				A_Behavior b = iter_behavior.next();
				if(b.state.equals(this.state)) {
					a_Behavior = b;
				}
			}
			
			if(a_Behavior == null)
				throw new Exception("Unable to find a behavior");
			
			return a_Behavior.exec(this, e);
			
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		
		
		return true;
	}
	

	
	
	
}
