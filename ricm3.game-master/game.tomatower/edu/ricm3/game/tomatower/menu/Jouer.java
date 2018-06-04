package edu.ricm3.game.tomatower.menu;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class Jouer extends JPanel{
	
	// BOUTON //
		// COMPORTEMENT //
	private Bouton bouton_mobs;
	private Bouton buton_towers;
		// COMPORTEMENT //
		// SOUTH //
	private Bouton bouton_lancer;
	private Bouton bouton_retour;
		// SOUTH //
		// MAP //
	private	HashMap<String,Bouton> cartes;
		// MAP //
	// BOUTON //
	
	
	// JLABEL //
		// COMPORTEMENT //
	private JLabel label_mobs;
	private JLabel label_towers;
	private JLabel label_comportement;
	private JLabel label_map;
		// COMPORTEMENT //
		// MAP //
	private	 JLabel label_cartes;
		// MAP //
	// JLABEL //
	
	
	// JPANEL //
		// SOUTH //
	private JPanel sud;
	private JPanel sud_lancer;
	private JPanel sud_retour;
		// SOUTH //
		// COMPORTEMENT //
	private JPanel panel_mobs;
	private JPanel panel_towers;
	private JPanel panel_mobs_towers;
	private JPanel panel_comportement;
	private JPanel panel_title;
	private JPanel panel_nord;
		// COMPORTEMENT //
		// MAP //
	private JPanel panel_cartes;
	private JPanel panel_titre_cartes;
	
		// MAP //
	// JPANEL //
	
	
	
	
	public Jouer() {
		// INSTANCIATION //
			// BOUTON //
				// SOUTH //
		bouton_lancer = new Bouton("LANCER");
		bouton_retour = new Bouton("RETOUR");
				// SOUTH //
				// COMPORTEMENT //
		bouton_mobs = new Bouton("SBIRE");
		buton_towers = new Bouton("TOURS");
				// COMPORTEMENT //
			// BOUTON //

			// LABEL //
				// COMPORTEMENT //
		label_mobs = new JLabel("Image de mobs");
		label_towers= new JLabel("Image de tours");
		label_comportement = new JLabel("Comportement");
				// COMPORTEMENT //
			// LABEL //
		
			// PANEL //
				// SOUTH //
		sud_lancer = new JPanel(new FlowLayout(FlowLayout.LEFT));
		sud_retour = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		sud = new JPanel(new GridLayout(1,2));
				// SOUTH //
				// COMPORTEMENT //
		panel_mobs = new JPanel(new FlowLayout());
		panel_towers = new JPanel(new FlowLayout());
		panel_mobs_towers = new JPanel(new GridLayout(2,1));
		panel_comportement = new JPanel(new BorderLayout());
		panel_title = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel_nord = new JPanel(new FlowLayout(FlowLayout.RIGHT));
				// COMPORTEMENT //
			// PANEL //
		// INSTANCIATION //
		
		// SOUTH //
		sud_lancer.add(bouton_lancer);
		sud_retour.add(bouton_retour);
		sud.add(sud_lancer);
		sud.add(sud_retour);
		// SOUTH //
		
		// COMPORTEMENT //
		panel_mobs.add(label_mobs);
		panel_mobs.add(bouton_mobs);
		panel_mobs.setBorder(BorderFactory.createLineBorder(Color.YELLOW)); //
		panel_towers.add(label_towers);
		panel_towers.add(buton_towers);
		panel_towers.setBorder(BorderFactory.createLineBorder(Color.YELLOW)); //
		panel_mobs_towers.add(panel_towers);
		panel_mobs_towers.add(panel_mobs);
		panel_mobs_towers.setBorder(BorderFactory.createLineBorder(Color.GREEN)); //
		panel_title.add(label_comportement);
		panel_title.setBorder(BorderFactory.createLineBorder(Color.red)); //
		panel_comportement.add(panel_title,BorderLayout.NORTH);
		panel_comportement.add(panel_mobs_towers,BorderLayout.CENTER);
		panel_comportement.setBorder(BorderFactory.createLineBorder(Color.black)); //
		panel_nord.add(panel_comportement);
		// COMPORTEMENT //
		
		// MAP //
		
		// MAP //
		
		this.setLayout(new BorderLayout());
		this.add(sud,BorderLayout.SOUTH);
		this.add(panel_nord,BorderLayout.NORTH);
		
	}
	
	public JButton getButtonRetour() {
		return bouton_retour;
	}
}
