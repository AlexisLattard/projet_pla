package edu.ricm3.game.tomatower;

import java.awt.image.BufferedImage;

public class Tower extends Living {

	public Tower(Model c_model, BufferedImage c_sprite, double c_scale, int cell_x, int cell_y) {
		super(c_model, false, c_sprite, c_scale, cell_x, cell_y, Direction.NONE);
		this.model.addTower(this);
	}

	public Tower(Model c_model, Boolean c_movment, BufferedImage c_sprite, double c_scale) {
		super(c_model, c_movment, c_sprite, c_scale, Direction.NONE);
		this.model.addTower(this);
	}

	

}
