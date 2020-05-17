// 
// Decompiled by Procyon v0.5.36
// 

package view;

import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Component;
import java.awt.Insets;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class MainMenu extends JFrame implements ActionListener
{
    JPanel p;
    GridBagConstraints g;
    JButton play;
    JButton edit;
    JButton exit;
    
    public MainMenu() {
        super("Unblock Me Game");
        this.p = new JPanel(new GridBagLayout());
        this.g = new GridBagConstraints();
        this.play = new JButton("PLAY");
        this.edit = new JButton("Levels Editing");
        this.exit = new JButton("EXIT");
        this.p.setBackground(Color.CYAN);
        this.setDefaultCloseOperation(3);
        this.setSize(600, 600);
        this.setLocation(550, 250);
        this.g.insets = new Insets(10, 10, 10, 10);
        this.g.fill = 1;
        this.g.gridx = 0;
        this.g.gridy = 0;
        this.g.gridwidth = 3;
        this.p.add(this.play, this.g);
        this.play.setFont(new Font("Serif", 1, 40));
        this.play.setFocusable(false);
        this.play.addActionListener(this);
        this.play.setBackground(Color.GREEN);
        this.g.gridx = 0;
        this.g.gridy = 1;
        this.p.add(this.edit, this.g);
        this.edit.setFont(new Font("Serif", 1, 40));
        this.edit.setFocusable(false);
        this.edit.addActionListener(this);
        this.edit.setBackground(Color.YELLOW);
        this.g.gridx = 0;
        this.g.gridy = 2;
        this.p.add(this.exit, this.g);
        this.exit.setFont(new Font("Serif", 1, 40));
        this.exit.setFocusable(false);
        this.exit.addActionListener(this);
        this.exit.setBackground(Color.RED);
        this.add(this.p);
        this.setVisible(true);
    }
    
    @Override
    public void actionPerformed(final ActionEvent e) {
        if (e.getSource() == this.play) {
            try {
                final LevelChoosing lc = new LevelChoosing();
                this.dispose();
            }
            catch (FileNotFoundException e2) {
                e2.printStackTrace();
            }
        }
        else if (e.getSource() == this.edit) {
            final LevelEdit le = new LevelEdit();
            this.dispose();
        }
        else if (e.getSource() == this.exit) {
            this.dispose();
        }
    }
}
