package view;


import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This class represents a the main frame where the user can choose what he wants to
 * do in the game.
 * @author sahsah1
 *
 */
public class MainMenu extends JFrame implements ActionListener{
	
	JPanel p = new JPanel(new GridBagLayout());
	GridBagConstraints g = new GridBagConstraints();
	JButton play = new JButton("PLAY");
	JButton edit = new JButton("Levels Editing");
	JButton exit = new JButton("EXIT");
	
	/**
	 * This frame's constructor.
	 */
	public MainMenu(){
		super("Unblock Me Game");
		p.setBackground(Color.CYAN);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(600, 600);
		setLocation(550, 250);
		g.insets = new Insets(10, 10, 10, 10);
		g.fill = GridBagConstraints.BOTH;
		
		g.gridx = 0;
		g.gridy = 0;
		g.gridwidth = 3;
		p.add(play,g);
		play.setFont(new Font("Serif", Font.BOLD, 40));
		play.setFocusable(false);
		play.addActionListener(this);
		play.setBackground(Color.GREEN);
		
		g.gridx = 0;
		g.gridy = 1;
		p.add(edit,g);
		edit.setFont(new Font("Serif", Font.BOLD, 40));
		edit.setFocusable(false);
		edit.addActionListener(this);
		edit.setBackground(Color.YELLOW);
		
		g.gridx = 0;
		g.gridy = 2;
		p.add(exit,g);
		exit.setFont(new Font("Serif", Font.BOLD, 40));
		exit.setFocusable(false);
		exit.addActionListener(this);
		exit.setBackground(Color.RED);
		
		add(p);
		setVisible(true);
	}
	
	/**
	 * This method controls the user clicks of buttons.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == play){
			try {
				LevelChoosing lc = new LevelChoosing();
				dispose();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		}
		else if (e.getSource() == edit){
			LevelEdit le = new LevelEdit();
			dispose();
		}
		else if (e.getSource() == exit){
			dispose();
		}
		
	}
}
