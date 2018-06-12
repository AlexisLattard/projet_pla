package edu.ricm3.game.tomatower.entities;

import edu.ricm3.game.tomatower.automaton.A_Automaton;
import edu.ricm3.game.tomatower.entities.enums.Direction;
import edu.ricm3.game.tomatower.entities.enums.Kind;
import edu.ricm3.game.tomatower.map.Cell;
import edu.ricm3.game.tomatower.mvc.Model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class Mobs extends Living {
	
	

	public Mobs(Model c_model, BufferedImage c_sprite[], double c_scale, Cell c_cell, Direction c_direction, Weapon c_weapon, A_Automaton c_automaton) {
		super(c_model, true, c_sprite, c_scale, c_cell, c_direction,c_weapon,initColisions(), c_automaton, Kind.Ennemis);
		this.canTake = false;
				
		if(this.automaton == null)
			System.out.println("ULLL");
		
		this.hp = 20;
		this.action_time = 100L;
	}
	
	
	@Override
	public void pop() {
		// TODO
		System.out.println("POP");
	}
	
	
	@Override
	public void wizz() {
		// TODO
	}
	
	
	public static ArrayList<Class<?>> initColisions() {
		ArrayList<Class<?>> res = new ArrayList<Class<?>>();
		res.add(Mobs.class);
		res.add(Portal.class);
		res.add(MobSpawn.class);
		return res;
	}
	
	

}
