package edu.ricm3.game.tomatower.menu;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.ricm3.game.tomatower.listeners.RetourMenuListener;

public class Regles extends JPanel{

	
	private JPanel nord;
	private JLabel titre;
	private JPanel sud;
	private Bouton boutonRetour;
	

	private static Regles INSTANCE = null;
    
    /** Point d'accès pour l'instance unique du singleton */
    public static synchronized Regles getInstance()
    {           
        if (INSTANCE == null){
        	INSTANCE = new Regles(); 
        }
        return INSTANCE;
    }
	
	private Regles() {      
		this.nord = new JPanel(new FlowLayout());
		this.titre = new JLabel("Regles");
		this.sud = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		this.boutonRetour = new Bouton("Retour");
		
		this.nord.add(titre);
		this.sud.add(boutonRetour);
		
		
		this.setLayout(new BorderLayout());
		this.add(nord,BorderLayout.NORTH);
		this.add(sud, BorderLayout.SOUTH);
		
		this.getButtonRetour().addActionListener(new RetourMenuListener());
	}
	
	/**
     * La fonction recupère le JButton retour
     * 
     * @return JButton
     */
    public JButton getButtonRetour(){
        return this.boutonRetour;
    }
}
