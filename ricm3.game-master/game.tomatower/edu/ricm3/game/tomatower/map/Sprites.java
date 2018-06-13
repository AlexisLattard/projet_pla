package edu.ricm3.game.tomatower.map;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Sprites {
	public BufferedImage sprite_crystal[];
	public BufferedImage sprite_player[];

	public BufferedImage sprite_tower_red[];
	public BufferedImage sprite_tower_blue[];
	public BufferedImage sprite_tower_yellow[];
	public BufferedImage sprite_tower_purple[];

	public BufferedImage sprite_upgrade_red[];
	public BufferedImage sprite_upgrade_blue[];
	public BufferedImage sprite_upgrade_yellow[];
	public BufferedImage sprite_upgrade_purple[];

	public BufferedImage sprite_mob_ghost[];
	public BufferedImage sprite_mob_hungry[];
	public BufferedImage sprite_mob_lantern[];
	public BufferedImage sprite_mob_plug[];
	public BufferedImage sprite_spawn_mobs;

	public BufferedImage sprite_cailloux;
	public BufferedImage sprite_arbre;
	public BufferedImage sprite_lac;
	public BufferedImage sprite_mur;
	public BufferedImage sprite_portal;
	public BufferedImage sprite_portal_in;

	// TEST
	public BufferedImage[] sprite_explosion;

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
		ArrayList<String> directions = new ArrayList<>();
		directions.add("down");
		directions.add("up");
		directions.add("right");
		directions.add("left");
		File imageFile;
		BufferedImage tmp_sprite;

		// Sprite du joueur
		imageFile = new File("game.tomatower/sprites/player/player.png");
		try {
			tmp_sprite = ImageIO.read(imageFile);
			sprite_player = this.splitSprite(tmp_sprite, 1, 4);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);

		}

		// Sprite cristal
		imageFile = new File("game.tomatower/sprites/Main_Cristal.png");
		try {
			tmp_sprite = ImageIO.read(imageFile);
			sprite_crystal = this.splitSprite(tmp_sprite, 1, 6);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		// Sprite Towers
		imageFile = new File("game.tomatower/sprites/towers/tower_red.png");
		try {
			tmp_sprite = ImageIO.read(imageFile);
			sprite_tower_red = this.splitSprite(tmp_sprite, 1, 6);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		imageFile = new File("game.tomatower/sprites/towers/tower_blue.png");
		try {
			tmp_sprite = ImageIO.read(imageFile);
			sprite_tower_blue = this.splitSprite(tmp_sprite, 1, 6);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		imageFile = new File("game.tomatower/sprites/towers/tower_yellow.png");
		try {
			tmp_sprite = ImageIO.read(imageFile);
			sprite_tower_yellow = this.splitSprite(tmp_sprite, 1, 6);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		imageFile = new File("game.tomatower/sprites/towers/tower_purple.png");
		try {
			tmp_sprite = ImageIO.read(imageFile);
			sprite_tower_purple = this.splitSprite(tmp_sprite, 1, 6);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		// Upgrades
		imageFile = new File("game.tomatower/sprites/upgrades/upgrade_red.png");
		try {
			tmp_sprite = ImageIO.read(imageFile);
			sprite_upgrade_red = this.splitSprite(tmp_sprite, 1, 6);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		imageFile = new File("game.tomatower/sprites/upgrades/upgrade_blue.png");
		try {
			tmp_sprite = ImageIO.read(imageFile);
			sprite_upgrade_blue = this.splitSprite(tmp_sprite, 1, 6);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		imageFile = new File("game.tomatower/sprites/upgrades/upgrade_yellow.png");
		try {
			tmp_sprite = ImageIO.read(imageFile);
			sprite_upgrade_yellow = this.splitSprite(tmp_sprite, 1, 6);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		imageFile = new File("game.tomatower/sprites/upgrades/upgrade_purple.png");
		try {
			tmp_sprite = ImageIO.read(imageFile);
			sprite_upgrade_purple = this.splitSprite(tmp_sprite, 1, 6);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		// Sprite Monstres
		imageFile = new File("game.tomatower/sprites/mobs/mobs_spawn.png");
		try {
			sprite_spawn_mobs = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		imageFile = new File("game.tomatower/sprites/mobs/Mob_Ghost.png");
		try {
			tmp_sprite = ImageIO.read(imageFile);
			sprite_mob_ghost = this.splitSprite(tmp_sprite, 1, 6);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		imageFile = new File("game.tomatower/sprites/mobs/Mob_Hungry.png");
		try {
			tmp_sprite = ImageIO.read(imageFile);
			sprite_mob_hungry = this.splitSprite(tmp_sprite, 1, 6);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		imageFile = new File("game.tomatower/sprites/mobs/Mob_Lantern.png");
		try {
			tmp_sprite = ImageIO.read(imageFile);
			sprite_mob_lantern = this.splitSprite(tmp_sprite, 1, 4);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		imageFile = new File("game.tomatower/sprites/mobs/Mob_Plug.png");
		try {
			tmp_sprite = ImageIO.read(imageFile);
			sprite_mob_plug = this.splitSprite(tmp_sprite, 1, 4);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		// Sprite Obstacles
		imageFile = new File("game.tomatower/sprites/obstacles/stone.png");
		try {
			sprite_cailloux = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		imageFile = new File("game.tomatower/sprites/obstacles/wall.png");
		try {
			sprite_mur = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		imageFile = new File("game.tomatower/sprites/obstacles/tree.png");
		try {
			sprite_arbre = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		imageFile = new File("game.tomatower/sprites/obstacles/water.png");
		try {
			sprite_lac = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		imageFile = new File("game.tomatower/sprites/obstacles/portal.png");
		try {
			sprite_portal = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		imageFile = new File("game.tomatower/sprites/obstacles/portal_in.png");
		try {
			sprite_portal_in = ImageIO.read(imageFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		// Sprite du joueur
		imageFile = new File("game.tomatower/sprites/explosion.png");
		try {
			tmp_sprite = ImageIO.read(imageFile);
			sprite_explosion = this.splitSprite(tmp_sprite, 1, 8);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

	}
}