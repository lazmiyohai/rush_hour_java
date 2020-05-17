package controller;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.Timer;

/**
 * This class represents the timer that indicates the seconds that have passed since
 * the game started.
 * @author sahsah1
 */
public class Clock extends JLabel implements ActionListener {
	private Timer timer;
	private int secondsPassed = 0;
	private int minutesPassed = 0;
	private String display = "";
	private final int delay = 1000;
	
	/**
	 * This class' constructor.
	 */
	public Clock (){
		setFont(new Font("Serif", Font.BOLD, 50));
		display = "00 : 00";
		setText(display);
		timer = new Timer(delay, this);
	}

	/**
	 * This method indicates what will the clock display after each second that have passed.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		secondsPassed = secondsPassed+1;
		if (secondsPassed<9){
			display = display.substring(0, 6)+secondsPassed;
		}
		if (secondsPassed>9){
			display = display.substring(0, 5)+secondsPassed;
		}
		if (secondsPassed>59){
			secondsPassed=0;
			minutesPassed++;
			display = display.substring(0, 1)+minutesPassed+" : 0"+secondsPassed;
		}
		setText(display);
	}
	
	public Timer getTimer(){
		return timer;
	}
	
	public int timePassed(){
		return 60*minutesPassed+secondsPassed;
	}
	
}
