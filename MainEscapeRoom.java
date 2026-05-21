/* 
Program: Main Escape Room - Controller 
Created By: Ella Chan, Chloe Chui, Vicky Wang
Date: May 20 2026
Version: 1.0

Draw the images required for the starting screen
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class MainEscapeRoom implements ActionListener{
	JFrame theFrame = new JFrame("Escape Room");
	JStartingScreen thePanel = new JStartingScreen();
	Timer theTimer = new Timer(1000/60, this);
	
	public void actionPerformed (ActionEvent evt){
		thePanel.repaint();
	}
	
	public MainEscapeRoom(){
		thePanel.setLayout(null);
        thePanel.setPreferredSize(new Dimension(1280, 720));

        theFrame.setContentPane(thePanel);
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theFrame.pack();
        theFrame.setVisible(true);
        theFrame.setResizable(false);
        theTimer.start();
	}
	
	public static void main(String[] args){
		new MainEscapeRoom();
	}

}
