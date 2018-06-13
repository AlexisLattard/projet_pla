package edu.ricm3.game.tomatower.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.ricm3.game.tomatower.menu.Jouer;
import edu.ricm3.game.tomatower.menu.My_Frame;

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
