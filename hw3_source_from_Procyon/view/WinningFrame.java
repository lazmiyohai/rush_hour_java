// 
// Decompiled by Procyon v0.5.36
// 

package view;

import controller.Clock;
import java.util.Stack;
import controller.Level;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.awt.Component;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import model.ListOfLevels;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class WinningFrame extends JFrame implements ActionListener
{
    JPanel p;
    GridBagConstraints gbc;
    JButton mainMenu;
    JButton chooseLev;
    JButton nextLevel;
    int bestTime;
    int currentTime;
    JLabel best;
    JLabel current;
    int currentLev;
    ListOfLevels list;
    
    public WinningFrame(final int bestTime, final int currentTime, final int currenLev) throws FileNotFoundException {
        super("Level Complete !");
        this.p = new JPanel(new GridBagLayout());
        this.gbc = new GridBagConstraints();
        (this.list = new ListOfLevels()).loadFromFile("Levels.json");
        this.p.setBackground(Color.BLUE);
        this.currentLev = currenLev;
        this.setDefaultCloseOperation(3);
        this.setLocation(400, 400);
        this.bestTime = bestTime;
        this.currentTime = currentTime;
        this.gbc.insets = new Insets(20, 20, 20, 20);
        this.setBackground(Color.BLACK);
        if (currentTime % 60 > 9) {
            this.current = new JLabel("You've completed this level in : 0" + currentTime / 60 + " : " + currentTime % 60);
        }
        else if (currentTime % 60 < 9) {
            this.current = new JLabel("You've completed this level in : 0" + currentTime / 60 + " : " + "0" + currentTime % 60);
        }
        this.current.setFont(new Font("Serif", 1, 40));
        this.gbc.gridx = 0;
        this.gbc.gridy = 0;
        this.p.add(this.current, this.gbc);
        if (bestTime % 60 > 9) {
            this.best = new JLabel("Best time for this level is : 0" + bestTime / 60 + " : " + bestTime % 60);
        }
        else if (bestTime % 60 < 9) {
            this.best = new JLabel("Best time for this level is : 0" + bestTime / 60 + " : " + "0" + bestTime % 60);
        }
        this.best.setFont(new Font("Serif", 1, 40));
        this.gbc.gridx = 0;
        this.gbc.gridy = 1;
        this.p.add(this.best, this.gbc);
        (this.mainMenu = new JButton("Main Menu")).addActionListener(this);
        this.mainMenu.setFont(new Font("Serif", 1, 40));
        this.gbc.gridx = 1;
        this.gbc.gridy = 0;
        this.p.add(this.mainMenu, this.gbc);
        this.mainMenu.setFocusable(false);
        this.mainMenu.setBackground(Color.CYAN);
        (this.chooseLev = new JButton("Choose another level")).addActionListener(this);
        this.chooseLev.setFont(new Font("Serif", 1, 40));
        this.gbc.gridx = 1;
        this.gbc.gridy = 1;
        this.p.add(this.chooseLev, this.gbc);
        this.chooseLev.setFocusable(false);
        this.chooseLev.setBackground(Color.GREEN);
        (this.nextLevel = new JButton("Next Level")).addActionListener(this);
        this.nextLevel.setFont(new Font("Serif", 1, 40));
        this.gbc.gridx = 1;
        this.gbc.gridy = 2;
        this.p.add(this.nextLevel, this.gbc);
        this.nextLevel.setFocusable(false);
        this.nextLevel.setBackground(Color.ORANGE);
        this.add(this.p);
        this.setVisible(true);
        this.pack();
    }
    
    @Override
    public void actionPerformed(final ActionEvent arg0) {
        if (arg0.getSource() == this.mainMenu) {
            final MainMenu mm = new MainMenu();
            this.dispose();
        }
        else if (arg0.getSource() == this.chooseLev) {
            try {
                final LevelChoosing lc = new LevelChoosing();
                this.dispose();
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        else if (arg0.getSource() == this.nextLevel) {
            try {
                if (this.currentLev < this.list.getLevels().size() - 1) {
                    final GameWindow gw = new GameWindow(this.list.getLevels().get(this.currentLev + 1), new Stack<Level>(), this.currentLev + 1, new Clock());
                    this.dispose();
                }
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
