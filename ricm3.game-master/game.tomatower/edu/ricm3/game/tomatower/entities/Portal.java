package edu.ricm3.game.tomatower.entities;

import edu.ricm3.game.tomatower.entities.enums.ObstaclesKind;
import edu.ricm3.game.tomatower.map.Cell;
import edu.ricm3.game.tomatower.Options;
import edu.ricm3.game.tomatower.mvc.Model;

import java.awt.image.BufferedImage;

public class Portal extends Inert {

    private boolean canActive;

    public Portal(Model c_model, BufferedImage c_sprite, double c_scale, Cell c_cell, ObstaclesKind c_kind) {
        super(c_model, false, c_sprite, c_scale, c_cell, c_kind);
        this.canActive = c_kind != ObstaclesKind.PORTAL_DESTINATION;
    }

    public void step(long now) {
        if(this.cell.getEntities().size() > 1) { // Player possiblement sur la case
            if(this.cell.getEntities().contains(this.model.getPlayer()) && canActive) {
                this.activePortal();
            }
        }

        // Le téléporteur peut de nouveau être activé si le joueur n'est pas dessus et que le joueur est sur la map de jeu
        if(!canActive && this.model.getCurrentMap() == this.model.getMainMap() && this.cell.getEntities().size() == 1 && this.obstacles_kind != ObstaclesKind.PORTAL_DESTINATION) {
            this.canActive = true;
        }
    }

    public void activePortal() {
        if(Options.ECHO_GAME_STATE)
            System.out.println("Portal : " + this.obstacles_kind + " ...");

        this.canActive = false;

        switch (this.obstacles_kind) {
            case PORTAL_TO_CHALLENGE:
                this.model.getMainMap().setCellIn(this.cell); // Le joueur reviendra sur la téléporteur qui l'a téléporté
                this.model.getRandomChallengeMap().setVisible();
                break;
            case PORTAL_TO_GAME:
                this.model.getMainMap().setVisible();
                break;
            case PORTAL_TO_STORE:
                this.model.getMainMap().setCellIn(this.cell); // Le joueur reviendra sur la téléporteur qui l'a téléporté
                this.model.getStoreMap().setVisible();
                break;
            default:
                if(Options.ECHO_GAME_STATE)
                    System.out.println("... this portal is not supported.");
        }
    }
}