// 
// Decompiled by Procyon v0.5.36
// 

package controller;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import model.Icons;
import javax.swing.JPanel;

public class Board extends JPanel
{
    Icons icons;
    ImageIcon ic;
    
    public Board() {
        this.icons = new Icons();
        this.ic = new ImageIcon("sea1.jpg");
    }
    
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.ic.getImage(), 0, 0, null);
    }
}
