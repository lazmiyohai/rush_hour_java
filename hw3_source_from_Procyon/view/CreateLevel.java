// 
// Decompiled by Procyon v0.5.36
// 

package view;

import java.awt.event.MouseEvent;
import java.io.IOException;
import controller.Level;
import java.awt.event.ActionEvent;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.Dimension;
import controller.EmptyBlock;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.io.FileNotFoundException;
import java.awt.Component;
import javax.swing.ListModel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JSplitPane;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.util.LinkedList;
import controller.Block;
import model.ListOfLevels;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class CreateLevel extends JFrame implements ActionListener, MouseListener
{
    ListOfLevels levels;
    Block target;
    LinkedList<Block> components;
    int[][] data;
    JLabel[][] background;
    GridBagConstraints gbc;
    GridBagConstraints gb;
    JPanel board;
    JPanel buttons;
    JSplitPane border;
    JButton add;
    JButton create;
    Block current;
    Block ver2;
    Block ver3;
    Block hor2;
    Block hor3;
    DefaultListModel<Integer> rowPos;
    JList<Integer> rowList;
    JScrollPane p;
    JLabel numOfRow;
    DefaultListModel<Integer> colPos;
    JList<Integer> colList;
    JScrollPane cp;
    JLabel numOfCol;
    
    public CreateLevel() throws FileNotFoundException {
        this.components = new LinkedList<Block>();
        this.data = new int[6][6];
        this.background = new JLabel[6][6];
        this.gbc = new GridBagConstraints();
        this.gb = new GridBagConstraints();
        this.board = new JPanel();
        this.buttons = new JPanel();
        this.add = new JButton("Add block");
        this.create = new JButton("Create level");
        this.rowPos = new DefaultListModel<Integer>();
        this.rowList = new JList<Integer>(this.rowPos);
        this.p = new JScrollPane(this.rowList);
        this.numOfRow = new JLabel("Row index :");
        this.colPos = new DefaultListModel<Integer>();
        this.colList = new JList<Integer>(this.colPos);
        this.cp = new JScrollPane(this.colList);
        this.numOfCol = new JLabel("Column index :");
        this.levels = new ListOfLevels();
        try {
            this.levels.loadFromFile("Levels.json");
        }
        catch (FileNotFoundException ex) {}
        this.create.setFocusable(false);
        this.setResizable(false);
        this.setDefaultCloseOperation(3);
        this.board.setLayout(new GridBagLayout());
        this.gbc.fill = 1;
        this.gb.fill = 1;
        this.gb.insets = new Insets(2, 2, 2, 2);
        this.numOfRow.setFont(new Font("Serif", 1, 20));
        this.numOfCol.setFont(new Font("Serif", 1, 20));
        this.add.setFont(new Font("Serif", 1, 30));
        this.add.addActionListener(this);
        this.create.addActionListener(this);
        this.create.setFont(new Font("Serif", 1, 30));
        for (int i = 0; i < 6; ++i) {
            this.rowPos.addElement(i);
            this.colPos.addElement(i);
        }
        this.rowList.setSelectionMode(0);
        this.rowList.setSelectedIndex(0);
        this.rowList.setVisibleRowCount(3);
        this.rowList.setFont(new Font("Serif", 1, 40));
        this.colList.setSelectionMode(0);
        this.colList.setSelectedIndex(0);
        this.colList.setVisibleRowCount(3);
        this.colList.setFont(new Font("Serif", 1, 40));
        this.buttons.setLayout(new GridBagLayout());
        this.buttons.setBackground(Color.CYAN);
        final JLabel type = new JLabel("Choose Block Type :");
        type.setFont(new Font("Serif", 1, 30));
        this.gb.gridx = 0;
        this.gb.gridy = 0;
        this.gb.gridheight = 1;
        this.gb.gridwidth = 2;
        this.buttons.add(type, this.gb);
        type.setForeground(Color.red);
        this.gb.gridx = 0;
        this.gb.gridy = 3;
        this.gb.gridheight = 1;
        this.gb.gridwidth = 1;
        this.buttons.add(new EmptyBlock(), this.gb);
        (this.hor2 = new Block(0, 1, 2)).addMouseListener(this);
        this.gb.gridx = 0;
        this.gb.gridy = 2;
        this.gb.gridheight = 1;
        this.gb.gridwidth = 2;
        this.buttons.add(this.hor2, this.gb);
        (this.hor3 = new Block(0, 1, 3)).addMouseListener(this);
        this.gb.gridx = 0;
        this.gb.gridy = 1;
        this.gb.gridheight = 1;
        this.gb.gridwidth = 3;
        this.buttons.add(this.hor3, this.gb);
        (this.ver2 = new Block(0, 2, 1)).addMouseListener(this);
        this.gb.gridx = 2;
        this.gb.gridy = 2;
        this.gb.gridheight = 2;
        this.gb.gridwidth = 1;
        this.buttons.add(this.ver2, this.gb);
        (this.ver3 = new Block(0, 3, 1)).addMouseListener(this);
        this.gb.gridx = 3;
        this.gb.gridy = 1;
        this.gb.gridheight = 3;
        this.gb.gridwidth = 1;
        this.buttons.add(this.ver3, this.gb);
        final JLabel pos = new JLabel("Choose block position :");
        pos.setFont(new Font("Serif", 1, 30));
        this.gb.gridx = 0;
        this.gb.gridy = 4;
        this.gb.gridheight = 1;
        this.gb.gridwidth = 2;
        this.buttons.add(pos, this.gb);
        pos.setForeground(Color.red);
        this.gb.gridx = 0;
        this.gb.gridy = 5;
        this.gb.gridheight = 1;
        this.gb.gridwidth = 1;
        this.buttons.add(this.numOfRow, this.gb);
        this.gb.gridx = 0;
        this.gb.gridy = 6;
        this.gb.gridheight = 1;
        this.gb.gridwidth = 1;
        this.buttons.add(this.p, this.gb);
        this.gb.gridx = 2;
        this.gb.gridy = 5;
        this.gb.gridheight = 1;
        this.gb.gridwidth = 1;
        this.buttons.add(this.numOfCol, this.gb);
        this.gb.gridx = 2;
        this.gb.gridy = 6;
        this.gb.gridheight = 1;
        this.gb.gridwidth = 1;
        this.buttons.add(this.cp, this.gb);
        this.gb.gridx = 0;
        this.gb.gridy = 7;
        this.gb.gridheight = 1;
        this.gb.gridwidth = 2;
        this.buttons.add(this.add, this.gb);
        this.add.setBackground(Color.BLUE);
        this.gb.gridx = 0;
        this.gb.gridy = 8;
        this.gb.gridheight = 1;
        this.gb.gridwidth = 2;
        this.buttons.add(this.create, this.gb);
        this.create.setBackground(Color.GREEN);
        for (int j = 0; j < 6; ++j) {
            for (int k = 0; k < 6; ++k) {
                this.gbc.gridx = j;
                this.gbc.gridy = k;
                final JLabel empty = new JLabel();
                empty.setPreferredSize(new Dimension(150, 150));
                empty.setBackground(Color.BLUE);
                empty.setOpaque(true);
                empty.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
                this.background[j][k] = empty;
                this.board.add(this.background[j][k], this.gbc);
                this.data[j][k] = 0;
            }
        }
        this.background[5][2].setBorder(BorderFactory.createMatteBorder(0, 0, 0, 5, Color.RED));
        this.background[5][1].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        this.board.remove(this.background[0][2]);
        this.board.remove(this.background[1][2]);
        this.data[0][2] = 2;
        this.data[1][2] = 2;
        this.target = new Block(1, 1, 2);
        this.gbc.gridx = 0;
        this.gbc.gridy = 2;
        this.gbc.gridheight = 1;
        this.gbc.gridwidth = 2;
        this.target.setCol(0);
        this.target.setRow(2);
        this.board.add(this.target, this.gbc);
        this.components.add(this.target);
        this.border = new JSplitPane(1, this.buttons, this.board);
        this.buttons.setFocusable(true);
        this.add.setFocusable(false);
        this.add(this.border);
        this.setVisible(true);
        this.pack();
    }
    
    @Override
    public void actionPerformed(final ActionEvent e) {
        if (e.getSource() == this.add) {
            final int rowNum = this.rowList.getSelectedValue();
            final int colNum = this.colList.getSelectedValue();
            if (this.current != null) {
                final Block temp = new Block(0, this.current.getHei(), this.current.getWid());
                if (temp.getHei() == 1 && colNum + temp.getWid() - 1 < 6 && this.data[colNum][rowNum] == 0 && this.data[colNum + 1][rowNum] == 0 && this.data[colNum + temp.getWid() - 1][rowNum] == 0) {
                    this.board.remove(this.background[colNum][rowNum]);
                    this.board.remove(this.background[colNum + 1][rowNum]);
                    this.board.remove(this.background[colNum + temp.getWid() - 1][rowNum]);
                    this.data[colNum][rowNum] = 1;
                    this.data[colNum + 1][rowNum] = 1;
                    this.data[colNum + temp.getWid() - 1][rowNum] = 1;
                    temp.setCol(colNum);
                    temp.setRow(rowNum);
                    this.gbc.gridx = colNum;
                    this.gbc.gridy = rowNum;
                    this.gbc.gridheight = temp.getHei();
                    this.gbc.gridwidth = temp.getWid();
                    this.board.add(temp, this.gbc);
                    this.components.add(temp);
                }
                else if (temp.getWid() == 1 && rowNum + temp.getHei() - 1 < 6 && this.data[colNum][rowNum] == 0 && this.data[colNum][rowNum + 1] == 0 && this.data[colNum][rowNum + temp.getHei() - 1] == 0) {
                    this.board.remove(this.background[colNum][rowNum]);
                    this.board.remove(this.background[colNum][rowNum + 1]);
                    this.board.remove(this.background[colNum][rowNum + temp.getHei() - 1]);
                    this.data[colNum][rowNum] = 1;
                    this.data[colNum][rowNum + 1] = 1;
                    this.data[colNum][rowNum + temp.getHei() - 1] = 1;
                    temp.setCol(colNum);
                    temp.setRow(rowNum);
                    this.gbc.gridx = colNum;
                    this.gbc.gridy = rowNum;
                    this.gbc.gridheight = temp.getHei();
                    this.gbc.gridwidth = temp.getWid();
                    this.board.add(temp, this.gbc);
                    this.components.add(temp);
                }
            }
            this.setVisible(true);
        }
        else if (e.getSource() == this.create) {
            final Level l = new Level(this.data, this.components, 1000000, -1);
            try {
                this.levels.getLevels().add(l);
                this.levels.saveToJsonFile("Levels.json");
            }
            catch (IOException e2) {
                e2.printStackTrace();
            }
            final MainMenu mm = new MainMenu();
            this.dispose();
        }
    }
    
    @Override
    public void mousePressed(final MouseEvent e) {
        this.current = (Block)e.getComponent();
        if (!this.current.isPressed()) {
            this.hor2.setPressed(false);
            this.hor2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            this.hor3.setPressed(false);
            this.hor3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            this.ver2.setPressed(false);
            this.ver2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            this.ver3.setPressed(false);
            this.ver3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            this.current.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
            this.current.setPressed(true);
        }
        else {
            this.current.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            this.current.setPressed(false);
            this.current = null;
        }
    }
    
    @Override
    public void mouseReleased(final MouseEvent e) {
    }
    
    @Override
    public void mouseClicked(final MouseEvent arg0) {
    }
    
    @Override
    public void mouseEntered(final MouseEvent arg0) {
    }
    
    @Override
    public void mouseExited(final MouseEvent arg0) {
    }
}
