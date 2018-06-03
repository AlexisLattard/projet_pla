/*
 * Educational software for a basic game development
 * Copyright (C) 2018  Pr. Olivier Gruber
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.ricm3.game.tomatower.mvc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import edu.ricm3.game.GameController;
import edu.ricm3.game.tomatower.Options;
import edu.ricm3.game.tomatower.entities.Direction;

/**
 * This class is to illustrate the most simple game controller. It does not
 * much, but it shows how to obtain the key strokes, mouse buttons, and mouse
 * moves.
 * 
 * With ' ', you see what you should never do, SLEEP. With '+' and '-', you can
 * add or remove some simulated overheads.
 * 
 * @author Pr. Olivier Gruber
 */

public class Controller extends GameController implements ActionListener {

	Model model;

	public Controller(Model m) {
		this.model = m;
	}

	/**
	 * Simulation step. Warning: the model has already executed its step.
	 * 
	 * @param now
	 *            is the current time in milliseconds.
	 */
	@Override
	public void step(long now) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// if (Options.ECHO_KEYBOARD)
		// System.out.println("KeyTyped: " + e);

	}



	@Override
	public void keyPressed(KeyEvent e) {
		if (Options.ECHO_KEYBOARD)
			System.out.println("KeyPressed: " + e.getKeyChar() + " code=" + e.getKeyCode());
		switch (e.getKeyCode()) {
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_KP_DOWN:
			model.player.move(Direction.DOWN);
			break;
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_KP_RIGHT:
            long start0 = System.nanoTime();
			model.player.move(Direction.RIGHT);
            long end0 = System.nanoTime();
            if(Options.ECHO_PERFORMANCE)
                System.out.println("Move action time" + (end0 - start0));
			break;
		case KeyEvent.VK_KP_LEFT:
		case KeyEvent.VK_LEFT:
			model.player.move(Direction.LEFT);
			break;
		case KeyEvent.VK_UP:
		case KeyEvent.VK_KP_UP:
			model.player.move(Direction.UP);
			break;
        case KeyEvent.VK_T:
			long start1 = System.nanoTime();
			System.out.println("PICK");
            this.model.getPlayer().pick();
            this.model.getPlayer().store();
            long end1 = System.nanoTime();
            if(Options.ECHO_PERFORMANCE)
            	System.out.println("Take action time: " + (end1 - start1));
            break;
        case KeyEvent.VK_P:
            long start2 = System.nanoTime();
            this.model.getPlayer().getBagEntity();
            this.model.getPlayer().throwAction();
            long end2 = System.nanoTime();
            if(Options.ECHO_PERFORMANCE)
            	System.out.println("Put action time : " + (end2 - start2));
            break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (Options.ECHO_KEYBOARD)
			System.out.println("KeyReleased: " + e.getKeyChar() + " code=" + e.getKeyCode());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (Options.ECHO_MOUSE)
			System.out.println("MouseClicked: (" + e.getX() + "," + e.getY() + ") button=" + e.getButton());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (Options.ECHO_MOUSE)
			System.out.println("MousePressed: (" + e.getX() + "," + e.getY() + ") button=" + e.getButton());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (Options.ECHO_MOUSE)
			System.out.println("MouseReleased: (" + e.getX() + "," + e.getY() + ") button=" + e.getButton());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (Options.ECHO_MOUSE_MOTION)
			System.out.println("MouseEntered: (" + e.getX() + "," + e.getY());
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (Options.ECHO_MOUSE_MOTION)
			System.out.println("MouseExited: (" + e.getX() + "," + e.getY());
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (Options.ECHO_MOUSE_MOTION)
			System.out.println("MouseDragged: (" + e.getX() + "," + e.getY());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (Options.ECHO_MOUSE_MOTION)
			System.out.println("MouseMoved: (" + e.getX() + "," + e.getY());
	}

	public void notifyVisible() {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
