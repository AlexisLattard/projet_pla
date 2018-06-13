package edu.ricm3.game.tomatower.menu;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import edu.ricm3.game.GameUI;
import edu.ricm3.game.tomatower.automaton.A_Automaton;
import edu.ricm3.game.tomatower.entities.enums.Entity_Name;
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
	private HashMap<Entity_Name,A_Automaton> comportement;
	private File carte_selectionner;
	private ChoixComportement choix_Comportement;
		// COMPORTEMENT //
	Model model ;
    Controller controller ;
    View view ;
	// AUTRES //	
	
	// BOUTON //
		// COMPORTEMENT //
	private Bouton bouton_comportement;
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
	private JPanel panel_comportement;
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
    public static synchronized Jouer getInstance()
    {           
        if (INSTANCE == null){
        	INSTANCE = new Jouer(); 
        }
        return INSTANCE;
    }
	
	private Jouer() {
		super();
		this.setLayout(new BorderLayout());
		// INSTANCIATION //
			// AUTRES //
		//TEST//
		this.model = new Model();
		this.controller = new Controller(model);
		this.model.initAutomatons(controller);
		this.carte_selectionner = null;
		//TEST//
		//TEST//
		
		this.choix_Comportement = new ChoixComportement(model.getAutomatons());
		this.cartes = new HashMap<File,Bouton>();
			// AUTRES //
			// BOUTON //
				// SOUTH //
		this.bouton_lancer = new Bouton("LANCER");
		this.bouton_retour = new Bouton("RETOUR");
				// SOUTH //
				// COMPORTEMENT //
		this.bouton_comportement = new Bouton("Comportements");
				// COMPORTEMENT //
			// BOUTON //

			// LABEL //
				// COMPORTEMENT //
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
		this.panel_comportement = new JPanel(new BorderLayout());
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
		this.getButtonRetour().addActionListener(new RetourMenuListener());
	}
	
	private void initSouth() {
		// SOUTH //
		this.sud_lancer.add(this.bouton_lancer);
		this.sud_retour.add(this.bouton_retour);
		this.sud.add(this.sud_lancer);
		this.sud.add(this.sud_retour);
		this.bouton_lancer.addActionListener(new LancerLaPartieListener());
		this.add(sud,BorderLayout.SOUTH);
	    // SOUTH //
	}
	
	private void initComportement() {
		// COMPORTEMENT //		
		this.panel_comportement.add(this.bouton_comportement,BorderLayout.CENTER);
		this.panel_comportement.setBorder(BorderFactory.createLineBorder(Color.black)); //
		this.panel_nord.add(this.panel_comportement);
		this.bouton_comportement.addActionListener(new ComportementListener());
		setComportement(choix_Comportement.getComportements());
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
        	choix_Comportement.setVisible(true);
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
        view = new View(model,controller);
        model.initModel(controller);
        new GameUI(model,view,controller,My_Frame.getInstance().getSize());
	}
	
	
	// Listener //
	
	public class LancerLaPartieListener implements ActionListener{	
		
	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
	        if (!comportement.isEmpty() && carte_selectionner != null) {
	        	My_Frame.getInstance().setVisible(false);
	        	Jouer.getInstance().createInstanceJeu();
	        }else {
	        	if (choix_Comportement == null) {
	        		bouton_comportement.setText("*Choisir un automate*"); 
	        		bouton_comportement.setForeground(Color.RED);        			
	        	}
	        	
	        }
	    }
	}
	// Listener

	protected void setComportement(HashMap<Entity_Name,A_Automaton> comportements) {
		this.comportement = comportements;
	}

	public Bouton getBoutonComportement() {
		return bouton_comportement;
	}
	
	public ChoixComportement getChoixComportement() {
		return choix_Comportement;
	}
}
