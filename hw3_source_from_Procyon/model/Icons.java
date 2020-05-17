// 
// Decompiled by Procyon v0.5.36
// 

package model;

import javax.swing.ImageIcon;

public class Icons
{
    private ImageIcon[] im;
    
    public Icons() {
        (this.im = new ImageIcon[5])[0] = new ImageIcon("targetBlock.png");
        this.im[1] = new ImageIcon("normalBlockHor2.png");
        this.im[2] = new ImageIcon("normalBlockHor3.png");
        this.im[3] = new ImageIcon("normalBlockVer2.png");
        this.im[4] = new ImageIcon("normalBlockVer3.png");
    }
    
    public ImageIcon[] getIcons() {
        return this.im;
    }
}
