package edu.ricm3.game.tomatower.entities;

import edu.ricm3.game.tomatower.entities.enums.Direction;
import edu.ricm3.game.tomatower.map.Cell;
import edu.ricm3.game.tomatower.mvc.Model;

/*
IT IS NOT AN ENTITY
 */

public class Weapon {
	Model model;
	int range;
	int power;
	int direction; // la direction est relative par rapport
	
	public Weapon() {
		
	}

	
	public void hit(Cell origin_cell, Direction origin_direction) {
		/*Cell cell_hit = ;
		cell_hit.damage(this.power);*/
	}
	
	public void upgrade() {
		this.power += 3;
	}
	

}