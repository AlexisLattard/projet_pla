package edu.ricm3.game.tomatower.menu;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import edu.ricm3.game.tomatower.automaton.A_Automaton;
import edu.ricm3.game.tomatower.entities.enums.EntityName;

public class EcritureDuFichier {
	public static final String FICHIER_COMPORTEMENTS = "./Autres/Comportements";
	public static final String FICHIER_SCORE = "./Autres/Score";
	private static final String COMPORTEMENTS = "#Ce fichier correspond au correspondance entité->automates\n";
	
	private PrintWriter fichier;
	
	public EcritureDuFichier(String chemin) {
		try {
			fichier = new PrintWriter(chemin);
		} catch (FileNotFoundException e) {
			System.out.println("Erreur Fichier");
			e.printStackTrace();
			fichier = null;
		}
	}
	
	public EcritureDuFichier(String chemin,Boolean notOverwrite) {
		try {
			fichier = new PrintWriter(new FileWriter(chemin, notOverwrite));
		} catch (IOException e) {
			System.out.println("Erreur Fichier");
			e.printStackTrace();
			fichier = null;
		}
	}
	
	public EcritureDuFichier(String chemin,String option) {
		try {
			fichier = new PrintWriter(chemin);
		} catch (FileNotFoundException e) {
			System.out.println("Erreur Fichier");
			fichier = null;
		}
	}
	
	public EcritureDuFichier(PrintWriter fichier) {
		this.fichier=fichier;
	}
	
	public void EcrireComportements(HashMap<EntityName,A_Automaton> automates) {
		if (this.fichier != null) {
			fichier.write(COMPORTEMENTS);
			for (EntityName entity : EntityName.values()) {
				String ligne = entity.name()+"="+automates.get(entity).getName()+"\n";
				fichier.write(ligne);
			}
		}
	}

	public void EcrireScore(String pseudo,int score) {
		if (fichier != null) {
			long temp = System.currentTimeMillis();
			String newscore = new String(pseudo+";"+score+";"+temp+"\n");
			fichier.write(newscore);
		}
	}

	public void fermeEcriture() {
		// TODO Auto-generated method stub
		fichier.close();
	}
}
