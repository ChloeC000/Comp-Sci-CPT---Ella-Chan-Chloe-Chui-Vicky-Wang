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
	//Properties
	JFrame theFrame = new JFrame("Escape Room");
	Timer theTimer = new Timer(1000/60, this);
	
	//Network Connection
	NetPanel ConnectionPanel = new NetPanel();
	
	//Introduction Panel

	Path1 Panel1 = new Path1();
	Path2Panel Panel2 = new Path2Panel();
	
	//Methods
	public void actionPerformed (ActionEvent evt){
		
	}
	
	
	//Constructor
	public MainEscapeRoom(){
		
		theFrame.setContentPane(Panel2);
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theFrame.pack();
        theFrame.setVisible(true);
        theFrame.setResizable(false);
        theTimer.start();
	}
	
	
	
	//Main program
	public static void main(String[] args){
		new MainEscapeRoom();
	}

}
