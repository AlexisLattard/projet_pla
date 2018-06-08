package edu.ricm3.game.tomatower.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

import edu.ricm3.game.tomatower.Options;
import edu.ricm3.game.tomatower.entities.enums.Direction;
import edu.ricm3.game.tomatower.entities.enums.Kind_Weapon;
import edu.ricm3.game.tomatower.map.Cell;
import edu.ricm3.game.tomatower.mvc.Model;

public class Player extends Living {

	private ArrayList<Tower> bag;
	private Tower hand = null;
	private int money = 25000000;
	public final int MAX_LIFE = 150;

	public Player(Model c_model, BufferedImage c_sprite[], double c_scale, Cell c_cell, Direction c_direction,
			Weapon c_weapon) {
		super(c_model, true, c_sprite, c_scale, c_cell, c_direction, c_weapon, initColisions());
		bag = new ArrayList<>();

		// TEST
		this.hp = 140;
	}

	public static ArrayList<Class<?>> initColisions() {
		ArrayList<Class<?>> res = new ArrayList<Class<?>>();
		res.add(Portal.class);
		res.add(Product.class);
		res.add(Upgrade.class);
		return res;
	}

	public void getBagEntity() {
		if (this.bag.size() >= 1) {
			if (hand == null) {
				hand = this.bag.remove(0);
			}
		}
	}

	public void throwAction() {
		if (Options.ECHO_GAME_STATE && hand == null)
			System.out.println("Rien dans la main");

		if (hand != null && hand.addEntityOnCell(this.getFrontCell())) {
			// Si vrai, alors la tourelle a été posée, donc plus rien en main			
			hand = null;
		}
	}

	public void pick() {
		Entity entity = this.getMap().getEntityCell(this.getFrontCell());

		if (entity instanceof Tower) {
			if (hand != null) // On a déjà quelque chose en main, on le remet dans le sac
				this.bag.add(hand);
			entity.removeEntityFromCell();
			hand = (Tower) (entity);
		}
	}

	public void store() {
		if (hand != null) {
			bag.add(hand);
			hand = null;
		}
	}
    public Tower getHand() {
    	return this.hand;
    }

	public void addBagProduct(Tower tower) {
		if (tower instanceof Tower) {
			this.bag.add(tower);
		}
	}

	public int getMoney() {
		return this.money;
	}

	public void decreaseMoney(int money) {
		this.money -= money;
	}

	public HashMap<Kind_Weapon, Integer> getBagNumberTower() {

		HashMap<Kind_Weapon, Integer> numbertowers = new HashMap<>();
		
		for (Kind_Weapon kw : Kind_Weapon.values()) {
			numbertowers.put(kw, 0);
		}

		for (Tower t : bag) {
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

	public void increaseMoney(int money) {
		this.money += money;
	}
}