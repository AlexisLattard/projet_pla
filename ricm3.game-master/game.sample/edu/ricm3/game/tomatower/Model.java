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
package edu.ricm3.game.tomatower;

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

public class Model extends GameModel {
    int cell_size = 50;
    int nb_cell_horizontal;
    int nb_cell_vertical;

    Player player;
    ArrayList<Mobs> mobs;
    ArrayList<Inert> obstacles;
    ArrayList<Tower> towers;
    Crystal crystal;

    BufferedImage sprite_player;
    BufferedImage sprite_mobs;
    BufferedImage sprite_cailloux;
    BufferedImage sprite_arbre;
    BufferedImage sprite_tower;
    BufferedImage sprite_lac;
    BufferedImage sprite_crystal;
    BufferedImage sprite_spawn_mobs;

    Overhead m_overhead = new Overhead();

    public Model() {
        loadSprites();
        mobs = new ArrayList<>();
        obstacles = new ArrayList<>();
        towers = new ArrayList<>();
        player = new Player(this, true, sprite_player, 1, 0, 0, Direction.LEFT);
        initMap();
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



    /**
     * Simulation step.
     *
     * @param now is the current time in milliseconds.
     */
    @Override
    public void step(long now) {

        m_overhead.overhead();

    }

    private void loadSprites() {

        File imageFile = new File("game.sample/sprites/player.png");
        try {
            sprite_player = ImageIO.read(imageFile);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }

        imageFile = new File("game.sample/sprites/mobs.png");
        try {
            sprite_cailloux = ImageIO.read(imageFile);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }

        imageFile = new File("game.sample/sprites/stone.png");
        try {
            sprite_cailloux = ImageIO.read(imageFile);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }


        imageFile = new File("game.sample/sprites/tower.png");
        try {
            sprite_tower = ImageIO.read(imageFile);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }

        imageFile = new File("game.sample/sprites/crystal.png");
        try {
            sprite_crystal = ImageIO.read(imageFile);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }

        imageFile = new File("game.sample/sprites/mobs_spawn.png");
        try {
            sprite_spawn_mobs = ImageIO.read(imageFile);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }
    }

    void initMap() {
        File map_file = new File("game.sample/maps/map1.txt");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(map_file));
            ArrayList<String[]> map_langugage = new ArrayList<>();
            String line;

            int row = 0;
            while ((line = reader.readLine()) != null && line != "") {
                map_langugage.add(line.split(" "));
                row++;
            }

            this.nb_cell_vertical = row;
            this.nb_cell_horizontal = map_langugage.get(0).length;

            row = 0;
            Iterator<String[]> iter_map = map_langugage.iterator();
            while (iter_map.hasNext()) {
                String[] line_elements = iter_map.next();

                for (int col = 0; col < line_elements.length; col++) {

                    switch (line_elements[col]) {
                        case "E":

                            break;
                        case "P":
                            System.out.println("PERSO");
                            this.getPlayer().initPosition(col, row);
                            break;
                        case "Os":
                            System.out.println("Stone");
                            Inert stone = new Inert(this, false, sprite_cailloux, 1, col, row, ObstaclesKind.Stone);
                            break;

                        case "Ol":
                            Inert lac = new Inert(this, false, sprite_lac, 1, col, row, ObstaclesKind.Lake);
                            break;

                        case "C":
                            if (this.crystal == null) {
                                System.out.println("map init : crystal");
                                this.crystal = new Crystal(this, sprite_crystal, col, row);
                            }
                            break;
                    }
                }
                row++;
            }

        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
    }

    public boolean freeCell(int cell_x, int cell_y) {

        if(cell_x < 0 || cell_x > nb_cell_horizontal -1 || cell_y < 0 || cell_y > nb_cell_vertical -1)
            return false;

        int[] pos_entity;
        ArrayList<Entity> entities = this.getAllEntities();
        Iterator<Entity> entitiesIterator = entities.iterator();
        while(entitiesIterator.hasNext()) {
            Entity m = entitiesIterator.next();
            pos_entity = m.getPosition();
            if(pos_entity[0] == cell_x && pos_entity[1] == cell_y && m.isVisible()) {
                return false;
            }
        }

        if(crystal != null) {
            int [][] pos_crystal = crystal.getPosition();
            for(int i = 0 ; i < 4; i++) {
                if(cell_x == pos_crystal[i][0] && cell_y == pos_crystal[i][1])
                    return false;
            }
        }

        return true;
    }

    public Entity getEntityCell(int cell_x, int cell_y) {


        if(cell_x < 0 || cell_x > nb_cell_horizontal -1 || cell_y < 0 || cell_y > nb_cell_vertical -1)
            return null;

        int[] pos_entity;
        ArrayList<Entity> entities = this.getAllEntities();
        Iterator<Entity> entitiesIterator = entities.iterator();
        while(entitiesIterator.hasNext()) {
            Entity m = entitiesIterator.next();
            pos_entity = m.getPosition();
            if(pos_entity[0] == cell_x && pos_entity[1] == cell_y && m.isVisible()) {
                return m;
            }
        }

        int[] pos_player = this.getPlayer().getPosition();
        if(cell_x == pos_player[0] && cell_y == pos_player[1]) {
            return this.getPlayer();
        }
        return null;
    }



}











