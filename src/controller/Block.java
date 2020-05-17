package controller;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.google.gson.annotations.Expose;

import model.Icons;

/**
 * This class represents a Block in the game's board. A block can be either a
 * target block or a normal block, this is determined by the block's value.
 * @author sahsah1
 */
public class Block extends JButton{
	// 0:normal , 1: target
	@Expose int value;
	@Expose int x,y;
	@Expose int height,width;
	@Expose boolean pressed = false;
	Icons icons = new Icons();
	ImageIcon ic;
	
	public boolean isPressed(){
		return pressed;
	}
	
	public void setPressed(boolean state){
		pressed = state;
	}
	
	public int getVal(){
		return value;
	}
	
	public int getWid(){
		return width;
	}
	
	public int getHei(){
		return height;
	}
	
	public int getCol(){
		return x;
	}
	
	public int getRow(){
		return y;
	}
	
	public void setCol(int val){
		x=val;
	}
	
	public void setRow(int val){
		y=val;
	}
	
	public ImageIcon getImg(){
		return ic;
	}
	
	/**
	 * Parameter constructor
	 * @param value the type of block it's going to be.
	 * @param height the height of the block.
	 * @param width the width of the block.
	 */
	public Block(int value, int height, int width){
		this.setBorder(BorderFactory.createEmptyBorder());
		setPreferredSize(new Dimension(150, 150));
		setMinimumSize(new Dimension(149, 149));
		this.value = value;
		this.height = height;
		this.width = width;
		//setBorder(BorderFactory.createEmptyBorder());
		if (value==1){
			ic = icons.getIcons()[0];
		}
		else if (height==1 && width==2){
			ic = icons.getIcons()[1];
		}
		else if (height==1 && width==3){
			ic = icons.getIcons()[2];
		}
		else if (height==2 && width==1){
			ic = icons.getIcons()[3];
		}
		else if (height==3 && width==1){
			ic = icons.getIcons()[4];
		}
		setIcon(ic);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setOpaque(false);
		setContentAreaFilled(false);
	}
}
