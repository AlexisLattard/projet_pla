package edu.ricm3.game.tomatower.entities;

import edu.ricm3.game.tomatower.entities.enums.Direction;
import edu.ricm3.game.tomatower.map.Cell;
import edu.ricm3.game.tomatower.map.Map;
import edu.ricm3.game.tomatower.mvc.Model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Tower extends Living {

	// public Tower(Model c_model, BufferedImage c_sprite, double c_scale, Cell
	// c_cell,Weapon c_weapon) {
	// super(c_model, false, c_sprite, c_scale, c_cell, Direction.LEFT,c_weapon);
	// this.model.addTower(this);
	// }

	public Tower(Model c_model, BufferedImage c_sprite[], Weapon c_weapon) {
		super(c_model, false, c_sprite, 1, Direction.LEFT, c_weapon, initColisions());
		// this.model.addTower(this);
	}

	// On pourrait simplement faire appel à celle définie dans Entity, mais c'est
	// plus clair d'indiquer clairement la gestion de collision aussi ici
	public static ArrayList<Class<?>> initColisions() {
		return new ArrayList<Class<?>>();
	}

	public void step(long now) {
		super.step(now);
	}

	/**
	 * Fonction pour tester si un ennemi est dans la range de la tour, et ce dans
	 * toutes les directions
	 * 
	 * @param enemies
	 * @return true s'il y en a un, false sinon
	 */
	public boolean enemyInRange(Entity enemy, Direction dir) {
		Cell c_cell = this.getCell();
		int c_abscisse = c_cell.getPosition()[0];
		int c_ordonnee = c_cell.getPosition()[1];
		int range = this.weapon.getRange();
		Map map = this.model.getCurrentMap();
		int abs = 0, ord = 0;
		boolean enemyNotFound = true;
		for (int k = 1; k <= range && enemyNotFound; k++) {
			for (abs = k, ord = 0; abs > 0 && enemyNotFound; abs--, ord++) {
				if (map.getCell(c_abscisse + abs, c_ordonnee + ord).getEntities().contains(enemy)) {
					enemyNotFound = false;
				}
			}
			for (; ord > 0 && enemyNotFound; abs--, ord--) {
				if (map.getCell(c_abscisse + abs, c_ordonnee + ord).getEntities().contains(enemy)) {
					enemyNotFound = false;
				}
			}
			for (; abs < 0 && enemyNotFound; abs++, ord--) {
				if (map.getCell(c_abscisse + abs, c_ordonnee + ord).getEntities().contains(enemy)) {
					enemyNotFound = false;
				}
			}
			for (; ord < 0 && enemyNotFound; abs++, ord++) {
				if (map.getCell(c_abscisse + abs, c_ordonnee + ord).getEntities().contains(enemy)) {
					enemyNotFound = false;
				}
			}
		}
		if (!enemyNotFound) {
			if (abs >= 0) {
				if (abs >= Math.abs(ord)) {
					return dir == Direction.RIGHT;
				} else {
					if (ord > 0)
						return dir == Direction.UP;
					else
						return dir == Direction.DOWN;
				}
			} else {
				if (Math.abs(abs) >= Math.abs(ord)) {
					return dir == Direction.LEFT;
				} else {
					if (ord > 0)
						return dir == Direction.UP;
					else
						return dir == Direction.DOWN;
				}
			}
		}
		return false;
	}

	/**
	 * Fonction pour tester si un ennemi est dans la range de la tour en ligne
	 * droite dans la direcion donnée
	 * 
	 * @param direction
	 * @param enemies
	 * @return true s'il y en a un, false sinon
	 */
	public boolean Closest(Direction c_direction, Entity enemy) {
		int c_abscisse = this.getCell().getPosition()[0];
		int c_ordonnee = this.getCell().getPosition()[1];
		int range = this.weapon.getRange();
		int direction = c_direction.getValue();
		Map c_map = this.model.getCurrentMap();

		switch (direction) {
		case 0:
			for (int i = c_ordonnee; i < c_ordonnee - range; i--) {
				ArrayList<Entity> tabEntites = c_map.getCell(c_abscisse, i).getEntities();
				if (tabEntites.contains(enemy)) {
					return true;
				}
			}
			break;

		case 1:
			for (int i = c_ordonnee; i < c_ordonnee + range; i++) {
				ArrayList<Entity> tabEntites = c_map.getCell(c_abscisse, i).getEntities();
				if (tabEntites.contains(enemy)) {
					return true;
				}
			}
			break;

		case 2:
			for (int i = c_abscisse; i < c_abscisse + range; i++) {
				ArrayList<Entity> tabEntites = c_map.getCell(i, c_ordonnee).getEntities();
				if (tabEntites.contains(enemy)) {
					return true;
				}
			}
			break;

		case 3:
			for (int i = c_abscisse; i < c_abscisse - range; i--) {
				ArrayList<Entity> tabEntites = c_map.getCell(i, c_ordonnee).getEntities();
				if (tabEntites.contains(enemy)) {
					return true;
				}
			}
			break;

		default:
			break;
		}

		return false;
	}

}