package edu.ricm3.game.tomatower.entities;

import edu.ricm3.game.tomatower.entities.enums.Direction;
import edu.ricm3.game.tomatower.mvc.Model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Tower extends Living {

	int idx;

	public Tower(Model c_model, BufferedImage c_sprite[], Weapon c_weapon) {
		super(c_model, false, c_sprite, 1, Direction.LEFT, c_weapon, initColisions());
		// this.model.addTower(this);
		this.hp = 50;
	}

	// On pourrai simplement faire appelle à celle défini dans Entity, mais c'est
	// plus clair d'indiquer clairement la gestion de colision aussi ici
	public static ArrayList<Class<?>> initColisions() {
		return new ArrayList<Class<?>>();
	}

	@Override
	public void paint(Graphics g) {
		if (this.isVisible()) {
			int d = (int) (this.getMap().getCellSize() * scale);
			int[] pos = this.getPosition();
			int x = pos[0] * model.getCurrentMap().getCellSize();
			int y = pos[1] * model.getCurrentMap().getCellSize();
			g.drawImage(sprite[idx], x, y, d, d, null);
		}
	}

	public void step(long now) {
		super.step(now);
		if (now - last_action > 250L) {
			last_action = now;
			idx = (idx + 1) % this.sprite.length;
		}
	}

}