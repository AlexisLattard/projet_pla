package edu.ricm3.game.tomatower;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class Entity {
    Model model;
    protected boolean movement;
    protected int cell_x;
    protected int cell_y;
    double scale = 1;
    BufferedImage sprite;
    Kind kind;
    protected boolean visible = true;


    public Entity(Model c_model, Boolean c_movment, BufferedImage c_sprite, double c_scale, int cell_x, int cell_y) {
        this.model = c_model;
        this.movement = c_movment;
        this.sprite = c_sprite;
        this.scale = c_scale;
        this.setVisible(cell_x, cell_y); // Initialisation avec position = visible
    }

    public Entity(Model c_model, Boolean c_movment, BufferedImage c_sprite, double c_scale) {
        this.model = c_model;
        this.movement = c_movment;
        this.sprite = c_sprite;
        this.scale = c_scale;
        this.cell_x = cell_x;
        this.cell_y = cell_y;
        this.setNotVisible(); // Initialisation sans position = pas visible
    }

    public boolean isVisible() {
        return this.visible;
    }
    public Boolean setVisible(int cell_x, int cell_y) {
        if(model.freeCell(cell_x, cell_y)) {
            System.out.println("Entitéposé");
            this.cell_x = cell_x;
            this.cell_y = cell_y;
            this.visible = true;
            return true;
        } else {
            return false;
        }
    }
    public void setNotVisible() {
        this.visible = false;
    }

    public void paint(Graphics g) {
        if(this.isVisible()) {
            int d = (int) (model.cell_size * scale);
            int x = (int) (cell_x * model.cell_size);
            int y = (int) (cell_y * model.cell_size);
            g.drawImage(sprite, x, y, d, d, null);
        }

    }

    public void initPosition(int cell_x, int cell_y) {
        this.setVisible(cell_x, cell_y);

    }



    public void move(int vertical, int horizontal) {

    }
    public void turn() {

    }

    public boolean canMove() {
        return this.movement;
    }

    public int[] getPosition() {
        return new int[]{this.cell_x, this.cell_y};
    }


    public void jump() { return ;}

    public void protect() { return ;} // protection

    public abstract void hit() ;

    public abstract void pick() ; // ramasser sur la map

    public abstract void throwAction() ; // lancer ce que on a dans la main

    public abstract void store() ; // mettre dans son sac

    public abstract void getEntity() ; //take entity

    public abstract void power(int hp) ; //recuperation d'energie


}
