package edu.ricm3.game.tomatower.menu;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Write a description of class Bouton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bouton extends JButton
{
    /**
     * Constructor for objects of class Bouton
     */
    public Bouton()
    {
        // initialise instance variables
    	super();
        this.setBackground(new Color(255,255,255));
    }
    
    /**
     * Constructor for objects of class Bouton
     */
    public Bouton(String name)
    {
        // initialise instance variables
        super(name);
        this.setBackground(new Color(255,255,255));
        this.setFocusPainted(false);
    }
    
    public Bouton(ImageIcon icon,int longueur,int largeur)
    {
        // initialise instance variables
    	super();
    	this.setIcon(icon,longueur,largeur);
        this.setBackground(new Color(255,255,255));
        this.setFocusPainted(false);
    }
    
    public Bouton(ImageIcon icon)
    {
        // initialise instance variables
    	super();
    	this.setIcon(icon);
        this.setBackground(new Color(255,255,255));
        this.setFocusPainted(false);
    }
    
    public void setIcon(ImageIcon icon,int longueur,int largeur){
        Image image = icon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(longueur, largeur,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        icon = new ImageIcon(newimg);  // transform it back
        this.setIcon(icon);
    }
}