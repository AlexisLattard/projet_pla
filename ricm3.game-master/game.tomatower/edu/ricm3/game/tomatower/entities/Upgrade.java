package edu.ricm3.game.tomatower.entities;

import java.awt.image.BufferedImage;

import edu.ricm3.game.tomatower.Options;
import edu.ricm3.game.tomatower.entities.enums.ObstaclesKind;
import edu.ricm3.game.tomatower.map.Cell;
import edu.ricm3.game.tomatower.mvc.Model;

public class Upgrade extends Buyable {

	public Upgrade(Model c_model, BufferedImage c_sprite, double c_scale, Cell c_cell, ObstaclesKind c_kind,
			Weapon c_weapon, int c_price) {
		super(c_model, c_sprite, c_scale, c_cell, c_kind, c_weapon, c_price);
	}

	public void action(Entity e) {
		super.action(e);

		if (this.model.getPlayer().getMoney() >= this.price) {
			this.model.getPlayer().decreaseMoney(this.price);
			weapon.upgrade();
			if (Options.ECHO_GAME_STATE) {
				System.out.println("Achat ! Prix : " + this.model.getPlayer().getMoney());
				System.out.println("Weapon : " + this.weapon.getPower() + " " + this.weapon.getRange());
			}
			this.price += 100;
		}
	}
}
