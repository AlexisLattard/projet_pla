package edu.ricm3.game.tomatower.entities;

import edu.ricm3.game.tomatower.Cell;
import edu.ricm3.game.tomatower.mvc.Model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class Entity {
    Model model;
    Cell cell;
    protected boolean movement;
    double scale = 1;
    BufferedImage sprite;
    Kind kind;
    protected boolean visible;


    public Entity(Model c_model, Boolean c_movment, BufferedImage c_sprite, double c_scale, Cell cell) {
        this.model = c_model;
        this.movement = c_movment;
        this.sprite = c_sprite;
        this.scale = c_scale;
        this.addEntityOnCell(cell);
    }

    public Entity(Model c_model, Boolean c_movment, BufferedImage c_sprite, double c_scale) {
        this.model = c_model;
        this.movement = c_movment;
        this.sprite = c_sprite;
        this.scale = c_scale;
        this.visible = false; // Initialisation sans position = pas visible
    }

    public boolean isVisible() {
        return this.visible;
    }

    public boolean addEntityOnCell(Cell c) {

        if(model.getMainMap().freeCell(c)) {
            //System.out.println("PUT ENTITY : (" + c.getPosition()[0] + " " + c.getPosition()[1] + ")");
            c.addEntity(this);
            this.cell = c;
            this.visible = true;
            return true;
        } else {
            System.out.println("not free");
            this.visible = false;
            return false;
        }
    }
    public void removeEntityFromCell() {
        //System.out.println("Set not visible");
        this.cell.removeEntity(this);
        this.cell = null;
        this.visible = false;
    }

    public void paint(Graphics g) {
        if(this.isVisible()) {
            int d = (int) (model.getMainMap().getCellSize() * scale);
            int[] pos = this.getPosition();
            int x = pos[0] * model.getMainMap().getCellSize();
            int y = pos[1] * model.getMainMap().getCellSize();
            g.drawImage(sprite, x, y, d, d, null);
        }
    }

    public void step(long now) {
        // Comportment - Automate
    }


    public void move(Direction d) {

    }
    public void turn(Direction d) {

    }

    public boolean canMove() {
        return this.movement;
    }

    public int[] getPosition() {
        return this.cell.getPosition();
    }


    public void jump() { return ;}

    public void protect() { return ;} // protection

    public abstract void hit() ;

    public abstract void pick() ; // ramasser sur la map

    public abstract void throwAction() ; // lancer ce que on a dans la main

    public abstract void store() ; // mettre dans son sac

    public abstract void getBagEntity() ; //take entity

    public abstract void power(int hp) ; //recuperation d'energie


}
