package edu.ricm3.game.tomatower.menu;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Regles extends JPanel{

	
	private JPanel nord;
	private JLabel titre;
	private JPanel sud;
	private Bouton boutonRetour;
	

	private static class ReglesHolder
    {       
        /** Instance unique non préinitialisée */
        private final static Regles INSTANCE = new Regles();
    }
 
    /** Point d'accès pour l'instance unique du singleton */
    public static Regles getInstance()
    {
	    return ReglesHolder.INSTANCE;
    }
	
	private Regles() {      
		this.nord = new JPanel(new FlowLayout());
		this.titre = new JLabel("Regles");
		this.sud = new JPanel(new FlowLayout(FlowLayout.LEFT));
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
