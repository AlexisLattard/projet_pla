package edu.ricm3.game.tomatower.menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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
        //Initialise les differente interface//
        
        this.setName("Tower Deffence");
        this.setContentPane(menu);
        this.setSize(400,400);
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo(null);
        
        menu.getButtonScore().addActionListener(new ScoreListener(this));
        menu.getButtonQuitter().addActionListener(new QuitterListener(this));
        menu.getButtonRegles().addActionListener(new ReglesListener(this));
        score.getButtonRetour().addActionListener(new RetourListener(this));
        regles.getButtonRetour().addActionListener(new RetourListener(this));
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
}
