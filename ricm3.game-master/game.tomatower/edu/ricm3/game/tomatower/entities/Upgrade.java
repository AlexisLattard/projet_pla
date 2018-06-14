package edu.ricm3.game.tomatower.entities;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import edu.ricm3.game.tomatower.Options;
import edu.ricm3.game.tomatower.automaton.A_Automaton;
import edu.ricm3.game.tomatower.entities.enums.EntityName;
import edu.ricm3.game.tomatower.entities.enums.ObstaclesKind;
import edu.ricm3.game.tomatower.map.Cell;
import edu.ricm3.game.tomatower.mvc.Model;
import static edu.ricm3.game.tomatower.LevelDesign.*;

public class Upgrade extends Buyable {

	ArrayList<A_Automaton> behaviors;

	public Upgrade(Model c_model, BufferedImage c_sprite, double c_scale, Cell c_cell, ObstaclesKind c_kind, Weapon c_weapon, int c_price) {
		super(c_model, c_sprite, c_scale, c_cell, c_kind, c_weapon, c_price);
		initbehavior();
	}

	public void upgradeWeapon() {

		if (buy(this.price)) {
			this.weapon.upgrade();
			this.price += INCREASES_TOWER_UPGRADE_AMOUNT;
		}
	}

	public void behaviorChangement() {
		if (buy(PRICE_BEHAVIOR_CHANGEMENT)) {
			ArrayList<Entity> entities = this.model.getEntities();
			Random random = new Random();
			int nextBehavior = random.nextInt(this.behaviors.size());
			A_Automaton behavior = this.behaviors.get(nextBehavior);

			if(Options.ECHO_GAME_STATE){
				System.out.println("Changement de comportement.");
			}
			this.model.getAutomatons().put(this.weapon.getKindWeapon(), behavior);
			for (Entity e : entities) {
				if (e instanceof Tower && ((Tower) e).getWeapon().equals(this.weapon)) {
					e.setAutomaton(behavior);
				}
			}
		}
	}

	public void initbehavior() {
		HashMap<EntityName, A_Automaton> behavior_tower = this.model.getAutomatons();
		behaviors = new ArrayList<>();
		behaviors.add(behavior_tower.get(EntityName.Tower_Red));
		behaviors.add(behavior_tower.get(EntityName.Tower_Yellow));
		behaviors.add(behavior_tower.get(EntityName.Tower_Blue));
		behaviors.add(behavior_tower.get(EntityName.Tower_Purple));
	}
}
