package edu.ricm3.game.tomatower.menu;

import java.util.Calendar;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Tableau extends JTable{
	
	public Tableau(Vector data,Vector column) {
		super(data,column);
        this.setModel(new ScoreTableModel(data,column));
        this.setAutoCreateRowSorter(true);
	}
	
	public class ScoreTableModel extends DefaultTableModel {
		
		
		 public ScoreTableModel(Vector data, Vector column) {
			super(data,column);
		}

		@Override
         public Class getColumnClass(int column) {
             switch (column) {
                 case 0:
                     return String.class;
                 case 1:
                     return Integer.class;
                 case 2:
                     return Calendar.class;
                 default:
                     return String.class;
             }
         }
     
         @Override
         public boolean isCellEditable(int row, int column) {
             return false;
         }
	}
}
