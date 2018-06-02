package edu.ricm3.game.tomatower.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import edu.ricm3.game.tomatower.Cell;
import edu.ricm3.game.tomatower.mvc.Model;

public class Player extends Living {

    ArrayList<Tower> bag;
    Tower hand = null;

    public Player(Model c_model, BufferedImage c_sprite, double c_scale, Cell c_cell, Direction c_direction) {
        super(c_model, true, c_sprite, c_scale, c_cell, c_direction);
        bag = new ArrayList<>();
        //TESTS
        Tower t1 = new Tower(this.model, false, this.model.getSpriteTower(), 1);
        Tower t2 = new Tower(this.model, false, this.model.getSpriteTower(), 1);
        Tower t3 = new Tower(this.model, false, this.model.getSpriteTower(), 1);
        Tower t4 = new Tower(this.model, false, this.model.getSpriteTower(), 1);
        bag.add(t1);
        bag.add(t2);
        bag.add(t3);
        bag.add(t4);
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g); //Paint aussi le weapon, a deplacer dans living du coup
    }



    public ArrayList<Tower> getBag() {
        return this.bag;
    }

    public void getBagEntity() {
        if(this.getBag().size() >= 1) {
            if(hand == null) {
                hand = this.getBag().remove(0);
            }
        }
    }

    public void throwAction() {
        if(hand == null)
            System.out.println("Rien dans la main");

        if(hand != null) {
            Cell front_cell = this.getFrontCell();
            if(hand.addEntityOnCell(front_cell)) {
                hand = null;
            }
        }
    }

    public void pick() { // Ou store ?
        Cell front_cell = this.getFrontCell();

        Entity entity = this.model.getMainMap().getEntityCell(front_cell);
        if (entity instanceof Tower) {
            //System.out.println("PICK ENTITY : (" + entity.getPosition()[0] + " " + entity.getPosition()[1] + ")");
            entity.removeEntityFromCell();
            hand = (Tower)(entity);
        }
    }

    public void store() {
        if(hand != null) {
            bag.add(hand);
            hand = null;
        }
    }




}
