// 
// Decompiled by Procyon v0.5.36
// 

package view;

import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class LevelEdit extends JFrame implements ActionListener
{
    JPanel p;
    GridBagConstraints gbc;
    JButton add;
    JButton del;
    
    public LevelEdit() {
        super("Level Editing");
        this.p = new JPanel(new GridBagLayout());
        this.gbc = new GridBagConstraints();
        this.setResizable(false);
        this.setSize(600, 600);
        this.setLocation(550, 250);
        this.p.setBackground(Color.YELLOW);
        this.setDefaultCloseOperation(3);
        this.gbc.insets = new Insets(20, 20, 20, 20);
        this.gbc.fill = 1;
        (this.add = new JButton("Create Level")).setFocusable(false);
        this.add.setFont(new Font("Serif", 1, 30));
        this.gbc.gridx = 0;
        this.gbc.gridy = 0;
        this.p.add(this.add, this.gbc);
        this.add.addActionListener(this);
        this.add.setBackground(Color.blue);
        (this.del = new JButton("Delete Levels")).setFocusable(false);
        this.del.setFont(new Font("Serif", 1, 30));
        this.gbc.gridx = 0;
        this.gbc.gridy = 1;
        this.p.add(this.del, this.gbc);
        this.del.addActionListener(this);
        this.del.setBackground(Color.LIGHT_GRAY);
        this.add(this.p);
        this.setVisible(true);
    }
    
    @Override
    public void actionPerformed(final ActionEvent e) {
        if (e.getSource() == this.add) {
            try {
                final CreateLevel createLevel = new CreateLevel();
            }
            catch (FileNotFoundException e2) {
                e2.printStackTrace();
            }
            this.dispose();
        }
        else if (e.getSource() == this.del) {
            try {
                final DeleteLevels deleteLevels = new DeleteLevels();
            }
            catch (FileNotFoundException e2) {
                e2.printStackTrace();
            }
            this.dispose();
        }
    }
}
