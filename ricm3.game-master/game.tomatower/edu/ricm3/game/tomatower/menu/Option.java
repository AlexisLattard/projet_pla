package edu.ricm3.game.tomatower.menu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Option extends JPanel{
	private Bouton bouton_retour;
	private JPanel sud;
	private Vector<Vector<Object>> resolution;
	private JComboBox resolution_list;
	
	private JPanel centre;
	private JPanel resolution_panel;
	private JPanel titre_panel;
	private JLabel titre;
	
	public Option() {
		this.setLayout(new BorderLayout());
		centre = new JPanel(new GridLayout(1,1));
		this.add(centre, BorderLayout.CENTER);
		initTitre();
		initSud();
		initResolution();
	}
	
	private void initResolution() {
		resolution = getResolutionAvailable();
		resolution_list = new JComboBox();
		FillCombobox();
		
		resolution_panel = new JPanel(new FlowLayout());
		resolution_panel.add(new JLabel("Resolution de l'écran:"));
		resolution_panel.add(resolution_list);
		
		centre.add(resolution_panel);
	}
	
	private void initSud() {
		bouton_retour = new Bouton("Retour");
		sud= new JPanel(new FlowLayout(FlowLayout.RIGHT));
		sud.add(bouton_retour);
		this.add(sud,BorderLayout.SOUTH);
	}
	
	private void initTitre() {
		titre = new JLabel("Options");
		titre_panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		titre_panel.add(titre);
		this.add(titre_panel, BorderLayout.NORTH);
	}
	
	private Vector<Vector<Object>> getResolutionAvailable() {
		File file = new File("./Autres/Resolution");
    	Vector<Vector<Object>> newResolution = new Vector<Vector<Object>>();
    	Scanner scanner;
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] split = line.split(";");
            Vector<Object> row = new Vector<Object>();
            row.add(split[0]);row.add(new Integer(split[1]));row.add(new Integer(split[2]));
            newResolution.add(row);
        }
        scanner.close();
        return newResolution;
	}

	private void FillCombobox() {
		if (resolution != null) {
			for (Vector<Object> row : resolution) {
				resolution_list.addItem(new String(row.get(1)+"x"+row.get(2)));
			}
		}
	}
	
	public JButton getButtonRetour() {
		return bouton_retour;
	}
}
