import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class Transition4 extends JPanel{
	// Properties
	BufferedImage imgBackground = null;
	BufferedImage imgDialogBox1 = null;	
	BufferedImage imgDialogBox2 = null;	
	int intPos1X = -80;
	int intPos2X = 960;
	int intTranTime = 0;

	// Methods
	// Paint the screen
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		// Create the screen
		g.drawImage(imgBackground, 0, 0, null);
		g.drawImage(imgDialogBox1, intPos1X, 100, null);
		g.drawImage(imgDialogBox2, intPos2X, 150, null);
		g.setFont(new Font("Arial", Font.BOLD, 30));  
		g.drawString("ICS4U1b Computer Science CPT", intPos1X + 50, 500);
		g.drawString("Teacher: Mr. Cadawas", intPos1X + 50, 550);
		g.drawString("Memebers: Chloe Chui, Ella Chan, Vicky Wang", intPos1X + 50, 600);		
	}
	
	// Constructor
	public Transition4(){
		super();		
		try{			
			imgBackground = ImageIO.read(new File("./Images/EndingTransition.png"));
			imgDialogBox1 = ImageIO.read(new File("./Images/EndTranDialog1.png"));
			imgDialogBox2 = ImageIO.read(new File("./Images/EndTranDialog2.png"));
		}catch(IOException e){
			System.out.println("Unable to transitiona image.");
		}	
	}
}
