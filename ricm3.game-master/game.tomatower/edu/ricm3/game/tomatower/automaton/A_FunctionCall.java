package edu.ricm3.game.tomatower.automaton;

import java.util.ArrayList;

import edu.ricm3.game.tomatower.Options;
import edu.ricm3.game.tomatower.entities.Entity;
import edu.ricm3.game.tomatower.entities.enums.Direction;
import edu.ricm3.game.tomatower.mvc.Controller;


public class A_FunctionCall extends A_Expression {
	
	String function_name;
	ArrayList<A_Parameter> parameters;
	Controller controller;
	
	
	public A_FunctionCall(String n, ArrayList<A_Parameter> p, Controller c) {
		this.function_name = n;
		this.parameters = p;
		this.controller = c;
	}
	
	
	public void exec(Entity e) throws Exception {
		
		switch (this.function_name) {
		case "Wizz":
			this.wizz(e);
			break;
		case "Pop":
			this.pop(e);
			break;
		case "Move":
			this.move(e);
			break;
		case "Jump":
			this.jump(e);
			break;
		case "Turn":
			this.turn(e);
			break;
		case "Hit":
			this.hit(e);
			break;
		case "Protect":
			this.protect(e);
			break;
		case "Pick":
			this.pick(e);
			break;
		case "Throw":
			this.throwActon(e);
			break;
		case "Store":
			this.store(e);
			break;
		case "Get":
			this.get(e);
			break;
		case "Power":
			this.power(e);
			break;
		case "Kamikaze":
			this.kamikaze(e);
			break;
		default:
			if(Options.ECHO_GAME_STATE)
				System.out.println("Invalid or empty action : " + this.function_name);
		}	
	}
	
	
	public boolean eval(Entity e) throws Exception{
		
		switch (this.function_name) {
			case "True":
				return true;
			case "Key" :
				return this.key(e);
			case "MyDir":
				return this.direction(e);
			case "Cell":
				return this.freeCell(e);
			case "Closest":
				return this.closest(e);
			case "GotPower":
				return this.gotPower(e);
			case "GotStuff":
				return this.gotStuff(e);
			
			default:
				if(Options.ECHO_GAME_STATE)
					System.out.println("Invalid condition : " + this.function_name + ". False condition.");
				return false;
		}
	}
	
	
	
	// Actions
	
	public void wizz(Entity e) {
		if(parameters.size() != 1 || (parameters.size() == 1 &&  !(parameters.get(0) instanceof A_DirectionParameter))) {
			e.wizz(Direction.FRONT);
		} else {
			e.wizz(((A_DirectionParameter)parameters.get(0)).value); 
		}
	}
	
	
	public void pop(Entity e) {
		if(parameters.size() != 1 || (parameters.size() == 1 &&  !(parameters.get(0) instanceof A_DirectionParameter))) {
			e.pop(Direction.FRONT);
		} else {
			e.pop(((A_DirectionParameter)parameters.get(0)).value); 
		}
	}
	
	
	public void move(Entity e) {
		if(parameters.size() != 1 || (parameters.size() == 1 &&  !(parameters.get(0) instanceof A_DirectionParameter))) {
			e.move(Direction.FRONT);
		} else {
			e.move(((A_DirectionParameter)parameters.get(0)).value); 
		}
	}
	
	
	public void jump(Entity e) {
		e.jump();
	}
	
	
	public void turn(Entity e) {
		if(parameters.size() != 1 || (parameters.size() == 1 &&  !(parameters.get(0) instanceof A_DirectionParameter))) {
//			e.move(Direction.ONTHERIGHT);
		}else {
			e.turn(((A_DirectionParameter)parameters.get(0)).value); 
		}
	}
	
	
	public void hit(Entity e) {
		if(parameters.size() != 1 || (parameters.size() == 1 &&  !(parameters.get(0) instanceof A_DirectionParameter))) {
			e.hit(Direction.FRONT);
		}else {
			e.hit(((A_DirectionParameter)parameters.get(0)).value); 
		}
	}
	
	
	public void protect(Entity e) {
		if(parameters.size() != 1 || (parameters.size() == 1 &&  !(parameters.get(0) instanceof A_DirectionParameter))) {
			e.protect(Direction.FRONT);
		} else {
			e.protect(((A_DirectionParameter)parameters.get(0)).value); ;
		}
	}
	
	
	public void pick(Entity e) {
		if(parameters.size() != 1 || (parameters.size() == 1 &&  !(parameters.get(0) instanceof A_DirectionParameter))) {
			e.pick(Direction.FRONT);
		}else {
			e.pick(((A_DirectionParameter)parameters.get(0)).value);
		}
	}
	
	
	public void throwActon(Entity e) {
		if(parameters.size() != 1 || (parameters.size() == 1 &&  !(parameters.get(0) instanceof A_DirectionParameter))) {
			e.throwAction(Direction.FRONT);
		} else {
			e.throwAction(((A_DirectionParameter)parameters.get(0)).value);
		}
	}
	
	
	public void store(Entity e) {
		e.store();
	}
	
	
	public void get(Entity e) {
		e.getBagEntity();
	}
	
	
	public void power(Entity e) {
		e.power();
	}
	
	
	public void kamikaze(Entity e) {
		e.kamikaze();
	}
	
	
	// Conditions
	
	public boolean freeCell(Entity e){
		if(this.parameters.size() != 2 || !(this.parameters.get(0) instanceof A_DirectionParameter) || !(this.parameters.get(1) instanceof A_EntityParameter)) {
			if(Options.ECHO_GAME_STATE)
				System.out.println("Invalid parameters for freeCell function"); 
			return false;
		}
		return e.cell((((A_DirectionParameter)this.parameters.get(0)).value), (((A_EntityParameter)this.parameters.get(1)).value));
	}
	
	
	public boolean key(Entity e) {
		if(this.parameters.size() != 1 || !(this.parameters.get(0) instanceof A_KeyParameter)) {
			if(Options.ECHO_GAME_STATE)
				System.out.println("Invalid parameters for key function");
			return false;
		}
		return (((A_KeyParameter)this.parameters.get(0)).value).equals(this.controller.getKeyPressed());
	}
	
	
	public boolean direction(Entity e) {
		if(parameters.size() != 1 || (parameters.size() == 1 &&  !(parameters.get(0) instanceof A_DirectionParameter))) {
			if(Options.ECHO_GAME_STATE)
				System.out.println("Invalid arguments for the Cell condition");
			return false;
		}
		return e.sameDirection(((A_DirectionParameter)parameters.get(0)).value);
	}
	
	
	public boolean closest(Entity e) {
		// TODO
		return false;
	}
	
	
	public boolean gotPower(Entity e) {
		return e.isAlive();
	}
	
	
	public boolean gotStuff(Entity e) {
		return e.gotStuff();
	}

}
