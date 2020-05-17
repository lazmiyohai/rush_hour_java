package model;

import javax.swing.ImageIcon;

/**
 * This class saves all the images used for game blocks.
 * @author sahsah1
 *
 */
public class Icons {
	
	private ImageIcon[] im;
	
	/**
	 * This class constructor.
	 */
	public Icons(){
		im = new ImageIcon[5];
		im[0] = new ImageIcon("targetBlock.png");
		im[1] = new ImageIcon("normalBlockHor2.png");
		im[2] = new ImageIcon("normalBlockHor3.png");
		im[3] = new ImageIcon("normalBlockVer2.png");
		im[4] = new ImageIcon("normalBlockVer3.png");
	}
	
	public ImageIcon[] getIcons(){
		return im;
	}
}
