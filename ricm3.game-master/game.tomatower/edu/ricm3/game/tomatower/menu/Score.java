package edu.ricm3.game.tomatower.menu;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.Scanner;
import java.util.Vector;
/**
 * Write a description of class Score here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Score extends JPanel
{
    private JPanel south;
    private JLabel titre;
    private JPanel north;    
    private JTable tableauDesScore;
    private JScrollPane scrollTableau;
    
    private Vector<String> columnNames;
    private Vector<Vector<Object>> data;
    private JButton boutonRetour;
        
    /**
     * Constructor for objects of class Score
     */
    
    private static class ScoreHolder
    {       
        /** Instance unique non préinitialisée */
        private final static Score INSTANCE = new Score();
    }
 
    /** Point d'accès pour l'instance unique du singleton */
    public static Score getInstance(ActionListener actionRetour)
    {
		ScoreHolder.INSTANCE.getButtonRetour().addActionListener(actionRetour);
	    return ScoreHolder.INSTANCE;
    }

	private Score()
    {
		System.out.println("création Score");
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
        data =  fillData();
        columnNames=new Vector<String>();
        columnNames.add("Psuedo");columnNames.add("Scores");columnNames.add("Date");
        tableauDesScore = new Tableau(data,columnNames);
        scrollTableau = new JScrollPane(tableauDesScore,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        // TABLEAU //
        
        this.setLayout(new BorderLayout());
        this.add(scrollTableau,BorderLayout.CENTER);
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
    
    public Vector<Vector<Object>> fillData(){
    	File file = new File("./Autres/Score");
    	Vector<Vector<Object>> newdata = new Vector<Vector<Object>>();
    	Scanner scanner;
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] split = line.split(";");
            Vector<Object> row = new Vector<Object>();
            row.add(split[0]);row.add(new Integer(split[1]));//row.add(new Long(split[2]));
            newdata.add(row);
        }
        scanner.close();
        return newdata;
    }
}