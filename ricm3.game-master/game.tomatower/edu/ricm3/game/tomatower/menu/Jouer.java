package edu.ricm3.game.tomatower.menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;

public class Jouer extends JPanel{

	// AUTRES //
		// COMPORTEMENT //
	private File comportement_tours;
	private File comportement_sbires;
	private JFileChooser fileChoser;
		// COMPORTEMENT //
	// AUTRES //	
	
	// BOUTON //
		// COMPORTEMENT //
	private Bouton bouton_mobs;
	private Bouton bouton_tours;
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
	
	// JTREE //
		JTree arbre_comportement;
	// JTREE //
	
	public Jouer() {
		// INSTANCIATION //
			// AUTRES //
		comportement_tours = null;
		comportement_sbires = null;
		fileChoser = new JFileChooser();
			// AUTRES //
			// BOUTON //
				// SOUTH //
		bouton_lancer = new Bouton("LANCER");
		bouton_retour = new Bouton("RETOUR");
				// SOUTH //
				// COMPORTEMENT //
		bouton_mobs = new Bouton("Choisir un automate");
		bouton_tours = new Bouton("Choisir un automate");
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
		panel_towers.add(bouton_tours);
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

		bouton_tours.addActionListener(new ComportementListener(this));
		bouton_mobs.addActionListener(new ComportementListener(this));
		bouton_lancer.addActionListener(new LancerListener());
		this.setLayout(new BorderLayout());
		this.add(sud,BorderLayout.SOUTH);
		this.add(panel_nord,BorderLayout.NORTH);
		
	}
	
	private class ComportementListener implements ActionListener
    {
		private JPanel panel;
    
    
	    public ComportementListener(JPanel panel)
	    {
	        this.panel = panel;
	    } 
        
        @Override
        public void actionPerformed(ActionEvent e)
        {
            //In response to a button click:
            int returnVal = fileChoser.showOpenDialog(panel);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                if (e.getSource() == bouton_mobs) {
                	setComportementSbires(fileChoser.getSelectedFile());
                }else {
                	setComportementTours(fileChoser.getSelectedFile());
                }
                
            	System.out.println("fichier recuperer");
            } else {
            	System.out.println("fichier non recuperer");
            }
        }
    }
	
	private class LancerListener implements ActionListener
    {
		
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (comportement_tours != null && comportement_sbires != null) {
            	System.out.println("Jeux Lancer");
            }else {
            	if (comportement_tours == null) {
        			bouton_tours.setText("*Choisir un automate "); 
        			bouton_tours.setForeground(Color.RED);
        			
            	}
            	if (comportement_sbires == null) {
        			bouton_mobs.setText("*Choisir un automate");
        			bouton_mobs.setForeground(Color.RED);
            	}
            }
        }
    }
	
	public JButton getButtonRetour() {
		return bouton_retour;
	}
	
	private void setComportementTours(File file) {
		if (file == null) {
			bouton_tours.setText("Choisir un automate");
		}else {
			bouton_tours.setText(file.getName());
			bouton_tours.setForeground(Color.BLACK);
		}
		comportement_tours = file;
	}
	
	private void setComportementSbires(File file) {
		if (file == null) {
			bouton_mobs.setText("Choisir un automate");
		}else {
			bouton_mobs.setText(file.getName());
			bouton_mobs.setForeground(Color.BLACK);
		}
		comportement_sbires = file;
	}
}
