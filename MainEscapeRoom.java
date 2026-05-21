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
	Timer theTimer = new Timer(1000/60, this);
	
	//Starting Panel
	
	//Introduction Panel
	
	public void actionPerformed (ActionEvent evt){
		
	}
	
	public MainEscapeRoom(){
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
