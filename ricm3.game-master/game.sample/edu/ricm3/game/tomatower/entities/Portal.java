package edu.ricm3.game.tomatower.entities;

import edu.ricm3.game.tomatower.Cell;
import edu.ricm3.game.tomatower.Map;
import edu.ricm3.game.tomatower.mvc.Model;

import java.awt.image.BufferedImage;

public class Portal extends Inert {

    Model model;
    Map destination;
    Map origin;

    public Portal(Model c_model, BufferedImage c_sprite, double c_scale, Cell c_cell) {
        super(c_model, false, c_sprite, c_scale, c_cell, ObstaclesKind.Lake.PORTAL);
    }

    public void activePortal() {
        destination.setVisible();
    }
}
