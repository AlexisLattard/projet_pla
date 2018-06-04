package edu.ricm3.game.tomatower.menu;

import javax.swing.*;
import java.awt.*;

public class Regles extends JPanel{

	
	private JPanel north;
	private JLabel title;
	private JPanel south;
	private Bouton boutonRetour;
	
	public Regles() {
		north = new JPanel(new FlowLayout());
		title = new JLabel("Regles");
		south = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		boutonRetour = new Bouton("Retour");
		
		north.add(title);
		south.add(boutonRetour);
		
		
		this.setLayout(new BorderLayout());
		this.add(north,BorderLayout.NORTH);
		this.add(south, BorderLayout.SOUTH);
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
