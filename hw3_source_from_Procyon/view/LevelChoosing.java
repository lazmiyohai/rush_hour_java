// 
// Decompiled by Procyon v0.5.36
// 

package view;

import controller.Clock;
import java.util.Stack;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import controller.Level;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JScrollPane;
import java.awt.GridBagConstraints;
import model.ListOfLevels;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class LevelChoosing extends JFrame implements ActionListener
{
    JPanel list;
    ListOfLevels levels;
    GridBagConstraints gbc;
    JScrollPane scroll;
    
    public LevelChoosing() throws FileNotFoundException {
        super("Choose a Level");
        this.list = new JPanel();
        this.levels = new ListOfLevels();
        this.gbc = new GridBagConstraints();
        this.scroll = new JScrollPane(this.list);
        this.setDefaultCloseOperation(3);
        this.list.setBackground(Color.GREEN);
        this.setSize(600, 800);
        this.setLocation(500, 0);
        this.setResizable(false);
        this.list.setLayout(new GridBagLayout());
        this.levels.loadFromFile("Levels.json");
        this.gbc.insets = new Insets(20, 20, 20, 20);
        if (this.levels.getLevels().size() == 0) {
            final JLabel empty = new JLabel("There are no Levels available !");
            empty.setFont(new Font("Serif", 1, 30));
            this.list.add(empty);
        }
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
            this.gbc.gridx = 0;
            this.gbc.gridy = i - 1;
            this.list.add(level, this.gbc);
            level.setBackground(Color.ORANGE);
            this.gbc.gridx = 1;
            this.gbc.gridy = i - 1;
            this.list.add(best, this.gbc);
            best.setForeground(Color.RED);
            level.addActionListener(this);
        }
        this.add(this.scroll);
        this.setVisible(true);
    }
    
    @Override
    public void actionPerformed(final ActionEvent e) {
        final int i = Integer.parseInt(((JButton)e.getSource()).getText().substring(6));
        final Level temp = this.levels.getLevels().get(i - 1);
        try {
            final GameWindow gw = new GameWindow(temp, new Stack<Level>(), i - 1, new Clock());
            this.dispose();
        }
        catch (FileNotFoundException e2) {
            e2.printStackTrace();
        }
    }
}
