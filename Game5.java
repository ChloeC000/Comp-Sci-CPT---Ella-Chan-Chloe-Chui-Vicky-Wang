/*
Course: ICS4U1b Computer Science
Teacher: Mr. Alfred Ron Cadawas
Memebers: Chloe Chui, Ella Chan, Vicky Wang
Assignment Name: CPT

This is the Game 5 (beat) View.
*/

// Import the IO, swing and JComponents libaraies
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class Game5 extends JPanel{
	// Properties
	// Create the DJ panel, dialog box, and the background images
	BufferedImage imgBackground = null;
	BufferedImage imgWheel = null;
	BufferedImage imgDialogBox = null;
	// Store the beat, example message and the player's result
	String strBeat = "";
	String strExample = "e.g. 1, 2, 1 = Press within 1 sec, wait for 1 sec, PressPress within 1 sec, wait for 1 sec, Press within 1 sec.";
	String strResult = "";
	// x, y coordination for the animation
	int intPosX;
	int intPosY;
	// Show the DJ panel with different lights
	boolean blnShowWheel;

	// Methods
	// Paint the screen
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		// Create the screen
		g.setColor(Color.WHITE);
		g.drawImage(imgBackground, 0, 0, null);
		// Show the DJ panel with different lights when the player clicks
		if (blnShowWheel == true){
			g.drawImage(imgWheel, 0, 0, null);
		}
		// Show the beats, example, and the result
		g.setFont(new Font("Arial", Font.BOLD, 30));  
		g.drawString(strBeat, 10, intPosY);
		g.setFont(new Font("Arial", Font.BOLD, 18));  
		g.drawString(strExample, 10, intPosY + 30);
		g.setFont(new Font("Arial", Font.BOLD, 30));  
		if (strResult != "") {
			g.drawImage(imgDialogBox, 300, 300, null);
			g.drawString(strResult, 410, 390);
		}
	}
	
	// Constructor
	public Game5(){
		super();		
		// initialize the animationn starting x position
		intPosY = 200;		
		// Load the DJ panel and background images
		try{
			imgDialogBox = ImageIO.read(new File("./Images/Game5Dialog.png"));
			imgBackground = ImageIO.read(new File("./Images/DJPanel.png"));
			imgWheel = ImageIO.read(new File("./Images/DJPanel2.png"));
		}catch(IOException e){
			System.out.println("Unable to DJ Panel image");
		}	
	}
}
