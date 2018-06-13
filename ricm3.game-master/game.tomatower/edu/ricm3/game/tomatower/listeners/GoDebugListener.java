package edu.ricm3.game.tomatower.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.ricm3.game.tomatower.menu.Debug;
import edu.ricm3.game.tomatower.menu.My_Frame;

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
