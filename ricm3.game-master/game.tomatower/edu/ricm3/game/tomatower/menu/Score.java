package edu.ricm3.game.tomatower.menu;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
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
    // instance variables - replace the example below with your own
    private JPanel tableau;
    private JPanel south;
    private JLabel titre;
    private JPanel north;    
    private JTable tableauDesScore;
    
    private Vector<String> columnNames;
    private Vector<Vector<Object>> data;
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
        data =  fillData();
        columnNames=new Vector<String>();
        columnNames.add("Psuedo");columnNames.add("Scores");columnNames.add("Date");
       
        tableau = new JPanel(new BorderLayout());
        tableauDesScore = new JTable(data,columnNames);
        tableauDesScore.setModel(new DefaultTableModel(data,columnNames) {
            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return String.class;
                    case 1:
                        return Integer.class;
                    case 2:
                        return Calendar.class;
                    default:
                        return String.class;
                }
            }
        
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        tableauDesScore.setAutoCreateRowSorter(true);
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
            row.add(split[0]);row.add(new Integer(split[1]));
            newdata.add(row);
        }
        scanner.close();
        return newdata;
    }
}