package edu.ricm3.game.tomatower.entities;

import edu.ricm3.game.tomatower.Options;
import edu.ricm3.game.tomatower.entities.enums.Direction;
import edu.ricm3.game.tomatower.map.Cell;
import edu.ricm3.game.tomatower.mvc.Model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class Living extends Entity {

	protected int hp;
	protected Direction direction;
	protected Weapon weapon;
	BufferedImage sprite[];

	Living(Model c_model, Boolean c_movement, BufferedImage c_sprite[], double c_scale, Cell c_cell,
			Direction c_direction) {
		super(c_model, c_movement, c_scale, c_cell);
		this.direction = c_direction;
		this.sprite = c_sprite;
	}

	Living(Model c_model, Boolean c_movement, BufferedImage c_sprite[], double c_scale, Direction c_direction) {
		super(c_model, c_movement, c_scale);
		this.direction = c_direction;
		this.sprite = c_sprite;
	}
		this.weapon = c_weapon;

	public void paint(Graphics g) {
		if (this.isVisible()) {
			int d = (int) (model.getCurrentMap().getCellSize() * scale);
			int[] pos = this.getPosition();
			int x = pos[0] * model.getCurrentMap().getCellSize();
			int y = pos[1] * model.getCurrentMap().getCellSize();
			g.drawImage(sprite[direction.getValue()], x, y, d, d, null);
		}
	}

	public void step(long now) {

	}

	public void move(Direction d) {
		this.turn(d);

		if (this.canMove()) {
			Cell cell_destination;
			switch (d) {
			case UP:
				cell_destination = this.model.getCurrentMap().getCell(this.getPosition()[0], this.getPosition()[1] - 1);
				break;
			case RIGHT:
				cell_destination = this.model.getCurrentMap().getCell(this.getPosition()[0] + 1, this.getPosition()[1]);
				break;
			case DOWN:
				cell_destination = this.model.getCurrentMap().getCell(this.getPosition()[0], this.getPosition()[1] + 1);
				break;
			case LEFT:
				cell_destination = this.model.getCurrentMap().getCell(this.getPosition()[0] - 1, this.getPosition()[1]);
				break;
			default:
				cell_destination = null;
			}

			this.addEntityOnCell(cell_destination);

		}
		if (Options.ECHO_GAME_STATE)
			System.out.println("Player direction : " + this.direction.toString());
	}
	public void turn(Direction d) {
		this.direction = d;
	}

	public Cell getFrontCell() {
		int[] current_pos = this.getPosition();
		int pos_front_cell_x = current_pos[0];
		int pos_front_cell_y = current_pos[1];

		switch (this.direction) {
		case LEFT:
			pos_front_cell_x = current_pos[0] - 1;
			break;
		case RIGHT:
			pos_front_cell_x = current_pos[0] + 1;
			break;
		case UP:
			pos_front_cell_y = current_pos[1] - 1;
			break;
		case DOWN:
			pos_front_cell_y = current_pos[1] + 1;
			break;
		default:
			return null;
		}
		return this.model.getCurrentMap().getCell(pos_front_cell_x, pos_front_cell_y);
	}

	public boolean isAlive() {
		return hp > 0;
	}

	public void damage(int power) {
		hp -= power;
	}

	public void hit() {
		this.weapon.hit(this.cell, this.direction);
	}

	public void pick() {

	}

	public void store() {

	}

	public void getBagEntity() {

	}

	public void power(int power) {
		this.hp += power;
	}

	public void throwAction() {

	}

}