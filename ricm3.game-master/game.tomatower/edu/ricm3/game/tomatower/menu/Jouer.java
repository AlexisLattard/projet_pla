package edu.ricm3.game.tomatower.menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Jouer extends JPanel{

	// AUTRES //
		// COMPORTEMENT //
	private File comportement_tours;
	private File comportement_sbires;
	private File carte_selectionner;
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
	private	HashMap<File,Bouton> cartes;
		// MAP //
	// BOUTON //
	
	// JLABEL //
		// COMPORTEMENT //
	private JLabel label_mobs;
	private JLabel label_towers;
	private JLabel label_comportement;
		// COMPORTEMENT //
		// MAP //
	private	 JLabel label_choix_carte;
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
	private JPanel panel_choix_carte;
	private JPanel panel_titre_carte;
		// MAP //
	// JPANEL //
	
	// JSCROLLBAR //
		// MAP //
	private JScrollPane scrollmap;
		// MAP //
	
	public Jouer() {
		this.setLayout(new BorderLayout());
		// INSTANCIATION //
			// AUTRES //
		comportement_tours = null;
		comportement_sbires = null;
		carte_selectionner = null;


		UIManager.put("FileChooser.readOnly", Boolean.TRUE);   
		fileChoser = new JFileChooser();
		
		cartes = new HashMap<File,Bouton>();
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
				// MAP //
		label_choix_carte = new JLabel("Cartes");
				// MAP //
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
				// MAP //
		panel_choix_carte = new JPanel(new BorderLayout());
		panel_cartes = new JPanel(new FlowLayout());
		panel_titre_carte = new JPanel(new FlowLayout(FlowLayout.CENTER));
				// MAP //
			// PANEL //
			// JSCROLLBAR //
				// MAP //
				// MAP //
			// JSCROLLBAR //
		// INSTANCIATION //
		
		initSouth()	;
		initComportement();
		initMap();
	}
	
	private void initSouth() {
		// SOUTH //
		sud_lancer.add(bouton_lancer);
		sud_retour.add(bouton_retour);
		sud.add(sud_lancer);
		sud.add(sud_retour);
		bouton_lancer.addActionListener(new LancerListener());
		this.add(sud,BorderLayout.SOUTH);
	    // SOUTH //
	}
	
	private void initComportement() {
		// COMPORTEMENT //
		
		panel_mobs.add(label_mobs);
		panel_mobs.add(bouton_mobs);
		panel_towers.add(label_towers);
		panel_towers.add(bouton_tours);
		panel_mobs_towers.add(panel_towers);
		panel_mobs_towers.add(panel_mobs);
		panel_title.add(label_comportement);
		panel_comportement.add(panel_title,BorderLayout.NORTH);
		panel_comportement.add(panel_mobs_towers,BorderLayout.CENTER);
		panel_comportement.setBorder(BorderFactory.createLineBorder(Color.black)); //
		panel_nord.add(panel_comportement);
		bouton_tours.addActionListener(new ComportementListener(this));
		bouton_mobs.addActionListener(new ComportementListener(this));
		this.add(panel_nord,BorderLayout.NORTH);
		// COMPORTEMENT //
	}

	private void initMap() {
		// MAP //
		scrollmap = new JScrollPane(panel_cartes,JScrollPane.VERTICAL_SCROLLBAR_NEVER,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		panel_titre_carte.add(label_choix_carte);
		panel_choix_carte.add(panel_titre_carte,BorderLayout.NORTH);
		panel_choix_carte.add(scrollmap,BorderLayout.CENTER);
		panel_choix_carte.setBorder(BorderFactory.createLineBorder(Color.black));
		fillMap();
		fillCartes();
		this.add(panel_choix_carte, BorderLayout.CENTER);
		// MAP //
	}
	
 	private void fillMap() {
		cartes.clear();
		File file = new File("./Map");
		for (File child : file.listFiles()) {
			if (child.isFile()) {
				Bouton bouton = new Bouton(new ImageIcon("./Image/Map_Generic.png"),100,100);
				bouton.setBorder(BorderFactory.createLineBorder(Color.black,5));
			    bouton.addActionListener(new CarteListener(child));
				cartes.put(child,bouton);
			}
        }
	}
	
	private void fillCartes() {
		panel_cartes.removeAll();
		if (!cartes.isEmpty()) {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					for (Map.Entry<File, Bouton> entry : cartes.entrySet()) {
					    File key = entry.getKey();
					    Bouton value = entry.getValue();
					    
					    JPanel panel = new JPanel(new BorderLayout());
					    JPanel titre = new JPanel(new FlowLayout(FlowLayout.CENTER));
					    titre.add(new JLabel(key.getName()));
					    panel.add(titre,BorderLayout.SOUTH);
					    panel.add(value,BorderLayout.CENTER);
					    panel_cartes.add(panel);
					}
				}
			});
		}
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
        			bouton_tours.setText("*Choisir un automate*"); 
        			bouton_tours.setForeground(Color.RED);        			
            	}
            	if (comportement_sbires == null) {
        			bouton_mobs.setText("*Choisir un automate*");
        			bouton_mobs.setForeground(Color.RED);
            	}
            	
            }
        }
    }

	private class CarteListener implements ActionListener
    {
		File file;
		public CarteListener(File file){
			this.file = file;
		}
		
        public void actionPerformed(ActionEvent e) {
        	
        	if (carte_selectionner != file) {
        		if (carte_selectionner != null) {
            		cartes.get(carte_selectionner).setBorder(BorderFactory.createLineBorder(Color.black,5)); //;
            	}
	        	((Bouton)(e.getSource())).setBorder(BorderFactory.createLineBorder(Color.GREEN,5)); //;
	    		carte_selectionner = file;
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
