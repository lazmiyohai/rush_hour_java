// 
// Decompiled by Procyon v0.5.36
// 

package view;

import javax.swing.BorderFactory;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.awt.Font;
import controller.Level;
import javax.swing.JLabel;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.GridBagConstraints;
import model.ListOfLevels;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class DeleteLevels extends JFrame implements ActionListener
{
    JPanel list;
    ListOfLevels levels;
    GridBagConstraints gbc;
    JScrollPane scroll;
    JButton del;
    JButton current;
    
    public DeleteLevels() throws FileNotFoundException {
        super("Delete a Level");
        this.list = new JPanel();
        this.levels = new ListOfLevels();
        this.gbc = new GridBagConstraints();
        this.scroll = new JScrollPane(this.list);
        this.setDefaultCloseOperation(3);
        this.setSize(600, 800);
        this.setLocation(500, 0);
        this.list.setBackground(Color.LIGHT_GRAY);
        this.setResizable(false);
        this.list.setLayout(new GridBagLayout());
        this.levels.loadFromFile("Levels.json");
        this.gbc.insets = new Insets(20, 20, 20, 20);
        this.gbc.fill = 1;
        for (int i = 1; i <= this.levels.getLevels().size(); ++i) {
            final JButton level = new JButton("Level " + i);
            level.setFocusable(false);
            final JLabel best = new JLabel();
            final int j = this.levels.getLevels().get(i - 1).getBestTime();
            if (j == 1000000) {
                best.setText("Best time is   : -- : --");
            }
            else if (j % 60 > 9) {
                best.setText("Best time is   : 0" + j / 60 + " : " + j % 60);
            }
            else {
                best.setText("Best time is   : 0" + j / 60 + " : " + "0" + j % 60);
            }
            best.setFont(new Font("Serif", 1, 40));
            level.setFont(new Font("Serif", 1, 40));
            level.setBackground(Color.ORANGE);
            this.gbc.gridx = 0;
            this.gbc.gridy = i - 1;
            this.list.add(level, this.gbc);
            this.gbc.gridx = 1;
            this.gbc.gridy = i - 1;
            this.list.add(best, this.gbc);
            level.addActionListener(this);
        }
        (this.del = new JButton("Delete Level!")).setFont(new Font("Serif", 1, 30));
        this.del.setBackground(Color.RED);
        this.del.setFocusable(false);
        this.del.addActionListener(this);
        this.gbc.gridx = 1;
        this.gbc.gridy = 100;
        this.list.add(this.del, this.gbc);
        if (this.levels.getLevels().size() == 0) {
            this.list.remove(this.del);
            final JLabel empty = new JLabel("There are no Levels available !");
            empty.setFont(new Font("Serif", 1, 30));
            this.list.add(empty);
        }
        this.add(this.scroll);
        this.setVisible(true);
    }
    
    @Override
    public void actionPerformed(final ActionEvent e) {
        if (e.getSource() == this.del) {
            if (this.current != null) {
                final int i = Integer.parseInt(this.current.getText().substring(6));
                this.levels.getLevels().remove(i - 1);
                try {
                    this.levels.saveToJsonFile("Levels.json");
                }
                catch (IOException e2) {
                    e2.printStackTrace();
                }
                try {
                    final DeleteLevels dl = new DeleteLevels();
                    this.dispose();
                }
                catch (FileNotFoundException e3) {
                    e3.printStackTrace();
                }
            }
        }
        else {
            if (this.current != null) {
                this.current.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            }
            this.current = (JButton)e.getSource();
            ((JButton)e.getSource()).setBorder(BorderFactory.createLineBorder(Color.RED, 3));
        }
    }
}
