/*
 * Educational software for a basic game development
 * Copyright (C) 2018  Pr. Olivier Gruber
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.ricm3.game.tomatower.mvc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

import edu.ricm3.game.GameModel;
import edu.ricm3.game.parser.Ast;
import edu.ricm3.game.parser.AutomataParser;
import edu.ricm3.game.parser.ParseException;
import edu.ricm3.game.tomatower.map.Map;
import edu.ricm3.game.tomatower.automaton.A_Automaton;
import edu.ricm3.game.tomatower.automaton.A_Builder;
import edu.ricm3.game.tomatower.entities.*;
import edu.ricm3.game.tomatower.entities.enums.Direction;
import edu.ricm3.game.tomatower.entities.enums.EntityName;
import edu.ricm3.game.tomatower.map.Sprites;
import static edu.ricm3.game.tomatower.LevelDesign.*;

public class Model extends GameModel {

	private Sprites game_sprites;

	private Map main_map;
	private Map map_store;
	private Map current_map;

	private Player player;
	private Crystal crystal;
	private MobSpawn mobSpawn;

	private HashMap<EntityName, Weapon> weapons;
	private HashMap<String, A_Automaton> automatons;
	private ArrayList<Entity> entities;

	public Model() {
		this.game_sprites = new Sprites();
		this.entities = new ArrayList<Entity>();
	}

	public void initModel(Controller c) {
		initWeapons();
		initAutomatons(c);
		initMaps();
	}

	@Override
	public void shutdown() {

	}

	/**
	 * Simulation step.
	 *
	 * @param now
	 *            is the current time in milliseconds.
	 */
	@Override
	public void step(long now) {
		for (Map m : getAllMaps()) {
			m.step(now);
		}
	}

	public Player getPlayer() {
		return this.player;
	}

	public void setPlayer(Player p) {
		this.player = p;
	}

	public Crystal getCrystal() {
		return this.crystal;
	}

	public void setCrystal(Crystal c) {
		this.crystal = c;
	}

	public MobSpawn getMobSpawn() {
		return this.mobSpawn;
	}

	public void setMobSpawn(MobSpawn mobSpawn) {
		this.mobSpawn = mobSpawn;
	}

	public Sprites getSprites() {
		return this.game_sprites;
	}

	public Map getMainMap() {
		return this.main_map;
	}

	public Map getStoreMap() {
		return this.map_store;
	}

	public ArrayList<Map> getAllMaps() {
		ArrayList<Map> res = new ArrayList<>();
		res.add(this.main_map);
		res.add(this.map_store);
		return res;
	}

	public void setCurrentMap(Map m) {
		this.current_map = m;
	}

	public Map getCurrentMap() {
		return this.current_map;
	}

	public HashMap<EntityName, Weapon> getWeapons() {
		return this.weapons;
	}

	public HashMap<String, A_Automaton> getAutomatons() {
		return this.automatons;
	}

	public ArrayList<Entity> getEntities() {
		return this.entities;
	}

	public void addEntity(Entity e) {
		this.entities.add(e);
	}

	public void removeEntity(Entity e) {
		this.entities.remove(e);
	}

	public void initMaps() {
		// Map principale
		main_map = new Map(this);
		setCurrentMap(main_map);
		this.main_map.initMap("game.txt");

		// Map store
		this.map_store = new Map(this);
		this.map_store.initMap("store.txt");
	}

	public void initWeapons() {
		this.weapons = new HashMap<>();
		Weapon player = new Weapon(this, HIT_PLAYER, RANGE_PLAYER, EntityName.Player);
		Weapon tower_red = new Weapon(this, HIT_TOWER_RED, RANGE_TOWER_RED, EntityName.Tower_Red);
		Weapon tower_blue = new Weapon(this, HIT_TOWER_BLUE, RANGE_TOWER_BLUE, EntityName.Tower_Blue);
		Weapon tower_purple = new Weapon(this, HIT_TOWER_PURPLE, RANGE_TOWER_PURPLE, EntityName.Tower_Purple);
		Weapon tower_yellow = new Weapon(this, HIT_TOWER_YELLOW, RANGE_TOWER_YELLOW, EntityName.Tower_Yellow);
		Weapon mob_hungry = new Weapon(this, HIT_MOB_HUNGRY, RANGE_MOB_HUNGRY, EntityName.Mob_Hungry);
		Weapon mob_ghost = new Weapon(this, HIT_MOB_GHOST, RANGE_MOB_GHOST, EntityName.Mob_Ghost);
		Weapon mob_lantern = new Weapon(this, HIT_MOB_LANTERN, RANGE_MOB_LANTERN, EntityName.Mob_Lantern);
		Weapon mob_plug = new Weapon(this, HIT_MOB_PLUG, RANGE_MOB_PLUG, EntityName.Mob_Plug);

		this.weapons.put(player.getKindWeapon(), player);
		this.weapons.put(tower_red.getKindWeapon(), tower_red);
		this.weapons.put(tower_blue.getKindWeapon(), tower_blue);
		this.weapons.put(tower_yellow.getKindWeapon(), tower_yellow);
		this.weapons.put(tower_purple.getKindWeapon(), tower_purple);
		this.weapons.put(mob_hungry.getKindWeapon(), mob_hungry);
		this.weapons.put(mob_ghost.getKindWeapon(), mob_ghost);
		this.weapons.put(mob_lantern.getKindWeapon(), mob_lantern);
		this.weapons.put(mob_plug.getKindWeapon(), mob_plug);

	}

	public void initAutomatons(Controller c) {
		this.automatons = new HashMap<>();

		try {
			new AutomataParser(new BufferedReader(new FileReader("game.tomatower/automaton/automata.txt")));
			Ast ast = AutomataParser.Run();
			A_Builder builder = new A_Builder(ast, c);
			this.automatons = builder.makeAutomatonsFromAst();
			System.out.println(automatons.keySet());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
