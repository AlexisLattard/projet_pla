package edu.ricm3.game.tomatower.menu;

import java.io.File;
import javax.swing.JFrame;
/**
 * Write a description of class My_Frame here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class My_Frame extends JFrame
{
    // instance variables - replace the example below with your own

    /**
     * Constructor for objects of class My_Frame
     */
    
    private static class My_FrameHolder
    {       
        /** Instance unique non préinitialisée */
        private final static My_Frame INSTANCE = new My_Frame();
    }
 
    /** Point d'accès pour l'instance unique du singleton */
    public static My_Frame getInstance()
    {
	    return My_FrameHolder.INSTANCE;
    }
    
    private My_Frame()
    {  
        setupJFrame();
        
    }
    
    private void setupJFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setName("PLA");
        this.setResizable(true);
    	if(!this.getOptionDemarage()) {
	        this.setSize(400, 400);
        }
		this.setLocationRelativeTo(null);
        this.getContentPane().add(Menu.getInstance());
    }
    
    private boolean getOptionDemarage() {

        System.out.println("=====Start_Setup=====");
    	File file = new File("./Autres/Options");
    	LecteurDeFichier lecteur = new LecteurDeFichier(file);
        while (lecteur.estFin()) {
        	String[] row = lecteur.getNextLineCSV("=");
        	if (row != null) {
	            if(!this.setOption(row)) {
	            	System.out.println("Fail to setup "+row[0]);
	            }else {
	            	System.out.println("Sucessfully setup "+row[0]+" = "+row[1]);
	            }
        	}
        }
        lecteur.fermeFichier();
        System.out.println("=====End_Setup=====");
        return true;
	}

	private boolean setOption(String[] split) {
		switch (split[0]) {
			case "Size":
				String[] values = split[1].split("x");
				Integer width = new Integer(values[0]);
				Integer height = new Integer(values[1]);
				this.setSize(width, height);
				return true;
			default:
				return false;
		}
	}
    
}
