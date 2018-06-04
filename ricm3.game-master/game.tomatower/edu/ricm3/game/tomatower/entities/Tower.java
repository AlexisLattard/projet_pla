package edu.ricm3.game.tomatower.entities;

import edu.ricm3.game.tomatower.entities.enums.Direction;
import edu.ricm3.game.tomatower.map.Cell;
import edu.ricm3.game.tomatower.mvc.Model;

import java.awt.image.BufferedImage;

public class Tower extends Living {

//	public Tower(Model c_model, BufferedImage c_sprite, double c_scale, Cell c_cell,Weapon c_weapon) {
//		super(c_model, false, c_sprite, c_scale, c_cell, Direction.LEFT,c_weapon);
//		//this.model.addTower(this);
//	}

	public Tower(Model c_model, BufferedImage c_sprite, Weapon c_weapon) {
		super(c_model, false, c_sprite, 1, Direction.LEFT, c_weapon);
		//this.model.addTower(this);
	}
	
	

	

}