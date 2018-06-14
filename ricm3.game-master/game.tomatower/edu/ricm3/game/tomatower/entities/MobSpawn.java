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

import com.sun.org.apache.bcel.internal.generic.AALOAD;

import edu.ricm3.game.tomatower.automaton.A_Automaton;
import edu.ricm3.game.tomatower.entities.enums.Direction;
import edu.ricm3.game.tomatower.entities.enums.Kind;
import edu.ricm3.game.tomatower.entities.enums.EntityName;
import edu.ricm3.game.tomatower.entities.enums.ObstaclesKind;
import edu.ricm3.game.tomatower.map.Cell;
import edu.ricm3.game.tomatower.mvc.Model;
import static edu.ricm3.game.tomatower.LevelDesign.*;


public class MobSpawn extends Inert {

	private MobSpawn main_instance;
	private int wave_id; // numéro de la vague
	private long last_wave = 0; // moment de la dernier vague
	private long wave_delay; // delais entre les vagues
	private int wave_total;
	private ArrayList<int[]> waves;
	
	boolean is_ready;
	private ArrayList<Mobs> current_wave;
	int id_mob_wave;
	long last_apparition = 0;
	
	private HashMap<BufferedImage[],A_Automaton> behaviors;
	private int nb_type;

	public MobSpawn(Model c_model, BufferedImage c_sprite, double c_scale, Cell c_cell, MobSpawn c_main_instance) {
		super(c_model, false, c_sprite, c_scale, c_cell, ObstaclesKind.MOBSPAWN, Kind.Ennemis);
		this.main_instance = c_main_instance;
		this.wave_id = 0;
		this.wave_delay = ACTION_TIME_MOBSPAWN;		
		this.current_wave = new ArrayList<>();
		this.is_ready = false;
		initWaves();
		
		//Test
		behaviors = new HashMap<>();
		behaviors.put(this.model.getSprites().sprite_mob_plug,this.model.getAutomatons().get("Agressiv"));
		behaviors.put(this.model.getSprites().sprite_mob_hungry,this.model.getAutomatons().get("FollowTheRightWall"));
		behaviors.put(this.model.getSprites().sprite_mob_lantern,this.model.getAutomatons().get("Rusher"));
		behaviors.put(this.model.getSprites().sprite_mob_ghost,this.model.getAutomatons().get("FollowTheLeftWall"));

	}

	@Override
	public void step(long now) {
		if (!is_ready &&  now - this.last_wave > wave_delay && main_instance == null && wave_id < waves.size()) {
			createWave();
			is_ready = true;
		} else if( is_ready && now - this.last_apparition > ACTION_TIME_SPAWN_SAME_WAVE) {
			System.out.println("Wave");
			instanciateWaveMobs(now);
		}
	}

	public void createWave() {
		
		////////
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
					this.current_wave.add(new Mobs(this.model, sprites, 1, this.model.getWeapons().get(EntityName.Tower_Red), behavior,MAX_LIFE_MOB_PLUG));
				}
			}
		}
		wave_id++;
	}
	
	
	public void instanciateWaveMobs(long now) {
		if(id_mob_wave < this.current_wave.size()) {
			this.current_wave.get(id_mob_wave).addEntityOnCell(this.cell);
			last_apparition = now;
			id_mob_wave++;
		}else {
			this.id_mob_wave = 0;
			last_wave = now;
			is_ready = false;
		}
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

			reader.close();
		} catch (IOException e) {

		}
		this.wave_total = waves.size();
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

	
	public int getWaveId() {
		return this.wave_id;
	}
	
	public int getWaveTotal() {
		return this.wave_total;
	}
}
