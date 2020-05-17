// 
// Decompiled by Procyon v0.5.36
// 

package controller;

import java.util.LinkedList;
import com.google.gson.annotations.Expose;

public class Level
{
    @Expose
    private int[][] data;
    @Expose
    private LinkedList<Block> components;
    @Expose
    private int bestTime;
    @Expose
    private int indexOfCur;
    
    public Level() {
        this.data = null;
        this.components = null;
        this.bestTime = 0;
    }
    
    public Level(final int[][] data, final LinkedList<Block> components, final int bestTime, final int indexOfCur) {
        this.bestTime = bestTime;
        this.indexOfCur = indexOfCur;
        this.data = new int[6][6];
        this.components = new LinkedList<Block>();
        for (int i = 0; i < 6; ++i) {
            for (int j = 0; j < 6; ++j) {
                this.data[i][j] = data[i][j];
            }
        }
        for (int i = 0; i < components.size(); ++i) {
            final Block temp = new Block(components.get(i).getVal(), components.get(i).getHei(), components.get(i).getWid());
            temp.setCol(components.get(i).getCol());
            temp.setRow(components.get(i).getRow());
            this.components.add(temp);
        }
    }
    
    public int[][] getData() {
        return this.data;
    }
    
    public LinkedList<Block> getComponents() {
        return this.components;
    }
    
    public int getIndex() {
        return this.indexOfCur;
    }
    
    public int getBestTime() {
        return this.bestTime;
    }
    
    public void setBestTime(final int newBest) {
        this.bestTime = newBest;
    }
}
