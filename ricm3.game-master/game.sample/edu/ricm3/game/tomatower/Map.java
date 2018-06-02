package edu.ricm3.game.tomatower;

import edu.ricm3.game.tomatower.entities.*;
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

    public Map(Model c_model, boolean c_visible) {
        this.model = c_model;
        this.cells = new ArrayList<>();
    }

    public int getCellSize() {
        return this.cell_size;
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

        Iterator<ArrayList<Cell>> iter_line_cells = this.model.getMainMap().cells.iterator();
        while (iter_line_cells.hasNext()) {
            ArrayList<Cell> line_cell = iter_line_cells.next();
            cells.addAll(line_cell);
        }

        Iterator<Cell> iter_cells = cells.iterator();
        return iter_cells;
    }

    public void initMap() {


        File map_file = new File("game.sample/maps/map1.txt");

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
            while (iter_map.hasNext()) {
                String[] line_elements = iter_map.next();

                ArrayList<Cell> cells_line = new ArrayList<>();
                for (int col = 0; col < line_elements.length; col++) {
                    Cell cell = new Cell(col, row);
                    cells_line.add(cell);
                    switch (line_elements[col]) {
                        case "E":

                            break;
                        case "P":
                            System.out.println("PERSO");
                            this.model.setPlayer(new Player(this.model, true, this.model.getSprites().sprite_player, 1, cell, Direction.UP));
                            break;
                        case "Os":
                            System.out.println("Stone");
                            Inert stone = new Inert(this.model, false, this.model.getSprites().sprite_cailloux, 1,cell, ObstaclesKind.Stone);
                            break;

                        case "Ol":
                            Inert lac = new Inert(this.model, false, this.model.getSprites().sprite_lac, 1, cell, ObstaclesKind.Lake);
                            break;

                        case "C":
                            if (this.model.getCrystal() == null) {
                                System.out.println("map init : crystal");
                                model.setCrystal(new Crystal(this.model, this.model.getSprites().sprite_crystal, col, row));
                            }
                            break;
                    }
                }
                cells.add(cells_line);
                row++;
            }
            this.setCellsMap(cells);
            long end1 = System.nanoTime();
            System.out.println("Init map : " + (end1 - start1));

        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
    }

    public boolean freeCell(Cell cell) {
        return  (cell != null)  && (cell.isFree());
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

    public void setVisible() {
        this.model.setCurrentMap(this);
    }

    public boolean isVisible() {
        return this.model.getCurrentMap() == this;
    }
}
