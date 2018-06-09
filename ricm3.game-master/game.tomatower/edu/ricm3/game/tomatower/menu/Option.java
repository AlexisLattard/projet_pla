package edu.ricm3.game.tomatower.menu;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class Option extends JPanel{
	
	private Bouton bouton_retour;
	private Bouton bouton_valider;
	private JPanel sud;
	private JPanel sud_valider;
	private JPanel sud_retour;
	private JComboBox<Object> resolution_list;
	private JComboBox<Object> optionFenetre_list;
	
	private JPanel centre;
	private JPanel resolution_panel;
	private JPanel optionFentre_panel;
	private JPanel titre_panel;
	private JLabel titre;
	
	/** Instance unique non préinitialisée */
    private static Option INSTANCE = null;
     
    /** Point d'accès pour l'instance unique du singleton */
    public static synchronized Option getInstance(ActionListener actionRetour)
    {           
        if (INSTANCE == null){
        	INSTANCE = new Option(actionRetour); 
        }
        return INSTANCE;
    }
    
	private Option(ActionListener actionRetour) {
		this.setLayout(new BorderLayout());
		this.centre = new JPanel(new GridLayout(2,1));
		this.add(centre, BorderLayout.CENTER);
		initTitre();
		initSud();
		initResolution();
		initOptionFenetre();
		bouton_retour.addActionListener(actionRetour);
		bouton_valider.addActionListener(new ValiderListener(this));
	}
	
	private void initResolution() {
		resolution_list = new JComboBox<Object>();
		FillCombobox();
		
		resolution_panel = new JPanel(new FlowLayout());
		resolution_panel.add(new JLabel("Resolution de l'écran:"));
		resolution_panel.add(resolution_list);
		
		centre.add(resolution_panel);
	}
	
	private void initOptionFenetre() {
		optionFenetre_list = new JComboBox<Object>(new String[] {"Plein écran","Fenetrer","Sans rebord"});
		
		optionFentre_panel = new JPanel(new FlowLayout());
		optionFentre_panel.add(new JLabel("Option de l'écran:"));
		optionFentre_panel.add(optionFenetre_list);
		
		centre.add(optionFentre_panel);
	}
	
	private void initSud() {
		bouton_retour = new Bouton("Retour");
		bouton_valider = new Bouton("Valider");
		sud= new JPanel(new GridLayout(1,2));
		sud_valider = new JPanel(new FlowLayout(FlowLayout.LEFT));
		sud_valider.add(bouton_valider);
		sud_retour = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		sud_retour.add(bouton_retour);
		sud.add(sud_valider);
		sud.add(sud_retour);
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
		Vector<Vector<Object>> resolution = getResolutionAvailable();
		if (resolution != null) {
			for (Vector<Object> row : resolution) {
				resolution_list.addItem(new String(row.get(1)+"x"+row.get(2)));
			}
		}
	}
	
	public JButton getButtonRetour() {
		return bouton_retour;
	}
	
	private class ValiderListener implements ActionListener
    {
        
        public ValiderListener(JPanel panel)
        {
        	
        } 
        
        @Override
        public void actionPerformed(ActionEvent e)
        {
        	actionResolution();
        	actionOptionFenetre();
        }

		private void actionOptionFenetre() {
			String resolution = (String) optionFenetre_list.getSelectedItem();

			My_Frame.getInstance().dispose();
			switch (resolution) {
				case "Fenetrer":
					My_Frame.getInstance().setUndecorated(false);
					My_Frame.getInstance().setLocationRelativeTo(null);
					break;
				case "Sans rebord":
					My_Frame.getInstance().setUndecorated(true);
					My_Frame.getInstance().setLocationRelativeTo(null);
					break;
				case "Plein écran":
					// TODO LMAO
					My_Frame.getInstance().setLocationRelativeTo(null);
					
					break;
				default:
			}
			My_Frame.getInstance().setVisible(true);
			
		}

		private void actionResolution() {
			// TODO Auto-generated method stub
			String resolution = (String) resolution_list.getSelectedItem();
			String[] values = resolution.split("x");
			Integer width = new Integer(values[0]);
			Integer height = new Integer(values[1]);
			My_Frame.getInstance().setSize(width, height);
		}   
    }
}
