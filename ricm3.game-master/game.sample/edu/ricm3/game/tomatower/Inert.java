package edu.ricm3.game.tomatower;

import java.awt.image.BufferedImage;

public class Inert extends Entity{
	
	ObstaclesKind obstacles_kind;
	
	BufferedImage[] sprite_lac;
	
	public Inert(Model c_model, Boolean c_movment, BufferedImage c_sprite, double c_scale, int cell_x, int cell_y, ObstaclesKind kind) {
		super(c_model, c_movment, c_sprite, c_scale, cell_x, cell_y);
		this.obstacles_kind = kind;
	}
	
	
	
	public void splitLacSprite() {
		/*
		 * 0 : up left
		 * 1 : up right
		 * 2 : up
		 * 3 : down left
		 * 4 : down right
		 * 5 : down
		 */
		int sub_image_size = 32;
		
	}

}
