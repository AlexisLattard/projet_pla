package edu.ricm3.game.tomatower.entities;

import edu.ricm3.game.tomatower.entities.enums.Direction;
import edu.ricm3.game.tomatower.map.Cell;
import edu.ricm3.game.tomatower.mvc.Model;

import java.awt.image.BufferedImage;

public class Mobs extends Living {

	public Mobs(Model c_model, BufferedImage c_sprite, double c_scale, Cell c_cell, Direction c_direction, Weapon c_weapon) {
		super(c_model, true, c_sprite, c_scale, c_cell, c_direction,c_weapon);
		//this.model.addMobs(this);
	}

	

}