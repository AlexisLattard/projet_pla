package edu.ricm3.game.tomatower.map;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Sprites {
	public BufferedImage sprite_upgrade_yellow;
	public BufferedImage sprite_upgrade_red;
	public BufferedImage sprite_player[];
	public BufferedImage sprite_mobs[];
	public BufferedImage sprite_tower[];
	public BufferedImage sprite_cailloux;
	public BufferedImage sprite_arbre;
	public BufferedImage sprite_lac;
	public BufferedImage sprite_crystal;
	public BufferedImage sprite_spawn_mobs;
	public BufferedImage sprite_portal;
	public BufferedImage sprite_portal_in;

	public Sprites() {
		loadSprites();
	}

	private void loadSprites() {
		ArrayList<String> directions = new ArrayList<>();
		directions.add("down");
		directions.add("up");
		directions.add("right");
		directions.add("left");
		File imageFile;
		sprite_player = new BufferedImage[4];
		for (int i = 0; i < 4; i++) {
			System.out.println("game.tomatower/sprites/player_" + directions.get(i) + ".png");
			imageFile = new File("game.tomatower/sprites/player_" + directions.get(i) + ".png");
			try {
				sprite_player[i] = ImageIO.read(imageFile);
			} catch (IOException ex) {
				ex.printStackTrace();
				System.exit(-1);
			}
		}

		imageFile = new File("game.tomatower/sprites/tower.png");
		sprite_tower = new BufferedImage[4];
		for (int i = 0; i < 4; i++) {
			try {
				sprite_tower[i] = ImageIO.read(imageFile);
			} catch (IOException ex) {
				ex.printStackTrace();
				System.exit(-1);
			}
		}

		imageFile = new File("game.tomatower/sprites/mobs.png");
		sprite_mobs = new BufferedImage[4];
		for (int i = 0; i < 4; i++) {
			try {
				sprite_mobs[i] = ImageIO.read(imageFile);
			} catch (IOException ex) {
				ex.printStackTrace();
				System.exit(-1);
			}
		}

		imageFile = new File("game.tomatower/sprites/stone.png");
		try {
			sprite_cailloux = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		imageFile = new File("game.tomatower/sprites/crystal.png");
		try {
			sprite_crystal = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		imageFile = new File("game.tomatower/sprites/mobs_spawn.png");
		try {
			sprite_spawn_mobs = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		imageFile = new File("game.tomatower/sprites/portal.png");
		try {
			sprite_portal = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		imageFile = new File("game.tomatower/sprites/mobs_spawn.png");
		try {
			sprite_spawn_mobs = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		imageFile = new File("game.tomatower/sprites/portal.png");
		try {
			sprite_portal = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		imageFile = new File("game.tomatower/sprites/portal_in.png");
		try {
			sprite_portal_in = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		imageFile = new File("game.tomatower/sprites/bulletb.png");
		try {
			sprite_upgrade_yellow = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		imageFile = new File("game.tomatower/sprites/bulletc.png");
		try {
			sprite_upgrade_red = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
	}
}