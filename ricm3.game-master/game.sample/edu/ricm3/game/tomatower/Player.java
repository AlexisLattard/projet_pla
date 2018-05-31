package edu.ricm3.game.tomatower;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import com.sun.java_cup.internal.runtime.Symbol;
import com.sun.javafx.collections.MappingChange.Map;

public class Player extends Living{
	
	
	
	HashMap<Tower, Integer> bag;


	public Player(Model c_model, Boolean c_movment, BufferedImage c_sprite, double c_scale, int cell_x, int cell_y) {
		super(c_model, c_movment, c_sprite, c_scale, cell_x, cell_y);
	}

	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}
	
	
}
