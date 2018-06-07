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
    private Menu menu;
    private Score score;
    private Regles regles;
    private Debug debug;
    private Jouer jouer;
    private Option option;

    /**
     * Constructor for objects of class My_Frame
     */
    public My_Frame()
    {
        // initialise instance variables
        
        //Initialise les differente interface//
        menu = new Menu();
        score = new Score();
        regles = new Regles();
        debug = new Debug();
        jouer = new Jouer();
        option = new Option();
        //Initialise les differente interface//

        
        setupJFrame();
        
        menu.getButtonScore().addActionListener(new ScoreListener(this));
        menu.getButtonQuitter().addActionListener(new QuitterListener(this));
        menu.getButtonRegles().addActionListener(new ReglesListener(this));
        menu.getButtonDebug().addActionListener(new DebugListener(this));
        menu.getButtonJouer().addActionListener(new JouerListener(this));
        menu.getButtonOption().addActionListener(new OptionListener(this));
        score.getButtonRetour().addActionListener(new RetourListener(this));
        regles.getButtonRetour().addActionListener(new RetourListener(this));
        debug.getButtonRetour().addActionListener(new RetourListener(this));
        jouer.getButtonRetour().addActionListener(new RetourListener(this));
        option.getButtonRetour().addActionListener(new RetourListener(this));
        
    }
    
    private void setupJFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setName("Tower Deffence");
        this.setContentPane(menu);
        this.setResizable(false);
    	if(!this.getOptionDemarage()) {
	        this.setSize(400, 400);
        }
        this.setLocationRelativeTo(null);
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
            frame.setContentPane(score);
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
            frame.setContentPane(option);
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
            frame.setContentPane(menu);
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
            frame.setContentPane(regles);
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
            frame.setContentPane(debug);
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
            frame.setContentPane(jouer);
            frame.getContentPane().repaint();
            frame.setVisible(true);
        }   
    }
}
