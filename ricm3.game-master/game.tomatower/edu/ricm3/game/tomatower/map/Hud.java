package edu.ricm3.game.tomatower.map;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

import edu.ricm3.game.tomatower.entities.Buyable;
import edu.ricm3.game.tomatower.entities.Entity;
import edu.ricm3.game.tomatower.entities.MobSpawn;
import edu.ricm3.game.tomatower.entities.Upgrade;
import edu.ricm3.game.tomatower.entities.Weapon;
import edu.ricm3.game.tomatower.entities.enums.Direction;
import edu.ricm3.game.tomatower.entities.enums.EntityName;
import edu.ricm3.game.tomatower.mvc.Model;

public class Hud {

	public Model model;
	private MobSpawn mobSpawn;
	private Font font;

	public final int MARGIN = 20;
	public int height_money;
	public int height_component_tower;
	public int width = 130;

	private HashMap<EntityName, Integer> positions;

	private BufferedImage sprite_background;
	private BufferedImage sprite_money;
	private BufferedImage sprite_component_tower;
	private BufferedImage sprite_star;
	private BufferedImage sprite_range;
	private BufferedImage sprite_tower_red;
	private BufferedImage sprite_tower_blue;
	private BufferedImage sprite_tower_yellow;
	private BufferedImage sprite_tower_purple;
	private BufferedImage sprite_health_player;
	private BufferedImage sprite_health_crystal;
	private BufferedImage sprite_arrow;

	public Hud(Model model, MobSpawn mobSpawn) {
		this.model = model;
		this.mobSpawn = mobSpawn;
		try {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.PLAIN, new File("game.tomatower/sprites/hud/Acme/Acme-Regular.ttf")));
			font = new Font("Acme", Font.PLAIN, 18);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		initSprite();
		initPositionHud();
	}

	public void initSprite() {
		File imageFile;

		// Sprite background
		imageFile = new File("game.tomatower/sprites/hud/hud_background.png");
		try {
			sprite_background = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		// Sprite money
		imageFile = new File("game.tomatower/sprites/hud/hud_component_money.png");
		try {
			sprite_money = ImageIO.read(imageFile);

		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		height_money = sprite_money.getHeight();

		// Sprite component tower
		imageFile = new File("game.tomatower/sprites/hud/hud_component_tower.png");
		try {
			sprite_component_tower = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		height_component_tower = sprite_component_tower.getHeight();

		// Sprite tower red
		imageFile = new File("game.tomatower/sprites/hud/hud_tower_red.png");
		try {
			sprite_tower_red = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		// Sprite tower blue
		imageFile = new File("game.tomatower/sprites/hud/hud_tower_blue.png");
		try {
			sprite_tower_blue = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		// Sprite tower yellow
		imageFile = new File("game.tomatower/sprites/hud/hud_tower_yellow.png");
		try {
			sprite_tower_yellow = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		// Sprite tower purple
		imageFile = new File("game.tomatower/sprites/hud/hud_tower_purple.png");
		try {
			sprite_tower_purple = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		// Sprite star
		imageFile = new File("game.tomatower/sprites/hud/star.png");
		try {
			sprite_star = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		// Sprite range
		imageFile = new File("game.tomatower/sprites/hud/bow.png");
		try {
			sprite_range = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		// Sprite health player
		imageFile = new File("game.tomatower/sprites/hud/hud_life.png");
		try {
			sprite_health_player = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		// Sprite health crystal
		imageFile = new File("game.tomatower/sprites/hud/hud_crystal.png");
		try {
			sprite_health_crystal = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		// Sprite component tower
		imageFile = new File("game.tomatower/sprites/hud/hud_arrow.png");
		try {
			sprite_arrow = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

	}

	public void paint(Graphics g) {
		int x = this.model.getCurrentMap().getMapDimention()[0];
		int y = this.model.getCurrentMap().getMapDimention()[1];
		HashMap<EntityName, Integer> towers = this.model.getPlayer().getBagNumberTower();
		HashMap<EntityName, Weapon> weapons = this.model.getWeapons();
		ArrayList<EntityName> entityName = new ArrayList<>();
		entityName.add(EntityName.Tower_Red);
		entityName.add(EntityName.Tower_Yellow);
		entityName.add(EntityName.Tower_Blue);
		entityName.add(EntityName.Tower_Purple);

		g.setFont(font);
		g.drawImage(sprite_background, x, 0, null);

		// Money
		g.drawImage(sprite_money, x + 13, MARGIN, null);
		g.setColor(Color.decode("#e6af13"));
		g.drawString(String.valueOf(this.model.getPlayer().getMoney()), x + 15, 43);

		// int j = height_money + 2 * MARGIN;
		for (EntityName kw : positions.keySet()) {
			g.drawImage(sprite_component_tower, x + 3, positions.get(kw) - 8, null);
			g.setColor(Color.WHITE);
			g.drawString(towers.get(kw).toString(), x + 45, positions.get(kw) + 22);
			g.drawImage(sprite_star, x + 75, positions.get(kw), 16, 16, null);
			g.drawString(String.valueOf(weapons.get(kw).getPower()), x + 95, positions.get(kw) + 14);
			g.drawImage(sprite_range, x + 75, positions.get(kw) + 24, 16, 16, null);
			g.drawString(String.valueOf(weapons.get(kw).getRange()), x + 95, positions.get(kw) + 35);
			// j += height_component_tower + MARGIN;
		}
		// Tower red
		g.drawImage(sprite_tower_red, x + 15, positions.get(EntityName.Tower_Red), null);

		// Tower blue
		g.drawImage(sprite_tower_yellow, x + 15, positions.get(EntityName.Tower_Yellow), null);

		// Tower yellow
		g.drawImage(sprite_tower_blue, x + 15, positions.get(EntityName.Tower_Blue), null);

		// Tower purple
		g.drawImage(sprite_tower_purple, x + 15, positions.get(EntityName.Tower_Purple), null);

		// Life
		int h = 150;
		float h_life = h * (this.model.getPlayer().getHp() / (float) this.model.getPlayer().getMaxLife());
		g.setColor(Color.decode("#4c0909"));
		g.fillRoundRect(x + 25, height_money + 4 * height_component_tower + 6 * MARGIN + 40, 15, 150, 10, 10);
		g.setColor(Color.decode("#d73f2e"));
		g.fillRoundRect(x + 25, height_money + 4 * height_component_tower + 6 * MARGIN + 40 + (h - (int) h_life), 15, (int) h_life, 10, 10);
		g.drawImage(sprite_health_player, x + 11, height_money + 4 * height_component_tower + 6 * MARGIN, null);

		// Crystal
		h_life = h * (this.model.getCrystal().getHp() / (float) this.model.getCrystal().getMaxLife());
		g.setColor(Color.decode("#094d49"));
		g.fillRoundRect(x + 87, height_money + 4 * height_component_tower + 6 * MARGIN + 40, 15, 150, 10, 10);
		g.setColor(Color.decode("#8ccfcb"));
		g.fillRoundRect(x + 87, height_money + 4 * height_component_tower + 6 * MARGIN + 40 + (h - (int) h_life), 15, (int) h_life, 10, 10);
		g.drawImage(sprite_health_crystal, x + 73, height_money + 4 * height_component_tower + 6 * MARGIN, null);

		// Arrow de selection
		switch (this.model.getPlayer().getTowerSelected()) {
		case Tower_Red:
			g.drawImage(sprite_arrow, x, positions.get(EntityName.Tower_Red) + 8, null);
			break;
		case Tower_Yellow:
			g.drawImage(sprite_arrow, x, positions.get(EntityName.Tower_Yellow) + 8, null);
			break;
		case Tower_Blue:
			g.drawImage(sprite_arrow, x, positions.get(EntityName.Tower_Blue) + 8, null);
			break;
		case Tower_Purple:
			g.drawImage(sprite_arrow, x, positions.get(EntityName.Tower_Purple) + 8, null);
			break;
		default:
			// Pas de towers selectionnées
		}

		// Affichage wave restantes
		g.setColor(Color.WHITE);
		g.drawString("Vagues Restantes", x + 2, height_money + 4 * height_component_tower + 6 * MARGIN + 80 + h);
		g.drawString(String.valueOf(this.mobSpawn.getWaveTotal() - this.mobSpawn.getWaveId()), x + 60, height_money + 4 * height_component_tower + 7 * MARGIN + 80 + h);

		// Affichage prix tower + upgrade
		Map map_store = this.model.getStoreMap();
		if (this.model.getCurrentMap().equals(map_store)) {
			g.drawString("Prix :", x + 2, height_money + 4 * height_component_tower + 6 * MARGIN + 125 + h);
			Entity entity = map_store.getEntityCell(this.model.getPlayer().getCellDirection(Direction.FRONT, 1));
			
			if(entity instanceof Buyable){
				int price = ((Buyable) entity).getPrice();
				g.drawString(String.valueOf(price), x + 45, height_money + 4 * height_component_tower + 6 * MARGIN + 125 + h);
				if(entity instanceof Upgrade){
					g.drawString("Comportement :", x + 2, height_money + 4 * height_component_tower + 6 * MARGIN + 142 + h);
					g.drawString(String.valueOf(200), x + 45, height_money + 4 * height_component_tower + 6 * MARGIN + 162 + h);
				}
			}
		}
	}

	public int getWidth() {
		return this.width;
	}

	public void initPositionHud() {
		positions = new HashMap<>();
		positions.put(EntityName.Tower_Red, height_money + 2 * MARGIN + 8);
		positions.put(EntityName.Tower_Yellow, height_component_tower + height_money + 3 * MARGIN + 8);
		positions.put(EntityName.Tower_Blue, 2 * height_component_tower + height_money + 4 * MARGIN + 8);
		positions.put(EntityName.Tower_Purple, 3 * height_component_tower + height_money + 5 * MARGIN + 8);
	}

}
