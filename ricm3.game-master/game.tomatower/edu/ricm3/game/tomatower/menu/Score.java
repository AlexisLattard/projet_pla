package edu.ricm3.game.tomatower.menu;

import javax.swing.*;
import java.awt.*;
/**
 * Write a description of class Score here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Score extends JPanel
{
    // instance variables - replace the example below with your own
    private JPanel tableau;
    private JPanel south;
    private JLabel titre;
    private JPanel north;    
    private Tableau tableauDesScore;
    
    private String[] columnNames= {"Pseudo","Score","Date"};
    private Object[][] data= {{"XxTHED4RK1LL3RDU38xX", new Integer(10)},
            {"Maxime", new Integer(4)},{"Tibaut", new Integer(3)},
            {"Corentin", new Integer(6)},{"Alexis", new Integer(7)},
            {"Romain", new Integer(5)},{"Andréas", new Integer(8)},
            {"Bertrand", new Integer(6)},{"Gruber", new Integer(7)},
            {"Perrin", new Integer(7)}
        };
    private JButton boutonRetour;
        
    /**
     * Constructor for objects of class Score
     */
    public Score()
    {
    	// BOUTON RETOUR //
        boutonRetour = new Bouton("retour");
        south = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        south.add(boutonRetour);
        // BOUTON RETOUR //
        
        // TITRE //
        titre = new JLabel("TABLEAU DES SCORES");
        north = new JPanel(new FlowLayout(FlowLayout.CENTER));
        north.add(titre);
        // TITRE //
        
        // TABLEAU //
        tableau = new JPanel(new BorderLayout());
        tableauDesScore = new Tableau(data,columnNames);
        // TABLEAU //
        
        // AFFICHEAGE //
        tableau.add(tableauDesScore.getTableHeader(),BorderLayout.NORTH);
        tableau.add(tableauDesScore,BorderLayout.CENTER);
        // AFFICHEAGE //
        
        this.setLayout(new BorderLayout());
        this.add(tableau,BorderLayout.CENTER);
        this.add(south,BorderLayout.SOUTH);
        this.add(north,BorderLayout.NORTH);
    }
    
    /**
     * La fonction recupère le JButton retour
     * 
     * @return JButton
     */
    public JButton getButtonRetour(){
        return boutonRetour;
    }
}