package edu.ricm3.game.tomatower.map;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Sprites {
    public BufferedImage sprite_player;
    public BufferedImage sprite_mobs;
    public BufferedImage sprite_cailloux;
    public BufferedImage sprite_arbre;
    public  BufferedImage sprite_tower;
    public BufferedImage sprite_lac;
    public BufferedImage sprite_crystal;
    public BufferedImage sprite_spawn_mobs;
    public BufferedImage sprite_portal;
    public BufferedImage sprite_portal_in;

    public Sprites() {
        loadSprites();
    }

    private void loadSprites() {

        File imageFile = new File("game.tomatower/sprites/player.png");
        try {
            sprite_player = ImageIO.read(imageFile);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }

        imageFile = new File("game.tomatower/sprites/mobs.png");
        try {
            sprite_cailloux = ImageIO.read(imageFile);
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


        imageFile = new File("game.tomatower/sprites/tower.png");
        try {
            sprite_tower = ImageIO.read(imageFile);
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

        imageFile = new File("game.tomatower/sprites/portal_in.png");
        try {
            sprite_portal_in = ImageIO.read(imageFile);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }
    }
}