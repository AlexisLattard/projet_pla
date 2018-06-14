package edu.ricm3.game.tomatower.entities;

import edu.ricm3.game.tomatower.automaton.A_Automaton;

import edu.ricm3.game.tomatower.entities.enums.Direction;
import edu.ricm3.game.tomatower.entities.enums.Kind;
import edu.ricm3.game.tomatower.map.Map;
import edu.ricm3.game.tomatower.mvc.Model;
import static edu.ricm3.game.tomatower.LevelDesign.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Tower extends Living {

	private int idx;
	private long last_sprite;

	public Tower(Model c_model, BufferedImage c_sprite[], Weapon c_weapon, A_Automaton c_automaton) {
		super(c_model, false, c_sprite, 1, c_weapon, initColisions(), c_automaton, Kind.Team, ACTION_TIME_TOWER,
				MAX_LIFE_TOWER);
		this.canTakeEntity = false;

	}

	// Actions

	@Override
	public void pop(Direction d) {
		System.out.println("POP " + this.hp + " " + this.max_life);

		if (this.hp < this.max_life / 5) { // Si il lui reste 1/5 de sa vie et qu'il fait un pop, il s'explose
			circleAttack(DAMAGE_DESTRUCTION_TOWER);
			kamikaze();
		}
	}

	@Override
	public void wizz(Direction d) {
		Direction[] dir = Direction.values();
		for (Direction direction : dir) {
			System.out.println(direction);
		}
		for (int i = 0; i < 4; i++) {
			if (this.cell(dir[i], Kind.Ennemis)) {
				for (Entity e : this.getCellDirection(dir[i], 1).getEntities()) {
					if (e.getKind().equals(Kind.Ennemis))
						e.wizz(dir[i]);
				}
			}
		}
	}

	public void step(long now) {
		super.step(now);
		if (now - last_sprite > 250L) {
			last_sprite = now;
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

	/**
	 * Fonction pour tester si un ennemi est dans la range de la tour en ligne
	 * droite dans la direcion donnée
	 * 
	 * @param direction
	 * @param enemies
	 * @return true s'il y en a un, false sinon
	 */
	public boolean closest(Kind k, Direction d) {
		int c_abscisse = this.getCell().getPosition()[0];
		int c_ordonnee = this.getCell().getPosition()[1];
		int range = this.weapon.getRange();
		Map c_map = this.model.getCurrentMap();
		ArrayList<Entity> tabEntities;

		switch (d) {
		case NORTH:
			tabEntities = c_map.getCell(c_abscisse, c_ordonnee - range).getEntities();
			for (Entity e : tabEntities) {
				if (e.getKind().equals(k)) {
					return true;
				}
			}
			break;

		case SOUTH:
			tabEntities = c_map.getCell(c_abscisse, c_ordonnee + range).getEntities();
			for (Entity e : tabEntities) {
				if (e.getKind().equals(k)) {
					return true;
				}
			}
			break;

		case EAST:
			tabEntities = c_map.getCell(c_abscisse + range, c_ordonnee).getEntities();
			for (Entity e : tabEntities) {
				if (e.getKind().equals(k)) {
					return true;
				}
			}
			break;

		case WEST:
			tabEntities = c_map.getCell(c_abscisse - range, c_ordonnee).getEntities();
			for (Entity e : tabEntities) {
				if (e.getKind().equals(k)) {
					return true;
				}
			}
			break;

		default:
			break;
		}
		return false;
	}
}