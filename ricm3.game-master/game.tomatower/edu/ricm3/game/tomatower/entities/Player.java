package edu.ricm3.game.tomatower.entities;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

import edu.ricm3.game.tomatower.automaton.A_Automaton;
import edu.ricm3.game.tomatower.entities.enums.Direction;
import edu.ricm3.game.tomatower.entities.enums.Kind;
import edu.ricm3.game.tomatower.entities.enums.Kind_Weapon;
import edu.ricm3.game.tomatower.map.Cell;
import edu.ricm3.game.tomatower.mvc.Model;

public class Player extends Living {

	private int money = 25000000;
	

	public Player(Model c_model, BufferedImage c_sprite[], double c_scale, Cell c_cell, Direction c_direction, Weapon c_weapon, A_Automaton c_automaton) {
		super(c_model, true, c_sprite, c_scale, c_cell, c_direction, c_weapon, initColisions(), c_automaton, Kind.Team);
		
		// TODO : Mettre dans le constructeur d'entity
		this.canTake = true;

		// TEST
		this.MAX_LIFE = 150;
		this.hp = MAX_LIFE;
		this.action_time = 150L;
		
	}

	
	// Actions
	
	@Override
	public void pop() {
		if(this.model.getCurrentMap().equals(this.model.getStoreMap())){
			this.model.getPlayer().pick(Direction.FRONT);
			this.model.getPlayer().store();
		}		
	}
	
	@Override
	public void wizz() {
		// TODO
	}
	
	@Override
	public void kamikaze() {
		super.kamikaze();
		this.model.setCurrentMap(this.model.getMainMap()); // Quand le joueur meurt, on regarde les monstres massacrer notre cristal sur la map principale
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
	}
	
	
	
	public HashMap<Kind_Weapon, Integer> getBagNumberTower() {

		HashMap<Kind_Weapon, Integer> numbertowers = new HashMap<>();
		
		for (Kind_Weapon kw : Kind_Weapon.values()) {
			numbertowers.put(kw, 0);
		}

		for (Tower t : this.bag) {
			switch (t.getWeapon().getKindWeapon()) {
				case Yellow:
					numbertowers.put(Kind_Weapon.Yellow, numbertowers.get(Kind_Weapon.Yellow) + 1);
					break;
				case Red:
					numbertowers.put(Kind_Weapon.Red, numbertowers.get(Kind_Weapon.Red) + 1);
					break;
				case Blue:
					numbertowers.put(Kind_Weapon.Blue, numbertowers.get(Kind_Weapon.Blue) + 1);
					break;
				case Purple:
					numbertowers.put(Kind_Weapon.Purple, numbertowers.get(Kind_Weapon.Purple) + 1);
					break;
			}
		}
		
		return numbertowers;
	}
	
	public static ArrayList<Class<?>> initColisions() {
		ArrayList<Class<?>> res = new ArrayList<Class<?>>();
		res.add(Portal.class);
//		res.add(Product.class);
//		res.add(Upgrade.class);
		return res;
	}
	
	


	
}