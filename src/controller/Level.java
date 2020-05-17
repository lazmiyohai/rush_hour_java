package controller;

import java.util.LinkedList;

import com.google.gson.annotations.Expose;

/**
 * This class represents a level in this game and the right arrangement of blocks
 * in it.
 * @author sahsah1
 */
public class Level {
	
	@Expose private int[][] data;
	@Expose private LinkedList<Block> components;
	@Expose private int bestTime;
	@Expose private int indexOfCur;
	
	/**
	 * This class constructor.
	 */
	public Level(){
		data = null;
		components = null;
		bestTime = 0;
	}
	
	/**
	 * A parameter constructor.
	 * @param data holds the current positions of blocks in the board.
	 * @param components an array holding all the blocks in this level.
	 * @param bestTime saves the best time for the completion of this level.
	 * @param indexOfCur the index of the current block in the components array.
	 */
	public Level(int[][] data, LinkedList<Block> components, int bestTime, int indexOfCur){
		this.bestTime = bestTime;
		this.indexOfCur = indexOfCur;
		this.data = new int[6][6];
		this.components = new LinkedList<Block>();
		for (int i=0;i<6;i++){
			for (int j=0;j<6;j++){
				this.data[i][j] = data[i][j];
			}
		}
		for (int i=0;i<components.size();i++){
			Block temp = new Block(components.get(i).getVal(), components.get(i).getHei(), components.get(i).getWid());
			temp.setCol(components.get(i).getCol());
			temp.setRow(components.get(i).getRow());
			this.components.add(temp);
		}
	}
	
	public int[][] getData(){
		return data;
	}
	
	public LinkedList<Block> getComponents(){
		return components;
	}
	
	public int getIndex(){
		return indexOfCur;
	}
	
	public int getBestTime(){
		return bestTime;
	}
	
	public void setBestTime(int newBest){
		bestTime = newBest;
	}
}
