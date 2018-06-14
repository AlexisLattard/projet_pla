package edu.ricm3.game.tomatower.menu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import edu.ricm3.game.tomatower.automaton.A_Automaton;
import edu.ricm3.game.tomatower.entities.enums.EntityName;

public class ChoixComportement extends JDialog{
	private HashMap<String, A_Automaton> automates;
	private Vector<JComboBox<Object>> choix;
	private Vector<JCheckBox> exclusion;
	private int nbElements ;
	
	private JPanel grille;
	private JScrollPane scrollGrille;
	
	private JPanel titre_panel;
	private JLabel titre_label;
	
	private JPanel sud_panel;
	private JPanel valider_panel;
	private JPanel aleatoire_panel;
	private JPanel Sauvegarde_panel;
	private Bouton valider_bouton;
	private Bouton aleatoire_bouton;
	private Bouton Sauvegarde_bouton;
	
	
	public ChoixComportement( HashMap<String, A_Automaton> automates) {
		super(My_Frame.getInstance());
		this.automates = automates;
		this.nbElements = EntityName.values().length;
		this.setLayout(new BorderLayout());
		this.setSize(400,500);
		initCentre();
		initSud();
		initNord();
	}
	
	private void initSud() {
		this.valider_bouton = new Bouton("Valider");
		this.valider_bouton.addActionListener(new ValiderComportementListener());
		this.Sauvegarde_bouton = new Bouton("Sauvegarder");
		this.Sauvegarde_bouton.addActionListener(new SauvegardeComportementListener());
		this.aleatoire_bouton = new Bouton("Aleatoire");
		this.aleatoire_bouton.addActionListener(new ComportementAleatoireListener());
		
		this.valider_panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		this.valider_panel.add(this.valider_bouton);
		this.aleatoire_panel= new JPanel(new FlowLayout(FlowLayout.RIGHT));
		this.aleatoire_panel.add(this.aleatoire_bouton);
		this.Sauvegarde_panel= new JPanel(new FlowLayout(FlowLayout.CENTER));
		this.Sauvegarde_panel.add(this.Sauvegarde_bouton);
		
		
		this.sud_panel = new JPanel(new GridLayout(1,3));
		this.sud_panel.add(this.valider_panel);
		this.sud_panel.add(this.Sauvegarde_panel);
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
		this.exclusion = new Vector<JCheckBox>(this.nbElements);
		this.grille = new JPanel(new GridLayout(this.nbElements,1));
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel.add(grille);
		this.scrollGrille = new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
										JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		remplirExclusion();
		remplirChoix();
		remplirGrille(this.automates);
		this.add(this.scrollGrille, BorderLayout.CENTER);
	}
	
	private void remplirChoix() {
		Object keys[] = this.automates.keySet().toArray();
		HashMap<String,String> sauvegarde = getSauvergarde();
		for(int i=0;i<this.nbElements;i++) {
			JComboBox<Object> combobox = new JComboBox<Object>(keys);
			Object selectedItem = sauvegarde.get(EntityName.values()[i].name());
			if (selectedItem != null) {
				combobox.setSelectedItem(selectedItem);
			}
			this.choix.add(combobox);
		}
	}
	
	private HashMap<String,String> getSauvergarde(){
		File file = new File(EcritureDuFichier.FICHIER_COMPORTEMENTS);
		LecteurDeFichier lecteur = new LecteurDeFichier(file);
		HashMap<String,String> sauvergarde = new HashMap<String,String>();
		if (lecteur.lecteurisCreated()) {
			while (!lecteur.estFin()) {
				String[] row = lecteur.getNextLineCSV("=");
				if (row != null && row.length == 2) {
					sauvergarde.put(row[0], row[1]);
				}
			}
		}
		lecteur.fermeFichier();
		return sauvergarde;
	}
	
	private void remplirExclusion(){
		for(int i=0;i<this.nbElements;i++) {
			this.exclusion.add(new JCheckBox());
		}
	}
	
	private void remplirGrille(HashMap<String,A_Automaton> listAutomates) {
		for (int i=0;i<this.nbElements;i++) {			
			JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			panel.add(new JLabel(EntityName.values()[i].name()));
			panel.add(this.choix.get(i));
			panel.add(this.exclusion.get(i));
			this.grille.add(panel);
		}
	}

	public void aleatoire() {
		if (SwingUtilities.isEventDispatchThread()) {
			int nb_automates = this.automates.size();
			if (nb_automates > 0) {
				for(int i=0;i<this.nbElements;i++) {
					if (!this.exclusion.get(i).isSelected()) {
						int indice = ThreadLocalRandom.current().nextInt(0, nb_automates);
						this.choix.get(i).setSelectedIndex(indice);
					}
				}
			}
		}
	}
	
	public  HashMap<EntityName,A_Automaton> getComportements() {
		HashMap<EntityName,A_Automaton> comportement = new HashMap<EntityName,A_Automaton>();
		for (int i=0;i<this.nbElements;i++) {
			Object item = choix.get(i).getSelectedItem();
			A_Automaton automate = this.automates.get(item);
			comportement.put(EntityName.values()[i], automate);
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
			setVisible(false);
		}
		
	}
	
	private class SauvegardeComportementListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			EcritureDuFichier ecrit =new EcritureDuFichier(EcritureDuFichier.FICHIER_COMPORTEMENTS);
			ecrit.EcrireComportements(getComportements());
			ecrit.fermeEcriture();
		}
		
	}
	
	// Listener//
}
