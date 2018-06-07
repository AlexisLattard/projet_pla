package edu.ricm3.game.tomatower.entities;

import edu.ricm3.game.tomatower.entities.enums.Direction;
import edu.ricm3.game.tomatower.entities.enums.Kind;
import edu.ricm3.game.tomatower.map.Cell;
import edu.ricm3.game.tomatower.map.Map;
import edu.ricm3.game.tomatower.mvc.Model;

import java.awt.Graphics;
import java.util.ArrayList;


public abstract class Entity {
    Model model;
    protected Cell cell;
    protected boolean movement;
    double scale = 1;
    Kind kind;
    protected boolean visible;
    protected ArrayList<Class<?>> entities_destination_allowed; 	// Utile pour determiner sur quelle cases l'entité peut se trouver
    															// Ex : Mobs peut aller sur d'autre case avec des mobs
    																//      Player peut aller sur des Portal

	Entity(Model c_model, Boolean c_movement, double c_scale, ArrayList<Class<?>> c_collisions, Cell c_cell) {
		this(c_model,c_movement,c_scale,c_collisions);
    	this.visible = true;
		this.addEntityOnCell(c_cell);
	}

    Entity(Model c_model, Boolean c_movement, double c_scale, ArrayList<Class<?>> c_collisions) {
    	this.model =  c_model;
    	this.movement =  c_movement;
    	this.scale =  c_scale;
    	this.entities_destination_allowed =  c_collisions;
    	this.visible = false;

    }

    public boolean isVisible() {
    	return this.visible;
    }

	public boolean addEntityOnCell(Cell c) {
		if (c != null && c.getMap().freeCell(c, this)) {
			// System.out.println("PUT ENTITY : (" + c.getPosition()[0] + " " +
			// c.getPosition()[1] + ")");
			if (this.cell != null) {
				this.cell.removeEntity(this);
				System.out.println("Remove");
			}
				
			c.addEntity(this);
			this.cell = c;
			this.visible = true;
			return true;
		} else {
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
	}

    public void step(long now) {
    	// if(isVisble) ou if(isActive) pour pas que l'entité continue son comportement dans le sac par exemple
        // Comportment - Automate
    }

	public boolean canMove() {
		return this.movement;
	}

	public int[] getPosition() {
		return this.cell.getPosition();
	}

	// Actions

	public void move(Direction d) {
	}

	public void turn(Direction d) {
	}

	public void jump() {
		return;
	}

	public void protect() {
		return;
	} // protection

	public abstract void hit();

	public abstract void damage(int power);

	public abstract void pick(); // ramasser sur la map

	public abstract void throwAction(); // lancer ce que on a dans la main

	public abstract void store(); // mettre dans son sac

	public abstract void getBagEntity(); // take entity

	public abstract void power(int power); // recuperation d'energie
    
    public static ArrayList<Class<?>> initColisions() {
    	return new ArrayList<Class<?>>();
    }
    
    public ArrayList<Class<?>> getEntitiesDestinationAllowed() {
    	return this.entities_destination_allowed;
    }
    
    public Cell getCell() {
    	return this.cell;
    }
    
    
    public Map getMap() {
    	return this.cell.getMap();
    }
    
    



}