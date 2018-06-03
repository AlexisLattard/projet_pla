package edu.ricm3.game.tomatower.entities;

import edu.ricm3.game.tomatower.entities.enums.ObstaclesKind;
import edu.ricm3.game.tomatower.map.Cell;
import edu.ricm3.game.tomatower.mvc.Model;

import java.awt.image.BufferedImage;

public abstract class Inert extends Entity {

    ObstaclesKind obstacles_kind;

    Inert(Model c_model, Boolean c_movement, BufferedImage c_sprite, double c_scale, Cell c_cell, ObstaclesKind c_kind) {
        super(c_model, c_movement, c_sprite, c_scale, c_cell);
        this.obstacles_kind = c_kind;
        //this.model.addObstacle(this);
    }


    public void protect() { return ;}
    public void hit() { return ; }
    public void pick() { return ; }
    public void store() { return ; }
    public void power(int power) { return ; }
    public void getBagEntity() { return ;}
    public void throwAction() { return ;}
    public void damage(int power) {return ;}


}
