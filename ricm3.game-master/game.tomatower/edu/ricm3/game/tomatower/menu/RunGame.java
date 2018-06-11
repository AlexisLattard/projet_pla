package edu.ricm3.game.tomatower.menu;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import edu.ricm3.game.GameUI;
import edu.ricm3.game.tomatower.mvc.Controller;
import edu.ricm3.game.tomatower.mvc.Model;
import edu.ricm3.game.tomatower.mvc.View;

public class RunGame {
	
	Model model;
	View view;
	Controller controller;
	GameUI game;
	
	private static RunGame INSTANCE = null;
    

    public static synchronized RunGame getInstance(){ 
        if (INSTANCE == null){
        	INSTANCE = new RunGame(); 
        }
        return INSTANCE;
    }
    
    
    private RunGame() {
    	// construct the game elements: model, controller, and view.
        model = new Model();
        controller = new Controller(model);
        view = new View(model,controller);
        game = new GameUI(model,view,controller,My_Frame.getInstance().getSize());
    }
}
