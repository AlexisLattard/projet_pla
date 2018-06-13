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
import edu.ricm3.game.tomatower.entities.enums.Entity_Name;
import edu.ricm3.game.tomatower.entities.enums.Kind_Weapon;
import edu.ricm3.game.tomatower.map.Sprites;

public class Model extends GameModel {

	private Sprites game_sprites;
	private Map main_map;
	private Map map_store;
	private Map current_map;

	private Player player;
	private Crystal crystal;

	private HashMap<Kind_Weapon, Weapon> weapons;
	private HashMap<String, A_Automaton> automatons;
	private ArrayList<Entity> entities;

	public Model() {
		game_sprites = new Sprites();
		entities = new ArrayList<Entity>();
	}

	public void initModel(Controller c) {

		this.initWeapons();
		this.initAutomatons(c);
		this.initMaps();

		// TEST

		// new Mobs(this, this.getSprites().sprite_mob1, 1, this.getMainMap().getCell(2,
		// 2), Direction.WEST, this.getWeapons().get(Kind_Weapon.Red),
		// this.getAutomatons().get("MoverRandom"));
		// new Mobs(this, this.getSprites().sprite_mob1, 1, this.getMainMap().getCell(2,
		// 2), Direction.WEST, this.getWeapons().get(Kind_Weapon.Red),
		// this.getAutomatons().get("MoverRandom"));
		// new Mobs(this, this.getSprites().sprite_mob2, 1, this.getMainMap().getCell(2,
		// 2), Direction.WEST, this.getWeapons().get(Kind_Weapon.Red),
		// this.automatons.get("Atest"));
		// new Mobs(this, this.getSprites().sprite_mob2, 1, this.getMainMap().getCell(6,
		// 8), Direction.WEST, this.getWeapons().get(Kind_Weapon.Yellow),
		// this.automatons.get("FollowTheWalls"));
		// new Mobs(this, this.getSprites().sprite_mob1, 1, this.getMainMap().getCell(4,
		// 4), Direction.WEST, this.getWeapons().get(Kind_Weapon.Yellow),
		// this.automatons.get("MoverRandom"));

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
		for (Map m : this.getAllMaps()) {
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

	public HashMap<Kind_Weapon, Weapon> getWeapons() {
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
		this.setCurrentMap(main_map);
		this.main_map.initMap("game.txt");

		// Map store
		this.map_store = new Map(this);
		this.map_store.initMap("store.txt");
	}

	public void initWeapons() {
		this.weapons = new HashMap<>();
		Weapon weapons1 = new Weapon(this, 3, 10, Kind_Weapon.Yellow);
		Weapon weapons2 = new Weapon(this, 1, 15, Kind_Weapon.Red);
		Weapon weapons3 = new Weapon(this, 2, 13, Kind_Weapon.Blue);
		Weapon weapons4 = new Weapon(this, 4, 8, Kind_Weapon.Purple);

		weapons.put(weapons1.getKindWeapon(), weapons1);
		weapons.put(weapons2.getKindWeapon(), weapons2);
		weapons.put(weapons3.getKindWeapon(), weapons3);
		weapons.put(weapons4.getKindWeapon(), weapons4);

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
