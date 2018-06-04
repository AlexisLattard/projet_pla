package edu.ricm3.game.tomatower.entities;

import edu.ricm3.game.tomatower.entities.enums.Direction;
import edu.ricm3.game.tomatower.entities.enums.Kind;
import edu.ricm3.game.tomatower.map.Cell;
import edu.ricm3.game.tomatower.mvc.Model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class Entity {
	Model model;
	protected Cell cell;
	protected boolean movement;
	double scale = 1;
	Kind kind;
	protected boolean visible;

	Entity(Model c_model, Boolean c_movement, double c_scale, Cell c_cell) {
		this.model = c_model;
		this.movement = c_movement;
		this.scale = c_scale;
		this.addEntityOnCell(c_cell);
	}

	Entity(Model c_model, Boolean c_movement, double c_scale) {
		this.model = c_model;
		this.movement = c_movement;
		this.scale = c_scale;
		this.visible = false; // Initialisation sans position = pas visible
	}

	public boolean isVisible() {
		return this.visible;
	}

	public boolean addEntityOnCell(Cell c) {

		if (model.getCurrentMap().freeCell(c)) {
			// System.out.println("PUT ENTITY : (" + c.getPosition()[0] + " " +
			// c.getPosition()[1] + ")");
			if (this.cell != null)
				this.cell.removeEntity(this);
			c.addEntity(this);
			this.cell = c;
			this.visible = true;
			return true;
		} else {
			return false;
		}
	}

	public void removeEntityFromCell() {
		// System.out.println("Set not visible");
		this.cell.removeEntity(this);
		this.cell = null;
		this.visible = false;
	}

	public void paint(Graphics g) {
	}

	public void step(long now) {
		// Comportment - Automate
	}

	public boolean canMove() {
		return this.movement;
	}

	public int[] getPosition() {
		return this.cell.getPosition();
	}

	// Actions

	public void move(Direction d) {
	}

	public void turn(Direction d) {
	}

	public void jump() {
		return;
	}

	public void protect() {
		return;
	} // protection

	public abstract void hit();

	public abstract void damage(int power);

	public abstract void pick(); // ramasser sur la map

	public abstract void throwAction(); // lancer ce que on a dans la main

	public abstract void store(); // mettre dans son sac

	public abstract void getBagEntity(); // take entity

	public abstract void power(int power); // recuperation d'energie

}