package edu.ricm3.game.tomatower.menu;

import javax.swing.*;

import java.awt.*;

public class Debug extends JPanel{

	private Bouton valider;
	private Bouton retour;
	private JEditorPane textArea ;
	private JScrollPane textArea_scroll;
	private JLabel titre;
	private JPanel nord;
	private JPanel sud;
	private JPanel sud_valider;
	private JPanel sud_retour;
	private JPanel centre;
	
	
	public Debug() {
		valider= new Bouton("Valider");
		retour = new Bouton("Retour");
		titre = new JLabel("Debug");
		textArea = new JEditorPane();
		textArea_scroll = new JScrollPane(textArea);
		nord = new JPanel(new FlowLayout(FlowLayout.CENTER));
		sud = new JPanel(new GridLayout(1,2));
		sud_valider = new JPanel(new FlowLayout(FlowLayout.LEFT));
		sud_retour = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		centre = new JPanel(new BorderLayout());
		
		// SUD //
		sud_valider.add(valider);
		sud_retour.add(retour);
		sud.add(sud_valider);
		sud.add(sud_retour);
		// SUD //
		
		// NORD //
		nord.add(titre);
		// NORD //
		
		// CENTRE //
		centre.add(textArea,BorderLayout.CENTER);
		centre.add(textArea_scroll,BorderLayout.EAST);
		// CENTRE //		
		
		this.setLayout(new BorderLayout());
		this.add(nord,BorderLayout.NORTH);
		this.add(sud,BorderLayout.SOUTH);
		this.add(centre,BorderLayout.CENTER);
		
	}


	public JButton getButtonValider() {
		return valider;
	}
	
	public JButton getButtonRetour() {
		return retour;
	}
	
}
