package edu.ricm3.game.tomatower.entities;

import edu.ricm3.game.tomatower.entities.enums.Direction;
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
	 * @param direction
	 * @param enemies
	 * @return true s'il y en a un, false sinon
	 */
	// public boolean enemyInRange(Direction direction, Entity[] enemies) {
	// Cell c_cell = this.getCell();
	// int c_abscisse = c_cell.getPosition()[0];
	// int c_ordonnee = c_cell.getPosition()[1];
	// int range = this.weapon.getRange();
	//
	// int x = 0;
	// for (int i = c_abscisse - range; i <= c_abscisse; i++) {
	// for (int j = c_ordonnee - x; j <c_ordonnee + x; j++) {
	// for (int ent = 0; ent < enemies.length; ent++) {
	// if (this.model.getCurrentMap().getCell(j,
	// i).getEntities().contains(enemies[ent])) {
	// return true;
	// }
	// }
	// x++;
	// }
	// }
	// for (int i = c_abscisse + 1; i < c_abscisse + range; i++) {
	// for (int j = c_ordonnee - x; j <c_ordonnee + x; j++) {
	// for (int ent = 0; ent < enemies.length; ent++) {
	// if (this.model.getCurrentMap().getCell(j,
	// i).getEntities().contains(enemies[ent])) {
	// return true;
	// }
	// }
	// x--;
	// }
	// }
	//
	// return false;
	// }

	public boolean enemyInRange(Direction c_direction, Entity[] enemies) {
		int c_abscisse = this.getCell().getPosition()[0];
		int c_ordonnee = this.getCell().getPosition()[1];
		int range = this.weapon.getRange();
		int direction = c_direction.getValue();

		switch (direction) {
		case 0:
			for (int i = c_ordonnee; i < c_ordonnee - range; i--) {
				for (int ent = 0; ent < enemies.length; ent++) {
					if (this.model.getCurrentMap().getCell(c_abscisse, i).getEntities().contains(enemies[ent])) {
						return true;
					}
				}
			}
			break;

		case 1:
			for (int i = c_ordonnee; i < c_ordonnee + range; i++) {
				for (int ent = 0; ent < enemies.length; ent++) {
					if (this.model.getCurrentMap().getCell(c_abscisse, i).getEntities().contains(enemies[ent])) {
						return true;
					}
				}
			}
			break;

		case 2:
			for (int i = c_abscisse; i < c_abscisse + range; i++) {
				for (int ent = 0; ent < enemies.length; ent++) {
					if (this.model.getCurrentMap().getCell(i, c_ordonnee).getEntities().contains(enemies[ent])) {
						return true;
					}
				}
			}
			break;

		case 3:
			for (int i = c_abscisse; i < c_abscisse - range; i--) {
				for (int ent = 0; ent < enemies.length; ent++) {
					if (this.model.getCurrentMap().getCell(i, c_ordonnee).getEntities().contains(enemies[ent])) {
						return true;
					}
				}
			}
			break;

		default:
			break;
		}

		return false;
	}

}