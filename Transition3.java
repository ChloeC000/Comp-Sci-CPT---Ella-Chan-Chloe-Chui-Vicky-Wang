/*
Course: ICS4U1b Computer Science
Teacher: Mr. Alfred Ron Cadawas
Memebers: Chloe Chui, Ella Chan, Vicky Wang
Assignment Name: CPT

This is the Path 3 transition View.
*/

// Import the IO, swing and JComponents libaraies
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class Transition3 extends JPanel{
	// Properties
	// Create the transition screen and the 2 animated dialog boxes
	BufferedImage imgBackground = null;
	BufferedImage imgDialogBox1 = null;	
	BufferedImage imgDialogBox2 = null;	
	// The x postions of the 2 dialog boxes
	int intPos1X = -85;
	int intPos2X = 950;
	// Show the transition within the preset period 
	int intTranTime = 0;

	// Methods
	// Paint the screen
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		// Create the transition screen and the 2 animated dialog boxes
		g.drawImage(imgBackground, 0, 0, null);
		g.drawImage(imgDialogBox1, intPos1X, 100, null);
		g.drawImage(imgDialogBox2, intPos2X, 150, null);
	}
	
	// Constructor
	public Transition3(){
		super();		
		try{			
			// Create the transition screen and the 2 animated dialog boxes
			imgBackground = ImageIO.read(new File("./Images/Path3Transition.png"));
			imgDialogBox1 = ImageIO.read(new File("./Images/Path3TranDialog1.png"));
			imgDialogBox2 = ImageIO.read(new File("./Images/Path3TranDialog2.png"));
		}catch(IOException e){
			System.out.println("Unable to transitiona image.");
		}	
	}
}
