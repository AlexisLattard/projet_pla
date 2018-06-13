package edu.ricm3.game.tomatower.menu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import edu.ricm3.game.tomatower.automaton.A_Automaton;
import edu.ricm3.game.tomatower.entities.enums.Entity_Name;

public class ChoixComportement extends JDialog{
	private HashMap<String, A_Automaton> automates;
	private Vector<JComboBox<Object>> choix;
	private int nbElements ;
	
	private JPanel grille;
	private JScrollPane scrollGrille;
	
	private JPanel titre_panel;
	private JLabel titre_label;
	
	private JPanel sud_panel;
	private JPanel valider_panel;
	private JPanel aleatoire_panel;
	private Bouton valider_bouton;
	private Bouton aleatoire_bouton;
	
	
	public ChoixComportement( HashMap<String, A_Automaton> automates) {
		super(My_Frame.getInstance());
		this.automates = automates;
		this.nbElements = Entity_Name.values().length;
		this.setLayout(new BorderLayout());
		this.setSize(400,200);
		initCentre();
		initSud();
		initNord();
	}
	
	private void initSud() {
		this.valider_bouton = new Bouton("Valider");
		this.valider_bouton.addActionListener(new ValiderComportementListener());
		this.aleatoire_bouton = new Bouton("Aleatoire");
		this.aleatoire_bouton.addActionListener(new ComportementAleatoireListener());
		
		this.valider_panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		this.valider_panel.add(this.valider_bouton);
		this.aleatoire_panel= new JPanel(new FlowLayout(FlowLayout.RIGHT));
		this.aleatoire_panel.add(this.aleatoire_bouton);
		
		
		this.sud_panel = new JPanel(new GridLayout(1,2));
		this.sud_panel.add(this.valider_panel);
		this.sud_panel.add(this.aleatoire_panel);
		this.add(this.sud_panel, BorderLayout.SOUTH);
		
	}
	
	private void initNord() {
		this.titre_panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		this.titre_label = new JLabel("Comportement");
		this.titre_panel.add(this.titre_label);
		this.add(this.titre_panel,BorderLayout.NORTH);
	}
	
	private void initCentre() {
		this.choix = new Vector<JComboBox<Object>>(this.nbElements);
		this.grille = new JPanel(new GridLayout(this.nbElements,1));
		this.scrollGrille = new JScrollPane(this.grille,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
										JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		remplirChoix(this.automates);
		remplirGrille(this.automates);
		this.add(this.scrollGrille, BorderLayout.CENTER);
	}
	
	private void remplirChoix(HashMap<String,A_Automaton> listAutomates) {
		Object keys[] = this.automates.keySet().toArray();
		for(int i=0;i<this.nbElements;i++) {
			choix.add(new JComboBox<Object>(keys));
		}
		
	}
	
	private void remplirGrille(HashMap<String,A_Automaton> listAutomates) {
		for (int i=0;i<this.nbElements;i++) {			
			JPanel panel = new JPanel(new FlowLayout());
			panel.add(new JLabel(Entity_Name.values()[i].name()));
			panel.add(this.choix.get(i));
			this.grille.add(panel);
		}
	}

	public void aleatoire() {
		if (SwingUtilities.isEventDispatchThread()) {
			int nb_automates = this.automates.size();
			if (nb_automates > 0) {
				for(int i=0;i<this.nbElements;i++) {
					int indice = ThreadLocalRandom.current().nextInt(0, nb_automates);
					this.choix.get(i).setSelectedIndex(indice);
				}
			}
		}
	}
	
	public  HashMap<Entity_Name,A_Automaton> getComportements() {
		// TODO Renvoyer la correspondance Entité-Automates
		HashMap<Entity_Name,A_Automaton> comportement = new HashMap<Entity_Name,A_Automaton>();
		for (int i=0;i<Entity_Name.values().length;i++) {
			A_Automaton automate = this.automates.get(choix.get(i).getSelectedItem());
			comportement.put(Entity_Name.values()[i], automate);
		}
		
		return comportement;
	}
	
	// Listener//
	private class ComportementAleatoireListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			aleatoire();
		}
	}
	
	private class ValiderComportementListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			HashMap<Entity_Name,A_Automaton> map = getComportements();
			Jouer.getInstance().setComportement(map);
			setVisible(false);
		}
		
	}
	// Listener//
}
