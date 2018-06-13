package edu.ricm3.game.tomatower.menu;


import java.util.Date;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Tableau extends JTable{
	DefaultTableCellRenderer centerRenderer;
	
	public Tableau(Vector data,Vector column) {
		super(data,column);
		
		this.centerRenderer = new DefaultTableCellRenderer();
		this.centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		this.setDefaultRenderer(String.class, centerRenderer);
		this.setDefaultRenderer(Integer.class, centerRenderer);
		this.setDefaultRenderer(Date.class, centerRenderer);
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
                     return Date.class;
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
