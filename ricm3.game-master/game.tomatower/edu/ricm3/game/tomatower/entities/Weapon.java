package edu.ricm3.game.tomatower.entities;



import edu.ricm3.game.tomatower.entities.enums.Direction;
import edu.ricm3.game.tomatower.entities.enums.EntityName;
import edu.ricm3.game.tomatower.map.Cell;
import edu.ricm3.game.tomatower.mvc.Model;

/*
IT IS NOT AN ENTITY
 */

public class Weapon {
	Model model;
	private int range;
	private int power;
	private EntityName kw;


	public Weapon(Model c_model, int c_power, int c_range, EntityName kw) {
		this.model = c_model;
		this.range = c_range;
		this.power = c_power;
		this.kw = kw;
	}
		

	public void hit(Living e, Direction d) {

		Cell shot = getCellToShot(e, d);

		if(shot != null)
			shot.damage(this.power);
		
	}
	
	public Cell getCellToShot(Entity e, Direction hit_direction) {
		return e.getCellDirection(hit_direction, this.range);
	}

	public void upgrade() {
		this.power += 3;
	}
	
	public int getRange() {
		return this.range;
	}
	
	public int getPower() {
		return this.power;
	}
	

	public EntityName getKindWeapon() {
		return this.kw;
	}
	

}