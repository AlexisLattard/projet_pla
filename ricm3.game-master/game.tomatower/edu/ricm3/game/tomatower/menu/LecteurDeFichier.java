package edu.ricm3.game.tomatower.menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.Vector;

public class LecteurDeFichier {
	private static final char COMMENTAIRE ='#';
	private Scanner lecteur;
	
	public LecteurDeFichier(File fichier) {
			ouvrirFichier(fichier);
	}
	
	public String getNextLine() {
		if (this.lecteur != null) {
			return this.lecteur.nextLine();
		}else {
			return null;
		}
	}
	
	public String[] getNextLineCSV(String separateur) {
		if (this.lecteur != null) {
			String line = getNextLine();
			while (!estFin() && line.charAt(0) == COMMENTAIRE) {
				line = getNextLine();
			}
			if (estFin() && line == null) {
				return null;
			}else {
				return line.split(separateur);
			}
		}else {
			return null;
		}
	}
	
	public Vector<Object> getNextLineCSV_Score(String separateur) {
		if (this.lecteur != null) {
			String[] elements = getNextLineCSV(separateur);
			int longueur = elements.length;
			Vector<Object> row = new Vector<Object>(longueur);
			if (longueur == 3) {
				long seconde = new Long(elements[2]);
				Date date =new Date(seconde);
		        DateFormat dfl = DateFormat.getDateTimeInstance(DateFormat.FULL,DateFormat.DEFAULT);
				
		        row.add(elements[0]); 				// Ajout Pseudo
		        row.add(new Integer(elements[1]));	// Ajout Score
		        row.add(dfl.format(date));			// Ajout Pseudo
			}
			return row;		
		}else {
			return null;
		}
	}
	
	public Vector<Object> getNextLineCSV_OptionTaille(String separateur){
		if (this.lecteur != null) {
	        Vector<Object> row = new Vector<Object>();
	        String[] elements = getNextLineCSV(separateur);

            row.add(elements[0]);
            row.add(new Integer(elements[1]));
            row.add(new Integer(elements[2]));
	        return row;
        }else {
			return null;
		}
	}
	
	public boolean fermeFichier() {
		if (this.lecteur != null) {
			this.lecteur.close();
			return true;
		}else {
			return false;
		}
	}
	
	public boolean changerDeFichier(File nouveauxFichier) {
		if (this.lecteur != null) {
			this.lecteur.close();
			return ouvrirFichier(nouveauxFichier);
		}else {
			return ouvrirFichier(nouveauxFichier);
		}
	}

	private boolean ouvrirFichier(File fichier) {
		try {
			this.lecteur = new Scanner(fichier);
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			this.lecteur = null;
			return false;
		}
	}

	public boolean estFin() {
		if (lecteur != null) {
			return !lecteur.hasNext();
		}else {
			return true;
		}
	}

	public boolean lecteurisCreated() {
		// TODO Auto-generated method stub
		return lecteur != null;
	}
}
