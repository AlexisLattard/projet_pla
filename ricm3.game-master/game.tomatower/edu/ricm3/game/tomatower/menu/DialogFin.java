package edu.ricm3.game.tomatower.menu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import edu.ricm3.game.tomatower.mvc.Model;

public class DialogFin extends JDialog{
	
	Bouton boutonQuitter;
	Bouton boutonRetourMenu;

	JPanel panelMessage;
	JPanel panelBouton;
	JPanel Nord;
	JLabel titre;
	JLabel message;
	JLabel score;
	
	public DialogFin(Boolean gagne,String pseudo,Model model) {
		super(model.getGameUI().getFrame());
		this.setLayout(new BorderLayout());
		this.setSize(400, 400);
		this.setLocationRelativeTo(null);
		initNord(gagne,pseudo,model);
		initCentre();
		enregistrementScore(pseudo,model);
	}
	
	private void initNord(Boolean gagne,String pseudo,Model model) {
		if (gagne) {
			this.titre = new JLabel("Victoire");
			this.message = new JLabel("Bravo "+pseudo+" tu as reussi à sauver ton crystal");
		}else {
			this.titre = new JLabel("Défaite");
			this.message = new JLabel("Domage "+pseudo+" tu n'as pas reussi à sauver ton crystal");
		}
		this.score = new JLabel("Score :"+model.getPlayer().getScore());
		JPanel titreEtMessage = new JPanel(new GridLayout(3,1));
		titreEtMessage.add(this.titre);
		titreEtMessage.add(this.message);
		titreEtMessage.add(this.score);
		this.Nord = new JPanel(new FlowLayout(FlowLayout.CENTER));
		this.Nord.add(titreEtMessage);
		this.add(Nord,BorderLayout.NORTH);
	}
	
	private void initCentre() {
		JPanel panelCentre = new JPanel(new FlowLayout(FlowLayout.CENTER));
		this.boutonQuitter = new Bouton("Quitter");
		this.boutonQuitter.addActionListener(new QuitterListener());
		this.boutonQuitter.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");
		this.boutonRetourMenu = new Bouton("Retour au Menu");
		this.boutonRetourMenu.addActionListener(new RetourMenuListener());
		this.boutonQuitter.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");
		
		this.panelBouton = new JPanel(new GridLayout(1,2));
		this.panelBouton.add(this.boutonQuitter);
		this.panelBouton.add(this.boutonRetourMenu);
		panelCentre.add(panelBouton);
		this.add(panelCentre,BorderLayout.CENTER);
	}
	
	public void enregistrementScore(String pseudo,Model model) {
		// TODO Auto-generated method stub
		EcritureDuFichier ecrit = new EcritureDuFichier(EcritureDuFichier.FICHIER_SCORE,true);
		ecrit.EcrireScore(pseudo,model.getPlayer().getScore());
		ecrit.fermeEcriture();
	}
	
	//Listener//
	
	private class QuitterListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			DialogFin.this.dispose();
			DialogFin.this.getOwner().dispose();
			My_Frame.getInstance().dispose();
			System.exit(0);
		}
		
	}
		
	private class RetourMenuListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			DialogFin.this.getOwner().dispose();
			DialogFin.this.dispose();
			JFrame frame = My_Frame.getInstance();
			frame.getContentPane().removeAll();
			frame.getContentPane().add(Jouer.getInstance());
			My_Frame.getInstance().setVisible(true);
		}
		
	}
	//Listener//
}
