package edu.ricm3.game.tomatower.entities;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

import edu.ricm3.game.tomatower.automaton.A_Automaton;
import edu.ricm3.game.tomatower.entities.enums.Direction;
import edu.ricm3.game.tomatower.entities.enums.Kind;
import edu.ricm3.game.tomatower.entities.enums.EntityName;
import edu.ricm3.game.tomatower.map.Cell;
import edu.ricm3.game.tomatower.mvc.Model;
import static edu.ricm3.game.tomatower.LevelDesign.*;

public class Player extends Living {

	private int money;
	private int score;

	public Player(Model c_model, BufferedImage c_sprite[], double c_scale, Cell c_cell, Direction c_direction, Weapon c_weapon, A_Automaton c_automaton) {
		super(c_model, true, c_sprite, c_scale, c_cell, c_weapon, initColisions(), c_automaton, Kind.Team, ACTION_TIME_PLAYER, MAX_LIFE_PLAYER);

		this.canTakeEntity = true;
		this.money = MONEY_PLAYER;
		this.score = 0;
	}

	// Actions

	@Override
	public void pop() {
		if (this.model.getCurrentMap().equals(this.model.getStoreMap())) {
			Entity entity = this.getMap().getEntityCell(this.getCellDirection(Direction.FRONT, 1));

			if (entity instanceof Product) {
				((Product) entity).buyTower();
			} else if (entity instanceof Upgrade) {
				((Upgrade) entity).upgradeWeapon();
			}

		}
	}

	@Override
	public void wizz() {
		if (this.model.getCurrentMap().equals(this.model.getStoreMap())) {
			Entity entity = this.getMap().getEntityCell(this.getCellDirection(Direction.FRONT, 1));

			if (entity instanceof Upgrade) {
				System.out.println("Tentative de chg de comportement.");
				((Upgrade) entity).behaviorChangement();
			}

		}
	}

	@Override
	public void kamikaze() {
		super.kamikaze();
		this.model.setCurrentMap(this.model.getMainMap()); // Quand le joueur meurt, on regarde les monstres massacrer
															// notre cristal sur la map principale
	}

	// Getters - setters

	public int getMoney() {
		return this.money;
	}

	public void decreaseMoney(int money) {
		this.money -= money;
	}

	public void increaseMoney(int money) {
		this.money += money;
		this.score += money;
	}

	public HashMap<EntityName, Integer> getBagNumberTower() {

		HashMap<EntityName, Integer> numbertowers = new HashMap<>();

		for (EntityName kw : EntityName.values()) {
			numbertowers.put(kw, 0);
		}

		for (Tower t : this.bag) {
			switch (t.getWeapon().getKindWeapon()) {
			case Tower_Yellow:
				numbertowers.put(EntityName.Tower_Yellow, numbertowers.get(EntityName.Tower_Yellow) + 1);
				break;
			case Tower_Red:
				numbertowers.put(EntityName.Tower_Red, numbertowers.get(EntityName.Tower_Red) + 1);
				break;
			case Tower_Blue:
				numbertowers.put(EntityName.Tower_Blue, numbertowers.get(EntityName.Tower_Blue) + 1);
				break;
			case Tower_Purple:
				numbertowers.put(EntityName.Tower_Purple, numbertowers.get(EntityName.Tower_Purple) + 1);
				break;
			}
		}

		return numbertowers;
	}

	public static ArrayList<Class<?>> initColisions() {
		ArrayList<Class<?>> res = new ArrayList<Class<?>>();
		res.add(Portal.class);
		return res;
	}

}