package edu.ricm3.game.tomatower.entities;

import edu.ricm3.game.tomatower.entities.enums.Kind;
import edu.ricm3.game.tomatower.entities.enums.ObstaclesKind;
import edu.ricm3.game.tomatower.map.Cell;
import edu.ricm3.game.tomatower.mvc.Model;

import java.awt.image.BufferedImage;

public class Obstacle extends Inert {

	public Obstacle(Model c_model, BufferedImage c_sprite, double c_scale, Cell c_cell, ObstaclesKind c_kindObstacle) {
		super(c_model, false, c_sprite, c_scale, c_cell, c_kindObstacle, Kind.Obstacle);
	}

	public Obstacle(Model c_model, double c_scale, Cell c_cell, ObstaclesKind c_kindObstacle, Kind c_kind) {
		super(c_model, false, c_scale, c_cell, c_kindObstacle, Kind.Obstacle);

	}
}