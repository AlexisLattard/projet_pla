package edu.ricm3.game.tomatower;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

import com.sun.java_cup.internal.runtime.Symbol;
import com.sun.javafx.collections.MappingChange.Map;

public class Player extends Living {


    ArrayList<Tower> bag;
    Tower hand = null;


    public Player(Model c_model, Boolean c_movment, BufferedImage c_sprite, double c_scale, int cell_x, int cell_y, Direction c_direction) {
        super(c_model, c_movment, c_sprite, c_scale, cell_x, cell_y, c_direction);
        bag = new ArrayList<>();
        //TESTS
        Tower t1 = new Tower(this.model, false, this.model.sprite_tower, 1);
        Tower t2 = new Tower(this.model, false, this.model.sprite_tower, 1);
        Tower t3 = new Tower(this.model, false, this.model.sprite_tower, 1);
        Tower t4 = new Tower(this.model, false, this.model.sprite_tower, 1);
        bag.add(t1);
        bag.add(t2);
        bag.add(t3);
        bag.add(t4);
        Tower t5 = new Tower(this.model, false, this.model.sprite_tower, 1);
        Tower t6 = new Tower(this.model, false, this.model.sprite_tower, 1);
        Tower t7 = new Tower(this.model, false, this.model.sprite_tower, 1);
        Tower t8 = new Tower(this.model, false, this.model.sprite_tower, 1);
        bag.add(t5);
        bag.add(t6);
        bag.add(t7);
        bag.add(t8);
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

    public void pick() { // Ou store ?
        int[] pos_front_cell = this.getPosFrontCell();


        Entity entity = this.model.getEntityCell(pos_front_cell[0], pos_front_cell[1]);
        if (entity instanceof Tower) {
            entity.setNotVisible();
            hand = (Tower)(entity);
        }
    }

    public ArrayList<Tower> getBag() {
        return this.bag;
    }

    public void getEntity() {
        if(this.getBag().size() >= 1) {
            if(hand == null)
                hand = this.getBag().remove(0);
            if(hand == null)
                System.out.println("Rien dans la main");
            else
                System.out.println("Tourrel prise");
        } else {
            System.out.println("Rien dans le sac");
        }
    }

    public void throwAction() {
        if(hand == null)
            System.out.println("Rien dans la main");

        if(hand != null) {
            int[] pos_front_cell = this.getPosFrontCell();
            if(hand.setVisible(pos_front_cell[0], pos_front_cell[1])) {
                hand = null;
            }

        }
    }

    public void store() {
        if(hand != null) {
            bag.add(hand);
            hand = null;
        }
    }




}
