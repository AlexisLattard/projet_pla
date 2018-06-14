package edu.ricm3.game.tomatower.map;

import edu.ricm3.game.tomatower.Options;
import edu.ricm3.game.tomatower.entities.*;
import edu.ricm3.game.tomatower.entities.enums.Direction;
import edu.ricm3.game.tomatower.entities.enums.EntityName;
import edu.ricm3.game.tomatower.entities.enums.ObstaclesKind;
import edu.ricm3.game.tomatower.mvc.Model;
import static edu.ricm3.game.tomatower.LevelDesign.*;

import java.awt.Color;
import java.awt.Graphics;
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

	public void step(long now) {
		Iterator<Cell> iter_cells_mainmap = this.getCellsIterator();
		while (iter_cells_mainmap.hasNext()) {
			Cell c = iter_cells_mainmap.next();
			c.step(now);
		}
	}

	public void paint(Graphics g) {
		Iterator<Cell> iter_cells = this.getCellsIterator();
		while (iter_cells.hasNext()) {
			Cell c = iter_cells.next();
			c.paint(g);
		}

		// Affichage de la main du personnage sur la cellule devant lui
		Player player = this.model.getPlayer();
		Tower hand = player.getHand();
		if (hand != null && this.model.getPlayer().isVisible()) {
			Cell dest = player.getCellDirection(Direction.FRONT, 1);
			if (dest != null) {
				int dest_position[] = dest.getPosition();
				int cell_size = this.cell_size;
				int x = dest_position[0] * cell_size;
				int y = dest_position[1] * cell_size;

				if (dest.isFree(hand)) {
					g.setColor(new Color(0, 255, 0, 100));
				} else {
					g.setColor(new Color(255, 0, 0, 100));
				}
				g.fillRect(x, y, cell_size, cell_size);
				g.drawImage(((Tower) hand).getSprite()[player.getDirection().getValue()], x, y, cell_size, cell_size,null);
			}
		}
	}

	public int getCellSize() {
		return this.cell_size;
	}

	public int[] getMapDimention() {
		return new int[] { cell_size * nb_cell_horizontal, cell_size * nb_cell_vertical };
	}

	public Cell getCell(int x, int y) {
		if (x < 0 || x > nb_cell_horizontal - 1 || y < 0 || y > nb_cell_vertical - 1)
			return null;
		else
			return this.cells.get(y).get(x);
	}

	private void setCellsMap(ArrayList<ArrayList<Cell>> c) {
		this.cells = c;
	}

	public Iterator<Cell> getCellsIterator() {
		ArrayList<Cell> cells = new ArrayList<>();

		for (ArrayList<Cell> line : this.cells) {
			cells.addAll(line);
		}

		return cells.iterator();
	}

	public boolean freeCell(Cell cell, Entity e) {
		return (cell != null) && (cell.isFree(e));
	}

	public Entity getEntityCell(Cell c) {

		if (c != null && !c.getEntities().isEmpty())
			return c.getEntities().get(0);
		else
			return null;
	}

	public Cell getCellIn() {
		return this.cell_portal_in;
	}

	public boolean isVisible() {
		return this.model.getCurrentMap() == this;
	}

	public void initMap(String path) {
		/*
		 * 
		 * Faire verification de map avec compteur Principal : Joueur, spawn ennemi,
		 * spawn joueur Defis : portal to principal, spawn ennemi
		 */
		File map_file = new File("game.tomatower/maps/" + path);

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
			ArrayList<ArrayList<Cell>> cells = new ArrayList<>();
			Iterator<String[]> iter_map = map_langugage.iterator();
			Crystal main_crystal = null;
			MobSpawn main_mobSpawn = null;

			while (iter_map.hasNext()) {
				String[] line_elements = iter_map.next();

				ArrayList<Cell> cells_line = new ArrayList<>();
				for (int col = 0; col < line_elements.length; col++) {
					Cell cell = new Cell(col, row, this);
					cells_line.add(cell);
					switch (line_elements[col]) {

					case "P":
						this.model.setPlayer(new Player(this.model, this.model.getSprites().sprite_player, 1, cell,
								Direction.NORTH, this.model.getWeapons().get(EntityName.Player),
								this.model.getAutomatons().get(EntityName.Player)));
						break;
					case "Os":
						// System.out.println("Stone");
						new Obstacle(this.model, this.model.getSprites().sprite_cailloux, 1, cell, ObstaclesKind.Stone);
						break;

					case "Ol":
						new Water(this.model, this.model.getSprites().sprite_lac, 1, cell, ObstaclesKind.Lake);

						break;

					case "Ow":
						new Obstacle(this.model, this.model.getSprites().sprite_mur, 1, cell, ObstaclesKind.Lake);
						break;

					case "Ot":
						new Obstacle(this.model, this.model.getSprites().sprite_arbre, 1, cell, ObstaclesKind.Lake);
						break;

					case "C":
						if (main_crystal == null) {
							main_crystal = new Crystal(this.model, this.model.getSprites().sprite_crystal, 2, cell,
									null);
							this.model.setCrystal(main_crystal);
						} else {
							new Crystal(this.model, this.model.getSprites().sprite_crystal, 0, cell, main_crystal);
						}

						break;
					case "Ms":
						if (main_mobSpawn == null) {
							main_mobSpawn = new MobSpawn(this.model, this.model.getSprites().sprite_spawn_mobs, 2, cell,
									null);
							this.model.setMobSpawn(main_mobSpawn);
						} else {
							new MobSpawn(this.model, this.model.getSprites().sprite_spawn_mobs, 0, cell, main_mobSpawn);
						}
					case "Pc":
						new Portal(this.model, this.model.getSprites().sprite_portal, 1, cell,
								ObstaclesKind.PORTAL_TO_CHALLENGE);
						break;
					case "Ps":
						new Portal(this.model, this.model.getSprites().sprite_portal, 1, cell,
								ObstaclesKind.PORTAL_TO_STORE);
						break;
					case "Pi":
						new Portal(this.model, this.model.getSprites().sprite_portal_in, 1, cell,
								ObstaclesKind.PORTAL_DESTINATION);
						this.cell_portal_in = cell;
						break;
					case "Po":
						new Portal(this.model, this.model.getSprites().sprite_portal, 1, cell,
								ObstaclesKind.PORTAL_TO_GAME);
						break;

					case "Str":
						new Product(this.model, this.model.getSprites().sprite_tower_red[0], 1, cell,
								ObstaclesKind.PRODUCT, this.model.getWeapons().get(EntityName.Tower_Red),
								PRICE_TOWER_PRODUCT);
						break;
					case "Stb":
						new Product(this.model, this.model.getSprites().sprite_tower_blue[0], 1, cell,
								ObstaclesKind.PRODUCT, this.model.getWeapons().get(EntityName.Tower_Blue),
								PRICE_TOWER_PRODUCT);
						break;
					case "Sty":
						new Product(this.model, this.model.getSprites().sprite_tower_yellow[0], 1, cell,
								ObstaclesKind.PRODUCT, this.model.getWeapons().get(EntityName.Tower_Yellow),
								PRICE_TOWER_PRODUCT);
						break;
					case "Stp":
						new Product(this.model, this.model.getSprites().sprite_tower_purple[0], 1, cell,
								ObstaclesKind.PRODUCT, this.model.getWeapons().get(EntityName.Tower_Purple),
								PRICE_TOWER_PRODUCT);
						break;

					case "Sur":
						new Upgrade(this.model, this.model.getSprites().sprite_upgrade_red[0], 1, cell,
								ObstaclesKind.UPGRADE, this.model.getWeapons().get(EntityName.Tower_Red),
								PRICE_TOWER_UPGRADE);
						break;
					case "Sub":
						new Upgrade(this.model, this.model.getSprites().sprite_upgrade_blue[0], 1, cell,
								ObstaclesKind.UPGRADE, this.model.getWeapons().get(EntityName.Tower_Blue),
								PRICE_TOWER_UPGRADE);
						break;
					case "Suy":
						new Upgrade(this.model, this.model.getSprites().sprite_upgrade_yellow[0], 1, cell,
								ObstaclesKind.UPGRADE, this.model.getWeapons().get(EntityName.Tower_Yellow),
								PRICE_TOWER_UPGRADE);
						break;
					case "Sup":
						new Upgrade(this.model, this.model.getSprites().sprite_upgrade_purple[0], 1, cell,
								ObstaclesKind.UPGRADE, this.model.getWeapons().get(EntityName.Tower_Purple),
								PRICE_TOWER_UPGRADE);
						break;
					default:

					}
				}
				cells.add(cells_line);
				row++;
			}
			this.setCellsMap(cells);

			reader.close();
		} catch (FileNotFoundException e) {

		} catch (IOException e) {

		}

	}
}