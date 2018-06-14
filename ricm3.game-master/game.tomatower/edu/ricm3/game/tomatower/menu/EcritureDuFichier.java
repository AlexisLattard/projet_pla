package edu.ricm3.game.tomatower.menu;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import edu.ricm3.game.tomatower.automaton.A_Automaton;
import edu.ricm3.game.tomatower.entities.enums.EntityName;

public class EcritureDuFichier {
	private static final String COMPORTEMENTS = "#Ce fichier correspond au correspondance entité->automates";
	private PrintWriter fichier;
	
	public EcritureDuFichier(String chemin) {
		try {
			fichier = new PrintWriter(chemin,"w+");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			fichier = null;
		}
	}
	
	public EcritureDuFichier(String chemin,String option) {
		try {
			fichier = new PrintWriter(chemin,option);
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
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
				String ligne = entity.name()+"="+automates.get(entity).getName();
				fichier.write(COMPORTEMENTS);
			}
		}
	}
}
