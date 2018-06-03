/*
 * Educational software for a basic game development
 * Copyright (C) 2018  Pr. Olivier Gruber
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.ricm3.game.tomatower.mvc;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;

import edu.ricm3.game.GameModel;
import edu.ricm3.game.tomatower.Cell;
import edu.ricm3.game.tomatower.Map;
import edu.ricm3.game.tomatower.entities.*;

public class Model extends GameModel {

    Sprites game_sprites;
    Map map;

    Player player;
    ArrayList<Mobs> mobs;
    ArrayList<Inert> obstacles;
    ArrayList<Tower> towers;
    Crystal crystal;

    Overhead m_overhead = new Overhead();

    public Model() {
        game_sprites = new Sprites();
        mobs = new ArrayList<>();
        obstacles = new ArrayList<>();
        towers = new ArrayList<>();
        map = new Map(this);
        map.initMap();
    }

    @Override
    public void shutdown() {

    }

    public Overhead getOverhead() {
        return this.m_overhead;
    }

    public ArrayList<Mobs> getMobs() {
        return this.mobs;
    }

    public void addMobs(Mobs m) { this.mobs.add(m);}

    public ArrayList<Inert> getObstacles() {
        return this.obstacles;
    }

    public void addObstacle(Inert o) { this.obstacles.add(o);}

    public Player getPlayer() { return this.player;}

    public ArrayList<Tower> getTowers() { return this.towers;}

    public void addTower(Tower t) {this.towers.add(t);}

    public ArrayList<Entity> getAllEntities() {
        ArrayList<Entity> entities = new ArrayList<>();
        entities.addAll(this.getMobs());
        entities.addAll(this.getObstacles());
        entities.addAll(this.getTowers());

        return entities;
    }

    public Sprites getSprites() {
        return this.game_sprites;
    }

    public Crystal getCrystal() {
        return this.crystal;
    }

    public Map getMap() {
        return this.map;
    }

    public void setPlayer(Player p) {
        this.player = p;
    }


    public BufferedImage getSpriteTower() {
        return this.game_sprites.sprite_tower;
    }

    /**
     * Simulation step.
     *
     * @param now is the current time in milliseconds.
     */
    @Override
    public void step(long now) {
        m_overhead.overhead();
    }

}











