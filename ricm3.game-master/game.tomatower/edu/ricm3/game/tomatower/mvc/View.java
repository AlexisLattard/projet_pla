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

import edu.ricm3.game.GameUI;
import edu.ricm3.game.GameView;
import edu.ricm3.game.tomatower.map.Hud;
import edu.ricm3.game.tomatower.map.Map;

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
	Map current_map;

	
	public View(Model m, Controller c) {
		model = m;
		ctr = c;
		hud = new Hud(m, m.getMobSpawn());
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
		Map map = this.model.getCurrentMap();
		if(current_map==null || !this.current_map.equals(map))
			resizeWindow(map);

		// erase background
		g.setColor(m_background);
		g.fillRect(0, 0, getWidth(), getHeight());

		this.model.getCurrentMap().paint(g);

		hud.paint(g);
	}

	public void initView(GameUI gameUI) {
		this.gameUI = gameUI;
	}
	
	
	private void resizeWindow(Map map) {
		this.current_map = map;
		int map_dimension[] = map.getMapDimention();
		int title_bar_height = 39; // TODO V2 : Trouvez la taille de la bar title
		this.gameUI.resizeWindow( map_dimension[0] + hud.getWidth(), map_dimension[1] + title_bar_height);

	}



}