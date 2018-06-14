package edu.ricm3.game.tomatower.automaton;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import edu.ricm3.game.tomatower.Options;
import edu.ricm3.game.tomatower.entities.Entity;
import edu.ricm3.game.tomatower.entities.Mobs;

public class A_Automaton {

	String name;
	ArrayList<A_Behavior> behaviors;
	String entry_state;

	public A_Automaton(String n, ArrayList<A_Behavior> b, String s) {
		this.name = n;
		this.behaviors = b;
		this.entry_state = s;
	}

	public boolean step(Entity e) {
		try {
			A_Behavior behavior = null;
			String entity_state = e.getCurrentState();
			if (entity_state == null) {
				e.setCurrentState(entry_state);
				entity_state = entry_state;
			}

			if (entity_state.equals("_")) { // choose a random behavior
				Random rand = new Random();
				int id_behavior = rand.nextInt(this.behaviors.size());
				behavior = this.behaviors.get(id_behavior);
			} else {
				Iterator<A_Behavior> iter_behavior = this.behaviors.iterator();
				while (iter_behavior.hasNext() && behavior == null) {
					A_Behavior b = iter_behavior.next();
					if (b.state.equals(entity_state)) {
						behavior = b;
					}
				}
			}

			if (behavior == null) {
				if (Options.ECHO_GAME_STATE)
					System.out.println(
							"Problem with the entity behavior : unable to find a bahavior (no state corresponding). Automaton deleted from the entity");
				e.removeAutomaton();
			}

			return behavior.exec(this, e);

		} catch (Exception exception) {
			exception.printStackTrace();
		}

		return true;
	}

	public String getCurrentState() {
		return this.entry_state;
	}

}
