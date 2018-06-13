package edu.ricm3.game.tomatower.menu;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.ricm3.game.tomatower.listeners.RetourMenuListener;


/**
 * 
 * 
 * @author (Lordey A.K.A the thief) 
 *
 */
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
    public static synchronized Option getInstance()
    {           
        if (INSTANCE == null){
        	INSTANCE = new Option(); 
        }
        return INSTANCE;
    }
    
	private Option() {
		this.setLayout(new BorderLayout());
		this.centre = new JPanel(new GridLayout(2,1));
		this.add(centre, BorderLayout.CENTER);
		initTitre();
		initSud();
		initResolution();
		initOptionFenetre();
		this.bouton_retour.addActionListener(new RetourMenuListener());
		this.bouton_valider.addActionListener(new ValiderListener(this));
	}
	
	private void initResolution() {
		this.resolution_list = new JComboBox<Object>();
		FillCombobox();
		
		this.resolution_panel = new JPanel(new FlowLayout());
		this.resolution_panel.add(new JLabel("Resolution de l'écran:"));
		this.resolution_panel.add(this.resolution_list);
		
		this.centre.add(this.resolution_panel);
	}
	
	private void initOptionFenetre() {
		this.optionFenetre_list = new JComboBox<Object>(new String[] {"Plein écran","Fenetrer","Sans rebord"});
		
		this.optionFentre_panel = new JPanel(new FlowLayout());
		this.optionFentre_panel.add(new JLabel("Option de l'écran:"));
		this.optionFentre_panel.add(this.optionFenetre_list);
		
		this.centre.add(this.optionFentre_panel);
	}
	
	private void initSud() {
		this.bouton_retour = new Bouton("Retour");
		this.bouton_valider = new Bouton("Valider");
		this.sud= new JPanel(new GridLayout(1,2));
		this.sud_valider = new JPanel(new FlowLayout(FlowLayout.LEFT));
		this.sud_valider.add(this.bouton_valider);
		this.sud_retour = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		this.sud_retour.add(this.bouton_retour);
		this.sud.add(this.sud_valider);
		this.sud.add(this.sud_retour);
		this.add(this.sud,BorderLayout.SOUTH);
	}
	
	private void initTitre() {
		this.titre = new JLabel("Options");
		this.titre_panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		this.titre_panel.add(this.titre);
		this.add(this.titre_panel, BorderLayout.NORTH);
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
				this.resolution_list.addItem(new String(row.get(1)+"x"+row.get(2)));
			}
		}
	}
	
	public JButton getButtonRetour() {
		return this.bouton_retour;
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
