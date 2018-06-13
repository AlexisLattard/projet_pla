package edu.ricm3.game.tomatower.entities;

import edu.ricm3.game.tomatower.automaton.A_Automaton;
import edu.ricm3.game.tomatower.entities.enums.Direction;
import edu.ricm3.game.tomatower.entities.enums.Kind;
import edu.ricm3.game.tomatower.map.Cell;
import edu.ricm3.game.tomatower.mvc.Model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Mobs extends Living {

	public Mobs(Model c_model, BufferedImage c_sprite[], double c_scale, Cell c_cell, Direction c_direction,
			Weapon c_weapon, A_Automaton c_automaton) {
		super(c_model, true, c_sprite, c_scale, c_cell, c_direction, c_weapon, initColisions(), c_automaton,
				Kind.Ennemis);
		this.canTake = false;

		if (this.automaton == null)
			System.out.println("ULLL");

		this.hp = 20;
		this.action_time = 500L;
	}

	@Override
	public void pop() {
		// TODO
		if(this.getMap().equals(this.model.getMainMap())) {
			this.getCellDirection(Direction.NORTH, 1).damage(50);
			this.getCellDirection(Direction.SOUTH, 1).damage(50);
			this.getCellDirection(Direction.EAST, 1).damage(50);
			this.getCellDirection(Direction.WEST, 1).damage(50);
			kamikaze();
		}
		
	}

	@Override
	public void wizz() {
		
	}

	public static ArrayList<Class<?>> initColisions() {
		ArrayList<Class<?>> res = new ArrayList<Class<?>>();
		res.add(Mobs.class);
		res.add(Portal.class);
		return res;
	}

}
