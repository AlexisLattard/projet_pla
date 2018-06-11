package edu.ricm3.game.tomatower.menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Regles extends JPanel{

	
	private JPanel north;
	private JLabel title;
	private JPanel south;
	private Bouton boutonRetour;
	

	private static Regles INSTANCE = null;
    
    /** Point d'accès pour l'instance unique du singleton */
    public static synchronized Regles getInstance(ActionListener actionRetour)
    {           
        if (INSTANCE == null){
        	INSTANCE = new Regles(actionRetour); 
        }
        return INSTANCE;
    }
	
	private Regles(ActionListener actionRetour) {      
		north = new JPanel(new FlowLayout());
		title = new JLabel("Regles");
		south = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		boutonRetour = new Bouton("Retour");
		
		north.add(title);
		south.add(boutonRetour);
		
		
		this.setLayout(new BorderLayout());
		this.add(north,BorderLayout.NORTH);
		this.add(south, BorderLayout.SOUTH);
		
		this.getButtonRetour().addActionListener(actionRetour);
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
