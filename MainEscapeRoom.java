/* 
Program: Main Escape Room - Controller 
Created By: Ella Chan, Chloe Chui, Vicky Wang
Date: May 20 2026
Version: 1.0

Draw the images required for the starting screen
 */
import java.awt.event.*;
import javax.swing.*;

public class MainEscapeRoom implements ActionListener{
	//Properties
	JFrame theFrame = new JFrame("Escape Room");
	Timer theTimer = new Timer(1000/60, this);
	
	//Network Connection
	SuperSocketMaster ssm = null;
	
	//Introduction Panel
	IntroductionView IntroductionPanel = new IntroductionView();
	
	
	//Methods
	public void actionPerformed (ActionEvent evt){
		
	}
	
	
	//Constructor
	public MainEscapeRoom(){
		
		theFrame.setContentPane(IntroductionPanel.IntroPanel);
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
