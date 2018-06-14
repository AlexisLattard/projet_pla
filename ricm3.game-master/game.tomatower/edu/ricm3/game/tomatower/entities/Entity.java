package edu.ricm3.game.tomatower.entities;

import edu.ricm3.game.tomatower.automaton.A_Automaton;
import edu.ricm3.game.tomatower.entities.enums.Direction;
import edu.ricm3.game.tomatower.entities.enums.Kind;
import edu.ricm3.game.tomatower.entities.enums.ObstaclesKind;
import edu.ricm3.game.tomatower.map.Cell;
import edu.ricm3.game.tomatower.map.Map;
import edu.ricm3.game.tomatower.mvc.Model;

import java.awt.Graphics;
import java.util.ArrayList;

public abstract class Entity {
	Model model;
	protected Cell cell;
	protected boolean can_move;
	protected Direction direction; // absolute direction North, south, east, west
	protected boolean visible;
	protected ArrayList<Class<?>> entities_destination_allowed; // Utile pour determiner sur quelle cases l'entité peut
																// se trouver
	protected double scale = 1;
	protected Kind kind;
	protected long last_action = 0;
	protected long action_time;

	protected A_Automaton automaton;
	protected String current_state;

	Entity(Model c_model, Boolean c_movement, double c_scale, ArrayList<Class<?>> c_collisions, A_Automaton c_automaton,
			Cell c_cell, Kind c_kind, long c_action_time, Direction c_direction) {
		this(c_model, c_movement, c_scale, c_collisions, c_automaton, c_kind, c_action_time, c_direction);
		this.visible = true;
		this.addEntityOnCell(c_cell);
	}

	Entity(Model c_model, Boolean c_movement, double c_scale, ArrayList<Class<?>> c_collisions, A_Automaton c_automaton,
			Kind c_kind, long c_action_time, Direction c_direction) {
		this.model = c_model;
		this.can_move = c_movement;
		this.scale = c_scale;
		this.entities_destination_allowed = c_collisions;
		this.visible = false;
		this.automaton = c_automaton;
		this.kind = c_kind;
		this.action_time = c_action_time;
		this.direction = c_direction;
		this.model.addEntity(this);

	}

	public abstract void paint(Graphics g);

	public void step(long now) {
		if (this.automaton != null && now - this.last_action > this.action_time) {
			if (this.automaton.step(this))
				this.last_action = now;
		}
	}

	// Actions

	public abstract void wizz(Direction d);

	public abstract void pop(Direction d);

	public void move(Direction d) {
		this.turn(d);
		if (this.can_move)
			this.addEntityOnCell(this.getCellDirection(Direction.FRONT, 1));
	}

	public void jump() {
	} // No entity can jump

	public void turn(Direction d) {
		if ((d == Direction.NORTH) || (this.direction == Direction.NORTH && d == Direction.FRONT)
				|| (this.direction == Direction.SOUTH && d == Direction.BACK)
				|| (this.direction == Direction.EAST && d == Direction.ONTHELEFT)
				|| (this.direction == Direction.WEST && d == Direction.ONTHERIGHT)) {
			this.direction = Direction.NORTH;
		} else if ((d == Direction.SOUTH) || (this.direction == Direction.NORTH && d == Direction.BACK)
				|| (this.direction == Direction.SOUTH && d == Direction.FRONT)
				|| (this.direction == Direction.EAST && d == Direction.ONTHERIGHT)
				|| (this.direction == Direction.WEST && d == Direction.ONTHELEFT)) {
			this.direction = Direction.SOUTH;
		} else if ((d == Direction.EAST) || (this.direction == Direction.NORTH && d == Direction.ONTHERIGHT)
				|| (this.direction == Direction.SOUTH && d == Direction.ONTHELEFT)
				|| (this.direction == Direction.EAST && d == Direction.FRONT)
				|| (this.direction == Direction.WEST && d == Direction.BACK)) {
			this.direction = Direction.EAST;
		} else if ((d == Direction.WEST) || (this.direction == Direction.NORTH && d == Direction.ONTHELEFT)
				|| (this.direction == Direction.SOUTH && d == Direction.ONTHERIGHT)
				|| (this.direction == Direction.EAST && d == Direction.BACK)
				|| (this.direction == Direction.WEST && d == Direction.FRONT)) {
			this.direction = Direction.WEST;
		}
	}

	public void protect(Direction d) {
	} // No enity can portect itself

	public abstract void hit(Direction d);

	public abstract void pick(Direction d);

	public abstract void throwAction(Direction d);

	public abstract void store();

	public abstract void getBagEntity();

	public abstract void power();

	public abstract void kamikaze();

	public abstract void damage(int power);

	// Conditions

	public boolean sameDirection(Direction d) {
		return this.direction.equals(d);
	}

	public abstract boolean isAlive();

	public abstract boolean gotStuff();

	public boolean cell(Direction d, Kind k) {
		Cell cell = getCellDirection(d, 1);
		if (cell != null) {
			return cell.containEntityKind(k);
		} else {
			return false;
		}
	}

	public boolean closest(Kind k, Direction d) {
		ArrayList<Entity> entities = this.model.getEntities();
		Entity closestEntity;
		int[] pos, posE = new int[2];
		boolean entityNotFound = true;
		int distanceMin = 0, distance;
		pos = this.getCell().getPosition();
		for (Entity e : entities) {
			if (e.isVisible() && e.getKind().equals(k)) {
				posE = e.getCell().getPosition();
				distance = Math.abs(pos[0] - posE[0]) + Math.abs(pos[1] - posE[1]);
				if (entityNotFound) {
					closestEntity = e;
					distanceMin = distance;
					entityNotFound = false;
				}
				if (distance < distanceMin) {
					closestEntity = e;
					distanceMin = distance;
				}
			}
		}
		if (!entityNotFound) {
			int abs = posE[0] - pos[0];
			int ord = posE[1] - pos[1];
			if (abs >= 0) {
				if (abs > Math.abs(ord)) {
					return d == Direction.EAST;
				} else {
					if (ord > 0) {
						if (Math.abs(abs) == Math.abs(ord))
							return d == Direction.SOUTH || d == Direction.EAST;
						return d == Direction.SOUTH;
					} else {
						if (Math.abs(abs) == Math.abs(ord))
							return d == Direction.NORTH || d == Direction.EAST;
						return d == Direction.NORTH;
					}
				}
			} else {
				if (Math.abs(abs) >= Math.abs(ord)) {
					return d == Direction.WEST;
				} else {
					if (ord > 0) {
						if (Math.abs(abs) == Math.abs(ord))
							return d == Direction.SOUTH || d == Direction.WEST;
						return d == Direction.SOUTH;
					} else {
						if (Math.abs(abs) == Math.abs(ord))
							return d == Direction.NORTH || d == Direction.WEST;
						return d == Direction.NORTH;
					}
				}
			}
		}
		return false;

	}

	// Getter - Setter - Attribute modification

	public boolean addEntityOnCell(Cell c) {
		if (c != null && c.getMap().freeCell(c, this)) {
			if (this.cell != null) {
				this.cell.removeEntity(this);
			}

			c.addEntity(this);
			this.cell = c;
			this.visible = true;
			return true;
		} else {
			return false;
		}
	}

	public void removeEntityFromCell() {
		this.cell.removeEntity(this);
		this.cell = null;
		this.visible = false;
	}

	public boolean isVisible() {
		return this.visible;
	}

	public boolean canMove() {
		return this.can_move;
	}

	public int[] getPosition() {
		return this.cell.getPosition();
	}

	public static ArrayList<Class<?>> initColisions() {
		return new ArrayList<Class<?>>();
	}

	public ArrayList<Class<?>> getEntitiesDestinationAllowed() {
		return this.entities_destination_allowed;
	}

	public Cell getCell() {
		return this.cell;
	}

	public Map getMap() {
		return this.cell.getMap();
	}

	public Direction getDirection() {
		return this.direction;
	}

	public Cell getCellDirection(Direction d, int range) {
		int[] current_pos = this.getPosition();
		int pos_front_cell_x = current_pos[0];
		int pos_front_cell_y = current_pos[1];

		if (d.equals(Direction.NORTH) || (direction.equals(Direction.NORTH) && d.equals(Direction.FRONT))
				|| (direction.equals(Direction.SOUTH) && d.equals(Direction.BACK))
				|| (direction.equals(Direction.EAST) && d.equals(Direction.ONTHELEFT))
				|| (direction.equals(Direction.WEST) && d.equals(Direction.ONTHERIGHT))) {
			pos_front_cell_y -= range;
		} else if ((d == Direction.SOUTH) || (direction.equals(Direction.NORTH) && d.equals(Direction.BACK))
				|| (direction.equals(Direction.SOUTH) && d.equals(Direction.FRONT))
				|| (direction.equals(Direction.EAST) && d.equals(Direction.ONTHERIGHT))
				|| (direction.equals(Direction.WEST) && d.equals(Direction.ONTHELEFT))) {
			pos_front_cell_y += range;
		} else if ((d == Direction.EAST) || (direction.equals(Direction.NORTH) && d.equals(Direction.ONTHERIGHT))
				|| (direction.equals(Direction.SOUTH) && d.equals(Direction.ONTHELEFT))
				|| (direction.equals(Direction.EAST) && d.equals(Direction.FRONT))
				|| (direction.equals(Direction.WEST) && d.equals(Direction.BACK))) {
			pos_front_cell_x += range;
		} else if ((d == Direction.WEST) || (direction.equals(Direction.NORTH) && d.equals(Direction.ONTHELEFT))
				|| (direction.equals(Direction.SOUTH) && d.equals(Direction.ONTHERIGHT))
				|| (direction.equals(Direction.EAST) && d.equals(Direction.BACK))
				|| (direction.equals(Direction.WEST) && d.equals(Direction.FRONT))) {
			pos_front_cell_x -= range;
		}

		return getMap().getCell(pos_front_cell_x, pos_front_cell_y);
	}

	public Kind getKind() {
		return this.kind;
	}

	public void removeAutomaton() {
		this.automaton = null;
	}

	public void setAutomaton(A_Automaton automaton) {
		this.automaton = automaton;
		this.current_state = automaton.getCurrentState();
	}

	public String getCurrentState() {
		return this.current_state;
	}

	public void setCurrentState(String state) {
		this.current_state = state;
	}
}