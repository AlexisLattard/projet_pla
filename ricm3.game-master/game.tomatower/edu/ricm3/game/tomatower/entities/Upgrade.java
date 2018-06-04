package edu.ricm3.game.tomatower.entities;

import java.awt.image.BufferedImage;

import edu.ricm3.game.tomatower.entities.enums.ObstaclesKind;
import edu.ricm3.game.tomatower.map.Cell;
import edu.ricm3.game.tomatower.mvc.Model;

public class Upgrade extends InertAction {

	Weapon weapon;
	int price;

	public Upgrade(Model c_model, Boolean c_movement, BufferedImage c_sprite, double c_scale, Cell c_cell,
			ObstaclesKind c_kind) {
		super(c_model, c_movement, c_sprite, c_scale, c_cell, c_kind);
		this.price = 200;
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

	public void action() {
		super.action();
		
		if(this.model.getPlayer().getMoney() >= this.price) {
			this.model.getPlayer().decreaseMoney(this.price);
			weapon.upgrade();
			this.price += 100;
		}

	}

}
