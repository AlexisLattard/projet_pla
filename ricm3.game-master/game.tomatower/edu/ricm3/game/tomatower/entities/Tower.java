package edu.ricm3.game.tomatower.entities;

import edu.ricm3.game.tomatower.automaton.A_Automaton;
import edu.ricm3.game.tomatower.entities.enums.Direction;
import edu.ricm3.game.tomatower.entities.enums.Kind;
import edu.ricm3.game.tomatower.mvc.Model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Tower extends Living {

	int idx;

	public Tower(Model c_model, BufferedImage c_sprite[], Weapon c_weapon, A_Automaton c_automaton) {
		super(c_model, false, c_sprite, 1, Direction.WEST, c_weapon, initColisions(), c_automaton, Kind.Team);
		this.canTake = false;
		
		//TEST
		this.MAX_LIFE = 50;
		this.hp = MAX_LIFE;
	}

	// Actions

	@Override
	public void pop() {
		System.out.println("POP " + this.hp + " "+ this.MAX_LIFE);
		
		if(this.hp < this.MAX_LIFE / 5) { // Si il lui reste 1/5 de sa vie et qu'il fait un pop, il s'explose
			this.getCellDirection(Direction.NORTH, 1).damage(50);
			this.getCellDirection(Direction.SOUTH, 1).damage(50);
			this.getCellDirection(Direction.EAST, 1).damage(50);
			this.getCellDirection(Direction.WEST, 1).damage(50);
			kamikaze();
		}
	}

	@Override
	public void wizz() {
		// TODO
	}

	public void step(long now) {
		super.step(now);
		if (now - last_action > 250L) {
			last_action = now;
			idx = (idx + 1) % this.sprite.length;
		}
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

}