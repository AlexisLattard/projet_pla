package edu.ricm3.game.tomatower.entities;



import edu.ricm3.game.tomatower.entities.enums.Direction;
import edu.ricm3.game.tomatower.map.Cell;
import edu.ricm3.game.tomatower.map.Map;
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
		

	public void hit(Living e) {
		/*
		 * Cell cell_hit = ; cell_hit.damage(this.power);
		 */
		Cell shot = getCellToShot(e.getCell(), e.getDirection(),e.getMap());
		if(shot != null)
			shot.damage(this.power);
		
	}
	
	public Cell getCellToShot(Cell origin_cell, Direction origin_direction,  Map map) {
		Direction dir = this.direction;
		if((origin_direction == Direction.UP && dir == Direction.UP) || (origin_direction == Direction.RIGHT && dir == Direction.LEFT) || (origin_direction == Direction.LEFT && dir == Direction.RIGHT) || (origin_direction == Direction.DOWN && dir == Direction.DOWN)) {
			return map.getCell(origin_cell.getPosition()[0], origin_cell.getPosition()[1]-1);
		} else if((origin_direction == Direction.UP && dir == Direction.RIGHT) || (origin_direction == Direction.RIGHT && dir == Direction.UP) || (origin_direction == Direction.LEFT && dir == Direction.DOWN) || (origin_direction == Direction.DOWN && dir == Direction.LEFT)) {
			return map.getCell(origin_cell.getPosition()[0] + 1, origin_cell.getPosition()[1]);
		} else if((origin_direction == Direction.UP && dir == Direction.LEFT) || (origin_direction == Direction.RIGHT && dir == Direction.DOWN) || (origin_direction == Direction.LEFT && dir == Direction.UP) || (origin_direction == Direction.DOWN && dir == Direction.RIGHT)) {
			return map.getCell(origin_cell.getPosition()[0] - 1, origin_cell.getPosition()[1]);
		} else if((origin_direction == Direction.UP && dir == Direction.DOWN) || (origin_direction == Direction.RIGHT && dir == Direction.RIGHT) || (origin_direction == Direction.LEFT && dir == Direction.LEFT) || (origin_direction == Direction.DOWN && dir == Direction.DOWN)) {
			return map.getCell(origin_cell.getPosition()[0], origin_cell.getPosition()[1] + 1);
		}
		return null; 
	}

	public void upgrade() {
		this.power += 3;
	}

}