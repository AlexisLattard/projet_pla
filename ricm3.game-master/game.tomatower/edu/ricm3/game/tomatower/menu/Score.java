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
    private JPanel nord;
    private JTable tableauDesScore;
    private String[] columnNames= {"Pseudo","Score"};
    private Object[][] data= {
            {"Maxime", new Integer(4)},{"Tibaut", new Integer(3)},
            {"Corentin", new Integer(6)},{"Alexis", new Integer(7)},
            {"Romain", new Integer(5)},{"Andréas", new Integer(8)},
            {"Bertrand", new Integer(6)}
        };
    private JButton boutonRetour;
        
    /**
     * Constructor for objects of class Score
     */
    public Score()
    {
        boutonRetour = new Bouton("retour");
        nord = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        nord.add(boutonRetour);
        
        tableau = new JPanel(new BorderLayout());
        tableauDesScore = new JTable(data,columnNames);
        tableau.add(tableauDesScore.getTableHeader(),BorderLayout.NORTH);
        tableau.add(tableauDesScore,BorderLayout.CENTER);
        
        this.setLayout(new BorderLayout());
        this.add(tableau,BorderLayout.CENTER);
        this.add(nord,BorderLayout.SOUTH);
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