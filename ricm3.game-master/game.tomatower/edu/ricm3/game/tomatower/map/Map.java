package edu.ricm3.game.tomatower.map;

import edu.ricm3.game.tomatower.Options;
import edu.ricm3.game.tomatower.entities.*;
import edu.ricm3.game.tomatower.entities.enums.Direction;
import edu.ricm3.game.tomatower.entities.enums.Kind_Weapon;
import edu.ricm3.game.tomatower.entities.enums.ObstaclesKind;
import edu.ricm3.game.tomatower.mvc.Model;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Map {
    Model model;

    private ArrayList<ArrayList<Cell>> cells;
    private int cell_size = 50;
    private int nb_cell_horizontal;
    private int nb_cell_vertical;
    private Cell cell_portal_in;


    public Map(Model c_model) {
        this.model = c_model;
        this.cells = new ArrayList<>();
    }


    public int getCellSize() {
        return this.cell_size;
    }
    
    public int[] getMapDimention() {
    	return new int[] {cell_size*nb_cell_horizontal,cell_size*nb_cell_vertical};
    }

    public Cell getCell(int x, int y) {
        if(x < 0 || x > nb_cell_horizontal-1 || y < 0 || y > nb_cell_vertical -1)
            return null;
        else
            return this.cells.get(y).get(x);
    }

    private void setCellsMap(ArrayList<ArrayList<Cell>> c) {
        this.cells = c;
    }


    public Iterator<Cell> getCellsIterator() {
        ArrayList<Cell> cells = new ArrayList<>();

        for(ArrayList<Cell> line : this.cells) {
            cells.addAll(line);
        }

        return cells.iterator();
    }

    public boolean freeCell(Cell cell, Entity e) {
        return  (cell != null)  && (cell.isFree(e));
    }

    public Entity getEntityCell(Cell c) {

        if(!c.getEntities().isEmpty())
            return c.getEntities().get(0);
        else
            return null;
    }

    public void step(long now) {
        Iterator<Cell> iter_cells_mainmap = this.getCellsIterator();
        while (iter_cells_mainmap.hasNext()) {
            Cell c = iter_cells_mainmap.next();
            c.step(now);
        }
    }

    public void setCellIn(Cell cell) {
        this.cell_portal_in = cell;
    }
    
    public Cell getCellIn() {
    	return this.cell_portal_in;
    }


    public boolean isVisible() {
        return this.model.getCurrentMap() == this;
    }

    public void initMap(String path) {
        /*

        Faire verification de map avec compteur
        Principal : Joueur, spawn ennemi, spawn joueur
        Defis : portal to principal, spawn ennemi
         */
        System.out.println(path);
        File map_file = new File("game.tomatower/maps/" + path);

        try {
            long start1 = System.nanoTime();
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
            ArrayList<ArrayList<Cell>> cells = new ArrayList<>();
            Iterator<String[]> iter_map = map_langugage.iterator();
            Crystal main_crystal = null;

            while (iter_map.hasNext()) {
                String[] line_elements = iter_map.next();

                ArrayList<Cell> cells_line = new ArrayList<>();
                for (int col = 0; col < line_elements.length; col++) {
                    Cell cell = new Cell(col, row, this);
                    cells_line.add(cell);
                    switch (line_elements[col]) {
                        case "E":

                            break;
                        case "P":
                            System.out.println("PERSO");
                            // TEST
                            Weapon w = new Weapon(this.model, 1, 7, Direction.UP, Kind_Weapon.Yellow);
                            this.model.setPlayer(new Player(this.model,  this.model.getSprites().sprite_player, 1, cell, Direction.UP, w));
                            break;
                        case "Os":
                            //System.out.println("Stone");
                            new Obstacle(this.model,  this.model.getSprites().sprite_cailloux, 1,cell, ObstaclesKind.Stone);
                            break;

                        case "Ol":
                            new Obstacle(this.model,  this.model.getSprites().sprite_lac, 1, cell, ObstaclesKind.Lake);
                            break;
                            
                        case "Ow":
                            new Obstacle(this.model,  this.model.getSprites().sprite_mur, 1, cell, ObstaclesKind.Lake);
                            break;
                            
                        case "Ot":
                            new Obstacle(this.model,  this.model.getSprites().sprite_arbre, 1, cell, ObstaclesKind.Lake);
                            break;
                            
                        case "C":
                            if(main_crystal == null) {
                                main_crystal = new Crystal(this.model, this.model.getSprites().sprite_crystal[0], 2, cell, ObstaclesKind.CRYSTAL, null);
                                this.model.setCrystal(main_crystal);
                            } else {
                                new Crystal(this.model, this.model.getSprites().sprite_crystal[0], 0, cell, ObstaclesKind.CRYSTAL, main_crystal);
                            }                            

                            break;
                        case "Pc":
                            new Portal(this.model, this.model.getSprites().sprite_portal, 1, cell, ObstaclesKind.PORTAL_TO_CHALLENGE);
                            break;
                        case "Ps":
                            new Portal(this.model, this.model.getSprites().sprite_portal, 1, cell, ObstaclesKind.PORTAL_TO_STORE);
                            break;
                        case "Pi":
                            new Portal(this.model, this.model.getSprites().sprite_portal_in, 1, cell, ObstaclesKind.PORTAL_DESTINATION);
                            this.cell_portal_in = cell;
                            break;
                        case "Po":
                            new Portal(this.model, this.model.getSprites().sprite_portal, 1, cell, ObstaclesKind.PORTAL_TO_GAME);
                            break;
                        case "Suy":
                            new Upgrade(this.model, this.model.getSprites().sprite_upgrade_yellow[0], 1, cell, ObstaclesKind.UPGRADE, this.model.getWeapons().get(Kind_Weapon.Yellow), 200);
                            break;
                        case "Sur":
                            new Upgrade(this.model, this.model.getSprites().sprite_upgrade_red[0], 1, cell, ObstaclesKind.UPGRADE, this.model.getWeapons().get(Kind_Weapon.Red), 200);
                            break;
                        case "Str":
                            new Product(this.model, this.model.getSprites().sprite_tower_red[0], 1, cell, ObstaclesKind.UPGRADE, this.model.getWeapons().get(Kind_Weapon.Red), 1000);
                            break;    
                    }
                }
                cells.add(cells_line);
                row++;
            }
            this.setCellsMap(cells);
            long end1 = System.nanoTime();
            if(Options.ECHO_GAME_STATE)
                System.out.println("Init map finished in " + (end1 - start1) + " ns");
            
            reader.close();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
        
    }

}