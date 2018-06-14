package edu.ricm3.game.tomatower.entities;

import java.awt.image.BufferedImage;

import edu.ricm3.game.tomatower.entities.enums.Kind;
import edu.ricm3.game.tomatower.entities.enums.ObstaclesKind;
import edu.ricm3.game.tomatower.map.Cell;
import edu.ricm3.game.tomatower.mvc.Model;

public abstract class Buyable extends Inert {

	protected Weapon weapon;
	protected int price;

	public Buyable(Model c_model, BufferedImage c_sprite, double c_scale, Cell c_cell, ObstaclesKind c_kind,
			Weapon c_weapon, int c_price) {
		super(c_model, false, c_sprite, c_scale, c_cell, c_kind, Kind.Takable);
		this.weapon = c_weapon;
		this.price = c_price;
	}

	@Override
	public void step(long now) {

	}
	
	
	public int getPrice(){
		return this.price;
	}
	public void increasePrice(int price) {
		this.price += price;
	}
	
	public boolean buy(int price){
		if(this.model.getPlayer().getMoney() >= price){
			this.model.getPlayer().decreaseMoney(price);
			return true;
		} else {
			return false;
		}
	}
	
}
