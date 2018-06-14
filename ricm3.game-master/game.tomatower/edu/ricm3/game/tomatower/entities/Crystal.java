package edu.ricm3.game.tomatower.entities;

import edu.ricm3.game.tomatower.entities.enums.Direction;
import edu.ricm3.game.tomatower.entities.enums.Kind;
import edu.ricm3.game.tomatower.map.Cell;
import edu.ricm3.game.tomatower.mvc.Model;

import java.awt.*;
import java.awt.image.BufferedImage;

import static edu.ricm3.game.tomatower.LevelDesign.*;

public class Crystal extends Living {

	private Crystal main_instance;

	public Crystal(Model c_model, BufferedImage c_sprite[], double c_scale, Cell c_cell, Crystal c_main_instance) {
		super(c_model, false, c_sprite, c_scale, c_cell, null, initColisions(), null, Kind.Danger, 0, MAX_LIFE_CRYSTAL);
		this.main_instance = c_main_instance;

	}

	@Override
	public void pop(Direction d) {

	}

	@Override
	public void wizz(Direction d) {

	}

	@Override
	public void damage(int power) {
		if (this.main_instance == null) {
			this.hp -= power;
		} else {
			this.main_instance.damage(power);
		}
	}

	@Override
	public void paint(Graphics g) {
		if (main_instance == null) {
			super.paint(g);
		}
	}
}