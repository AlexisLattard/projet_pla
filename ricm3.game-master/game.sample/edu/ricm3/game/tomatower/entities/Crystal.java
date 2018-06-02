package edu.ricm3.game.tomatower.entities;

import edu.ricm3.game.tomatower.mvc.Model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class Crystal {
	int hp = 100;
	BufferedImage sprite;
	Model model;
	
	int crystal_dimenstion = 4;
	int cells[][];
	
	public Crystal(Model c_model, BufferedImage c_sprite, int cell_x, int cell_y) {
		this.model = c_model;
		this.sprite = c_sprite;
		cells = new int[][]{{cell_x, cell_y}, {cell_x + 1, cell_y}, {cell_x, cell_y+1}, {cell_x+1,cell_y+1}};
	}

	
	public void paint(Graphics g) {
		int cell_size = model.getMainMap().getCellSize();
		g.drawImage(sprite, cells[0][0] * cell_size, cells[0][1] * cell_size, cell_size * 2, cell_size * 2, null);
	}

	public int[][] getPosition() {
		return cells;
	}
	
}
