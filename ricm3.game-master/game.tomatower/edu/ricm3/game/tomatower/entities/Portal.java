package edu.ricm3.game.tomatower.entities;

import edu.ricm3.game.tomatower.Options;
import edu.ricm3.game.tomatower.entities.enums.Kind;
import edu.ricm3.game.tomatower.entities.enums.ObstaclesKind;
import edu.ricm3.game.tomatower.map.Cell;
import edu.ricm3.game.tomatower.map.Map;
import edu.ricm3.game.tomatower.mvc.Model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.PrimitiveIterator.OfDouble;

public class Portal extends Inert {
	
	Entity last_entity_on_portal_destination;
	long time_arrived;
	long timer_portal_purge = 5000L;

	public Portal(Model c_model, BufferedImage c_sprite, double c_scale, Cell c_cell, ObstaclesKind c_kind) {
		super(c_model, false, c_sprite, c_scale, c_cell, c_kind, Kind.Gate);
		this.action_time = 500L;
	}
	

	@Override
	public void step(long now) {
		
		if(now - last_action > action_time) {
			last_action = now;
			
			ArrayList<Entity> cell_entities = this.getCell().getEntities();
			if(cell_entities.size() > 1 && !this.obstacles_kind.equals(ObstaclesKind.PORTAL_DESTINATION)) {
				this.action(cell_entities.get(1));
			}
			purgePortalDestination(cell_entities, now);
		}
	}
	

	public void action(Entity e) {
		if (Options.ECHO_GAME_STATE)
			System.out.println("Portal : " + this.obstacles_kind + " ...");
		
		Map dest = null;
		
		switch (this.obstacles_kind) {
			case PORTAL_TO_GAME:
				dest = this.model.getMainMap();
				break;
			case PORTAL_TO_STORE:
				dest = this.model.getStoreMap();
				break;
			default:
				if (Options.ECHO_GAME_STATE)
					System.out.println("... this portal is not supported.");
				return;
		}
		
		if(e.addEntityOnCell(dest.getCellIn())) { 
			if(e instanceof Player)
				this.model.setCurrentMap(dest);
		}else {
			if(Options.ECHO_GAME_STATE)
				System.out.println("Une entité est occupe déjà le téléporteur d'entrée");
		}
		
		
	}
	
	public void purgePortalDestination(ArrayList<Entity> cell_entities, long now) {
		if(this.obstacles_kind.equals(ObstaclesKind.PORTAL_DESTINATION)) {
			if(cell_entities.size() == 1) {
				last_entity_on_portal_destination = null;
			}else {
				Entity entity_on_portal = cell_entities.get(1	);
				if(this.last_entity_on_portal_destination == null) {
					this.last_entity_on_portal_destination = entity_on_portal;
					time_arrived = now;
				} else if(now - time_arrived > timer_portal_purge) {
					entity_on_portal.kamikaze();
				}
			}
		}
	}

}