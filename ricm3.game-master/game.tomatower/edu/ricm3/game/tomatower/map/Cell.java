package edu.ricm3.game.tomatower.map;

import edu.ricm3.game.tomatower.Options;
import edu.ricm3.game.tomatower.entities.Entity;
import edu.ricm3.game.tomatower.entities.Mobs;
import edu.ricm3.game.tomatower.entities.enums.Kind;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Cell {
    private int col;
    private int row;
    private Map map;
    private ArrayList<Entity> entities;

    public Cell(int c_col, int c_row, Map c_map) {
        this.col = c_col;
        this.row = c_row;
        this.map = c_map;
        this.entities = new ArrayList<>();
    }
    
    public void paint(Graphics g) {
        for(Entity e : this.entities) {
        	if(e.isVisible())
        		e.paint(g);
        }
    }

    public void step(long now) {
  
    	Entity e;
    	for(int i = 0; i < this.entities.size(); i++) {  // Pas d'itérateur car certaine actions modifient entities
    		e = this.entities.get(i);
    		e.step(now);
    	}
    }
    
    public boolean containEntityKind(Kind k) {
    	if(k.equals(Kind.Void) && this.entities.size() == 0) {
    		return true;
    	}
    		
    	boolean found = false;
    	Iterator<Entity> iterator = this.entities.iterator();
    	while(iterator.hasNext() && !found) {
    		Entity entity = iterator.next();
    		if(entity.getKind().equals(k))
    			found = true;
    	}
    	return found;
    }

    public void damage(int power) {
        // Si on considère que un hit est une bombre (fait des dégats sur toutes les entités)
        for (Entity e : this.entities) {
        	System.out.println("Hit sur la case ("+this.col + ", "+this.row +"), d'une puissance de " + power);
            e.damage(power);
        }
    }

    

    /*
    Return if the cell is free for the entity e,
    if e is null, the function return if the cell contain someting
     */
    public boolean isFree(Entity e) {
    	if(this.entities.size() == 0) {
    		return true;
    	}else {
    		if(e == null) {
    			for(Entity entity : this.entities) {
                    if(entity.isVisible())
                        return false;
                }
    			return true;
    		} else {
    			
    			Iterator<Entity> iter_entity = this.entities.iterator();
    			Iterator<Class<?>> iter_class = e.getEntitiesDestinationAllowed().iterator();
    			boolean allowed = true;
    			while(iter_entity.hasNext() && allowed) {
    				Entity entity = iter_entity.next();
    				allowed = false;
    				while(iter_class.hasNext() && !allowed) {
    					Class<?> class_colision = iter_class.next();
    					if(entity.isVisible() && entity.getClass().equals(class_colision) && !allowed) {
    						allowed = true;
    					}	
    				}
    			}
    			if(Options.ECHO_GAME_STATE && !allowed) {
    				System.out.println("Cell destination not free");
    			}
    			return allowed;
    		}
    	}
    }
    

    
    public ArrayList<Entity> getEntities() {
        return this.entities;
    }

    public void addEntity(Entity e) {
        this.entities.add(e);
    }

    public int[] getPosition() {
        return new int[]{this.col, this.row};
    }
   
    public Map getMap() {
    	return this.map;
    }

    public Iterator<Entity> getEntitiesIterator() {
        return this.entities.iterator();
    }
    
    public void removeEntity(Entity e) {
        this.entities.remove(e);
    }

    
    
    

}