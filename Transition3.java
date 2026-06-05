import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class Transition3 extends JPanel{
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
	}
	
	// Constructor
	public Transition3(){
		super();		
		try{			
			imgBackground = ImageIO.read(new File("./Images/Path3Transition.png"));
			imgDialogBox1 = ImageIO.read(new File("./Images/Path3TranDialog1.png"));
			imgDialogBox2 = ImageIO.read(new File("./Images/Path3TranDialog2.png"));
		}catch(IOException e){
			System.out.println("Unable to transitiona image.");
		}	
	}
}
