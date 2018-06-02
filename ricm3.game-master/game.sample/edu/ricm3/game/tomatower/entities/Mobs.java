package edu.ricm3.game.tomatower.entities;

import edu.ricm3.game.tomatower.Cell;
import edu.ricm3.game.tomatower.mvc.Model;

import java.awt.image.BufferedImage;

public class Mobs extends Living {

	public Mobs(Model c_model, Boolean c_movment, BufferedImage c_sprite, double c_scale, Cell c_cell, Direction c_direction) {
		super(c_model, c_movment, c_sprite, c_scale, c_cell, c_direction);
		//this.model.addMobs(this);
	}

	

}
