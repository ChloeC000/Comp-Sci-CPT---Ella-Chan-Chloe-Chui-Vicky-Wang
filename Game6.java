/*
Course: ICS4U1b Computer Science
Teacher: Mr. Alfred Ron Cadawas
Memebers: Chloe Chui, Ella Chan, Vicky Wang
Assignment Name: CPT

This is the Game 6 (puzzle) View.
*/

// Import the IO, swing and JComponents libaraies
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class Game6 extends JPanel{
	// Properties
	// Create the 4 big and small pieces of newspaper and the background images
	BufferedImage imgBackground = null;
	BufferedImage imgDialogBox = null;
	BufferedImage imgPaperSmall1 = null;
	BufferedImage imgPaperSmall2 = null;
	BufferedImage imgPaperSmall3 = null;
	BufferedImage imgPaperSmall4 = null;
	BufferedImage imgPaper1 = null;
	BufferedImage imgPaper2 = null;
	BufferedImage imgPaper3 = null;
	BufferedImage imgPaper4 = null;
	// Store the x, y coordination of the 4 pieces of newspaper
	int intPaper1X = 800;
	int intPaper1Y = 510;
	int intPaper2X = 800;
	int intPaper2Y = 270;
	int intPaper3X = 800;
	int intPaper3Y = 150;
	int intPaper4X = 800;
	int intPaper4Y = 390;
	// Show the big newspaper or not
	boolean blnShowPaper1 = false;
	boolean blnShowPaper2 = false;
	boolean blnShowPaper3 = false;
	boolean blnShowPaper4 = false;
	String strMessage = "";

	// Methods
	// Paint the screen
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		// Create the screen
		g.drawImage(imgBackground, 0, 0, null);
		g.setColor(Color.RED);
		g.drawRect(50, 90, 325, 488);
		if (blnShowPaper1 == true){
			// Show the first big piece of newspaper
			g.drawImage(imgPaper1, 50, 90, null);
		} else {
			// Show the first small piece of newspaper
			g.drawImage(imgPaperSmall1, intPaper1X, intPaper1Y, null);
		}		
		if (blnShowPaper2 == true){
			// Show the second big piece of newspaper
			g.drawImage(imgPaper2, 196, 90, null);
		} else {
			// Show the second small piece of newspaper
			g.drawImage(imgPaperSmall2, intPaper2X, intPaper2Y, null);
		}
		if (blnShowPaper3 == true){
			// Show the third big piece of newspaper
			g.drawImage(imgPaper3, 50, 321, null);
		} else {
			// Show the third small piece of newspaper
			g.drawImage(imgPaperSmall3, intPaper3X, intPaper3Y, null);
		}
		if (blnShowPaper4 == true){
			g.drawImage(imgPaper4, 188, 358, null);
			// Show the forth big piece of newspaper
		} else {
			// Show the forth small piece of newspaper
			g.drawImage(imgPaperSmall4, intPaper4X, intPaper4Y, null);
		}
		// Display the game instruction
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 30));  
		g.drawString("Put the four pieces of newpaper back together.", 10, 50);
		g.setFont(new Font("Arial", Font.BOLD, 22));  
		g.drawString(strMessage, 10, 80);
		if (blnShowPaper1 == true && blnShowPaper2 == true && blnShowPaper3 == true && blnShowPaper4 == true){
			// Show the close button if the 4 pieces of newspaper are all in the correct position
			g.drawImage(imgDialogBox, 300, 290, null);
		}
	}
	
	// Constructor
	public Game6(){
		super();
		try{
			// Load the background image and the result dialog box
			imgBackground = ImageIO.read(new File("./Images/Game6Background.png"));
			imgDialogBox = ImageIO.read(new File("./Images/Game6Dialog.png"));
			// Load the small newspaper images
			imgPaperSmall1 = ImageIO.read(new File("./Images/Paper1Small.png"));
			imgPaperSmall2 = ImageIO.read(new File("./Images/Paper2Small.png"));
			imgPaperSmall3 = ImageIO.read(new File("./Images/Paper3Small.png"));
			imgPaperSmall4 = ImageIO.read(new File("./Images/Paper4Small.png"));
			// Load the big newspaper images
			imgPaper1 = ImageIO.read(new File("./Images/Paper1.png"));
			imgPaper2 = ImageIO.read(new File("./Images/Paper2.png"));
			imgPaper3 = ImageIO.read(new File("./Images/Paper3.png"));
			imgPaper4 = ImageIO.read(new File("./Images/Paper4.png"));
		}catch(IOException e){
			System.out.println("Unable to Game 6 Background image.");
		}
	}
}
