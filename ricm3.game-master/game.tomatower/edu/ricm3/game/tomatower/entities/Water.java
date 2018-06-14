package edu.ricm3.game.tomatower.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import edu.ricm3.game.tomatower.entities.enums.Kind;
import edu.ricm3.game.tomatower.entities.enums.ObstaclesKind;
import edu.ricm3.game.tomatower.map.Cell;
import edu.ricm3.game.tomatower.mvc.Model;

public class Water extends Obstacle {

	int idx;
	private BufferedImage animation_sprite[];

	public Water(Model c_model, BufferedImage c_sprite[], double c_scale, Cell c_cell, ObstaclesKind c_kindObstacle) {
		super(c_model, c_scale, c_cell, c_kindObstacle, Kind.Obstacle);
		animation_sprite = c_sprite;
	}

	@Override
	public void paint(Graphics g) {
		if (this.isVisible()) {
			int d = (int) (this.getMap().getCellSize() * scale);
			int[] pos = this.getPosition();
			int x = pos[0] * model.getCurrentMap().getCellSize();
			int y = pos[1] * model.getCurrentMap().getCellSize();
			g.drawImage(animation_sprite[idx], x, y, d, d, null);
		}
	}

	public void step(long now) {
		super.step(now);
		if (now - last_action > 250L) {
			last_action = now;
			idx = (idx + 1) % this.animation_sprite.length;
		}
	}

}
