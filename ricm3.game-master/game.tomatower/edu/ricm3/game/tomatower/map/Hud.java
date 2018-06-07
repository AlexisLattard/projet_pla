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
	public BufferedImage sprite_tower_red;
	public BufferedImage sprite_tower_blue;
	public BufferedImage sprite_tower_yellow;
	public BufferedImage sprite_tower_purple;
	public BufferedImage sprite_health_player;
	public BufferedImage sprite_health_crystal;

	public Hud(Model model) {
		this.model = model;
		try {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(
					Font.createFont(Font.PLAIN, new File("game.tomatower/sprites/hud/Acme/Acme-Regular.ttf")));
			font = new Font("Acme",Font.PLAIN, 18);
			// font = Font.createFont(Font.TRUETYPE_FONT, new
			// File("game.tomatower/sprites/hud/Acme/Acme-Regular.ttf"));
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

	}

	public void paint(Graphics g) {
		int x = this.model.getCurrentMap().getMapDimention()[0];
		int y = this.model.getCurrentMap().getMapDimention()[1];
		g.setFont(font);

		g.drawImage(sprite_background, x, 0, null);

		// Money
		g.drawImage(sprite_money, x + 13, MARGIN, null);
		g.setColor(Color.decode("#e6af13"));
		g.drawString(String.valueOf(this.model.getPlayer().getMoney()), x + 15, 43);

		// Tower red
		g.drawImage(sprite_component_tower, x + 3, height_money + 2 * MARGIN, null);

		// Tower blue
		g.drawImage(sprite_component_tower, x + 3, height_money + height_component_tower + 3 * MARGIN, null);

		// Tower yellow
		g.drawImage(sprite_component_tower, x + 3, height_money + 2 * height_component_tower + 4 * MARGIN, null);

		// Tower purple
		g.drawImage(sprite_component_tower, x + 3, height_money + 3 * height_component_tower + 5 * MARGIN, null);

		// Life
		g.drawImage(sprite_health_player, x + 11, height_money + 4 * height_component_tower + 6 * MARGIN, null);

		// Crystal
		g.drawImage(sprite_health_crystal, x + 73, height_money + 4 * height_component_tower + 6 * MARGIN, null);

	}

}
