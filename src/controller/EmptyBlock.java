package controller;

import java.awt.Dimension;

import javax.swing.JLabel;


/**
 * This class represents a transparent block that is filling the board wherever there
 * is not an actual block.
 * @author sahsah1
 */
public class EmptyBlock extends JLabel {
	
	/**
	 * This class' constructor.
	 */
	public EmptyBlock(){
		setPreferredSize(new Dimension(150, 150));
		setMinimumSize(new Dimension(149, 149));
		setOpaque(false);
	}
}
