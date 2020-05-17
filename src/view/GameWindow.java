package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Stack;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import controller.Block;
import controller.Board;
import controller.Clock;
import controller.EmptyBlock;
import controller.Level;
import model.Icons;
import model.ListOfLevels;

/**
 * This class represents the frame of the game, with its board.
 * @author sahsah1
 *
 */
public class GameWindow extends JFrame implements KeyListener,MouseListener, ActionListener{
	Stack<Level> levels = new Stack<Level>();
	boolean isOver = false;
	int levelNum;
	ListOfLevels allLevels;
	Level currentLevel;
	int bestTime;
	LinkedList<Block> components = new LinkedList<Block>();
	EmptyBlock[][] board = new EmptyBlock[6][6];
	int[][] data = new int[6][6];
	GridBagConstraints gbc = new GridBagConstraints();
	GridBagConstraints gb = new GridBagConstraints();
	Board buttons = new Board();
	JPanel options = new JPanel();
	JSplitPane border;
	Block b;
	Block c;
	Block current;
	Icons icons = new Icons();
	JButton undo = new JButton("Undo");
	JButton back = new JButton("Main Menu");
	JButton hint = new JButton("Hint");
	Clock clock ;
	
	/**
	 * This frame constructor.
	 * @param level the current level to load.
	 * @param levels a stack holding all the previous moves made in the level.
	 * @param levelNum the index of the current level in the list of levels.
	 * @param clock the timer of the game window.
	 * @throws FileNotFoundException
	 */
	public GameWindow (Level level, Stack<Level> levels, int levelNum, Clock clock) throws FileNotFoundException{
		super("Unblock Me");
		this.levelNum = levelNum;
		this.clock = clock;
		allLevels = new ListOfLevels();
		allLevels.loadFromFile("Levels.json");
		setLocation(100, 50);
		currentLevel = level;
		if (levels.size()==0){
			undo.setEnabled(false);
			clock.getTimer().start();
		}
		data = currentLevel.getData();
		this.levels = levels;
		components = currentLevel.getComponents();
		bestTime = currentLevel.getBestTime();
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		buttons.setLayout(new GridBagLayout());
		gbc.fill = GridBagConstraints.BOTH;
		
		options.setLayout(new GridBagLayout());
		options.setBackground(Color.WHITE);
		
		gb.gridx = 0;
		gb.gridy = 0;
		options.add(clock, gb);
		clock.setForeground(Color.RED);
		gb.insets = new Insets(50, 50, 50, 50);
		
		gb.gridx = 0;
		gb.gridy = 1;
		options.add(undo,gb);
		undo.setFont(new Font("Serif", Font.BOLD, 30));
		undo.addActionListener(this);
		undo.setBackground(Color.BLUE);
		
		
		gb.gridx = 0;
		gb.gridy = 2;
		options.add(back,gb);
		back.setFont(new Font("Serif", Font.BOLD, 30));
		back.addActionListener(this);
		back.setBackground(Color.CYAN);
		
		gb.gridx = 0;
		gb.gridy = 3;
		options.add(hint,gb);
		hint.setFont(new Font("Serif", Font.BOLD, 30));
		hint.setBackground(Color.YELLOW);
		hint.setEnabled(false);
		
		
		for (int i=0;i<6;i++){
			for(int j=0;j<6;j++){
				gbc.gridx = i;
				gbc.gridy = j;
				board[i][j] = new EmptyBlock();
				buttons.add(board[i][j],gbc);
			}
		}
		
		for (int i=0;i<components.size();i++){
			Block temp = new Block(components.get(i).getVal(), components.get(i).getHei(), components.get(i).getWid());
			temp.setCol(components.get(i).getCol());
			temp.setRow(components.get(i).getRow());
			for(int j=0;temp.getWid()>1 && j<temp.getWid();j++){
				buttons.remove(board[temp.getCol()+j][temp.getRow()]);
			}
			for (int j=0;temp.getHei()>1 && j<temp.getHei();j++){
				buttons.remove(board[temp.getCol()][temp.getRow()+j]);
			}
			board[5][2].setBorder(BorderFactory.createMatteBorder(0, 0, 0, 5, Color.RED));
			gbc.gridx = temp.getCol();
			gbc.gridy = temp.getRow();
			gbc.gridheight = temp.getHei();
			gbc.gridwidth = temp.getWid();
			buttons.add(temp,gbc);
			temp.addMouseListener(this);
			temp.addKeyListener(this);
			components.set(i, temp);
		}
		if (currentLevel.getIndex() == -1){
			current = null;
		}
		else {
			current = components.get(currentLevel.getIndex());
			current.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
			current.setPressed(true);
		}
		
		hint.setFocusable(false);
		back.setFocusable(false);
		undo.setFocusable(false);
		border = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, buttons, options);
		add(border);
		border.setEnabled(false);
		
		setVisible(true);
		pack();
	}
	
	/**
	 * This method checks if the game is over, and shows the appropriate message.
	 * @throws IOException
	 */
	public void gameOver() throws IOException{
		if (data[5][2] == 2){
			clock.getTimer().stop();
			int stopTime = clock.timePassed();
			if (stopTime<bestTime){
				bestTime = stopTime;
			}
			allLevels.getLevels().get(levelNum).setBestTime(bestTime);
			allLevels.saveToJsonFile("Levels.json");
			WinningFrame wf = new WinningFrame(bestTime, stopTime,levelNum);
			isOver = true;
			dispose();
		}
	}
	
	/**
	 * This method controls the movement of a game block with the keyboard's keys.
	 */
	public void keyPressed (KeyEvent e){
		try{
			if (e.getKeyCode()==KeyEvent.VK_RIGHT && current.getHei()==1 && current.getCol()+current.getWid()<6 && data[current.getCol()+current.getWid()][current.getRow()]==0){
				levels.add(new Level(data,components,bestTime,components.indexOf(current)));
				data[current.getCol()][current.getRow()] = 0;
				data[current.getCol()+current.getWid()][current.getRow()] = current.getVal()+1;
				buttons.remove(board[current.getCol()+current.getWid()][current.getRow()]);
				gbc.gridx = current.getCol()+1;
				current.setCol(current.getCol()+1);
				gbc.gridy = current.getRow();
				gbc.gridwidth = current.getWid();
				gbc.gridheight = 1;
				buttons.add(current,gbc);
				gbc.gridx = current.getCol()-1;
				gbc.gridy = current.getRow();
				gbc.gridwidth = 1;
				gbc.gridheight = 1;
				buttons.add(board[gbc.gridx][gbc.gridy],gbc);
				undo.setEnabled(true);
			}
			if (e.getKeyCode()==KeyEvent.VK_LEFT && current.getHei()==1 && current.getCol()!=0 && data[current.getCol()-1][current.getRow()]==0){
				levels.add(new Level(data,components, bestTime,components.indexOf(current)));
				data[current.getCol()+current.getWid()-1][current.getRow()] = 0;
				data[current.getCol()-1][current.getRow()] = current.getVal()+1;
				buttons.remove(board[current.getCol()-1][current.getRow()]);
				gbc.gridx = current.getCol()-1;
				current.setCol(current.getCol()-1);
				gbc.gridy = current.getRow();
				gbc.gridwidth = current.getWid();
				gbc.gridheight = 1;
				buttons.add(current,gbc);
				gbc.gridx = current.getCol()+current.getWid();
				gbc.gridy = current.getRow();
				gbc.gridwidth = 1;
				gbc.gridheight = 1;
				buttons.add(board[gbc.gridx][gbc.gridy],gbc);
			}
			if (e.getKeyCode()==KeyEvent.VK_UP && current.getWid()==1 && current.getRow()!=0 && data[current.getCol()][current.getRow()-1]==0){
				levels.add(new Level(data,components,bestTime,components.indexOf(current)));
				data[current.getCol()][current.getRow()+current.getHei()-1] = 0;
				data[current.getCol()][current.getRow()-1] = current.getVal()+1;
				buttons.remove(board[current.getCol()][current.getRow()-1]);
				gbc.gridx = current.getCol();
				gbc.gridy = current.getRow()-1;
				current.setRow(current.getRow()-1);
				gbc.gridwidth = 1;
				gbc.gridheight = current.getHei();
				buttons.add(current,gbc);
				gbc.gridx = current.getCol();
				gbc.gridy = current.getRow()+current.getHei();
				gbc.gridwidth = 1;
				gbc.gridheight = 1;
				buttons.add(board[gbc.gridx][gbc.gridy],gbc);
				undo.setEnabled(true);
			}
			if (e.getKeyCode()==KeyEvent.VK_DOWN && current.getWid()==1 && current.getRow()+current.getHei()<6 && data[current.getCol()][current.getRow()+current.getHei()]==0){
				levels.add(new Level(data,components,bestTime,components.indexOf(current)));
				data[current.getCol()][current.getRow()] = 0;
				data[current.getCol()][current.getRow()+current.getHei()] = current.getVal()+1;
				buttons.remove(board[current.getCol()][current.getRow()+current.getHei()]);
				gbc.gridx = current.getCol();
				gbc.gridy = current.getRow()+1;
				current.setRow(current.getRow()+1);
				gbc.gridwidth = 1;
				gbc.gridheight = current.getHei();
				buttons.add(current,gbc);
				gbc.gridx = current.getCol();
				gbc.gridy = current.getRow()-1;
				gbc.gridwidth = 1;
				gbc.gridheight = 1;
				buttons.add(board[gbc.gridx][gbc.gridy],gbc);
				undo.setEnabled(true);
			}
			setVisible(true);
			gameOver();
		}
		catch(RuntimeException | IOException er){
			//Do nothing.
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * This method controls what happens when a user press on a game block.
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		current = (Block)e.getComponent();
		if (!current.isPressed()){
			for (Block temp : components){
				temp.setPressed(false);
				temp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			}
			current.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
			current.setPressed(true);
		}
		else{
			current.setBorder(BorderFactory.createEmptyBorder());
			current.setPressed(false);
			current = null;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * This method controls what happens if a user clicks on a button.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == undo){
			if (!levels.isEmpty()){	
				Level l = levels.pop();
				try {
					int i =components.indexOf(current);
					GameWindow gw = new GameWindow(l,levels,levelNum,clock);
					dispose();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
			else{
				
			}
		}
		else if (arg0.getSource() == back){
			MainMenu mm = new MainMenu();
			dispose();
		}
	}
	
}
