package edu.ricm3.game.tomatower.entities;



import edu.ricm3.game.tomatower.entities.enums.Direction;
import edu.ricm3.game.tomatower.entities.enums.Kind_Weapon;
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
	private Kind_Weapon kw;


	public Weapon(Model c_model, int c_range, int c_power, Kind_Weapon kw) {
		this.model = c_model;
		this.range = c_range;
		this.power = c_power;
		this.kw = kw;
	}
	
	public int getRange() {
		return this.range;
	}
	
	public int getPower() {
		return this.power;
	}
	

	public Kind_Weapon getKindWeapon() {
		return this.kw;
	}
		

	public void hit(Living e, Direction d) {
		/*
		 * Cell cell_hit = ; cell_hit.damage(this.power);
		 */
		Cell shot = getCellToShot(e.getCell(), e.getDirection(), d ,e.getMap());
		if(shot != null)
			shot.damage(this.power);
		
	}
	
	public Cell getCellToShot(Cell origin_cell, Direction entity_direction, Direction hit_direction,  Map map) {
		int col = origin_cell.getPosition()[0];
		int row = origin_cell.getPosition()[1];
		if((hit_direction == Direction.NORTH) ||
				(entity_direction == Direction.NORTH && hit_direction == Direction.FRONT) ||
				(entity_direction == Direction.SOUTH && hit_direction == Direction.BACK ) ||
				(entity_direction == Direction.EAST && hit_direction == Direction.ONTHELEFT) ||
				(entity_direction == Direction.WEST && hit_direction == Direction.ONTHERIGHT)) {
			return map.getCell(col, row -1);
		} else if((hit_direction == Direction.SOUTH) ||
				(entity_direction == Direction.NORTH && hit_direction == Direction.BACK) ||
				(entity_direction == Direction.SOUTH && hit_direction == Direction.FRONT ) ||
				(entity_direction == Direction.EAST && hit_direction == Direction.ONTHERIGHT) ||
				(entity_direction == Direction.WEST && hit_direction == Direction.ONTHELEFT)) {
			return map.getCell(col, row +1);
		} else if((hit_direction == Direction.EAST) ||
				(entity_direction == Direction.NORTH && hit_direction == Direction.ONTHERIGHT) ||
				(entity_direction == Direction.SOUTH && hit_direction == Direction.ONTHELEFT ) ||
				(entity_direction == Direction.EAST && hit_direction == Direction.FRONT) ||
				(entity_direction == Direction.WEST && hit_direction == Direction.BACK)) {
			return map.getCell(col + 1, row);
		} else if((hit_direction == Direction.WEST) ||
				(entity_direction == Direction.NORTH && hit_direction == Direction.ONTHELEFT) ||
				(entity_direction == Direction.SOUTH && hit_direction == Direction.ONTHERIGHT ) ||
				(entity_direction == Direction.EAST && hit_direction == Direction.BACK) ||
				(entity_direction == Direction.WEST && hit_direction == Direction.FRONT)) {
			return map.getCell(col-1, row);
		}
		return null; 
	}

	public void upgrade() {
		this.power += 3;
	}
	

}