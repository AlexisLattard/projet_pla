package edu.ricm3.game.tomatower.menu;

import java.util.Calendar;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Tableau extends JTable{
	private DefaultTableModel tablemodel;
	private Object[][] data;
	private String[] columns = {"Track #", "Title", "Length"};
	
	public Tableau() {
		data = null;
		
		tablemodel = new DefaultTableModel(data,columns) {
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
        };
        this.setModel(tablemodel);
		this.setAutoCreateRowSorter(true);
	}
	
	public Tableau(Object[][] data) {
		this.data = data;
		tablemodel = new DefaultTableModel(this.data,this.columns) {
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
        };
        this.setModel(tablemodel);
		this.setAutoCreateRowSorter(true);
	}
	
	public Tableau(Object[][] data,String[] columns) {
		this.data = data;
		this.columns = columns;
        tablemodel = new DefaultTableModel(this.data,this.columns) {
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
        };
        this.setModel(tablemodel);
		this.setAutoCreateRowSorter(true);
	}
}
