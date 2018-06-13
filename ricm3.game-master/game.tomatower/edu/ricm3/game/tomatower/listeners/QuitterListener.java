package edu.ricm3.game.tomatower.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.ricm3.game.tomatower.menu.My_Frame;

public class QuitterListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
    	My_Frame.getInstance().dispose();
        System.exit(0);
	}

}
