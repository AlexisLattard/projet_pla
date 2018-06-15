package edu.ricm3.game.tomatower.menu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Write a description of class Menu here.
 * 
 * @author (Lordey A.K.A the thief) 
 * @version (a version number or a date)
 */
public class Menu extends JPanel
{
    // instance variables - replace the example below with your own
    
    // JButton //
    private Bouton boutonJouer;
    private Bouton boutonRegles;
    private Bouton boutonQuitter;
    private Bouton boutonScore;
    private Bouton boutonOption;
    private Bouton boutonDebug;
    private Bouton boutonCredit;
    // JButton //
    
    private JPanel boutonsCentre;
    private JPanel panel_centre;
    private JPanel panel_nord;

    
    
    private static class MenuHolder
    {       
        /** Instance unique non préinitialisée */
        private final static Menu INSTANCE = new Menu();
    }
 
    /** Point d'accès pour l'instance unique du singleton */
    public static Menu getInstance()
    {
	    return MenuHolder.INSTANCE;
    }
    
    
    /**
     * Constructor for objects of class Menu
     */
    public Menu()
    {
    	super();
        // initialise instance variables
        this.setLayout(new BorderLayout());
        // Initialisation des bouton//
        this.boutonJouer = new Bouton("Jouer");
        this.boutonScore = new Bouton("Score");
        this.boutonRegles = new Bouton("Regles");
        this.boutonQuitter = new Bouton("Quitter");
        this.boutonCredit = new Bouton("Credit");
        this.boutonOption = new Bouton();
        this.boutonDebug = new Bouton();
        this.boutonOption.setIcon(new ImageIcon("./Image/Option.png"),30,30);
        this.boutonDebug.setIcon(new ImageIcon("./Image/Debug.png"),30,30);
        // Initialisation des bouton//
        
        // Initialisation du conteneur des boutons//
        this.boutonsCentre = new JPanel(new GridLayout(5,1));
        // Initialisation du conteneur des boutons//
        
        
        //Initialisation du conteneur des boutons//
        this.panel_centre = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.panel_nord = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        this.panel_nord.add(this.boutonDebug);
        this.panel_nord.add(this.boutonOption);
        this.panel_centre.add(this.boutonsCentre);
        //Initialisation du conteneur des boutons//
        
        // Ajout des boutons dans le conteneur//
        this.boutonsCentre.add(this.boutonJouer);
        this.boutonsCentre.add(this.boutonRegles);
        this.boutonsCentre.add(this.boutonScore);
        this.boutonsCentre.add(this.boutonCredit);
        this.boutonsCentre.add(this.boutonQuitter);
        // Ajout des boutons dans le conteneur//
        
        // Ajout des Listeners//
        this.getButtonScore().addActionListener(new GoScoreListener());
		this.getButtonOption().addActionListener(new GoOptionListener());
		this.getButtonQuitter().addActionListener(new QuitterListener());
		this.getButtonRegles().addActionListener(new GoRegleListener());
		this.getButtonDebug().addActionListener(new GoDebugListener());
		this.getButtonJouer().addActionListener(new GoJouerListener());
        // Ajout des Listeners//
        this.add(this.panel_centre,BorderLayout.CENTER);
        this.add(this.panel_nord,BorderLayout.NORTH);
        
    }
    
    // Listener//
    public class GoDebugListener implements ActionListener{

        
        @Override
        public void actionPerformed(ActionEvent e)
        {
        	JFrame frame = My_Frame.getInstance();
        	JPanel panel = Debug.getInstance();
        	frame.getContentPane().removeAll();
        	frame.getContentPane().add(panel);
        	panel.repaint();
        	frame.setVisible(true);
        }
    }

    public class GoJouerListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e)
        {
        	JFrame frame = My_Frame.getInstance();
        	JPanel panel = Jouer.getInstance();
        	frame.getContentPane().removeAll();
        	frame.getContentPane().add(panel);
        	panel.repaint();
        	frame.setVisible(true);
        }
    }
    
    public class GoOptionListener implements ActionListener{

    	@Override
    	public void actionPerformed(ActionEvent e) {
    		// TODO Auto-generated method stub
        	JFrame frame = My_Frame.getInstance();
        	JPanel panel = Option.getInstance();
        	frame.getContentPane().removeAll();
        	frame.getContentPane().add(panel);
        	panel.repaint();
        	frame.setVisible(true);
    	}

    }
    
    public class GoRegleListener implements ActionListener{

    	@Override
    	public void actionPerformed(ActionEvent e) {
    		// TODO Auto-generated method stub
        	JFrame frame = My_Frame.getInstance();
        	JPanel panel = Regles.getInstance();
        	frame.getContentPane().removeAll();
        	frame.getContentPane().add(panel);
        	panel.repaint();
        	frame.setVisible(true);
    	}

    }
    

    public class GoScoreListener implements ActionListener{
	
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
	    	JFrame frame = My_Frame.getInstance();
	    	Score panel = Score.getInstance();
	    	panel.fillData();
	    	frame.getContentPane().removeAll();
	    	frame.getContentPane().add(panel);
	    	panel.repaint();
	    	frame.setVisible(true);
		}

    }
    
    public class QuitterListener implements ActionListener{

    	@Override
    	public void actionPerformed(ActionEvent e) {
    		// TODO Auto-generated method stub
        	My_Frame.getInstance().dispose();
            System.exit(0);
    	}

    }
    // Listener//
    
    
        
    public JButton getButtonScore(){
        return this.boutonScore;
    }
    
    public JButton getButtonQuitter(){
        return this.boutonQuitter;
    }
    
    public JButton getButtonRegles(){
        return this.boutonRegles;
    }
    
    public JButton getButtonDebug(){
        return this.boutonDebug;
    }
    public JButton getButtonJouer(){
        return this.boutonJouer;
    }
    public JButton getButtonOption(){
        return this.boutonOption;
    }
}