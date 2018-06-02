package edu.ricm3.game.tomatower;

import edu.ricm3.game.tomatower.entities.Entity;
import edu.ricm3.game.tomatower.entities.Living;

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
        // SI un hit = bombe sur une case
        Iterator<Entity> iter = this.getEntitiesIterator();
        while (iter.hasNext()) {
            Entity e = iter.next();
            if(e instanceof Living)
                ((Living) e).damage(power);
        }

        /* OU si on fait des degat sur une seul entité par hit
        Entity e = entities.get(0);
        if(e instanceof Living)
            ((Living) e).damage(power);
         */
    }

    public void removeEntity(Entity e) {
        this.getEntities().remove(e);
    }

    public boolean isFree() {

        Iterator<Entity> iter = this.getEntitiesIterator();
        while(iter.hasNext()) {
            Entity e = iter.next();
            if(e.isVisible())
                return false;
        }
        return true;
    }

    public void paint(Graphics g) {
        Iterator<Entity> iter = this.getEntitiesIterator();
        while (iter.hasNext()) {
            Entity e = iter.next();
            e.paint(g);
        }
    }

    public void step(long now) {
        Iterator<Entity> iter = this.getEntitiesIterator();
        while (iter.hasNext()){
            Entity e = iter.next();
            e.step(now);
        }
    }

}
