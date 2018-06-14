package edu.ricm3.game.tomatower.entities;

import edu.ricm3.game.tomatower.automaton.A_Automaton;
import edu.ricm3.game.tomatower.entities.enums.Direction;
import edu.ricm3.game.tomatower.entities.enums.Kind;
import edu.ricm3.game.tomatower.map.Cell;
import edu.ricm3.game.tomatower.map.Map;
import edu.ricm3.game.tomatower.mvc.Model;
import static edu.ricm3.game.tomatower.LevelDesign.*;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Mobs extends Living {

	public Mobs(Model c_model, BufferedImage c_sprite[], double c_scale, Cell c_cell, Weapon c_weapon,
			A_Automaton c_automaton, int c_max_life) {
		super(c_model, true, c_sprite, c_scale, c_cell, c_weapon, initColisions(), c_automaton, Kind.Ennemis,
				ACTION_TIME_MOBS, c_max_life);
		this.canTakeEntity = false;
	}

	public Mobs(Model c_model, BufferedImage c_sprite[], double c_scale, Weapon c_weapon, A_Automaton c_automaton,
			int c_max_life) {
		super(c_model, true, c_sprite, c_scale, c_weapon, initColisions(), c_automaton, Kind.Ennemis, ACTION_TIME_MOBS,
				c_max_life);
		this.canTakeEntity = false;
	}

	@Override
	public void pop(Direction d) {
		// TODO
		if (getMap().equals(this.model.getMainMap())) {
			circleAttack(DAMAGE_DESTRUCTION_MOB);
			kamikaze();
		}

	}

	@Override
	public void wizz(Direction d) {
		if (getMap().equals(this.model.getMainMap())) {
			this.turn(d);
			if (this.cell(Direction.FRONT, Kind.Ennemis) || this.cell(Direction.FRONT, Kind.Void))
				this.move(Direction.FRONT);
			if (this.cell(Direction.FRONT, Kind.Ennemis) || this.cell(Direction.FRONT, Kind.Void))
				this.move(Direction.FRONT);
		}
	}

	public static ArrayList<Class<?>> initColisions() {
		ArrayList<Class<?>> res = new ArrayList<Class<?>>();
		res.add(Mobs.class);
		res.add(Portal.class);
		res.add(MobSpawn.class);
		return res;
	}

}
