package edu.ricm3.game.tomatower.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class RetourMenuListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
    	JFrame frame = My_Frame.getInstance();
    	JPanel panel = Menu.getInstance();
    	frame.getContentPane().removeAll();
    	frame.getContentPane().add(panel);
    	panel.repaint();
    	frame.setVisible(true);
	}

}
