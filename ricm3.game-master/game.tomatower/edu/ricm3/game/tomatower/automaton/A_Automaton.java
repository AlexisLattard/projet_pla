package edu.ricm3.game.tomatower.automaton;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import edu.ricm3.game.parser.Ast;
import edu.ricm3.game.tomatower.entities.Entity;
import edu.ricm3.game.tomatower.entities.Mobs;


public class A_Automaton {
	
	String name;
	ArrayList<A_Behavior> behaviors;
	String state;
	
	public A_Automaton(String n, ArrayList<A_Behavior> b, String s) {
		this.name = n;
		this.behaviors = b;
		this.state = s;
	}
	
	
	
	public boolean step(Entity e) {
		try {
			A_Behavior behavior = null;
			if(this.state.equals("_")) { // choose a random behavior
				Random rand = new Random();
				int id_behavior = rand.nextInt(this.behaviors.size());
				behavior = this.behaviors.get(id_behavior);
			} else {
				Iterator<A_Behavior> iter_behavior = this.behaviors.iterator();
				while(iter_behavior.hasNext() && behavior == null) {
					A_Behavior b = iter_behavior.next();
					if(b.state.equals(this.state)) {
						behavior = b;
					}
				}
			}
			
			
			if(behavior == null)
				throw new Exception("Unable to find a behavior");
			
			return behavior.exec(this, e);
			
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		
		return true;
	}
	

	
	
	
}
