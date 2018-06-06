package edu.ricm3.game.tomatower.entities;

import java.awt.image.BufferedImage;

import edu.ricm3.game.tomatower.entities.enums.ObstaclesKind;
import edu.ricm3.game.tomatower.map.Cell;
import edu.ricm3.game.tomatower.map.Map;
import edu.ricm3.game.tomatower.mvc.Model;

public abstract class InertAction extends Inert {

	boolean canActive;

	InertAction(Model c_model, Boolean c_movement, BufferedImage c_sprite, double c_scale, Cell c_cell,
			ObstaclesKind c_kind, Map c_map) {
		super(c_model, c_movement, c_sprite, c_scale, c_cell, c_kind, c_map);
	}

	public void step(long now) {
		
		if (this.cell.getEntities().size() > 1) { // Player possiblement sur la case
			if (this.cell.getEntities().contains(this.model.getPlayer()) && canActive) {
				this.action(this.model.getPlayer());
			}
		}
		
	}

	public void action(Entity e) {
		this.canActive = false;
	}
}
