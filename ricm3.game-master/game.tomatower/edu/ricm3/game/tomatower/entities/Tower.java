package edu.ricm3.game.tomatower.entities;

import edu.ricm3.game.tomatower.entities.enums.Direction;
import edu.ricm3.game.tomatower.map.Cell;
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

	public boolean enemyInRange(Entity[] enemies) {
		Cell c_cell = this.getCell();
		int c_abscisse = c_cell.getPosition()[0];
		int c_ordonnee = c_cell.getPosition()[1];
		int range = this.weapon.getRange();

		int x = 0;
		for (int i =  c_abscisse - range; i <= c_abscisse; i++) {
			for (int j = c_ordonnee - x; j <c_ordonnee + x; j++) {
				for (int ent = 0; ent < enemies.length; ent++) {
					if (this.model.getCurrentMap().getCell(j, i).getEntities().contains(enemies[ent])) {
						return true;
					}
				}
				x++;
			}
		}
		for (int i = c_abscisse + 1; i < c_abscisse + range; i++) {
			for (int j = c_ordonnee - x; j <c_ordonnee + x; j++) {
				for (int ent = 0; ent < enemies.length; ent++) {
					if (this.model.getCurrentMap().getCell(j, i).getEntities().contains(enemies[ent])) {
						return true;
					}
				}
				x--;
			}
		}

		return false;
	}

}