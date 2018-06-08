package edu.ricm3.game.tomatower.menu;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;
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
		System.out.println("création_MyFrame");      
        setupJFrame();
        
    }
    
    private void setupJFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setName("Tower Deffence");
        this.setContentPane(Menu.getInstance());
        this.setResizable(false);
    	if(!this.getOptionDemarage()) {
	        this.setSize(400, 400);
        }
        this.setLocationRelativeTo(null);
        
        Menu.getInstance().getButtonScore().addActionListener(new ScoreListener(this));
        Menu.getInstance().getButtonQuitter().addActionListener(new QuitterListener(this));
        Menu.getInstance().getButtonRegles().addActionListener(new ReglesListener(this));
        Menu.getInstance().getButtonDebug().addActionListener(new DebugListener(this));
        Menu.getInstance().getButtonJouer().addActionListener(new JouerListener(this));
        Menu.getInstance().getButtonOption().addActionListener(new OptionListener(this));
    }
    
    private boolean getOptionDemarage() {

        System.out.println("=====Start_Setup=====");
    	File file = new File("./Autres/Options");
    	Scanner scanner;
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.charAt(0) != '#') {
	            String[] row = line.split("=");
	            if(!this.setOption(row)) {
	            	System.out.println("Fail to setup "+row[0]);
	            }else {
	            	System.out.println("Sucessfully setup "+line);
	            }
            }
        }
        scanner.close();
        System.out.println("=====End_Setup=====");
        return true;
	}

	private boolean setOption(String[] split) {
		switch (split[0]) {
			case "Size":
				try {
					String[] values = split[1].split("x");
					Integer width = new Integer(values[0]);
					Integer height = new Integer(values[1]);
					this.setSize(width, height);
				}catch(Exception e) {
					e.printStackTrace();
					return false;
				}
				break;
			default:
				return false;
		}
		return true;
	}

	private class ScoreListener implements ActionListener
    {
        private JFrame frame;
        
        public ScoreListener(JFrame frame)
        {
            this.frame = frame;
        } 
        
        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("Score");
            frame.setContentPane(Score.getInstance(new RetourListener(frame)));
            frame.getContentPane().repaint();
            frame.setVisible(true);
        }   
    }
    
    private class OptionListener implements ActionListener
    {
        private JFrame frame;
        
        public OptionListener(JFrame frame)
        {
            this.frame = frame;
        } 
        
        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("Option");
            frame.setContentPane(Option.getInstance(new RetourListener(frame)));
            frame.getContentPane().repaint();
            frame.setVisible(true);
        }   
    }
    
    private class RetourListener implements ActionListener
    {
        private JFrame frame;
        
        public RetourListener(JFrame frame)
        {
            this.frame = frame;
        } 
        
        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("Retour");
            frame.setContentPane(Menu.getInstance());
            frame.getContentPane().repaint();
            frame.setVisible(true);
        }   
    }
    
    private class QuitterListener implements ActionListener
    {
        private JFrame frame;
        
        public QuitterListener(JFrame frame)
        {
            this.frame = frame;
        } 
        
        @Override
        public void actionPerformed(ActionEvent e)
        {
            frame.dispose();
            System.out.println("Quitter");
            System.exit(0);
        }   
    }
    
    private class ReglesListener implements ActionListener
    {
        private JFrame frame;
        
        public ReglesListener(JFrame frame)
        {
            this.frame = frame;
        } 
        
        @Override
        public void actionPerformed(ActionEvent e)
        {
        	System.out.println("Regles");
            frame.setContentPane(Regles.getInstance(new RetourListener(frame)));
            frame.getContentPane().repaint();
            frame.setVisible(true);
        }   
    }
    
    private class DebugListener implements ActionListener
    {
        private JFrame frame;
        
        public DebugListener(JFrame frame)
        {
            this.frame = frame;
        } 
        
        @Override
        public void actionPerformed(ActionEvent e)
        {
        	System.out.println("Debug");
            frame.setContentPane(Debug.getInstance(new RetourListener(frame)));
            frame.getContentPane().repaint();
            frame.setVisible(true);
        }   
    }
    
    private class JouerListener implements ActionListener
    {
        private JFrame frame;
        
        public JouerListener(JFrame frame)
        {
            this.frame = frame;
        } 
        
        @Override
        public void actionPerformed(ActionEvent e)
        {
        	System.out.println("Jouer");
            frame.setContentPane(Jouer.getInstance(new RetourListener(frame)));
            frame.getContentPane().repaint();
            frame.setVisible(true);
        }   
    }
}
