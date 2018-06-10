package edu.ricm3.game.tomatower.automaton;

import java.util.ArrayList;

import edu.ricm3.game.tomatower.entities.Entity;
import edu.ricm3.game.tomatower.entities.enums.Direction;


public class A_FunctionCall extends A_Expression {
	
	String function_name;
	ArrayList<A_Parameter> parameters;
	
	
	public A_FunctionCall(String n, ArrayList<A_Parameter> p) {
		this.function_name = n;
		this.parameters = p;
	}
	
	
	public void exec(Entity e) throws Exception {
		
		switch (this.function_name) {
		case "Move":
			this.move(e);
			break;

		default:
			throw new Exception("Invalid action function");
		}	
	}
	
	
	public boolean eval(Entity e) throws Exception {
		
		switch (this.function_name) {
			case "Cell":
				return this.freeCell(e);
			case "True":
				return true;
			default:
				throw new Exception("Invalid condtion function");
		}
	}
	
	// Actions
	
	public void move(Entity e) throws Exception {
		if(parameters.size() >= 2 || (parameters.size() == 1 &&  !(parameters.get(0) instanceof A_DirectionParameter)))
			throw new Exception("Invalid argument(s)");
		
		if(parameters.size() == 1)
			e.move(((A_DirectionParameter)parameters.get(0)).value); 
		else
			e.move(Direction.DOWN);
	}
	
	
	// Conditions
	
	public boolean freeCell(Entity e) throws Exception {
		if(this.parameters.size() != 2 || !(this.parameters.get(0) instanceof A_DirectionParameter) || !(this.parameters.get(1) instanceof A_EntityParameter))
			throw new Exception("Invalid parameters for freeCell function");
		
		return e.freeCell((((A_DirectionParameter)this.parameters.get(0)).value));
		
	}

}
