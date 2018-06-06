package edu.ricm3.game.tomatower.entities;

import java.awt.image.BufferedImage;

import edu.ricm3.game.tomatower.Options;
import edu.ricm3.game.tomatower.entities.enums.ObstaclesKind;
import edu.ricm3.game.tomatower.map.Cell;
import edu.ricm3.game.tomatower.map.Map;
import edu.ricm3.game.tomatower.mvc.Model;

public abstract class Buyable extends InertAction {

	Weapon weapon;
	int price;

	public Buyable(Model c_model, BufferedImage c_sprite, double c_scale, Cell c_cell, ObstaclesKind c_kind,
			Weapon c_weapon, int c_price, Map c_map) {
		super(c_model, false, c_sprite, c_scale, c_cell, c_kind, c_map);
		this.weapon = c_weapon;
		this.price = c_price;
	}

	@Override
	public void step(long now) {
		super.step(now);

		// Le téléporteur peut de nouveau être activé si le joueur n'est pas dessus et
		// que le joueur est sur la map de jeu
		if (!canActive && this.cell.getEntities().size() == 1) {
			this.canActive = true;
		}
	}

}
