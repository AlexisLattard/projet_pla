package edu.ricm3.game.tomatower.menu;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import edu.ricm3.game.GameUI;
import edu.ricm3.game.tomatower.mvc.Controller;
import edu.ricm3.game.tomatower.mvc.Model;
import edu.ricm3.game.tomatower.mvc.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Jouer extends JPanel{

	// AUTRES //
		// COMPORTEMENT //
	private HashMap<Integer,Object> comportement_Tours;
	private HashMap<Integer,Object> comportement_Monstres;
	private File carte_selectionner;
	
	private ChoixComportement choix_Comportement_Tours;
	private ChoixComportement choix_Comportement_Monstres;
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
	
	
	private static Jouer INSTANCE = null;
    
    /** Point d'accès pour l'instance unique du singleton */
    public static synchronized Jouer getInstance(ActionListener actionRetour)
    {           
        if (INSTANCE == null){
        	INSTANCE = new Jouer(actionRetour); 
        }
        return INSTANCE;
    }
	
	private Jouer(ActionListener actionRetour) {
		this.setLayout(new BorderLayout());
		// INSTANCIATION //
			// AUTRES //
		this.carte_selectionner = null;
		//TEST//
		HashMap<String,Object> listAutomates = new HashMap<String,Object>();
		listAutomates.put(new String("Automates1"), new String("Automates1"));
		listAutomates.put(new String("Automates2"), new String("Automates2"));
		listAutomates.put(new String("Automates4"), new String("Automates3"));
		listAutomates.put(new String("Automates5"), new String("Automates4"));
		listAutomates.put(new String("Automates6"), new String("Automates5"));
		listAutomates.put(new String("Automates7"), new String("Automates6"));
		listAutomates.put(new String("Automates8"), new String("Automates7"));
		//TEST//
		this.choix_Comportement_Tours = new ChoixComportement(listAutomates,3,new ValiderComportementToursListener());
		this.choix_Comportement_Monstres = new ChoixComportement(listAutomates,3,new ValiderComportementMonstreListener());
		
		this.cartes = new HashMap<File,Bouton>();
			// AUTRES //
			// BOUTON //
				// SOUTH //
		this.bouton_lancer = new Bouton("LANCER");
		this.bouton_retour = new Bouton("RETOUR");
				// SOUTH //
				// COMPORTEMENT //
		this.bouton_mobs = new Bouton("Comportements des monstres");
		this.bouton_tours = new Bouton("Comportements des tours");
				// COMPORTEMENT //
			// BOUTON //

			// LABEL //
				// COMPORTEMENT //
		this.label_mobs = new JLabel("Image de mobs");
		this.label_towers= new JLabel("Image de tours");
		this.label_comportement = new JLabel("Comportement");
				// COMPORTEMENT //
				// MAP //
		this.label_choix_carte = new JLabel("Cartes");
				// MAP //
			// LABEL //
		
			// PANEL //
				// SOUTH //
		this.sud_lancer = new JPanel(new FlowLayout(FlowLayout.LEFT));
		this.sud_retour = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		this.sud = new JPanel(new GridLayout(1,2));
				// SOUTH //
				// COMPORTEMENT //
		this.panel_mobs = new JPanel(new FlowLayout());
		this.panel_towers = new JPanel(new FlowLayout());
		this.panel_mobs_towers = new JPanel(new GridLayout(2,1));
		this.panel_comportement = new JPanel(new BorderLayout());
		this.panel_title = new JPanel(new FlowLayout(FlowLayout.CENTER));
		this.panel_nord = new JPanel(new FlowLayout(FlowLayout.RIGHT));
				// COMPORTEMENT //
				// MAP //
		this.panel_choix_carte = new JPanel(new BorderLayout());
		this.panel_titre_carte = new JPanel(new FlowLayout(FlowLayout.CENTER));
		this.panel_cartes = new JPanel(new WrapLayout());
		
				// MAP //
		// INSTANCIATION //
		
		initSouth()	;
		initComportement();
		initMap();
		this.getButtonRetour().addActionListener(actionRetour);
	}
	
	private void initSouth() {
		// SOUTH //
		this.sud_lancer.add(this.bouton_lancer);
		this.sud_retour.add(this.bouton_retour);
		this.sud.add(this.sud_lancer);
		this.sud.add(this.sud_retour);
		this.bouton_lancer.addActionListener(new LancerListener());
		this.add(sud,BorderLayout.SOUTH);
	    // SOUTH //
	}
	
	private void initComportement() {
		// COMPORTEMENT //		
		this.panel_mobs.add(this.label_mobs);
		this.panel_mobs.add(this.bouton_mobs);
		this.panel_towers.add(this.label_towers);
		this.panel_towers.add(this.bouton_tours);
		this.panel_mobs_towers.add(this.panel_towers);
		this.panel_mobs_towers.add(this.panel_mobs);
		this.panel_title.add(this.label_comportement);
		this.panel_comportement.add(this.panel_title,BorderLayout.NORTH);
		this.panel_comportement.add(this.panel_mobs_towers,BorderLayout.CENTER);
		this.panel_comportement.setBorder(BorderFactory.createLineBorder(Color.black)); //
		this.panel_nord.add(this.panel_comportement);
		this.bouton_tours.addActionListener(new ComportementListener());
		this.bouton_mobs.addActionListener(new ComportementListener());
		setComportementTours(choix_Comportement_Tours.getComportements());
		setComportementMonstres(choix_Comportement_Monstres.getComportements());
		this.add(this.panel_nord,BorderLayout.NORTH);
		// COMPORTEMENT //
	}

	private void initMap() {
		// MAP //
		this.scrollmap = new JScrollPane(panel_cartes,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.scrollmap.getVerticalScrollBar().setUnitIncrement(10);
		this.panel_titre_carte.add(this.label_choix_carte);
		this.panel_choix_carte.add(this.panel_titre_carte,BorderLayout.NORTH);
		this.panel_choix_carte.add(this.scrollmap,BorderLayout.CENTER);
		fillMap();
		fillCartes();
		this.add(this.panel_choix_carte, BorderLayout.CENTER);
		// MAP //
	}
	
 	private void fillMap() {
 		this.cartes.clear();
		File file = new File("./Map");
		ImageIcon image = new ImageIcon("./Image/Map_Generic.png");
		Border border = BorderFactory.createLineBorder(Color.black,5);
		for (File child : file.listFiles()) {
			if (child.isFile()) {
				Bouton bouton = new Bouton(image);
				bouton.setBorder(border);
			    bouton.addActionListener(new CarteListener(child));
			    this.cartes.put(child,bouton);
			}
        }
	}
	
	private void fillCartes() {
		this.panel_cartes.removeAll();
		if (!this.cartes.isEmpty()) {
			for (Map.Entry<File, Bouton> entry : this.cartes.entrySet()) {
			    File key = entry.getKey();
			    Bouton value = entry.getValue();
			    JPanel panel = new JPanel(new BorderLayout());
			    JPanel titre = new JPanel(new FlowLayout(FlowLayout.CENTER));
			    titre.add(new JLabel(key.getName()));
			    panel.add(titre,BorderLayout.SOUTH);
			    panel.add(value,BorderLayout.CENTER);
			    this.panel_cartes.add(panel);
			}
			setSelected((File) this.cartes.keySet().toArray()[0]);			
		}
	}
	
	private class ComportementListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            //In response to a button click:
            if (e.getSource() == bouton_mobs) {
            	choix_Comportement_Monstres.setVisible(true);
            }else {
            	choix_Comportement_Tours.setVisible(true);
            }
        }
    }
	
	private class LancerListener implements ActionListener
    {
		
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (!comportement_Tours.isEmpty() && !comportement_Monstres.isEmpty() && carte_selectionner != null) {
            	My_Frame.getInstance().setVisible(false);
            	createInstanceJeu();
            }else {
            	if (choix_Comportement_Tours == null) {
        			bouton_tours.setText("*Choisir un automate*"); 
        			bouton_tours.setForeground(Color.RED);        			
            	}
            	if (comportement_Monstres == null) {
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
        	setSelected(file);
        }
    }
	
	private void setSelected(File file) {

    	if (carte_selectionner != file) {
    		if (carte_selectionner != null) {
        		cartes.get(carte_selectionner).setBorder(BorderFactory.createLineBorder(Color.black,5)); //;
        	}
    		cartes.get(file).setBorder(BorderFactory.createLineBorder(Color.GREEN,5)); //;
    		carte_selectionner = file;
    	}
	}
	
	public JButton getButtonRetour() {
		return this.bouton_retour;
	}
	
	private void createInstanceJeu() {
		Model model = new Model();
        Controller controller = new Controller(model);
        View view = new View(model,controller);
        model.initModel(controller);
        new GameUI(model,view,controller,My_Frame.getInstance().getSize());
	}
	
	private class ValiderComportementToursListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Jouer.getInstance(null).setComportementTours(choix_Comportement_Tours.getComportements());
			choix_Comportement_Tours.setVisible(false);
			System.out.println(comportement_Tours.get(0));
		}
		
	}
	
	private class ValiderComportementMonstreListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Jouer.getInstance(null).setComportementMonstres(choix_Comportement_Monstres.getComportements());
			choix_Comportement_Monstres.setVisible(false);
			System.out.println(comportement_Monstres.get(0));
		}
		
	}

	private void setComportementTours(HashMap<Integer,Object> comportements_Tours) {
		this.comportement_Tours = comportements_Tours;
	}
	
	private void setComportementMonstres(HashMap<Integer,Object> comportements_Monstres) {
		this.comportement_Monstres = comportements_Monstres;
	}

}
