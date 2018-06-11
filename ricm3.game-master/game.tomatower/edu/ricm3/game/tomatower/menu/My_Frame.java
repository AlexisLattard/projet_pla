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
		Menu menu = Menu.getInstance();
		menu.getButtonScore().addActionListener(new ScoreListener());
		menu.getButtonQuitter().addActionListener(new QuitterListener());
		menu.getButtonRegles().addActionListener(new ReglesListener());
		menu.getButtonDebug().addActionListener(new DebugListener());
		menu.getButtonJouer().addActionListener(new JouerListener());
		menu.getButtonOption().addActionListener(new OptionListener());
        this.getContentPane().add(menu);
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
        @Override
        public void actionPerformed(ActionEvent e)
        {
        	JFrame frame = My_Frame.getInstance();
        	JPanel panel = Score.getInstance(new RetourListener());
        	frame.getContentPane().removeAll();
        	frame.getContentPane().add(panel);
        	panel.repaint();
        	frame.setVisible(true);
        }   
    }
    
    private class OptionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
        	JFrame frame = My_Frame.getInstance();
        	JPanel panel = Option.getInstance(new RetourListener());
        	frame.getContentPane().removeAll();
        	frame.getContentPane().add(panel);
        	panel.repaint();
        	frame.setVisible(true);
        }   
    }
    
    private static class RetourListener implements ActionListener
    {
        
        @Override
        public void actionPerformed(ActionEvent e)
        {
        	JFrame frame = My_Frame.getInstance();
        	JPanel panel = Menu.getInstance();
        	frame.getContentPane().removeAll();
        	frame.getContentPane().add(panel);
        	panel.repaint();
        	frame.setVisible(true);
        }   
    }
    
    private class QuitterListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
        	My_Frame.getInstance().dispose();
            System.exit(0);
        }   
    }
    
    private class ReglesListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
        	JFrame frame = My_Frame.getInstance();
        	JPanel panel = Regles.getInstance(new RetourListener());
        	frame.getContentPane().removeAll();
        	frame.getContentPane().add(panel);
        	panel.repaint();
        	frame.setVisible(true);
        }   
    }
    
    private class DebugListener implements ActionListener
    {
        
        @Override
        public void actionPerformed(ActionEvent e)
        {
        	JFrame frame = My_Frame.getInstance();
        	JPanel panel = Debug.getInstance(new RetourListener());
        	frame.getContentPane().removeAll();
        	frame.getContentPane().add(panel);
        	panel.repaint();
        	frame.setVisible(true);
        }   
    }
    
    private class JouerListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
        	JFrame frame = My_Frame.getInstance();
        	JPanel panel = Jouer.getInstance(new RetourListener());
        	frame.getContentPane().removeAll();
        	frame.getContentPane().add(panel);
        	panel.repaint();
        	frame.setVisible(true);
        }   
    }
}
