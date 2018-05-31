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
package edu.ricm3.game.tomatower;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;

import edu.ricm3.game.GameView;

public class View extends GameView {

  private static final long serialVersionUID = 1L;

  Color m_background = Color.gray;
  long last;
  int npaints;
  int fps;
  Model model;
  Controller ctr;
  
  public View(Model m, Controller c) {
    model = m;
    ctr = c;
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
    

    g.drawImage(model.sprite_tower, 50, 50, 50, 50, null);
    
    Iterator<Mobs> iter_mobs = model.getMobs().iterator();
    while (iter_mobs.hasNext()) {
      Mobs m = iter_mobs.next();
      m.paint(g);
    }
    
    Iterator<Inert> iter_inert = model.getObstacles().iterator();
    while (iter_inert.hasNext()) {
      Inert i = iter_inert.next();
      i.paint(g);
    }
    
    model.player.paint(g);
    
  }

}
