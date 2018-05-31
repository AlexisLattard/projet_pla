package edu.ricm3.game.tomatower;

import java.awt.image.BufferedImage;

public class Living extends Entity {
	
	int hp;
	int speed_x;
	int speed_y;
	Weapon weapon;
	
	public Living(Model c_model, Boolean c_movment, BufferedImage c_sprite, double c_scale, int cell_x, int cell_y) {
		super(c_model, c_movment, c_sprite, c_scale, cell_x, cell_y);
	}
	
	
	
}
