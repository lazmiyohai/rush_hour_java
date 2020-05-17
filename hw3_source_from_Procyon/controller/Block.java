// 
// Decompiled by Procyon v0.5.36
// 

package controller;

import java.awt.Color;
import javax.swing.Icon;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import model.Icons;
import com.google.gson.annotations.Expose;
import javax.swing.JButton;

public class Block extends JButton
{
    @Expose
    int value;
    @Expose
    int x;
    @Expose
    int y;
    @Expose
    int height;
    @Expose
    int width;
    @Expose
    boolean pressed;
    Icons icons;
    ImageIcon ic;
    
    public boolean isPressed() {
        return this.pressed;
    }
    
    public void setPressed(final boolean state) {
        this.pressed = state;
    }
    
    public int getVal() {
        return this.value;
    }
    
    public int getWid() {
        return this.width;
    }
    
    public int getHei() {
        return this.height;
    }
    
    public int getCol() {
        return this.x;
    }
    
    public int getRow() {
        return this.y;
    }
    
    public void setCol(final int val) {
        this.x = val;
    }
    
    public void setRow(final int val) {
        this.y = val;
    }
    
    public ImageIcon getImg() {
        return this.ic;
    }
    
    public Block(final int value, final int height, final int width) {
        this.pressed = false;
        this.icons = new Icons();
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setPreferredSize(new Dimension(150, 150));
        this.setMinimumSize(new Dimension(149, 149));
        this.value = value;
        this.height = height;
        this.width = width;
        if (value == 1) {
            this.ic = this.icons.getIcons()[0];
        }
        else if (height == 1 && width == 2) {
            this.ic = this.icons.getIcons()[1];
        }
        else if (height == 1 && width == 3) {
            this.ic = this.icons.getIcons()[2];
        }
        else if (height == 2 && width == 1) {
            this.ic = this.icons.getIcons()[3];
        }
        else if (height == 3 && width == 1) {
            this.ic = this.icons.getIcons()[4];
        }
        this.setIcon(this.ic);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setOpaque(false);
        this.setContentAreaFilled(false);
    }
}
