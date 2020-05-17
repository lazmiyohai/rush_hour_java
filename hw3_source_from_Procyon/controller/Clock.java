// 
// Decompiled by Procyon v0.5.36
// 

package controller;

import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import javax.swing.JLabel;

public class Clock extends JLabel implements ActionListener
{
    private Timer timer;
    private int secondsPassed;
    private int minutesPassed;
    private String display;
    private final int delay = 1000;
    
    public Clock() {
        this.secondsPassed = 0;
        this.minutesPassed = 0;
        this.display = "";
        this.setFont(new Font("Serif", 1, 50));
        this.setText(this.display = "00 : 00");
        this.timer = new Timer(1000, this);
    }
    
    @Override
    public void actionPerformed(final ActionEvent e) {
        ++this.secondsPassed;
        if (this.secondsPassed < 9) {
            this.display = String.valueOf(this.display.substring(0, 6)) + this.secondsPassed;
        }
        if (this.secondsPassed > 9) {
            this.display = String.valueOf(this.display.substring(0, 5)) + this.secondsPassed;
        }
        if (this.secondsPassed > 59) {
            this.secondsPassed = 0;
            ++this.minutesPassed;
            this.display = String.valueOf(this.display.substring(0, 1)) + this.minutesPassed + " : 0" + this.secondsPassed;
        }
        this.setText(this.display);
    }
    
    public Timer getTimer() {
        return this.timer;
    }
    
    public int timePassed() {
        return 60 * this.minutesPassed + this.secondsPassed;
    }
}
