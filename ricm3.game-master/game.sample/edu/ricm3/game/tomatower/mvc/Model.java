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
import java.util.ArrayList;
import java.util.Iterator;

import edu.ricm3.game.GameModel;
import edu.ricm3.game.tomatower.Cell;
import edu.ricm3.game.tomatower.Map;
import edu.ricm3.game.tomatower.entities.*;

public class Model extends GameModel {

    Sprites game_sprites;

    private Map main_map;
    private ArrayList<Map> maps_challenge;
    private Map map_store;
    private Map current_map;

    Player player;
    //ArrayList<Mobs> mobs;
    //ArrayList<Inert> obstacles;
    //ArrayList<Tower> towers;
    Crystal crystal;

    Overhead m_overhead = new Overhead();

    public Model() {
        game_sprites = new Sprites();
        this.initMaps();
        current_map = main_map;

        /*
        VOIR CI DESSOUS LES FONCTIONS ASSOCIEES
        mobs = new ArrayList<>();
        obstacles = new ArrayList<>();
        towers = new ArrayList<>();
        */
    }

    @Override
    public void shutdown() {

    }

    public Overhead getOverhead() {
        return this.m_overhead;
    }

    /*
    LES FONCTIONS SUIVANTES SONT UTILES (ONT DU SENS) UNIQUEMENT
    SI ON DECOMMENTE LA LIGNE ASSOCIE DANS LES CONSTRUCTEURS
    RESPECTIFS POUR AJOUTER LES OBJETS CREERS AUX COLLECTIONS
    mobs, obstacles et towers.

    public ArrayList<Mobs> getMobs() {
        return this.mobs;
    }

    public void addMobs(Mobs m) {
        this.mobs.add(m);
    }

    public ArrayList<Inert> getObstacles() {
        return this.obstacles;
    }

    public void addObstacle(Inert o) {
        this.obstacles.add(o);
    }

    public ArrayList<Tower> getTowers() {
        return this.towers;
    }

    public void addTower(Tower t) {
        this.towers.add(t);
    }
    public ArrayList<Entity> getAllEntities() {
        ArrayList<Entity> entities = new ArrayList<>();
        entities.addAll(this.getMobs());
        entities.addAll(this.getObstacles());
        entities.addAll(this.getTowers());

        return entities;
    }*/

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player p) {
        this.player = p;
    }

    public Sprites getSprites() {
        return this.game_sprites;
    }

    public Crystal getCrystal() {
        return this.crystal;
    }

    public void setCrystal(Crystal c) {
        this.crystal = c;
    }

    public Map getMainMap() {
        return this.main_map;
    }

    public Map getStoreMap() {
        return this.map_store;
    }

    public ArrayList<Map> getChallengesMap() {
        return this.maps_challenge;
    }

    public void setCurrentMap(Map m) {
        this.current_map = m;
    }

    public Map getCurrentMap() {
        return this.current_map;
    }

    public void addChallengeMap(Map m) {
        this.getChallengesMap().add(m);
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
        this.getMainMap().step(now);
        if(this.current_map != getMainMap())
            current_map.step(now);

    }

    /*

    Pour l'instant un map challenge,
    A faire : un dossier + parcourir toutes les maps de ce fichier
     */
    public void initMaps() {
        main_map = new Map(this);
        this.getMainMap().initMap("map1.txt");

        this.maps_challenge = new ArrayList<>();
        Map map_challenge = new Map(this);
        this.addChallengeMap(map_challenge);

        Iterator<Map> iter_map_challenge = this.getChallengesMap().iterator();
        while (iter_map_challenge.hasNext()) {
            Map m = iter_map_challenge.next();
            m.initMap("defis");
        }

    }

}











