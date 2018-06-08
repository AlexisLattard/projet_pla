package edu.ricm3.game.tomatower.menu;

import javax.swing.JFrame;
import java.lang.String;
/**
 * Write a description of class Main here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class main
{
      public static void main(String argv[]){
    	long debut= System.currentTimeMillis();
    	long fin= System.currentTimeMillis();
    	long residu = fin-debut;
    	

    	debut= System.currentTimeMillis();
        JFrame frame =My_Frame.getInstance();
    	fin= System.currentTimeMillis();
    	System.out.println("Fenetre afficher en "+(fin-debut-residu)+" ms");
        frame.setVisible(true);
      }
}