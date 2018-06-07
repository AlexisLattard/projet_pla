package edu.ricm3.game.tomatower.automaton;

import java.io.File;

public class ParcoursArbre {

	AST Arbre[];

	public ParcoursArbre(File automate) {
		for (int i = 0; i < Arbre.length; i++) {
			Arbre[i] = new AST();
		}
	}
}
