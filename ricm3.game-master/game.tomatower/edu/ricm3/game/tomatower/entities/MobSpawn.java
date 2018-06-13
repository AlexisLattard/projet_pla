package edu.ricm3.game.tomatower.entities;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import edu.ricm3.game.tomatower.automaton.A_Automaton;
import edu.ricm3.game.tomatower.entities.enums.Direction;
import edu.ricm3.game.tomatower.entities.enums.Kind;
import edu.ricm3.game.tomatower.entities.enums.Kind_Weapon;
import edu.ricm3.game.tomatower.entities.enums.ObstaclesKind;
import edu.ricm3.game.tomatower.map.Cell;
import edu.ricm3.game.tomatower.mvc.Model;

public class MobSpawn extends Inert {

	private MobSpawn main_instance;
	private int wave_id; // numéro de la vague
	private long last_wave = 0; // moment de la dernier vague
	private long wave_delay; // delais entre les vagues
	private ArrayList<int[]> waves;
	private HashMap<BufferedImage[],A_Automaton> behaviors;
	private int nb_type;

	public MobSpawn(Model c_model, BufferedImage c_sprite, double c_scale, Cell c_cell, MobSpawn c_main_instance) {
		super(c_model, false, c_sprite, c_scale, c_cell, ObstaclesKind.MOBSPAWN, Kind.MobSpawn);
		this.main_instance = c_main_instance;
		this.wave_id = 0;
		this.wave_delay = 10000;		
		initWaves();
		
		//Test
		behaviors = new HashMap<>();
		behaviors.put(this.model.getSprites().sprite_mob1, this.model.getAutomatons().get("FollowTheWalls"));
		behaviors.put(this.model.getSprites().sprite_mob2, this.model.getAutomatons().get("FollowTheWalls"));

	}

	@Override
	public void step(long now) {
		if (now - this.last_wave > wave_delay && main_instance == null && wave_id < waves.size()) {
			starWave();
			last_wave = now;
		}
	}

	public void starWave() {
		int nb_monstre[] = waves.get(wave_id);
		
		A_Automaton behavior;
		Object[] tab_behavior;
		BufferedImage[] sprites;
		Object[] tab_sprites;

		tab_sprites = behaviors.keySet().toArray();
		tab_behavior = behaviors.values().toArray();
		for (int j = 0; j < nb_type; j++) {
			behavior = (A_Automaton) tab_behavior[j];
			sprites = (BufferedImage[]) tab_sprites[j];
			if (behavior != null && sprites != null) {
				for (int i = 0; i < nb_monstre[j]; i++) {
					new Mobs(this.model, sprites, 1, this.model.getMainMap().getCell(2, 2), Direction.WEST, this.model.getWeapons().get(Kind_Weapon.Red), behavior);
				}
			}
		}
		wave_id++;
	}

	public void initWaves() {
		waves = new ArrayList<>();
		String line;

		// Lecture fichier + entete
		try {

			File wave_file = new File("game.tomatower/maps/waves.txt");
			BufferedReader reader = new BufferedReader(new FileReader(wave_file));
			nb_type = Integer.valueOf(reader.readLine().trim());

			while ((line = reader.readLine()) != null && line != "") {
				waves.add(stringToInt(line.split(","), nb_type));
			}

		} catch (IOException e) {

		}
	}

	public int[] stringToInt(String[] tab, int nb_type) {

		int resultat[] = new int[nb_type];
		int i = 0;
		for (String str : tab) {
			resultat[i] = Integer.parseInt(str.trim());
			i++;
		}
		return resultat;
	}

}
