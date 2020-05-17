// 
// Decompiled by Procyon v0.5.36
// 

package view;

import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.FileNotFoundException;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import controller.Clock;
import javax.swing.JButton;
import model.Icons;
import javax.swing.JSplitPane;
import javax.swing.JPanel;
import controller.Board;
import java.awt.GridBagConstraints;
import controller.EmptyBlock;
import controller.Block;
import java.util.LinkedList;
import model.ListOfLevels;
import controller.Level;
import java.util.Stack;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class GameWindow extends JFrame implements KeyListener, MouseListener, ActionListener
{
    Stack<Level> levels;
    boolean isOver;
    int levelNum;
    ListOfLevels allLevels;
    Level currentLevel;
    int bestTime;
    LinkedList<Block> components;
    EmptyBlock[][] board;
    int[][] data;
    GridBagConstraints gbc;
    GridBagConstraints gb;
    Board buttons;
    JPanel options;
    JSplitPane border;
    Block b;
    Block c;
    Block current;
    Icons icons;
    JButton undo;
    JButton back;
    JButton hint;
    Clock clock;
    
    public GameWindow(final Level level, final Stack<Level> levels, final int levelNum, final Clock clock) throws FileNotFoundException {
        super("Unblock Me");
        this.levels = new Stack<Level>();
        this.isOver = false;
        this.components = new LinkedList<Block>();
        this.board = new EmptyBlock[6][6];
        this.data = new int[6][6];
        this.gbc = new GridBagConstraints();
        this.gb = new GridBagConstraints();
        this.buttons = new Board();
        this.options = new JPanel();
        this.icons = new Icons();
        this.undo = new JButton("Undo");
        this.back = new JButton("Main Menu");
        this.hint = new JButton("Hint");
        this.levelNum = levelNum;
        this.clock = clock;
        (this.allLevels = new ListOfLevels()).loadFromFile("Levels.json");
        this.setLocation(100, 50);
        this.currentLevel = level;
        if (levels.size() == 0) {
            this.undo.setEnabled(false);
            clock.getTimer().start();
        }
        this.data = this.currentLevel.getData();
        this.levels = levels;
        this.components = this.currentLevel.getComponents();
        this.bestTime = this.currentLevel.getBestTime();
        this.setResizable(false);
        this.setDefaultCloseOperation(3);
        this.buttons.setLayout(new GridBagLayout());
        this.gbc.fill = 1;
        this.options.setLayout(new GridBagLayout());
        this.options.setBackground(Color.WHITE);
        this.gb.gridx = 0;
        this.gb.gridy = 0;
        this.options.add(clock, this.gb);
        clock.setForeground(Color.RED);
        this.gb.insets = new Insets(50, 50, 50, 50);
        this.gb.gridx = 0;
        this.gb.gridy = 1;
        this.options.add(this.undo, this.gb);
        this.undo.setFont(new Font("Serif", 1, 30));
        this.undo.addActionListener(this);
        this.undo.setBackground(Color.BLUE);
        this.gb.gridx = 0;
        this.gb.gridy = 2;
        this.options.add(this.back, this.gb);
        this.back.setFont(new Font("Serif", 1, 30));
        this.back.addActionListener(this);
        this.back.setBackground(Color.CYAN);
        this.gb.gridx = 0;
        this.gb.gridy = 3;
        this.options.add(this.hint, this.gb);
        this.hint.setFont(new Font("Serif", 1, 30));
        this.hint.setBackground(Color.YELLOW);
        this.hint.setEnabled(false);
        for (int i = 0; i < 6; ++i) {
            for (int j = 0; j < 6; ++j) {
                this.gbc.gridx = i;
                this.gbc.gridy = j;
                this.board[i][j] = new EmptyBlock();
                this.buttons.add(this.board[i][j], this.gbc);
            }
        }
        for (int i = 0; i < this.components.size(); ++i) {
            final Block temp = new Block(this.components.get(i).getVal(), this.components.get(i).getHei(), this.components.get(i).getWid());
            temp.setCol(this.components.get(i).getCol());
            temp.setRow(this.components.get(i).getRow());
            for (int k = 0; temp.getWid() > 1 && k < temp.getWid(); ++k) {
                this.buttons.remove(this.board[temp.getCol() + k][temp.getRow()]);
            }
            for (int k = 0; temp.getHei() > 1 && k < temp.getHei(); ++k) {
                this.buttons.remove(this.board[temp.getCol()][temp.getRow() + k]);
            }
            this.board[5][2].setBorder(BorderFactory.createMatteBorder(0, 0, 0, 5, Color.RED));
            this.gbc.gridx = temp.getCol();
            this.gbc.gridy = temp.getRow();
            this.gbc.gridheight = temp.getHei();
            this.gbc.gridwidth = temp.getWid();
            this.buttons.add(temp, this.gbc);
            temp.addMouseListener(this);
            temp.addKeyListener(this);
            this.components.set(i, temp);
        }
        if (this.currentLevel.getIndex() == -1) {
            this.current = null;
        }
        else {
            (this.current = this.components.get(this.currentLevel.getIndex())).setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
            this.current.setPressed(true);
        }
        this.hint.setFocusable(false);
        this.back.setFocusable(false);
        this.undo.setFocusable(false);
        this.add(this.border = new JSplitPane(1, this.buttons, this.options));
        this.border.setEnabled(false);
        this.setVisible(true);
        this.pack();
    }
    
    public void gameOver() throws IOException {
        if (this.data[5][2] == 2) {
            this.clock.getTimer().stop();
            final int stopTime = this.clock.timePassed();
            if (stopTime < this.bestTime) {
                this.bestTime = stopTime;
            }
            this.allLevels.getLevels().get(this.levelNum).setBestTime(this.bestTime);
            this.allLevels.saveToJsonFile("Levels.json");
            final WinningFrame wf = new WinningFrame(this.bestTime, stopTime, this.levelNum);
            this.isOver = true;
            this.dispose();
        }
    }
    
    @Override
    public void keyPressed(final KeyEvent e) {
        try {
            if (e.getKeyCode() == 39 && this.current.getHei() == 1 && this.current.getCol() + this.current.getWid() < 6 && this.data[this.current.getCol() + this.current.getWid()][this.current.getRow()] == 0) {
                this.levels.add(new Level(this.data, this.components, this.bestTime, this.components.indexOf(this.current)));
                this.data[this.current.getCol()][this.current.getRow()] = 0;
                this.data[this.current.getCol() + this.current.getWid()][this.current.getRow()] = this.current.getVal() + 1;
                this.buttons.remove(this.board[this.current.getCol() + this.current.getWid()][this.current.getRow()]);
                this.gbc.gridx = this.current.getCol() + 1;
                this.current.setCol(this.current.getCol() + 1);
                this.gbc.gridy = this.current.getRow();
                this.gbc.gridwidth = this.current.getWid();
                this.gbc.gridheight = 1;
                this.buttons.add(this.current, this.gbc);
                this.gbc.gridx = this.current.getCol() - 1;
                this.gbc.gridy = this.current.getRow();
                this.gbc.gridwidth = 1;
                this.gbc.gridheight = 1;
                this.buttons.add(this.board[this.gbc.gridx][this.gbc.gridy], this.gbc);
                this.undo.setEnabled(true);
            }
            if (e.getKeyCode() == 37 && this.current.getHei() == 1 && this.current.getCol() != 0 && this.data[this.current.getCol() - 1][this.current.getRow()] == 0) {
                this.levels.add(new Level(this.data, this.components, this.bestTime, this.components.indexOf(this.current)));
                this.data[this.current.getCol() + this.current.getWid() - 1][this.current.getRow()] = 0;
                this.data[this.current.getCol() - 1][this.current.getRow()] = this.current.getVal() + 1;
                this.buttons.remove(this.board[this.current.getCol() - 1][this.current.getRow()]);
                this.gbc.gridx = this.current.getCol() - 1;
                this.current.setCol(this.current.getCol() - 1);
                this.gbc.gridy = this.current.getRow();
                this.gbc.gridwidth = this.current.getWid();
                this.gbc.gridheight = 1;
                this.buttons.add(this.current, this.gbc);
                this.gbc.gridx = this.current.getCol() + this.current.getWid();
                this.gbc.gridy = this.current.getRow();
                this.gbc.gridwidth = 1;
                this.gbc.gridheight = 1;
                this.buttons.add(this.board[this.gbc.gridx][this.gbc.gridy], this.gbc);
            }
            if (e.getKeyCode() == 38 && this.current.getWid() == 1 && this.current.getRow() != 0 && this.data[this.current.getCol()][this.current.getRow() - 1] == 0) {
                this.levels.add(new Level(this.data, this.components, this.bestTime, this.components.indexOf(this.current)));
                this.data[this.current.getCol()][this.current.getRow() + this.current.getHei() - 1] = 0;
                this.data[this.current.getCol()][this.current.getRow() - 1] = this.current.getVal() + 1;
                this.buttons.remove(this.board[this.current.getCol()][this.current.getRow() - 1]);
                this.gbc.gridx = this.current.getCol();
                this.gbc.gridy = this.current.getRow() - 1;
                this.current.setRow(this.current.getRow() - 1);
                this.gbc.gridwidth = 1;
                this.gbc.gridheight = this.current.getHei();
                this.buttons.add(this.current, this.gbc);
                this.gbc.gridx = this.current.getCol();
                this.gbc.gridy = this.current.getRow() + this.current.getHei();
                this.gbc.gridwidth = 1;
                this.gbc.gridheight = 1;
                this.buttons.add(this.board[this.gbc.gridx][this.gbc.gridy], this.gbc);
                this.undo.setEnabled(true);
            }
            if (e.getKeyCode() == 40 && this.current.getWid() == 1 && this.current.getRow() + this.current.getHei() < 6 && this.data[this.current.getCol()][this.current.getRow() + this.current.getHei()] == 0) {
                this.levels.add(new Level(this.data, this.components, this.bestTime, this.components.indexOf(this.current)));
                this.data[this.current.getCol()][this.current.getRow()] = 0;
                this.data[this.current.getCol()][this.current.getRow() + this.current.getHei()] = this.current.getVal() + 1;
                this.buttons.remove(this.board[this.current.getCol()][this.current.getRow() + this.current.getHei()]);
                this.gbc.gridx = this.current.getCol();
                this.gbc.gridy = this.current.getRow() + 1;
                this.current.setRow(this.current.getRow() + 1);
                this.gbc.gridwidth = 1;
                this.gbc.gridheight = this.current.getHei();
                this.buttons.add(this.current, this.gbc);
                this.gbc.gridx = this.current.getCol();
                this.gbc.gridy = this.current.getRow() - 1;
                this.gbc.gridwidth = 1;
                this.gbc.gridheight = 1;
                this.buttons.add(this.board[this.gbc.gridx][this.gbc.gridy], this.gbc);
                this.undo.setEnabled(true);
            }
            this.setVisible(true);
            this.gameOver();
        }
        catch (RuntimeException ex) {}
        catch (IOException ex2) {}
    }
    
    @Override
    public void keyReleased(final KeyEvent e) {
    }
    
    @Override
    public void keyTyped(final KeyEvent e) {
    }
    
    @Override
    public void mouseClicked(final MouseEvent e) {
    }
    
    @Override
    public void mouseEntered(final MouseEvent e) {
    }
    
    @Override
    public void mouseExited(final MouseEvent e) {
    }
    
    @Override
    public void mousePressed(final MouseEvent e) {
        this.current = (Block)e.getComponent();
        if (!this.current.isPressed()) {
            for (final Block temp : this.components) {
                temp.setPressed(false);
                temp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            }
            this.current.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
            this.current.setPressed(true);
        }
        else {
            this.current.setBorder(BorderFactory.createEmptyBorder());
            this.current.setPressed(false);
            this.current = null;
        }
    }
    
    @Override
    public void mouseReleased(final MouseEvent e) {
    }
    
    @Override
    public void actionPerformed(final ActionEvent arg0) {
        if (arg0.getSource() == this.undo) {
            if (!this.levels.isEmpty()) {
                final Level l = this.levels.pop();
                try {
                    final int i = this.components.indexOf(this.current);
                    final GameWindow gw = new GameWindow(l, this.levels, this.levelNum, this.clock);
                    this.dispose();
                }
                catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        else if (arg0.getSource() == this.back) {
            final MainMenu mm = new MainMenu();
            this.dispose();
        }
    }
}
