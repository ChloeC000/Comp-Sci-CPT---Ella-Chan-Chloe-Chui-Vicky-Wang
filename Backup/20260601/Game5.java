import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class Game5 extends JPanel{
	// Properties
	BufferedImage imgBackground = null;
	BufferedImage imgWheel = null;
	String strBeat = "";
	String strExample = "e.g. 1, 2, 1 = Press within 1 sec, wait for 1 sec, PressPress within 1 sec, wait for 1 sec, Press within 1 sec.";
	String strResult = "";
	int intPosX;
	int intPosY;
	boolean blnShowWheel;
	MainGame mainGame;

	// Methods
	// Paint the screen
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		// Create the screen
		g.setColor(Color.WHITE);
		g.drawImage(imgBackground, 0, 0, null);
		if (blnShowWheel == true){
			g.drawImage(imgWheel, 0, 0, null);
		}		
		g.setFont(new Font("Arial", Font.BOLD, 30));  
		g.drawString(strBeat, 10, intPosY);
		g.setFont(new Font("Arial", Font.BOLD, 18));  
		g.drawString(strExample, 10, intPosY + 30);
		g.setFont(new Font("Arial", Font.BOLD, 30));  
		g.drawString(strResult, 10, 600);
	}
	
	// Constructor
	public Game5(){
		super();		
		intPosY = 200;
		try{
			imgBackground = ImageIO.read(new File("./Images/DJPanel.png"));
			imgWheel = ImageIO.read(new File("./Images/DJPanel2.png"));
		}catch(IOException e){
			System.out.println("Unable to DJ Panel image");
		}	
	}
}
