package edu.ricm3.game.tomatower.menu;

import javax.swing.*;
import java.awt.*;

/**
 * Write a description of class Menu here.
 * 
 * @author (Lordey A.K.A the thief) 
 * @version (a version number or a date)
 */
public class Menu extends JPanel
{
    // instance variables - replace the example below with your own
    
    // JButton //
    private Bouton boutonJouer;
    private Bouton boutonRegles;
    private Bouton boutonQuitter;
    private Bouton boutonScore;
    private Bouton boutonOption;
    private Bouton boutonDebug;
    // JButton //
    
    private JPanel boutonsCentre;
    private JPanel panel_centre;
    private JPanel panel_nord;

    /**
     * Constructor for objects of class Menu
     */
    public Menu()
    {
        // initialise instance variables
        this.setLayout(new BorderLayout());
        // Initialisation des bouton//
        boutonJouer = new Bouton("Jouer");
        boutonScore = new Bouton("Score");
        boutonRegles = new Bouton("Regles");
        boutonQuitter = new Bouton("Quitter");
        boutonOption = new Bouton();
        boutonDebug = new Bouton();
        boutonOption.setIcon(new ImageIcon("./Image/Option.png"),30,30);
        boutonDebug.setIcon(new ImageIcon("./Image/Debug.png"),30,30);
        // Initialisation des bouton//
        
        // Initialisation du conteneur des boutons//
        boutonsCentre = new JPanel(new GridLayout(4,1));
        // Initialisation du conteneur des boutons//
        
        
        //Initialisation du conteneur des boutons//
        panel_centre = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel_nord = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel_nord.add(boutonDebug);
        panel_nord.add(boutonOption);
        panel_centre.add(boutonsCentre);
        //Initialisation du conteneur des boutons//
        
        // Ajout des boutons dans le conteneur//
        boutonsCentre.add(boutonJouer);
        boutonsCentre.add(boutonRegles);
        boutonsCentre.add(boutonScore);
        boutonsCentre.add(boutonQuitter);
        // Ajout des boutons dans le conteneur//
        this.add(panel_centre,BorderLayout.CENTER);
        this.add(panel_nord,BorderLayout.NORTH);
        
    }
        
    public JButton getButtonScore(){
        return boutonScore;
    }
    
    public JButton getButtonQuitter(){
        return boutonQuitter;
    }
    
    public JButton getButtonRegles(){
        return boutonRegles;
    }
}