package edu.ricm3.game.tomatower.entities;

import edu.ricm3.game.tomatower.entities.enums.Direction;
import edu.ricm3.game.tomatower.map.Cell;
import edu.ricm3.game.tomatower.mvc.Model;
import sun.util.logging.resources.logging_de;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.sun.org.apache.xerces.internal.impl.xpath.XPath.Step;

public class Mobs extends Living {
	
	

	public Mobs(Model c_model, BufferedImage c_sprite[], double c_scale, Cell c_cell, Direction c_direction, Weapon c_weapon) {
		super(c_model, true, c_sprite, c_scale, c_cell, c_direction,c_weapon,initColisions());
		//this.model.addMobs(this);
	}
	
	public static ArrayList<Class<?>> initColisions() {
		ArrayList<Class<?>> res = new ArrayList<Class<?>>();
		res.add(Mobs.class);
		return res;
	}
	
	public void step(long now) {
    	if(now- last_action > 1000L) {
			this.move(Direction.RIGHT);
			last_action = now;
		}
	}
	

}
