package edu.ricm3.game.tomatower.menu;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChoixComportement extends JDialog{
	private Vector<JComboBox<Object>> choix;
	private JPanel grille;
	
	public ChoixComportement(HashMap<String,Object> listAutomates,int nbElements) {
		choix = new Vector<JComboBox<Object>>(nbElements);
		remplirChoix(listAutomates,nbElements);
		remplirGrille(listAutomates,nbElements);
		grille = new JPanel(new GridLayout(nbElements,1));
	}
	
	private void remplirChoix(HashMap<String,Object> listAutomates,int nbElements) {
		JComboBox<Object> key = new JComboBox<Object>(listAutomates.keySet().toArray());
		for(int i=0;i<nbElements;i++) {
			choix.get(0).addItem(key);
		}
	}
	
	private void remplirGrille(HashMap<String,Object> listAutomates,int nbElements) {
		int i=0;
		for (Map.Entry<String, Object> entry : listAutomates.entrySet()) {
			String key = entry.getKey();
			
			JPanel panel = new JPanel(new FlowLayout());
			panel.add(new JLabel(key));
			panel.add(choix.get(i));
			grille.add(panel);
			i++;
		}
	}
}
