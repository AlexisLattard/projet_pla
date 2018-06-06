package edu.ricm3.game.tomatower.entities;

import java.awt.image.BufferedImage;

import edu.ricm3.game.tomatower.Options;
import edu.ricm3.game.tomatower.entities.enums.ObstaclesKind;
import edu.ricm3.game.tomatower.map.Cell;
import edu.ricm3.game.tomatower.map.Map;
import edu.ricm3.game.tomatower.mvc.Model;

public class Product extends Buyable {
	
	public Product(Model c_model, BufferedImage c_sprite, double c_scale, Cell c_cell, ObstaclesKind c_kind,
			Weapon c_weapon, int c_price, Map c_map) {
		super(c_model, c_sprite, c_scale, c_cell, c_kind, c_weapon, c_price, c_map);
	}
	
	public void action(Entity e) {
		super.action(e);

		if (this.model.getPlayer().getMoney() >= this.price) {
			Tower tower = new Tower(this.model, this.model.getSprites().sprite_tower_red, this.weapon);
			this.model.getPlayer().addBagProduct(tower);
			this.model.getPlayer().decreaseMoney(this.price);
			if (Options.ECHO_GAME_STATE) {
				System.out.println("Achat ! Prix : " + this.model.getPlayer().getMoney());
				System.out.println("Weapon : " + this.weapon.getPower() + " " + this.weapon.getRange());
			}
			this.price += 500;
		}
	}

}
