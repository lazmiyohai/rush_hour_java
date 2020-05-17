package view;

import java.awt.Color;
import com.google.gson.Gson;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This class represents a frame that channels you either to the creation frame, or to
 * the delete levels frame.
 * @author sahsah1
 */
public class LevelEdit extends JFrame implements ActionListener {

	JPanel p = new JPanel(new GridBagLayout());
	GridBagConstraints gbc = new GridBagConstraints();
	JButton add;
	JButton del;
	
	/**
	 * This frame's constructor.
	 */
	public LevelEdit() {
		super ("Level Editing");
		setResizable(false);
		setSize(600, 600);
		setLocation(550, 250);
		p.setBackground(Color.YELLOW);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		gbc.insets = new Insets(20, 20, 20, 20);
		gbc.fill = GridBagConstraints.BOTH;
		
		add = new JButton("Create Level");
		add.setFocusable(false);
		add.setFont(new Font("Serif", Font.BOLD, 30));
		gbc.gridx = 0;
		gbc.gridy = 0;
		p.add(add, gbc);
		add.addActionListener(this);
		add.setBackground(Color.blue);
		
		del = new JButton("Delete Levels");
		del.setFocusable(false);
		del.setFont(new Font("Serif", Font.BOLD, 30));
		gbc.gridx = 0;
		gbc.gridy = 1;
		p.add(del, gbc);
		del.addActionListener(this);
		del.setBackground(Color.LIGHT_GRAY);
		
		add(p);
		setVisible(true);
	}
	/**
	 * This method controls what happens when a user clicks a button.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == add){
			try {
				CreateLevel cl = new CreateLevel();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			dispose();
		}
		else if (e.getSource() == del){
			try {
				DeleteLevels dl = new DeleteLevels();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			dispose();
		}
		
	}

}
