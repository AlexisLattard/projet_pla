package edu.ricm3.game.tomatower.entities;

import java.awt.image.BufferedImage;

import edu.ricm3.game.tomatower.Options;
import edu.ricm3.game.tomatower.entities.enums.ObstaclesKind;
import edu.ricm3.game.tomatower.map.Cell;
import edu.ricm3.game.tomatower.mvc.Model;

public class Upgrade extends InertAction {

	Weapon weapon;
	int price;

	public Upgrade(Model c_model, BufferedImage c_sprite, double c_scale, Cell c_cell,
			ObstaclesKind c_kind,Weapon c_weapon) {
		super(c_model, false, c_sprite, c_scale, c_cell, c_kind);
		this.weapon = c_weapon;
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
			if(Options.ECHO_GAME_STATE) {
				System.out.println("Achat ! Prix : "+this.model.getPlayer().getMoney());
				System.out.println("Weapon : "+this.weapon.getPower()+" "+this.weapon.getRange());
			}
			this.price += 100;
		}

	}

}
