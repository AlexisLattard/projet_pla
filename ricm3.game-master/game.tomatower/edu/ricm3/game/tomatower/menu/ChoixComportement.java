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
import javax.swing.JFrame;
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
	
	
	public ChoixComportement(HashMap<String,Object> listAutomates,int nbElements) {
		super(My_Frame.getInstance());
		this.listAutomates = listAutomates;
		this.setLayout(new BorderLayout());
		this.setSize(400,200);
		initCentre(nbElements);
		initSud();
		initNord();
	}
	
	private void initSud() {
		valider_bouton = new Bouton("Valider");
		valider_bouton.addActionListener(new ValiderListener(this));
		aleatoire_bouton = new Bouton("Aleatoire");
		aleatoire_bouton.addActionListener(new AleatoireListener());
		
		valider_panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		valider_panel.add(valider_bouton);
		aleatoire_panel= new JPanel(new FlowLayout(FlowLayout.RIGHT));
		aleatoire_panel.add(aleatoire_bouton);
		
		
		sud_panel = new JPanel(new GridLayout(1,2));
		sud_panel.add(valider_panel);sud_panel.add(aleatoire_panel);
		this.add(sud_panel, BorderLayout.SOUTH);
		
	}
	
	private void initNord() {
		titre_panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		titre_label = new JLabel("Comportement");
		titre_panel.add(titre_label);
		this.add(titre_panel,BorderLayout.NORTH);
	}
	
	private void initCentre(int nbElements) {
		choix = new Vector<JComboBox<Object>>(nbElements);
		grille = new JPanel(new GridLayout(nbElements,1));
		scrollGrille = new JScrollPane(grille,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
										JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		remplirChoix(listAutomates,nbElements);
		remplirGrille(listAutomates,nbElements);
		this.add(scrollGrille, BorderLayout.CENTER);
	}
	
	private void remplirChoix(HashMap<String,Object> listAutomates,int nbElements) {
		Object keys[] = listAutomates.keySet().toArray();
		for(int i=0;i<nbElements;i++) {
			choix.add(new JComboBox<Object>(keys));
		}
		
	}
	
	private void remplirGrille(HashMap<String,Object> listAutomates,int nbElements) {
		for (int i=0;i<choix.size();i++) {			
			JPanel panel = new JPanel(new FlowLayout());
			panel.add(new JLabel("Item n°"+i));
			panel.add(choix.get(i));
			grille.add(panel);
		}
	}

	private void aleatoire() {
		if (SwingUtilities.isEventDispatchThread()) {
			int size = listAutomates.size();
			for(int i=0;i<choix.capacity();i++) {
				int indice = ThreadLocalRandom.current().nextInt(0, size);
				choix.get(i).setSelectedIndex(indice);
			}
		}
	}

	private class AleatoireListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			aleatoire();
		}
		
	}
	
	private class ValiderListener implements ActionListener{
		JDialog dialog;
		
		public ValiderListener(JDialog dialog) {
			this.dialog = dialog;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			dialog.setVisible(false);
		}
		
	}
	
}
