package edu.ricm3.game.tomatower;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Entity {
	Model model;
	boolean movment;
	int cell_x;
	int cell_y;
	double scale = 1;
	BufferedImage sprite;
	Kind kind;
	
	
	public Entity(Model c_model, Boolean c_movment, BufferedImage c_sprite, double c_scale, int cell_x, int cell_y) {
		this.model = c_model;
		this.movment = c_movment;
		this.sprite = c_sprite;
		this.scale = c_scale;
		this.cell_x = cell_x;
		this.cell_y = cell_y;
	}
	
	public void paint(Graphics g) {
		int d = (int)(model.cell_size * scale);
        int x = (int)(cell_x * model.cell_size);
        int y = (int)(cell_y * model.cell_size);
        g.drawImage(sprite, x, y, d, d, null);

	}
	
	public void initPosition(int cell_x, int cell_y) {
		this.cell_x = cell_x;
		this.cell_y = cell_y;
	}
	
	public void move(int vertical, int horizontal) {
        if(movment) {
            if(cell_x + horizontal >= 0 && cell_x + horizontal < model.nb_cell_horizontal)
                cell_x += horizontal;
            if(cell_y + vertical >= 0 && cell_y + vertical < model.nb_cell_vertical)
                cell_y += vertical;
        }

    }
}
