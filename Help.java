/*
Course: ICS4U1b Computer Science
Teacher: Mr. Alfred Ron Cadawas
Memebers: Chloe Chui, Ella Chan, Vicky Wang
Assignment Name: CPT

This is the Help page View.
*/

// Import the IO, swing and JComponents libaraies
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class Help extends JPanel {
	// Properties
	// Load the help page image
	BufferedImage imgBackground = null;
	// Create the Back button
	JButton BackBut = new JButton(new ImageIcon("./images/BackButton.png"));

	// Methods
	// Paint the screen
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		// Create the screen
		g.setColor(Color.WHITE);
		g.drawImage(imgBackground, 0, 0, null);
		g.setFont(new Font("Arial", Font.BOLD, 30));  
	}
	public void ShowHelp(int intPageIndex){
		try{
			// Show the correct help page based on the help page index handled by the Contoller (MainGame.Java)
			if (intPageIndex == 0) {
				// Show the network message help
				imgBackground = ImageIO.read(new File("./Images/MessageHelp.png"));
			} else if (intPageIndex == 1) {
				// Show the path 1 help
				imgBackground = ImageIO.read(new File("./Images/Path1Help.png"));
			} else if (intPageIndex == 2) {
				// Show the path 2 help
				imgBackground = ImageIO.read(new File("./Images/Path2Help.png"));
			} else if (intPageIndex == 3) {
				// Show the path 3 help
				imgBackground = ImageIO.read(new File("./Images/Path3Help.png"));
			} else if (intPageIndex == 4) {
				// Show the Game 1 (card game) help
				imgBackground = ImageIO.read(new File("./Images/Game1Help.png"));
			} else if (intPageIndex == 5) {
				// Show the Game 2 (direction) help
				imgBackground = ImageIO.read(new File("./Images/Game2Help.png"));
			} else if (intPageIndex == 8) {
				// Show the Game 5 (beat) help
				imgBackground = ImageIO.read(new File("./Images/Game5Help.png"));
			} else if (intPageIndex == 9) {
				// Show the Game 6 (puzzle) help
				imgBackground = ImageIO.read(new File("./Images/Game6Help.png"));
			}
		}catch(IOException e){
			System.out.println("Unable to Help page image");
		}		
	}	
	// Constructor
	public Help(){
		super();
		setLayout(null);
		// Create the Back button
        BackBut.setBounds(10, 10, 128, 50);
        BackBut.setContentAreaFilled(false);		//Make the button background invisible
		BackBut.setBorderPainted(false);
        BackBut.setVisible(true);
        add(BackBut);
	}
}
