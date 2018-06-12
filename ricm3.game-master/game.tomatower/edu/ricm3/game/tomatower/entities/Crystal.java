package edu.ricm3.game.tomatower.entities;

import edu.ricm3.game.tomatower.entities.enums.Direction;
import edu.ricm3.game.tomatower.entities.enums.Kind;
import edu.ricm3.game.tomatower.entities.enums.ObstaclesKind;
import edu.ricm3.game.tomatower.map.Cell;
import edu.ricm3.game.tomatower.mvc.Model;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Crystal extends Living {

	private Crystal main_instance;


	public Crystal(Model c_model, BufferedImage c_sprite[], double c_scale, Cell c_cell, Crystal c_main_instance) {
		super(c_model, false, c_sprite, c_scale, c_cell, Direction.NORTH,null,initColisions(),null, Kind.Danger);
		this.main_instance = c_main_instance;
		this.hp = 550;
		this.MAX_LIFE = 1000;

	}

	@Override
	public void pop(){
		
	}
	
	@Override
	public void wizz(){
		
	}
	
	@Override
	public void damage(int power) {
		if (main_instance == null)
			this.hp -= power;
		else
			main_instance.damage(power);
		System.out.println(this.hp);
	}

	@Override
	public void paint(Graphics g) {
		if (main_instance == null)
			super.paint(g);
	}
}