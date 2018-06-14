package edu.ricm3.game.tomatower.entities;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import edu.ricm3.game.tomatower.Options;
import edu.ricm3.game.tomatower.automaton.A_Automaton;
import edu.ricm3.game.tomatower.entities.enums.EntityName;
import edu.ricm3.game.tomatower.entities.enums.ObstaclesKind;
import edu.ricm3.game.tomatower.map.Cell;
import edu.ricm3.game.tomatower.mvc.Model;
import static edu.ricm3.game.tomatower.LevelDesign.*;

public class Product extends Buyable {

	public Product(Model c_model, BufferedImage c_sprite, double c_scale, Cell c_cell, ObstaclesKind c_kind, Weapon c_weapon, int c_price) {
		super(c_model, c_sprite, c_scale, c_cell, c_kind, c_weapon, c_price);
	}

	public void buyTower() {
		Tower tower;

		if (this.model.getPlayer().getMoney() >= this.price) {

			switch (this.weapon.getKindWeapon()) {
			case Tower_Yellow:
				tower = new Tower(this.model, this.model.getSprites().sprite_tower_yellow, this.weapon, this.model.getAutomatons().get(EntityName.Tower_Yellow));
				this.model.getPlayer().setTowerSelected(EntityName.Tower_Yellow);
				break;
			case Tower_Red:
				tower = new Tower(this.model, this.model.getSprites().sprite_tower_red, this.weapon, this.model.getAutomatons().get(EntityName.Tower_Red));
				this.model.getPlayer().setTowerSelected(EntityName.Tower_Red);
				break;
			case Tower_Blue:
				tower = new Tower(this.model, this.model.getSprites().sprite_tower_blue, this.weapon, this.model.getAutomatons().get(EntityName.Tower_Blue));
				this.model.getPlayer().setTowerSelected(EntityName.Tower_Blue);
				break;
			case Tower_Purple:
				tower = new Tower(this.model, this.model.getSprites().sprite_tower_purple, this.weapon, this.model.getAutomatons().get(EntityName.Tower_Purple));
				this.model.getPlayer().setTowerSelected(EntityName.Tower_Purple);
				break;
			default:
				tower = null;
			}

			this.model.getPlayer().addBagProduct(tower);
			this.model.getPlayer().decreaseMoney(this.price);
			if (Options.ECHO_GAME_STATE) {
				System.out.println("Achat ! Prix : " + this.model.getPlayer().getMoney());
				System.out.println("Weapon : " + this.weapon.getPower() + " " + this.weapon.getRange());
			}
			this.price += INCREASES_TOWER_PRODUCT_AMOUNT;
		}
	}

}
