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

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;

import edu.ricm3.game.GameUI;
import edu.ricm3.game.GameView;
import edu.ricm3.game.tomatower.entities.Tower;
import edu.ricm3.game.tomatower.map.Cell;
import edu.ricm3.game.tomatower.map.Hud;

public class View extends GameView {

	private static final long serialVersionUID = 1L;
	Color m_background = Color.white;
	long last;
	int npaints;
	int fps;
	Model model;
	Controller ctr;
	GameUI gameUI;
	Hud hud;

	public View(Model m, Controller c) {
		model = m;
		ctr = c;
		hud = new Hud(m);
	}

	private void computeFPS() {
		long now = System.currentTimeMillis();
		if (now - last > 1000L) {
			fps = npaints;
			last = now;
			npaints = 0;
		}
		m_game.setFPS(fps, "npaints=" + npaints);
		npaints++;
	}

	@Override
	protected void _paint(Graphics g) {
		computeFPS();

		// erase background
		g.setColor(m_background);
		g.fillRect(0, 0, getWidth(), getHeight());

		Iterator<Cell> iter_cells = this.model.getCurrentMap().getCellsIterator();
		while (iter_cells.hasNext()) {
			Cell c = iter_cells.next();
			c.paint(g);
		}
		hud.paint(g);
		
		
		// Affichage de la main du personnage sur la cellule devant lui
		Tower hand = this.model.getPlayer().getHand();
		if(hand != null) {
    		Cell dest = this.model.getPlayer().getFrontCell();
    		if(dest != null) {
    			int d = (int) (this.model.getPlayer().getMap().getCellSize());
    			int x = dest.getPosition()[0] * model.getCurrentMap().getCellSize();
    			int y = dest.getPosition()[1] * model.getCurrentMap().getCellSize();
        		if(dest.isFree(hand)) {
        			g.setColor(new Color(0, 255, 0, 100)); 
        		}else {
        			g.setColor(new Color(255, 0, 0, 100)); 
        		}
        		g.fillRect(x,y,d,d);
        		g.drawImage(((Tower)hand).getSprite()[this.model.getPlayer().getDirection().getValue()], x, y, d, d, null);
    		}	
    	}
	}
	
	public void setGameUI(GameUI gameUI) {
		this.gameUI = gameUI;
	}

}