package edu.ricm3.game.tomatower.map;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import edu.ricm3.game.tomatower.entities.enums.Kind_Weapon;
import edu.ricm3.game.tomatower.mvc.Model;

public class Hud {

	public Model model;
	int width;
	int height;
	Font font;

	public final int MARGIN = 20;

	public int height_money;
	public int height_component_tower;

	public BufferedImage sprite_background;
	public BufferedImage sprite_money;
	public BufferedImage sprite_component_tower;
	public BufferedImage sprite_star;
	public BufferedImage sprite_range;
	public BufferedImage sprite_tower_red;
	public BufferedImage sprite_tower_blue;
	public BufferedImage sprite_tower_yellow;
	public BufferedImage sprite_tower_purple;
	public BufferedImage sprite_health_player;
	public BufferedImage sprite_health_crystal;
	public BufferedImage sprite_arrow;

	public Hud(Model model) {
		this.model = model;
		try {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.PLAIN, new File("game.tomatower/sprites/hud/Acme/Acme-Regular.ttf")));
			font = new Font("Acme", Font.PLAIN, 18);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		initSprite();
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
		g.setFont(font);

		g.drawImage(sprite_background, x, 0, null);

		// Money
		g.drawImage(sprite_money, x + 13, MARGIN, null);
		g.setColor(Color.decode("#e6af13"));
		g.drawString(String.valueOf(this.model.getPlayer().getMoney()), x + 15, 43);

		int j = height_money + 2 * MARGIN;
		for (Kind_Weapon kw : Kind_Weapon.values()) {
			g.drawImage(sprite_component_tower, x + 3, j, null);
			g.setColor(Color.WHITE);
			g.drawString(this.model.getPlayer().getBagNumberTower().get(kw).toString(), x + 45, j + 30);
			g.drawImage(sprite_star, x + 75, j + 8, 16, 16, null);
			g.drawString(String.valueOf(this.model.getWeapons().get(kw).getPower()), x + 95, j + 22);
			g.drawImage(sprite_range, x + 75, j + 30, 16, 16, null);
			g.drawString(String.valueOf(this.model.getWeapons().get(kw).getRange()), x + 95, j + 42);
			j += height_component_tower + MARGIN;
		}
		// Tower red
		g.drawImage(sprite_tower_red, x + 15, height_money + 2 * MARGIN + 8, null);

		// Tower blue
		g.drawImage(sprite_tower_blue, x + 15, height_component_tower + height_money + 3 * MARGIN + 8, null);

		// Tower yellow
		g.drawImage(sprite_tower_yellow, x + 15, 2 * height_component_tower + height_money + 4 * MARGIN + 8, null);

		// Tower purple
		g.drawImage(sprite_tower_purple, x + 15, 3 * height_component_tower + height_money + 5 * MARGIN + 8, null);

		// Life
		int h = 150;
		float h_life = h * (this.model.getPlayer().getHp() / (float) this.model.getPlayer().MAX_LIFE);
		g.setColor(Color.decode("#4c0909"));
		g.fillRoundRect(x + 25, height_money + 4 * height_component_tower + 6 * MARGIN + 40, 15, 150, 10, 10);
		g.setColor(Color.decode("#d73f2e"));
		g.fillRoundRect(x + 25, height_money + 4 * height_component_tower + 6 * MARGIN + 40 + (h - (int) h_life), 15,
				(int) h_life, 10, 10);
		g.drawImage(sprite_health_player, x + 11, height_money + 4 * height_component_tower + 6 * MARGIN, null);

		// Crystal
		h_life = h * (this.model.getCrystal().getHp() / (float) this.model.getCrystal().MAX_LIFE);
		g.setColor(Color.decode("#094d49"));
		g.fillRoundRect(x + 87, height_money + 4 * height_component_tower + 6 * MARGIN + 40, 15, 150, 10, 10);
		g.setColor(Color.decode("#8ccfcb"));

		g.fillRoundRect(x + 87, height_money + 4 * height_component_tower + 6 * MARGIN + 40 + (h - (int) h_life), 15,
				(int) h_life, 10, 10);
		g.drawImage(sprite_health_crystal, x + 73, height_money + 4 * height_component_tower + 6 * MARGIN, null);

		// Arrow de selection
		switch (this.model.getPlayer().getTowerSelected()) {
		case Red:
			g.drawImage(sprite_arrow, x, height_money + 2 * MARGIN + 16, null);
			break;
		case Blue:
			g.drawImage(sprite_arrow, x, height_component_tower + height_money + 3 * MARGIN + 16, null);
			break;
		case Yellow:
			g.drawImage(sprite_arrow, x, 2 * height_component_tower + height_money + 4 * MARGIN + 16, null);
			break;
		case Purple:
			g.drawImage(sprite_arrow, x, 3 * height_component_tower + height_money + 5 * MARGIN + 16, null);
			break;
		}

	}

}
