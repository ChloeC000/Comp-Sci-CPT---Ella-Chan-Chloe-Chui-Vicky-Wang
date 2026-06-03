import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class Intro extends JPanel {
	// Properties
	BufferedImage imgBackground = null;
	BufferedImage imgCursor = null;
	int intTitlePosY = -50;
	int intMessagePosY = 790;
	int intMouseX;
	int intMouseY;

	// Methods
	// Paint the screen
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		// Create the screen
		g.setColor(Color.WHITE);
		g.drawImage(imgBackground, 0, 0, null);
		g.setFont(new Font("Arial", Font.BOLD, 30));  
		g.drawString("Encroaching Mist", 300, intTitlePosY);
		g.drawString("Shoot the deer's body to start.", 10, intMessagePosY);		
		g.drawImage(imgCursor, intMouseX, intMouseY, null);
	}
	
	// Constructor
	public Intro(){
		super();
		try{
			imgBackground = ImageIO.read(new File("./Images/IntroBackground.png"));
			imgCursor = ImageIO.read(new File("./Images/Target Cursor.png"));
		}catch(IOException e){
			System.out.println("Unable to intro background image");
		}
	}
}
