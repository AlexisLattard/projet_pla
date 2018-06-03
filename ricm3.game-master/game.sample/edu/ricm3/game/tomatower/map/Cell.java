package edu.ricm3.game.tomatower.map;

import edu.ricm3.game.tomatower.entities.Entity;
import edu.ricm3.game.tomatower.entities.Living;
import edu.ricm3.game.tomatower.entities.Portal;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Cell {
    private int col;
    private int row;
    private ArrayList<Entity> entities;

    public Cell(int c_col, int c_row) {
        this.col = c_col;
        this.row = c_row;
        this.entities = new ArrayList<>();
    }

    public ArrayList<Entity> getEntities() {
        return this.entities;
    }

    public void addEntity(Entity e) {
        this.getEntities().add(e);
    }

    public int[] getPosition() {
        return new int[]{this.col, this.row};
    }

    public Iterator<Entity> getEntitiesIterator() {
        return this.entities.iterator();
    }

    public void damage(int power) {
        // Si on considère que un hit est une bombre (fait des dégats sur toutes les entités)
        for (Entity e : this.entities) {
            e.damage(power);
        }

        /* Si on considère que le hit est une balle (dégat sur une entité)
        if(!this.entites.isEmpty()) {
            e.damage(power);
        }
         */
    }

    public void removeEntity(Entity e) {
        this.getEntities().remove(e);
    }

    /*
    Return if the cell is free for the entity e,
    if e is null, the function return if the cell contain someting
     */
    public boolean isFree() {
        Entity e = null;
        if(e == null) {
            Iterator<Entity> iter = this.getEntitiesIterator();
            while(iter.hasNext()) {
                Entity entity = iter.next();
                if(entity.isVisible() && !(entity instanceof Portal))
                    return false;
            }
            return true;
        } else {

            return true;
        }
    }

    public void paint(Graphics g) {
        for(Entity e : this.entities) {
            e.paint(g);
        }
    }

    public void step(long now) {
       for(Entity e : this.entities) {
           e.step(now);
       }
    }

}
