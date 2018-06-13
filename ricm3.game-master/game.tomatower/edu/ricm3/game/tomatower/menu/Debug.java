package edu.ricm3.game.tomatower.menu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Debug extends JPanel{

	private Bouton valider;
	private Bouton retour;
	private JEditorPane zoneTexte ;
	private JScrollPane zoneTexte_scroll;
	private JLabel titre;
	private JPanel nord;
	private JPanel sud;
	private JPanel sud_valider;
	private JPanel sud_retour;
	private JPanel centre;
	

	private static class DebugHolder
    {       
        /** Instance unique non préinitialisée */
        private final static Debug INSTANCE = new Debug();
    }
 
    /** Point d'accès pour l'instance unique du singleton */
    public static Debug getInstance()
    {
	    return DebugHolder.INSTANCE;
    }
	
	private Debug() {      
		this.valider= new Bouton("Envoyé");
		this.retour = new Bouton("Retour");
		this.titre = new JLabel("Debug");
		this.zoneTexte = new JEditorPane();
		this.zoneTexte_scroll = new JScrollPane(this.zoneTexte,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.nord = new JPanel(new FlowLayout(FlowLayout.CENTER));
		this.sud = new JPanel(new GridLayout(1,2));
		this.sud_valider = new JPanel(new FlowLayout(FlowLayout.LEFT));
		this.sud_retour = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		this.centre = new JPanel(new BorderLayout());
		
		// SUD //
		this.sud_valider.add(this.valider);
		this.sud_retour.add(this.retour);
		this.sud.add(this.sud_valider);
		this.sud.add(this.sud_retour);
		// SUD //
		
		// NORD //
		this.nord.add(this.titre);
		// NORD //
		
		// CENTRE //
		this.centre.add(this.zoneTexte_scroll,BorderLayout.CENTER);
		// CENTRE //		
		
		this.setLayout(new BorderLayout());
		this.add(this.nord,BorderLayout.NORTH);
		this.add(this.sud,BorderLayout.SOUTH);
		this.add(this.centre,BorderLayout.CENTER);
		
		this.getButtonRetour().addActionListener(new RetourMenuListener());
	}


	public JButton getButtonValider() {
		return valider;
	}
	
	public JButton getButtonRetour() {
		return retour;
	}
	
}
