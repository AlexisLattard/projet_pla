package edu.ricm3.game.tomatower.entities;

import edu.ricm3.game.tomatower.Options;
import edu.ricm3.game.tomatower.automaton.A_Automaton;
import edu.ricm3.game.tomatower.entities.enums.Direction;
import edu.ricm3.game.tomatower.entities.enums.Kind;
import edu.ricm3.game.tomatower.entities.enums.Kind_Weapon;
import edu.ricm3.game.tomatower.map.Cell;
import edu.ricm3.game.tomatower.mvc.Model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class Living extends Entity {

	protected int hp;
	protected boolean canTake;
	protected ArrayList<Tower> bag;
	protected Tower hand = null;
	protected Weapon weapon;
	public int MAX_LIFE;
	BufferedImage sprite[];
	protected Kind_Weapon tower_selected = Kind_Weapon.Red;

	Living(Model c_model, Boolean c_movement, BufferedImage c_sprite[],
			double c_scale, Cell c_cell, Direction c_direction,
			Weapon c_weapon, ArrayList<Class<?>> c_collisions,
			A_Automaton c_automaton, Kind c_kind) {
		super(c_model, c_movement, c_scale, c_collisions, c_automaton, c_cell,
				c_kind);
		this.direction = c_direction;
		this.sprite = c_sprite;
		this.weapon = c_weapon;
		bag = new ArrayList<>();
	}

	Living(Model c_model, Boolean c_movement, BufferedImage c_sprite[],
			double c_scale, Direction c_direction, Weapon c_weapon,
			ArrayList<Class<?>> c_collisions, A_Automaton c_automaton,
			Kind c_kind) {
		super(c_model, c_movement, c_scale, c_collisions, c_automaton, c_kind);
		this.direction = c_direction;
		this.sprite = c_sprite;
		this.weapon = c_weapon;
		bag = new ArrayList<>();
	}

	@Override
	public void paint(Graphics g) {
		if (this.isVisible()) {
			int cell_size = this.model.getCurrentMap().getCellSize();
			int[] pos = this.getPosition();

			int d = (int) (cell_size * scale);
			int x = pos[0] * cell_size;
			int y = pos[1] * cell_size;
			g.drawImage(sprite[direction.getValue()], x, y, d, d, null);
		}
	}

	@Override
	public void step(long now) {
		super.step(now);

		if (this.hp <= 0) {
			this.cell.removeEntity(this);
		}
	}

	// Actions

	@Override
	public void hit(Direction d) {
		this.weapon.hit(this, d);
	}

	@Override
	public void pick(Direction d) {
		if (this.canTake) {
			Entity entity = this.getMap().getEntityCell(
					this.getCellDirection(d, 1));

			if (entity instanceof Tower) {
				if (hand != null) // On a déjà quelque chose en main, on le
									// remet dans le sac
					this.bag.add(hand);
				entity.removeEntityFromCell();
				hand = (Tower) (entity);
			} else if (entity instanceof Buyable) {
				((Buyable) entity).action();
			}
		}
	}

	@Override
	public void store() {
		if (hand != null) {
			bag.add(hand);
			hand = null;
		}
	}

	@Override
	public void getBagEntity() {
		if (this.canTake && this.bag.size() >= 1) {
			store();

			int i = 0;
			while (i < this.bag.size()
					&& !this.bag.get(i).getWeapon().getKindWeapon()
							.equals(this.tower_selected)) {
				i++;
			}

			if (i < this.bag.size()) {
				hand = this.bag.remove(i);
			}
		}
	}

	@Override
	public void power() {
		this.hp += MAX_LIFE / 10; // On recupere 1/10 de sa maximale
	}

	@Override
	public void throwAction(Direction d) {
		if (Options.ECHO_GAME_STATE && this.hand == null)
			System.out.println("Rien dans la main");

		if (this.canTake && this.hand != null
				&& this.hand.addEntityOnCell(this.getCellDirection(d, 1))) {
			// Si vrai, alors la tourelle a été posée, donc plus rien en main
			hand = null;
		}
	}

	@Override
	public void damage(int power) {
		this.hp -= power;
	}

	// Conditions
	@Override
	public boolean isAlive() {
		return hp > 0;
	}

	@Override
	public boolean gotStuff() {
		return canTake && this.bag.size() > 0;
	}

	// Getters - setters

	public Direction getDirection() {
		return this.direction;
	}

	public Weapon getWeapon() {
		return this.weapon;
	}

	public int getHp() {
		return this.hp;
	}

	public BufferedImage[] getSprite() {
		return this.sprite;
	}

	public Tower getHand() {
		return this.hand;
	}

	public void addBagProduct(Tower tower) {
		if (tower instanceof Tower) {
			this.bag.add(tower);
		}
	}

	public void setTowerSelected(Kind_Weapon kw) {
		this.tower_selected = kw;
	}

	public Kind_Weapon getTowerSelected() {
		return this.tower_selected;
	}

}