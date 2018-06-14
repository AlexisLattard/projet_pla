package edu.ricm3.game.tomatower.entities;

import edu.ricm3.game.tomatower.automaton.A_Automaton;
import edu.ricm3.game.tomatower.entities.enums.Direction;
import edu.ricm3.game.tomatower.entities.enums.Kind;
import edu.ricm3.game.tomatower.map.Cell;
import edu.ricm3.game.tomatower.map.Map;
import edu.ricm3.game.tomatower.mvc.Model;
import static edu.ricm3.game.tomatower.LevelDesign.*;


import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Tower extends Living {

	// public Tower(Model c_model, BufferedImage c_sprite, double c_scale, Cell
	// c_cell,Weapon c_weapon) {
	// super(c_model, false, c_sprite, c_scale, c_cell, Direction.LEFT,c_weapon);
	// this.model.addTower(this);
	// }

	public Tower(Model c_model, BufferedImage c_sprite[], Weapon c_weapon, A_Automaton c_automaton) {
		super(c_model, false, c_sprite, 1, c_weapon, initColisions(), c_automaton, Kind.Team,ACTION_TIME_TOWER, MAX_LIFE_TOWER);
		this.canTakeEntity = false;
		
	}

	// Actions

	@Override
	public void pop() {
		System.out.println("POP " + this.hp + " "+ this.max_life);
		
		if(this.hp < this.max_life / 5) { // Si il lui reste 1/5 de sa vie et qu'il fait un pop, il s'explose
			circleAttack(DAMAGE_DESTRUCTION_TOWER);
			kamikaze();
		}
	}

	@Override
	public void wizz() {
		// TODO
	}

	// On pourrai simplement faire appelle à celle défini dans Entity, mais c'est
	// plus clair d'indiquer clairement la gestion de colision aussi ici
	public static ArrayList<Class<?>> initColisions() {
		return new ArrayList<Class<?>>();
	}

	/**
	 * Fonction pour tester si un ennemi est dans la range de la tour en ligne
	 * droite dans la direcion donnée
	 * 
	 * @param direction
	 * @param enemies
	 * @return true s'il y en a un, false sinon
	 */
	public boolean closest(Kind k, Direction d) {
		int c_abscisse = this.getCell().getPosition()[0];
		int c_ordonnee = this.getCell().getPosition()[1];
		int range = this.weapon.getRange();
		Map c_map = this.model.getCurrentMap();
		ArrayList<Entity> tabEntities;

		switch (d) {
		case SOUTH:
			tabEntities = c_map.getCell(c_abscisse, c_ordonnee - range).getEntities();
			for (Entity e : tabEntities) {
				if (e.getKind().equals(k)) {
					return true;
				}
			}
			break;

		case NORTH:
			tabEntities = c_map.getCell(c_abscisse, c_ordonnee + range).getEntities();
			for (Entity e : tabEntities) {
				if (e.getKind().equals(k)) {
					return true;
				}
			}
			break;

		case EAST:
			tabEntities = c_map.getCell(c_abscisse + range, c_ordonnee).getEntities();
			for (Entity e : tabEntities) {
				if (e.getKind().equals(k)) {
					return true;
				}
			}
			break;

		case WEST:
			tabEntities = c_map.getCell(c_abscisse - range, c_ordonnee).getEntities();
			for (Entity e : tabEntities) {
				if (e.getKind().equals(k)) {
					return true;
				}
			}
			break;

		default:
			break;
		}

		return false;
	}

	// /**
	// * Fonction pour tester si un ennemi est dans la range de la tour, et ce dans
	// * toutes les directions
	// *
	// * @param enemies
	// * @return true s'il y en a un, false sinon
	// */
	// public boolean enemyInRange(Entity enemy, Direction dir) {
	// Cell c_cell = this.getCell();
	// int c_abscisse = c_cell.getPosition()[0];
	// int c_ordonnee = c_cell.getPosition()[1];
	// int range = this.weapon.getRange();
	// Map map = this.model.getCurrentMap();
	// int abs = 0, ord = 0;
	// boolean enemyNotFound = true;
	// for (int k = 1; k <= range && enemyNotFound; k++) {
	// for (abs = k, ord = 0; abs > 0 && enemyNotFound; abs--, ord++) {
	// if (map.getCell(c_abscisse + abs, c_ordonnee +
	// ord).getEntities().contains(enemy)) {
	// enemyNotFound = false;
	// }
	// }
	// for (; ord > 0 && enemyNotFound; abs--, ord--) {
	// if (map.getCell(c_abscisse + abs, c_ordonnee +
	// ord).getEntities().contains(enemy)) {
	// enemyNotFound = false;
	// }
	// }
	// for (; abs < 0 && enemyNotFound; abs++, ord--) {
	// if (map.getCell(c_abscisse + abs, c_ordonnee +
	// ord).getEntities().contains(enemy)) {
	// enemyNotFound = false;
	// }
	// }
	// for (; ord < 0 && enemyNotFound; abs++, ord++) {
	// if (map.getCell(c_abscisse + abs, c_ordonnee +
	// ord).getEntities().contains(enemy)) {
	// enemyNotFound = false;
	// }
	// }
	// }
	// if (!enemyNotFound) {
	// if (abs >= 0) {
	// if (abs >= Math.abs(ord)) {
	// return dir == Direction.EAST;
	// } else {
	// if (ord > 0)
	// return dir == Direction.NORTH;
	// else
	// return dir == Direction.SOUTH;
	// }
	// } else {
	// if (Math.abs(abs) >= Math.abs(ord)) {
	// return dir == Direction.WEST;
	// } else {
	// if (ord > 0)
	// return dir == Direction.NORTH;
	// else
	// return dir == Direction.SOUTH;
	// }
	// }
	// }
	// return false;
	// }

	// /**
	// * Fonction pour tester si un ennemi est dans la range de la tour en ligne
	// * droite dans la direcion donnée
	// *
	// * @param direction
	// * @param enemies
	// * @return true s'il y en a un, false sinon
	// */
	// public boolean closest(Kind k, Direction d) {
	// int c_abscisse = this.getCell().getPosition()[0];
	// int c_ordonnee = this.getCell().getPosition()[1];
	// int range = this.weapon.getRange();
	// Map c_map = this.model.getCurrentMap();
	//
	// switch (d) {
	// case SOUTH:
	// for (int i = c_ordonnee; i < c_ordonnee - range; i--) {
	// ArrayList<Entity> tabEntities = c_map.getCell(c_abscisse, i).getEntities();
	// for(Entity e : tabEntities) {
	// if (e.getKind().equals(k)) {
	// return true;
	// }
	// }
	// }
	// break;
	//
	// case NORTH:
	// for (int i = c_ordonnee; i < c_ordonnee + range; i++) {
	// ArrayList<Entity> tabEntities = c_map.getCell(c_abscisse, i).getEntities();
	// for(Entity e : tabEntities) {
	// if (e.getKind().equals(k)) {
	// return true;
	// }
	// }
	// }
	// break;
	//
	// case EAST:
	// for (int i = c_abscisse; i < c_abscisse + range; i++) {
	// ArrayList<Entity> tabEntities = c_map.getCell(i, c_ordonnee).getEntities();
	// for(Entity e : tabEntities) {
	// if (e.getKind().equals(k)) {
	// return true;
	// }
	// }
	// }
	// break;
	//
	// case WEST:
	// for (int i = c_abscisse; i < c_abscisse - range; i--) {
	// ArrayList<Entity> tabEntities = c_map.getCell(i, c_ordonnee).getEntities();
	// for(Entity e : tabEntities) {
	// if (e.getKind().equals(k)) {
	// return true;
	// }
	// }
	// }
	// break;
	//
	// default:
	// break;
	// }
	//
	// return false;
	// }

}