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
import javax.swing.JScrollPane;

import controller.Clock;
import controller.Level;
import model.ListOfLevels;

/**
 * This class represents the frame that shows all the existing levels, and lets
 * the user choose which one he wants to play.
 * @author sahsah1
 */
public class LevelChoosing extends JFrame implements ActionListener {
	
	JPanel list = new JPanel();
	ListOfLevels levels = new ListOfLevels();
	GridBagConstraints gbc = new GridBagConstraints();
	JScrollPane scroll = new JScrollPane(list);
	
	/**
	 * This is the class constructor.
	 * @throws FileNotFoundException
	 */
	public LevelChoosing() throws FileNotFoundException{
		super("Choose a Level");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		list.setBackground(Color.GREEN);
		setSize(600, 800);
		setLocation(500, 0);
		setResizable(false);
		list.setLayout(new GridBagLayout());
		levels.loadFromFile("Levels.json");
		gbc.insets = new Insets(20, 20, 20, 20);
		if (levels.getLevels().size() == 0){
			JLabel empty = new JLabel("There are no Levels available !");
			empty.setFont(new Font("Serif", Font.BOLD, 30));
			list.add(empty);
		}
		for (int i=1;i<=levels.getLevels().size();i++){
			JButton level = new JButton("Level "+i);
			level.setFocusable(false);
			JLabel best = new JLabel();
			int j = levels.getLevels().get(i-1).getBestTime();
			if (j==1000000){
				best.setText("Best time is   : -- : --");
			}
			else {
				if (j%60>9){
					best.setText("Best time is   : 0"+j/60+" : "+j%60);
				}
				else{
					best.setText("Best time is   : 0"+j/60+" : "+"0"+j%60);
				}
			}
			best.setFont(new Font("Serif", Font.BOLD, 40));
			level.setFont(new Font("Serif", Font.BOLD, 40));
			gbc.gridx = 0;
			gbc.gridy = i-1;
			list.add(level, gbc);
			level.setBackground(Color.ORANGE);
			gbc.gridx = 1;
			gbc.gridy = i-1;
			list.add(best, gbc);
			best.setForeground(Color.RED);
			level.addActionListener(this);
		}
		
		add(scroll);
		setVisible(true);
	}
	
	/**
	 * This method controls what happens when a user clicks a button.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		int i = Integer.parseInt(((JButton)e.getSource()).getText().substring(6));
		Level temp = levels.getLevels().get(i-1);
		try {
			GameWindow gw = new GameWindow(temp, new Stack<Level>(), i-1, new Clock());
			dispose();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}
}
