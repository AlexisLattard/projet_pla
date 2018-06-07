package edu.ricm3.game.tomatower.entities;

import edu.ricm3.game.tomatower.entities.enums.Direction;
import edu.ricm3.game.tomatower.map.Cell;
import edu.ricm3.game.tomatower.mvc.Model;

/*
IT IS NOT AN ENTITY
 */

public class Weapon {
	Model model;
	private int range;
	private int power;
	private Direction direction; // la direction est relative par rapport


	public Weapon(Model c_model, int c_range, int c_power, Direction c_direction) {
		this.model = c_model;
		this.range = c_range;
		this.power = c_power;
		this.direction = c_direction;
	}
	
	public int getRange() {
		return this.range;
	}
	
	public int getPower() {
		return this.power;
	}
		

	public void hit(Cell origin_cell, Direction origin_direction) {
		/*
		 * Cell cell_hit = ; cell_hit.damage(this.power);
		 */
	}

	public void upgrade() {
		this.power += 3;
	}

}