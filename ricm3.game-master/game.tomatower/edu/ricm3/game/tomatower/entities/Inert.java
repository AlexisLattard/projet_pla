package edu.ricm3.game.tomatower.entities;

import edu.ricm3.game.tomatower.entities.enums.ObstaclesKind;
import edu.ricm3.game.tomatower.map.Cell;
import edu.ricm3.game.tomatower.mvc.Model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class Inert extends Entity {

	ObstaclesKind obstacles_kind;
	BufferedImage sprite;

	Inert(Model c_model, Boolean c_movement, BufferedImage c_sprite, double c_scale, Cell c_cell,
			ObstaclesKind c_kind) {
		super(c_model, c_movement, c_scale, c_cell);
		this.obstacles_kind = c_kind;
		this.sprite = c_sprite;
		// this.model.addObstacle(this);
	}

	public void paint(Graphics g) {
		if (this.isVisible()) {
			int d = (int) (model.getCurrentMap().getCellSize() * scale);
			int[] pos = this.getPosition();
			int x = pos[0] * model.getCurrentMap().getCellSize();
			int y = pos[1] * model.getCurrentMap().getCellSize();
			g.drawImage(sprite, x, y, d, d, null);
		}
	}

	public void protect() {
		return;
	}

	public void hit() {
		return;
	}

	public void pick() {
		return;
	}

	public void store() {
		return;
	}

	public void power(int power) {
		return;
	}

	public void getBagEntity() {
		return;
	}

	public void throwAction() {
		return;
	}

	public void damage(int power) {
		return;
	}

}