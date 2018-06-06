package edu.ricm3.game.tomatower.entities;

import edu.ricm3.game.tomatower.entities.enums.ObstaclesKind;
import edu.ricm3.game.tomatower.map.Cell;
import edu.ricm3.game.tomatower.map.Map;
import edu.ricm3.game.tomatower.Options;
import edu.ricm3.game.tomatower.mvc.Model;

import java.awt.image.BufferedImage;

public class Portal extends InertAction {

	public Portal(Model c_model, BufferedImage c_sprite, double c_scale, Cell c_cell, ObstaclesKind c_kind) {
		super(c_model, false, c_sprite, c_scale, c_cell, c_kind);
		this.canActive = c_kind != ObstaclesKind.PORTAL_DESTINATION;
	}

	@Override
	public void step(long now) {
		super.step(now);
		
		// Le téléporteur peut de nouveau être activé si le joueur n'est pas dessus et
		// que le joueur est sur la map de jeu
		if (!canActive && this.model.getCurrentMap() == this.model.getMainMap() && this.cell.getEntities().size() == 1
				&& this.obstacles_kind != ObstaclesKind.PORTAL_DESTINATION) {
			this.canActive = true;
		}
	}

	public void action(Entity e) {
		if (Options.ECHO_GAME_STATE)
			System.out.println("Portal : " + this.obstacles_kind + " ...");

		super.action(e);
		Map dest = null;
		
		switch (this.obstacles_kind) {
		case PORTAL_TO_CHALLENGE:
			this.model.getMainMap().setCellIn(this.cell);// Le joueur reviendra sur la téléporteur qui l'a téléporteur
			dest = this.model.getRandomChallengeMap();
			break;
		case PORTAL_TO_GAME:
			dest = this.model.getMainMap();
			break;
		case PORTAL_TO_STORE:
			this.model.getMainMap().setCellIn(this.cell); // Le joueur reviendra sur la téléporteur qui l'a téléporté
			dest = this.model.getStoreMap();
			break;
		default:
			if (Options.ECHO_GAME_STATE)
				System.out.println("... this portal is not supported.");
			return;
		}
		
		if(e instanceof Player)
			this.model.setCurrentMap(dest);
		e.addEntityOnCell(dest.getCellIn());
	}

}