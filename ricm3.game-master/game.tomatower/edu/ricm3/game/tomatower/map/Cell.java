package edu.ricm3.game.tomatower.map;

import edu.ricm3.game.tomatower.entities.Entity;
import edu.ricm3.game.tomatower.entities.enums.Kind;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

public class Cell {
	private int col;
	private int row;
	private Map map;
	private ArrayList<Entity> entities;

	// TEST
	boolean displayed_damage = false;
	int id_explosion = 0;
	int id_last_explosion = 8;
	long time_damage = 0;
	long last_paint = 0;
	long speed = 300L;
	BufferedImage[] explosion_sprite;

	public Cell(int c_col, int c_row, Map c_map) {
		this.col = c_col;
		this.row = c_row;
		this.map = c_map;
		this.entities = new ArrayList<>();
		explosion_sprite = this.map.model.getSprites().sprite_explosion;
	}

	public void paint(Graphics g) {
		for (Entity e : this.entities) {
			if (e.isVisible())
				e.paint(g);
		}

		if (displayed_damage) {
			paintExplosion(g);
		}

	}

	public void paintExplosion(Graphics g) {
		if (id_explosion < id_last_explosion && last_paint + speed > time_damage) {
			g.drawImage(explosion_sprite[id_explosion], col * 50, row * 50, 50, 50, null);
			last_paint = time_damage;
			id_explosion++;
		} else { // fin de l'explosion
			displayed_damage = false;
			id_explosion = 0;
		}
	}

	public void step(long now) {

		Entity e;
		for (int i = 0; i < this.entities.size(); i++) { // Pas d'itérateur car certaine actions modifient entities
			e = this.entities.get(i);
			e.step(now);
		}
		if (displayed_damage) {
			time_damage = now;
			last_paint = now;
		}
	}

	public boolean containEntityKind(Kind k) {
		if (k.equals(Kind.Void) && this.entities.size() == 0) {
			return true;
		}

		boolean found = false;
		Iterator<Entity> iterator = this.entities.iterator();
		while (iterator.hasNext() && !found) {
			Entity entity = iterator.next();
			if (entity.getKind().equals(k))
				found = true;
		}
		return found;
	}

	public void damage(int power) {
		// Si on considère que un hit est une bombre (fait des dégats sur toutes les
		// entités)
		int nb_entities = entities.size();
		if (nb_entities > 0)
			entities.get(nb_entities - 1).damage(power);

		// TEST
		displayed_damage = true;

	}

	/*
	 * Return if the cell is free for the entity e, if e is null, the function
	 * return if the cell contain someting
	 */
	public boolean isFree(Entity e) {
		if (this.entities.size() == 0) {
			return true;
		} else {
			if (e == null) {
				for (Entity entity : this.entities) {
					if (entity.isVisible())
						return false;
				}
				return true;
			} else {

				Iterator<Entity> iter_entity = this.entities.iterator();

				boolean allowed = true;
				while (iter_entity.hasNext() && allowed) {
					Entity entity = iter_entity.next();
					allowed = false;
					Iterator<Class<?>> iter_class = e.getEntitiesDestinationAllowed().iterator();
					while (iter_class.hasNext() && !allowed) {
						Class<?> class_colision = iter_class.next();
						if (entity.isVisible() && entity.getClass().equals(class_colision) && !allowed) {
							allowed = true;
						}
					}
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
		return new int[] { this.col, this.row };
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