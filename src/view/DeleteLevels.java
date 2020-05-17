package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.ListOfLevels;

/**
 * This class represents the frame that lets the user delete existing levels.
 * @author sahsah1
 */
public class DeleteLevels extends JFrame implements ActionListener {
	
	JPanel list = new JPanel();
	ListOfLevels levels = new ListOfLevels();
	GridBagConstraints gbc = new GridBagConstraints();
	JScrollPane scroll = new JScrollPane(list);
	JButton del;
	JButton current;
	
	/**
	 * This frame's constructor.
	 * @throws FileNotFoundException
	 */
	public DeleteLevels() throws FileNotFoundException{
		super ("Delete a Level");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(600, 800);
		setLocation(500, 0);
		list.setBackground(Color.LIGHT_GRAY);
		setResizable(false);
		list.setLayout(new GridBagLayout());
		levels.loadFromFile("Levels.json");
		gbc.insets = new Insets(20, 20, 20, 20);
		gbc.fill = GridBagConstraints.BOTH;
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
			level.setBackground(Color.ORANGE);

			gbc.gridx = 0;
			gbc.gridy = i-1;
			list.add(level, gbc);
			gbc.gridx = 1;
			gbc.gridy = i-1;
			list.add(best, gbc);
			level.addActionListener(this);
		}
		del = new JButton("Delete Level!");
		del.setFont(new Font("Serif", Font.BOLD, 30));
		del.setBackground(Color.RED);
		del.setFocusable(false);
		del.addActionListener(this);
		gbc.gridx = 1;
		gbc.gridy = 100;
		list.add(del,gbc);
		if (levels.getLevels().size() == 0){
			list.remove(del);
			JLabel empty = new JLabel("There are no Levels available !");
			empty.setFont(new Font("Serif", Font.BOLD, 30));
			list.add(empty);
		}
		
		add(scroll);
		setVisible(true);
	}
	
	/**
	 * This method controls the actions of button clicking.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==del){
			if (current != null){
				int i = Integer.parseInt(current.getText().substring(6));
				levels.getLevels().remove(i-1);
				try {
					levels.saveToJsonFile("Levels.json");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				try {
					DeleteLevels dl = new DeleteLevels();
					dispose();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		}
		else{
			if (current != null){
				current.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			}
			current = (JButton)e.getSource();
			((JButton)e.getSource()).setBorder(BorderFactory.createLineBorder(Color.RED, 3));
		}
	}
}
