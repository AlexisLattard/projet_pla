package edu.ricm3.game.tomatower.entities;

import edu.ricm3.game.tomatower.Options;
import edu.ricm3.game.tomatower.automaton.A_Automaton;
import edu.ricm3.game.tomatower.entities.enums.Direction;
import edu.ricm3.game.tomatower.entities.enums.Kind;
import edu.ricm3.game.tomatower.entities.enums.EntityName;
import edu.ricm3.game.tomatower.map.Cell;
import edu.ricm3.game.tomatower.mvc.Model;
import static edu.ricm3.game.tomatower.LevelDesign.*;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class Living extends Entity {

	protected int hp;
	protected boolean canTakeEntity;
	protected long last_action;
	protected ArrayList<Tower> bag;
	protected Tower hand = null;
	protected Weapon weapon;

	protected int max_life;
	protected BufferedImage sprite[];
	protected EntityName tower_selected;

	Living(Model c_model, Boolean c_movement, BufferedImage c_sprite[], double c_scale, Cell c_cell, Weapon c_weapon,
			ArrayList<Class<?>> c_collisions, A_Automaton c_automaton, Kind c_kind, long c_action_time,
			int c_max_life) {
		super(c_model, c_movement, c_scale, c_collisions, c_automaton, c_cell, c_kind, c_action_time, Direction.NORTH);
		this.sprite = c_sprite;
		this.weapon = c_weapon;
		this.last_action = System.nanoTime();
		this.tower_selected = EntityName.Tower_Red;
		this.max_life = c_max_life;
		this.hp = max_life;
		this.bag = new ArrayList<>();
	}

	Living(Model c_model, Boolean c_movement, BufferedImage c_sprite[], double c_scale, Weapon c_weapon,
			ArrayList<Class<?>> c_collisions, A_Automaton c_automaton, Kind c_kind, long c_action_time,
			int c_max_life) {
		super(c_model, c_movement, c_scale, c_collisions, c_automaton, c_kind, c_action_time, Direction.NORTH);
		this.sprite = c_sprite;
		this.weapon = c_weapon;
		this.last_action = System.nanoTime();
		this.tower_selected = EntityName.Tower_Red;
		this.max_life = c_max_life;
		this.hp = max_life;
		this.bag = new ArrayList<>();

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
			death();
		}
	}

	// Actions

	@Override
	public void hit(Direction d) {
		this.weapon.hit(this, d);
	}

	@Override
	public void pick(Direction d) {
		if (this.canTakeEntity) {
			Entity entity = this.getMap().getEntityCell(this.getCellDirection(d, 1));
			if (entity instanceof Tower) {
				if (this.hand != null) // On a déjà quelque chose en main, on le remet dans le sac
					this.bag.add(hand);
				entity.removeEntityFromCell();
				this.hand = (Tower) (entity);
			}
		}
	}

	@Override
	public void store() {
		if (this.hand != null) {
			this.bag.add(hand);
			this.hand = null;
		}
	}

	@Override
	public void getBagEntity() {

		if (this.canTakeEntity && this.bag.size() >= 1) {
			store();

			int i = 0;
			while (i < this.bag.size() && !this.bag.get(i).getWeapon().getKindWeapon().equals(this.tower_selected)) {
				i++;
			}

			if (i < this.bag.size()) {
				this.hand = this.bag.remove(i);
			}
		}
	}

	@Override
	public void power() {
		if(this.hp < max_life)
			this.hp += max_life / 10; // On recupere 1/10 de sa maximale
	}

	@Override
	public void throwAction(Direction d) {
		if (Options.ECHO_GAME_STATE && this.hand == null)
			System.out.println("Rien dans la main");

		if (this.canTakeEntity && this.hand != null && this.hand.addEntityOnCell(this.getCellDirection(d, 1))) {
			// Si vrai, alors la tourelle a été posée, donc plus rien en main
			this.hand = null;
		}
	}

	@Override
	public void kamikaze() {
		this.hp = 0;
	}

	@Override

	public void damage(int power) {
		this.hp -= power;
	}

	public void circleAttack(int power) {
		this.getCellDirection(Direction.NORTH, 1).damage(power);
		this.getCellDirection(Direction.SOUTH, 1).damage(power);
		this.getCellDirection(Direction.EAST, 1).damage(power);
		this.getCellDirection(Direction.WEST, 1).damage(power);
	}

	public void death() {

		if (this instanceof Mobs) {
			this.model.getPlayer().increaseMoney(EARNED_MONEY_WHEN_MOB_DIED);
		}
		this.model.removeEntity(this);
		removeEntityFromCell();

	}

	// Conditions
	@Override
	public boolean isAlive() {
		return this.hp > this.max_life / 5;
	}

	@Override
	public boolean gotStuff() {
		return this.canTakeEntity && this.bag.size() > 0;
	}

	// Getters - setters

	public Direction getDirection() {
		return this.direction;
	}

	public Weapon getWeapon() {
		return this.weapon;
	}

	public void setLastAction(long now) {
		this.last_action = now;
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

	public void setTowerSelected(EntityName kw) {
		this.tower_selected = kw;
	}

	public EntityName getTowerSelected() {
		return this.tower_selected;
	}

	public int getMaxLife() {
		return this.max_life;
	}

}