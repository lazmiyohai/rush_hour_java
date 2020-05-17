package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.LinkedList;
import java.util.Stack;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import controller.Block;
import controller.EmptyBlock;
import controller.Level;
import model.ListOfLevels;

/**
 * This class represents the frame that lets you create new levels to the game.
 * @author sahsah1
 */
public class CreateLevel extends JFrame implements ActionListener,MouseListener {
	ListOfLevels levels;
	Block target;
	LinkedList<Block> components = new LinkedList<>();
	int[][] data = new int[6][6];
	JLabel[][] background = new JLabel[6][6];
	GridBagConstraints gbc = new GridBagConstraints();
	GridBagConstraints gb = new GridBagConstraints();
	JPanel board = new JPanel();
	JPanel buttons = new JPanel();
	JSplitPane border;
	JButton add = new JButton("Add block");
	JButton create = new JButton("Create level");
	Block current;
	Block ver2;
	Block ver3;
	Block hor2;
	Block hor3;
	
	DefaultListModel<Integer> rowPos = new DefaultListModel<Integer>();
	JList<Integer> rowList = new JList<Integer>(rowPos);
	JScrollPane p = new JScrollPane(rowList);
	JLabel numOfRow = new JLabel("Row index :");
	
	DefaultListModel<Integer> colPos = new DefaultListModel<Integer>();
	JList<Integer> colList = new JList<Integer>(colPos);
	JScrollPane cp = new JScrollPane(colList);
	JLabel numOfCol = new JLabel("Column index :");
	
	/**
	 * This frame constructor.
	 * @throws FileNotFoundException
	 */
	public CreateLevel() throws FileNotFoundException{
		levels = new ListOfLevels();
		try{
			levels.loadFromFile("Levels.json");
		}
		catch(FileNotFoundException e){
			
		}
		create.setFocusable(false);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		board.setLayout(new GridBagLayout());
		gbc.fill = GridBagConstraints.BOTH;
		gb.fill = GridBagConstraints.BOTH;
		gb.insets = new Insets(2, 2, 2, 2);
		numOfRow.setFont(new Font("Serif", Font.BOLD, 20));
		numOfCol.setFont(new Font("Serif", Font.BOLD, 20));
		add.setFont(new Font("Serif", Font.BOLD, 30));
		add.addActionListener(this);
		create.addActionListener(this);
		create.setFont(new Font("Serif", Font.BOLD, 30));
		
		for (int i =0;i<6;i++){
			rowPos.addElement(i);
			colPos.addElement(i);
		}
		rowList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		rowList.setSelectedIndex(0);
		rowList.setVisibleRowCount(3);
		rowList.setFont(new Font("Serif", Font.BOLD, 40));
		
		colList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		colList.setSelectedIndex(0);
		colList.setVisibleRowCount(3);
		colList.setFont(new Font("Serif", Font.BOLD, 40));
		
		buttons.setLayout(new GridBagLayout());
		buttons.setBackground(Color.CYAN);
		
		JLabel type = new JLabel("Choose Block Type :");
		type.setFont(new Font("Serif", Font.BOLD, 30));
		gb.gridx = 0;
		gb.gridy = 0;
		gb.gridheight = 1;
		gb.gridwidth = 2;
		buttons.add(type, gb);
		type.setForeground(Color.red);
		
		gb.gridx = 0;
		gb.gridy = 3;
		gb.gridheight = 1;
		gb.gridwidth = 1;
		buttons.add(new EmptyBlock(), gb);
		
		hor2 = new Block(0, 1, 2);
		hor2.addMouseListener(this);
		gb.gridx = 0;
		gb.gridy = 2;
		gb.gridheight = 1;
		gb.gridwidth = 2;
		buttons.add(hor2,gb);
		
		hor3 = new Block(0, 1, 3);
		hor3.addMouseListener(this);
		gb.gridx = 0;
		gb.gridy = 1;
		gb.gridheight = 1;
		gb.gridwidth = 3;
		buttons.add(hor3,gb);
		
		ver2 = new Block(0, 2, 1);
		ver2.addMouseListener(this);
		gb.gridx = 2;
		gb.gridy = 2;
		gb.gridheight = 2;
		gb.gridwidth = 1;
		buttons.add(ver2,gb);
		
		ver3 = new Block(0, 3, 1);
		ver3.addMouseListener(this);
		gb.gridx = 3;
		gb.gridy = 1;
		gb.gridheight = 3;
		gb.gridwidth = 1;
		buttons.add(ver3,gb);
		
		JLabel pos = new JLabel("Choose block position :");
		pos.setFont(new Font("Serif", Font.BOLD, 30));
		gb.gridx = 0;
		gb.gridy = 4;
		gb.gridheight = 1;
		gb.gridwidth = 2;
		buttons.add(pos, gb);
		pos.setForeground(Color.red);
		
		gb.gridx = 0;
		gb.gridy = 5;
		gb.gridheight = 1;
		gb.gridwidth = 1;
		buttons.add(numOfRow, gb);
		
		gb.gridx = 0;
		gb.gridy = 6;
		gb.gridheight = 1;
		gb.gridwidth = 1;
		buttons.add(p, gb);
		
		gb.gridx = 2;
		gb.gridy = 5;
		gb.gridheight = 1;
		gb.gridwidth = 1;
		buttons.add(numOfCol, gb);
		
		gb.gridx = 2;
		gb.gridy = 6;
		gb.gridheight = 1;
		gb.gridwidth = 1;
		buttons.add(cp, gb);
		
		gb.gridx = 0;
		gb.gridy = 7;
		gb.gridheight = 1;
		gb.gridwidth = 2;
		buttons.add(add, gb);
		add.setBackground(Color.BLUE);
		
		gb.gridx = 0;
		gb.gridy = 8;
		gb.gridheight = 1;
		gb.gridwidth = 2;
		buttons.add(create, gb);
		create.setBackground(Color.GREEN);
		
		for (int i=0;i<6;i++){
			for(int j=0;j<6;j++){
				gbc.gridx = i;
				gbc.gridy = j;
				JLabel empty = new JLabel();
				empty.setPreferredSize(new Dimension(150, 150));
				empty.setBackground(Color.BLUE);
				empty.setOpaque(true);
				empty.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
				background[i][j] = empty;
				board.add(background[i][j],gbc);
				data[i][j]=0;
			}
		}
		
		background[5][2].setBorder(BorderFactory.createMatteBorder(0, 0, 0, 5, Color.RED));
		background[5][1].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		board.remove(background[0][2]);
		board.remove(background[1][2]);
		data[0][2] = 2;
		data[1][2] = 2;
		
		target = new Block(1, 1, 2);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridheight = 1;
		gbc.gridwidth = 2;
		target.setCol(0);
		target.setRow(2);
		board.add(target,gbc);
		components.add(target);
		
		border = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, buttons, board);
		buttons.setFocusable(true);
		add.setFocusable(false);
		add(border);
		setVisible(true);
		pack();
	}
	
	/**
	 * This method controls the actions that occurs from pressing the frame's buttons.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == add){
			int rowNum = rowList.getSelectedValue();
			int colNum = colList.getSelectedValue();
			if (current != null){
				Block temp = new Block(0, current.getHei(), current.getWid());
				if (temp.getHei()==1 && colNum+temp.getWid()-1<6 && data[colNum][rowNum]==0 && data[colNum+1][rowNum]==0 && data[colNum+temp.getWid()-1][rowNum]==0){
					board.remove(background[colNum][rowNum]);
					board.remove(background[colNum+1][rowNum]);
					board.remove(background[colNum+temp.getWid()-1][rowNum]);
					data[colNum][rowNum] = 1;
					data[colNum+1][rowNum] = 1;
					data[colNum+temp.getWid()-1][rowNum] = 1;
					temp.setCol(colNum);
					temp.setRow(rowNum);
					gbc.gridx = colNum;
					gbc.gridy = rowNum;
					gbc.gridheight = temp.getHei();
					gbc.gridwidth = temp.getWid();
					board.add(temp,gbc);
					components.add(temp);
				}
				else if (temp.getWid()==1 && rowNum+temp.getHei()-1<6 && data[colNum][rowNum]==0 && data[colNum][rowNum+1]==0 && data[colNum][rowNum+temp.getHei()-1]==0){
					board.remove(background[colNum][rowNum]);
					board.remove(background[colNum][rowNum+1]);
					board.remove(background[colNum][rowNum+temp.getHei()-1]);
					data[colNum][rowNum] = 1;
					data[colNum][rowNum+1] = 1;
					data[colNum][rowNum+temp.getHei()-1] = 1;
					temp.setCol(colNum);
					temp.setRow(rowNum);
					gbc.gridx = colNum;
					gbc.gridy = rowNum;
					gbc.gridheight = temp.getHei();
					gbc.gridwidth = temp.getWid();
					board.add(temp,gbc);
					components.add(temp);
				}
			}
			setVisible(true);
		}
		else if (e.getSource() == create){
			Level l = new Level(data, components, 1000000, -1);
			try{
				levels.getLevels().add(l);
				levels.saveToJsonFile("Levels.json");
			}
			catch (IOException e1) {
				e1.printStackTrace();
			}
			MainMenu mm = new MainMenu();
			dispose();
		}
	}
	
	/**
	 * This method controls what happens if a user clicks on one of the Blocks.
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		current = (Block)e.getComponent();
		if (!current.isPressed()){
			hor2.setPressed(false);
			hor2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			hor3.setPressed(false);
			hor3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			ver2.setPressed(false);
			ver2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			ver3.setPressed(false);
			ver3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			current.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
			current.setPressed(true);
		}
		else{
			current.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			current.setPressed(false);
			current = null;
		}
		
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}
}
