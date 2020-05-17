package controller;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import model.Icons;

/**
 * This class represents the game's board, with its own unique background.
 * @author sahsah1
 */
public class Board extends JPanel{
	
	Icons icons = new Icons();
	ImageIcon ic = new ImageIcon("sea1.jpg");
	
	/**
	 * This method paints the the board's panel with the desired image.
	 */
	@Override
	public void paintComponent (Graphics g){
		super.paintComponent(g);
		g.drawImage(ic.getImage(), 0, 0, null);
	}
}
