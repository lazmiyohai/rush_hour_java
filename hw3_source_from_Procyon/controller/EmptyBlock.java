// 
// Decompiled by Procyon v0.5.36
// 

package controller;

import java.awt.Dimension;
import javax.swing.JLabel;

public class EmptyBlock extends JLabel
{
    public EmptyBlock() {
        this.setPreferredSize(new Dimension(150, 150));
        this.setMinimumSize(new Dimension(149, 149));
        this.setOpaque(false);
    }
}
