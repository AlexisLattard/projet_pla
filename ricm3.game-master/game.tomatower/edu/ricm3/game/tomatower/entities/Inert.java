package edu.ricm3.game.tomatower.entities;

import edu.ricm3.game.tomatower.entities.enums.Direction;
import edu.ricm3.game.tomatower.entities.enums.Kind;
import edu.ricm3.game.tomatower.entities.enums.ObstaclesKind;
import edu.ricm3.game.tomatower.map.Cell;
import edu.ricm3.game.tomatower.mvc.Model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class Inert extends Entity {

	protected ObstaclesKind obstacles_kind;
	protected BufferedImage sprite;

	Inert(Model c_model, Boolean c_movement, BufferedImage c_sprite, double c_scale, Cell c_cell, ObstaclesKind c_kindObstacle, Kind c_kind) {
		super(c_model, c_movement, c_scale, initColisions(), null, c_cell, c_kind, 0, Direction.NONE);
		this.obstacles_kind = c_kindObstacle;
		this.sprite = c_sprite;
	}

	
	@Override
	public void paint(Graphics g) {
		if (this.isVisible()) {
			int cell_size = this.model.getCurrentMap().getCellSize();
			int[] pos = getPosition();

			int d = (int) (cell_size * this.scale);
			int x = pos[0] * cell_size;
			int y = pos[1] * cell_size;
			g.drawImage(this.sprite, x, y, d, d, null);
		}
	}

	
	// Actions
	
	@Override
	public void wizz() {}
	
	@Override
	public void pop() {}
	
	@Override
	public void turn(Direction d) {}
	
	@Override
	public void hit(Direction d) {}

	@Override
	public void pick(Direction d) {}

	@Override
	public void throwAction(Direction d) {}
	
	@Override
	public void store() {}

	@Override
	public void power() {}

	@Override
	public void getBagEntity() {}
	
	@Override
	public void kamikaze() {}
	
	@Override
	public void damage(int power) {}
	
	
	// Conditions
	
	@Override
	public boolean isAlive() {
		return true;
	}
	
	@Override
	public boolean gotStuff() {
		return false;
	}

	

	

}