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
        this.addEntityOnCell(cell); // Initialisation avec position = visible
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

    public Boolean addEntityOnCell(Cell c) {

        if(model.getMap().freeCell(c)) {
            System.out.println("PUT ENTITY : (" + c.getPosition()[0] + " " + c.getPosition()[1] + ")");
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
        System.out.println("Set not visible");
        this.cell.removeEntity(this);
        this.visible = false;
    }

    public void paint(Graphics g) {
        if(this.isVisible()) {
            int d = (int) (model.getMap().getCellSize() * scale);
            int[] pos = this.getPosition();
            int x = pos[0] * model.getMap().getCellSize();
            int y = pos[1] * model.getMap().getCellSize();
            g.drawImage(sprite, x, y, d, d, null);
        }
    }

    public void step() {
        // Comportment - Automate
    }

    public void initPosition(Cell c) {
        this.cell = c;  // A changer, initialisation de la position des ennemis sur un même spawn ?Init sur case illegal
    }



    public void move(int vertical, int horizontal) {

    }
    public void turn() {

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
