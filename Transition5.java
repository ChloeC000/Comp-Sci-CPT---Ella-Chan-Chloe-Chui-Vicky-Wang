/*
Course: ICS4U1b Computer Science
Teacher: Mr. Alfred Ron Cadawas
Memebers: Chloe Chui, Ella Chan, Vicky Wang
Assignment Name: CPT

This is the ending transition View.
*/

// Import the IO, swing and JComponents libaraies
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class Transition5 extends JPanel{
	// Properties
	// Create the transition screen and the 2 animated dialog boxes
	BufferedImage imgBackground = null;
	BufferedImage imgDialogBox1 = null;	
	BufferedImage imgDialogBox2 = null;	
	// The x postions of the 2 dialog boxes
	int intPos1X = -660;
	int intPos2X = 963;
	// Show the transition within the preset period 
	int intTranTime = 0;

	// Methods
	// Paint the screen
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		// Create the screen
		// Create the transition screen and the 2 animated dialog boxes
		g.drawImage(imgBackground, 0, 0, null);
		g.drawImage(imgDialogBox1, intPos1X, 150, null);
		g.drawImage(imgDialogBox2, intPos2X, 180, null);
		g.setFont(new Font("Arial", Font.BOLD, 30));  
		// Display the CPT info
		g.drawString("ICS4U1b Computer Science CPT", intPos1X + 50, 500);
		g.drawString("Teacher: Mr. Cadawas", intPos1X + 50, 550);
		g.drawString("Members: Chloe Chui, Ella Chan, Vicky Wang", intPos1X + 50, 600);		
	}
	
	// Constructor
	public Transition5(){
		super();		
		try{			
			// Create the transition screen and the 2 animated dialog boxes
			imgBackground = ImageIO.read(new File("./Images/GameOverTransition.png"));
			imgDialogBox1 = ImageIO.read(new File("./Images/GameOverTranDialog1.png"));
			imgDialogBox2 = ImageIO.read(new File("./Images/GameOverTranDialog2.png"));
		}catch(IOException e){
			System.out.println("Unable to transitiona image.");
		}	
	}
}
