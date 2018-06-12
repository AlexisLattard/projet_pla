package edu.ricm3.game.tomatower.entities;

import java.awt.image.BufferedImage;

import edu.ricm3.game.tomatower.entities.enums.Direction;
import edu.ricm3.game.tomatower.entities.enums.Kind;
import edu.ricm3.game.tomatower.entities.enums.Kind_Weapon;
import edu.ricm3.game.tomatower.entities.enums.ObstaclesKind;
import edu.ricm3.game.tomatower.map.Cell;
import edu.ricm3.game.tomatower.mvc.Model;

public class MobSpawn extends Inert{
	
	private MobSpawn main_instance;
	private int wave_id; // numéro de la vague
    private long last_wave = 0; // moment de la dernier vague
    private long wave_delay; // delais entre les vagues
	
	
	public MobSpawn(Model c_model, BufferedImage c_sprite, double c_scale, Cell c_cell, MobSpawn c_main_instance){
		super(c_model, false, c_sprite, c_scale, c_cell,ObstaclesKind.MOBSPAWN,Kind.MobSpawn);
		this.main_instance = c_main_instance;
		this.wave_id = 0;
		this.wave_delay = 10000L;
	}
	
	@Override
	public void step(long now){
		if(now - this.last_wave > wave_delay && main_instance == null){			
			starWave();
			last_wave = now;
		}
	}
	public void starWave(){	
     
        new Mobs(this.model, this.model.getSprites().sprite_mob1, 1, this.model.getMainMap().getCell(2, 2), Direction.WEST, this.model.getWeapons().get(Kind_Weapon.Red), this.model.getAutomatons().get("MoverRandom"));
        new Mobs(this.model, this.model.getSprites().sprite_mob1, 1, this.model.getMainMap().getCell(2, 2), Direction.WEST, this.model.getWeapons().get(Kind_Weapon.Red), this.model.getAutomatons().get("MoverRandom"));

//		for(int i=0;i<10;i++){
//	        new Mobs(this.model, this.model.getSprites().sprite_mob1, 1, this.model.getMainMap().getCell(2, 2), Direction.WEST, this.model.getWeapons().get(Kind_Weapon.Red), this.model.getAutomatons().get("AMoverRandom"));
//		}
		
		for(int i=0;i<0;i++){
	        new Mobs(this.model, this.model.getSprites().sprite_mob2, 1, this.model.getMainMap().getCell(1, 1), Direction.WEST, this.model.getWeapons().get(Kind_Weapon.Red), this.model.getAutomatons().get("MoverRandom"));
		}
	}
	
}
