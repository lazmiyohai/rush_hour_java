package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


import controller.Clock;
import controller.Level;
import model.ListOfLevels;

/**
 * This class represents the frame that shows up when a user completes a level.
 * @author sahsah1
 */
public class WinningFrame extends JFrame implements ActionListener {
	
	JPanel p = new JPanel(new GridBagLayout());
	GridBagConstraints gbc = new GridBagConstraints();
	JButton mainMenu;
	JButton chooseLev;
	JButton nextLevel;
	int bestTime;
	int currentTime;
	JLabel best;
	JLabel current;
	int currentLev;
	ListOfLevels list = new ListOfLevels();
	
	/**
	 * This is the parameter constructor of the frame.
	 * @param bestTime the best time for completion of this level.
	 * @param currentTime the current time for completion of this level.
	 * @param currenLev the index of this level in the list of levels.
	 * @throws FileNotFoundException
	 */
	public WinningFrame(int bestTime, int currentTime, int currenLev) throws FileNotFoundException{
		super ("Level Complete !");
		list.loadFromFile("Levels.json");
		p.setBackground(Color.BLUE);
		this.currentLev = currenLev;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation(400, 400);
		this.bestTime = bestTime;
		this.currentTime = currentTime;
		gbc.insets = new Insets(20, 20, 20, 20);
		setBackground(Color.BLACK);
		if (currentTime%60>9){
			current = new JLabel("You've completed this level in : 0"+currentTime/60+" : "+currentTime%60);
		}
		else if (currentTime%60<9){
			current = new JLabel("You've completed this level in : 0"+currentTime/60+" : "+"0"+currentTime%60);
		}
		current.setFont(new Font("Serif", Font.BOLD, 40));
		gbc.gridx = 0;
		gbc.gridy = 0;
		p.add(current, gbc);
		
		if (bestTime%60>9){
			best = new JLabel("Best time for this level is : 0"+bestTime/60+" : "+bestTime%60);
		}
		else if (bestTime%60<9){
			best = new JLabel("Best time for this level is : 0"+bestTime/60+" : "+"0"+bestTime%60);
		}
		best.setFont(new Font("Serif", Font.BOLD, 40));
		gbc.gridx = 0;
		gbc.gridy = 1;
		p.add(best, gbc);
		
		mainMenu = new JButton("Main Menu");
		mainMenu.addActionListener(this);
		mainMenu.setFont(new Font("Serif", Font.BOLD, 40));
		gbc.gridx = 1;
		gbc.gridy = 0;
		p.add(mainMenu, gbc);
		mainMenu.setFocusable(false);
		mainMenu.setBackground(Color.CYAN);
		
		chooseLev = new JButton("Choose another level");
		chooseLev.addActionListener(this);
		chooseLev.setFont(new Font("Serif", Font.BOLD, 40));
		gbc.gridx = 1;
		gbc.gridy = 1;
		p.add(chooseLev, gbc);
		chooseLev.setFocusable(false);
		chooseLev.setBackground(Color.GREEN);
		
		nextLevel = new JButton("Next Level");
		nextLevel.addActionListener(this);
		nextLevel.setFont(new Font("Serif", Font.BOLD, 40));
		gbc.gridx = 1;
		gbc.gridy = 2;
		p.add(nextLevel, gbc);
		nextLevel.setFocusable(false);
		nextLevel.setBackground(Color.ORANGE);
		
		add(p);
		setVisible(true);
		pack();
	}
	
	/**
	 * This method controls the clicks of a user on buttons.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == mainMenu){
			MainMenu mm = new MainMenu();
			dispose();
		}
		else if (arg0.getSource() == chooseLev){
			try {
				LevelChoosing lc = new LevelChoosing();
				dispose();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		else if (arg0.getSource() == nextLevel){
			try {
				if (currentLev<list.getLevels().size()-1){
					GameWindow gw = new GameWindow(list.getLevels().get(currentLev+1), new Stack<Level>(), currentLev+1, new Clock());
					dispose();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
}
