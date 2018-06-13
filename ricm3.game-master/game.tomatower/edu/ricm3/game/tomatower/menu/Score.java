package edu.ricm3.game.tomatower.menu;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.util.Date;
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
    private Tableau tableauDesScore;
    private JScrollPane scrollTableau;
    
    private Vector<String> columnNames;
    private Vector<Vector<Object>> data;
    private Bouton boutonRetour;
        
    /**
     * Constructor for objects of class Score
     */
    
    private static Score INSTANCE = null;
    
    /** Point d'accès pour l'instance unique du singleton */
    public static synchronized Score getInstance(ActionListener actionRetour)
    {           
        if (INSTANCE == null){
        	INSTANCE = new Score(actionRetour); 
        }
        return INSTANCE;
    }

	private Score(ActionListener retourListener)
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
        data = new Vector<Vector<Object>>();
        columnNames=new Vector<String>();
        columnNames.add("Pseudo");columnNames.add("Scores");columnNames.add("Date");
        tableauDesScore = new Tableau(data,columnNames);
        scrollTableau = new JScrollPane(tableauDesScore,
        								JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		fillData();
        
        // TABLEAU //
        
        this.setLayout(new BorderLayout());
        this.add(scrollTableau,BorderLayout.CENTER);
        this.add(south,BorderLayout.SOUTH);
        this.add(north,BorderLayout.NORTH);
        this.getButtonRetour().addActionListener(retourListener);
    }
    
    /**
     * La fonction recupère le JButton retour
     * 
     * @return JButton
     */
    public JButton getButtonRetour(){
        return boutonRetour;
    }
    
    public void fillData(){
    	data.removeAllElements();
    	File file = new File("./Autres/Score");
    	Scanner scanner;
		try {
			scanner = new Scanner(file);
			String line;
			String[] split;
			Vector<Object> row;
			long seconde;
			Date date;
	        DateFormat dfl = DateFormat.getDateTimeInstance(DateFormat.FULL,DateFormat.DEFAULT);
	        while (scanner.hasNextLine()) {
	        	line = scanner.nextLine();
	            split = line.split(";");
	            row = new Vector<Object>();
	            seconde = new Long(split[2]);
	            date = new Date(seconde);
	            row.add(split[0]);row.add(new Integer(split[1]));row.add(dfl.format(date));
	            data.add(row);
	        }
	        scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}