package edu.ricm3.game.tomatower;

import java.awt.image.BufferedImage;

public class Inert extends Entity {

    ObstaclesKind obstacles_kind;

    public Inert(Model c_model, Boolean c_movment, BufferedImage c_sprite, double c_scale, int cell_x, int cell_y, ObstaclesKind kind) {
        super(c_model, c_movment, c_sprite, c_scale, cell_x, cell_y);
        this.obstacles_kind = kind;
        this.model.addObstacle(this);
    }


    public void protect() { return ;}
    public void hit() { return ; }
    public void pick() { return ; }
    public void store() { return ; }
    public void power(int hp) { return ; }
    public void getEntity() { return ;}
    public void throwAction() { return ;}


}
