/*
Course: ICS4U1b Computer Science
Teacher: Mr. Alfred Ron Cadawas
Memebers: Chloe Chui, Ella Chan, Vicky Wang
Assignment Name: CPT

This is the intro screen.
*/

// Import the IO, swing and JComponents libaraies
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class Intro extends JPanel {
	// Properties
	// Create the background and target cursor images
	BufferedImage imgBackground = null;
	BufferedImage imgCursor = null;
	// Use for the animation Y position
	int intTitlePosY = -50;
	int intMessagePosY = 790;
	// Get the mouse pointer location as the target cursor location
	int intMouseX;
	int intMouseY;

	// Methods
	// Paint the screen with the animated game name "Encroaching Mist" and the start instruction
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		// Create the screen		
		g.drawImage(imgBackground, 0, 0, null);
		g.setColor(Color.WHITE);
		// Show the game name and the intro page instruction
		g.setFont(new Font("Arial", Font.BOLD, 60));  
		g.drawString("Encroaching Mist", 220, intTitlePosY);
		g.setFont(new Font("Arial", Font.BOLD, 30)); 
		g.drawString("Shoot the deer's body to start.", 10, intMessagePosY);	
		// Make the target cursor on the screen	
		g.drawImage(imgCursor, intMouseX, intMouseY, null);
	}
	
	// Constructor
	public Intro(){
		super();
		// Load the background image and the target cursor
		try{
			imgBackground = ImageIO.read(new File("./Images/IntroBackground.png"));
			imgCursor = ImageIO.read(new File("./Images/Target Cursor.png"));
		}catch(IOException e){
			System.out.println("Unable to intro background image");
		}
	}
}
