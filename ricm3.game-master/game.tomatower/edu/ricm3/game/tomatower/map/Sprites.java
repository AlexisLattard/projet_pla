package edu.ricm3.game.tomatower.map;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Sprites {
	public BufferedImage sprite_upgrade_yellow[];
	public BufferedImage sprite_upgrade_red[];
	public BufferedImage sprite_player[];
	public BufferedImage sprite_player_run[][];
	public BufferedImage sprite_mobs[];
	public BufferedImage sprite_mobs_run[][];
	public BufferedImage sprite_tower_red[];
	public BufferedImage sprite_cailloux;
	public BufferedImage sprite_arbre;
	public BufferedImage sprite_lac;
	public BufferedImage sprite_mur;
	public BufferedImage sprite_crystal[];
	public BufferedImage sprite_spawn_mobs;
	public BufferedImage sprite_portal;
	public BufferedImage sprite_portal_in;
	public BufferedImage sprite_background;

	public Sprites() {
		loadSprites();
	}

	BufferedImage[] splitSprite(BufferedImage image, int nrows, int ncols) {
		int width = image.getWidth(null);
		int height = image.getHeight(null);
		BufferedImage images[] = new BufferedImage[nrows * ncols];
		int w = width / ncols;
		int h = height / nrows;
		for (int i = 0; i < nrows; i++) {
			for (int j = 0; j < ncols; j++) {
				int x = j * w;
				int y = i * h;
				images[(i * ncols) + j] = image.getSubimage(x, y, w, h);
			}
		}
		return images;
	}

	private void loadSprites() {

		File imageFile;

		BufferedImage tmp_sprite;

		// Sprite du joueur
		imageFile = new File("game.tomatower/sprites/player.png");
		try {
			tmp_sprite = ImageIO.read(imageFile);
			sprite_player = this.splitSprite(tmp_sprite, 1, 4);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		imageFile = new File("game.tomatower/sprites/tower_red.png");
		try {
			tmp_sprite = ImageIO.read(imageFile);
			sprite_tower_red = this.splitSprite(tmp_sprite, 1, 6);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		imageFile = new File("game.tomatower/sprites/mobs.png");
		try {
			tmp_sprite = ImageIO.read(imageFile);
			sprite_mobs = this.splitSprite(tmp_sprite, 1, 4);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		imageFile = new File("game.tomatower/sprites/stone.png");
		try {
			sprite_cailloux = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		imageFile = new File("game.tomatower/sprites/wall.png");
		try {
			sprite_mur = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		imageFile = new File("game.tomatower/sprites/tree.png");
		try {
			sprite_arbre = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		imageFile = new File("game.tomatower/sprites/water.png");
		try {
			sprite_lac = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		imageFile = new File("game.tomatower/sprites/Main_Cristal.png");
		try {
			tmp_sprite = ImageIO.read(imageFile);
			sprite_crystal = this.splitSprite(tmp_sprite, 1, 6);
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

		imageFile = new File("game.tomatower/sprites/grass.png");
		try {
			sprite_background = ImageIO.read(imageFile);

		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		imageFile = new File("game.tomatower/sprites/upgrade_yellow.png");
		try {
			tmp_sprite = ImageIO.read(imageFile);
			sprite_upgrade_yellow = this.splitSprite(tmp_sprite, 1, 6);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		imageFile = new File("game.tomatower/sprites/upgrade_red.png");
		try {
			tmp_sprite = ImageIO.read(imageFile);
			sprite_upgrade_red = this.splitSprite(tmp_sprite, 1, 6);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
	}
}