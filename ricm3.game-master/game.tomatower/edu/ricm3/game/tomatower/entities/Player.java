package edu.ricm3.game.tomatower.entities;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import edu.ricm3.game.tomatower.Options;
import edu.ricm3.game.tomatower.entities.enums.Direction;
import edu.ricm3.game.tomatower.map.Cell;
import edu.ricm3.game.tomatower.mvc.Model;

public class Player extends Living {

    private ArrayList<Entity> bag;
    private Entity hand = null;
    private int money = 2500;

    public Player(Model c_model, BufferedImage c_sprite, double c_scale, Cell c_cell, Direction c_direction,Weapon c_weapon) {
        super(c_model, true, c_sprite, c_scale, c_cell, c_direction, c_weapon, initColisions());
        bag = new ArrayList<>();
        
        
        // Pour tester
//        Tower t1 = new Tower(this.model,  this.model.getSprites().sprite_tower, this.model.getWeapons().get("yellow"));
//        Tower t2 = new Tower(this.model,  this.model.getSprites().sprite_tower, this.model.getWeapons().get("yellow"));
//        Tower t3 = new Tower(this.model,  this.model.getSprites().sprite_tower, this.model.getWeapons().get("red"));
//        Tower t4 = new Tower(this.model,  this.model.getSprites().sprite_tower, this.model.getWeapons().get("red"));
//        bag.add(t1);bag.add(t2);bag.add(t3);bag.add(t4);
    }
    
    public static ArrayList<Class<?>> initColisions() {
    	ArrayList<Class<?>> res = new ArrayList<Class<?>>();
		res.add(Portal.class);
		res.add(Product.class);
		res.add(Upgrade.class);
    	return res;
    }

	public void getBagEntity() {
		if (this.bag.size() >= 1) {
			if (hand == null) {
				hand = this.bag.remove(0);
			}
		}
	}

	public void throwAction() {
		if (Options.ECHO_GAME_STATE && hand == null)
			System.out.println("Rien dans la main");

		if (hand != null && hand.addEntityOnCell(this.getFrontCell())) {
			// Si vrai, alors la tourelle a été posée, donc plus rien en main
			hand = null;
		}
	}

	public void pick() {
		Entity entity = this.model.getCurrentMap().getEntityCell(this.getFrontCell());

		if (entity instanceof Tower) {
			if (hand != null) // On a déjà quelque chose en main, on le remet dans le sac
				this.bag.add(hand);
			entity.removeEntityFromCell();
			hand = (Tower) (entity);
		}
	}

	public void store() {
		if (hand != null) {
			bag.add(hand);
			hand = null;
		}
	}

    public void store() {
        if(hand != null) {
            bag.add(hand);
            hand = null;
        }
    }
    
    public void addBagProduct(Entity entity) {
        if (entity instanceof Tower) {
        	this.bag.add(entity);
        }
    }
    
    public int getMoney() {
    	return this.money;
    }
    
    public void decreaseMoney(int money) {
    	this.money -=money;
    }
    
    public void increaseMoney(int money) {
    	this.money +=money;
    }




}