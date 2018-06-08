package edu.ricm3.game.tomatower.entities;

import edu.ricm3.game.tomatower.entities.enums.Direction;
import edu.ricm3.game.tomatower.map.Cell;
import edu.ricm3.game.tomatower.mvc.Model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Mobs extends Living {

	public Mobs(Model c_model, BufferedImage c_sprite[], double c_scale, Cell c_cell, Direction c_direction,
			Weapon c_weapon) {
		super(c_model, true, c_sprite, c_scale, c_cell, c_direction, c_weapon, initColisions());
		// this.model.addMobs(this);
		this.hp = 20;
	}

	public static ArrayList<Class<?>> initColisions() {
		ArrayList<Class<?>> res = new ArrayList<Class<?>>();
		res.add(Mobs.class);
		res.add(Portal.class);
		return res;
	}

	public void step(long now) {
		super.step(now);

		if (now - last_action > 1000L) {
			last_action = now;
			if (this.getMap() == this.model.getMainMap()) {
				this.move(Direction.RIGHT);
			} else {
				this.move(Direction.UP);
			}

		}
	}

}
