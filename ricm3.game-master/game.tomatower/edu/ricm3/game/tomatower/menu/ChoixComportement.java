package edu.ricm3.game.tomatower.menu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class ChoixComportement extends JDialog{
	private HashMap<String,Object> listAutomates;
	private Vector<JComboBox<Object>> choix;
	
	private JPanel grille;
	private JScrollPane scrollGrille;
	
	private JPanel titre_panel;
	private JLabel titre_label;
	
	private JPanel sud_panel;
	private JPanel valider_panel;
	private JPanel aleatoire_panel;
	private Bouton valider_bouton;
	private Bouton aleatoire_bouton;
	
	
	public ChoixComportement(HashMap<String,Object> listAutomates,int nbElements,ActionListener actionretour) {
		super(My_Frame.getInstance());
		this.listAutomates = listAutomates;
		this.setLayout(new BorderLayout());
		this.setSize(400,200);
		initCentre(nbElements);
		initSud(actionretour);
		initNord();
	}
	
	private void initSud(ActionListener actionretour) {
		this.valider_bouton = new Bouton("Valider");
		this.valider_bouton.addActionListener(actionretour);
		this.aleatoire_bouton = new Bouton("Aleatoire");
		this.aleatoire_bouton.addActionListener(new ComportementAleatoireListener(this));
		
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
	
	private void initCentre(int nbElements) {
		this.choix = new Vector<JComboBox<Object>>(nbElements);
		this.grille = new JPanel(new GridLayout(nbElements,1));
		this.scrollGrille = new JScrollPane(this.grille,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
										JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		remplirChoix(this.listAutomates,nbElements);
		remplirGrille(this.listAutomates,nbElements);
		this.add(this.scrollGrille, BorderLayout.CENTER);
	}
	
	private void remplirChoix(HashMap<String,Object> listAutomates,int nbElements) {
		Object keys[] = listAutomates.keySet().toArray();
		for(int i=0;i<nbElements;i++) {
			choix.add(new JComboBox<Object>(keys));
		}
		
	}
	
	private void remplirGrille(HashMap<String,Object> listAutomates,int nbElements) {
		for (int i=0;i<this.choix.size();i++) {			
			JPanel panel = new JPanel(new FlowLayout());
			panel.add(new JLabel("Item n°"+i));
			panel.add(this.choix.get(i));
			this.grille.add(panel);
		}
	}

	public void aleatoire() {
		if (SwingUtilities.isEventDispatchThread()) {
			int size = this.listAutomates.size();
			for(int i=0;i<this.choix.capacity();i++) {
				int indice = ThreadLocalRandom.current().nextInt(0, size);
				this.choix.get(i).setSelectedIndex(indice);
			}
		}
	}
	
	public  HashMap<Integer,Object> getComportements() {
		// TODO Renvoyer la correspondance Entité-Automates
		HashMap<Integer,Object> comportement = new HashMap<Integer,Object>();
		
		for (int i=0;i<this.choix.size();i++) {
			comportement.put(new Integer(i), choix.get(i).getSelectedItem());
		}
		
		return comportement;
	}
	
	// Listener//
	private class ComportementAleatoireListener implements ActionListener{
		private ChoixComportement comportement;
		
		public ComportementAleatoireListener(ChoixComportement comportement) {
			super();
			this.comportement = comportement;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			comportement.aleatoire();
		}
	}
	// Listener//
}
