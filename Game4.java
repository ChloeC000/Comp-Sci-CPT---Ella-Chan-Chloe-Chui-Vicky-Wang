/*
Course: ICS4U1b Computer Science
Teacher: Mr. Alfred Ron Cadawas
Memebers: Chloe Chui, Ella Chan, Vicky Wang
Assignment Name: CPT

This is the Game 4 (riddle) View.
*/

// Import the IO, swing and JComponents libaraies
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class Game4 extends JPanel {
	// Properties
	BufferedImage imgBackground = null;
	// Store the riddle, result and the options
	String strRiddle = "";
	String strResult = "";
	String[] strChoice = {"", "", "", ""};
	// For the animation x position effect
	int intPosX;
	// Create radio buttons in array for the riddle options
	JRadioButton[] radioChoice = new JRadioButton[4];
	// Create the close button
	JButton CloseBut = new JButton(new ImageIcon("./images/Exit Button.png"));

	// Methods
	// Paint the screen
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		// Create the screen
		g.setColor(Color.WHITE);
		g.drawImage(imgBackground, 0, 0, null);
		g.setFont(new Font("Arial", Font.BOLD, 30));  
		g.drawString(strRiddle, intPosX, 220);		
		g.drawString(strResult, 100, 500);
	}
	public void Init(){
		// Initialize the ratio buttons
		ButtonGroup rbGroup = new ButtonGroup();
		int x = 100;
        int y = 280;
        setFont(new Font("Arial", Font.BOLD, 30)); 
        // The Controller links with this View and assign the riddle options to the radio buttons
		for (int i = 0; i < 4; i++) {
            radioChoice[i] = new JRadioButton(strChoice[i]);
            radioChoice[i].setBounds(150, y + (i * 35), 500, 35);
            radioChoice[i].setForeground(Color.WHITE);
            radioChoice[i].setFont(new Font("Arial", Font.PLAIN, 30));
            radioChoice[i].setOpaque(false);
            rbGroup.add(radioChoice[i]);
            add(radioChoice[i]);
        }
	}
	
	// Constructor
	public Game4(){
		super();		
		setLayout(null);		
		
		// Create the close button
		CloseBut.setBounds(800, 210, 32, 32);
		CloseBut.setContentAreaFilled(false);		//Make the button background invisible
		CloseBut.setBorderPainted(false);
		add(CloseBut);
		
		// Init the question x position
		intPosX = -200;
		// Create the game background image
		try{
			imgBackground = ImageIO.read(new File("./Images/Game4Background.png"));
		}catch(IOException e){
			System.out.println("Unable to game 4 background image");
		}
	}
}
