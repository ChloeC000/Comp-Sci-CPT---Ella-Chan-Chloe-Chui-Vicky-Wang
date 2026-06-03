import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class Game6 extends JPanel{
	// Properties
	BufferedImage imgBackground = null;
	BufferedImage imgPaperSmall1 = null;
	BufferedImage imgPaperSmall2 = null;
	BufferedImage imgPaperSmall3 = null;
	BufferedImage imgPaperSmall4 = null;
	BufferedImage imgPaper1 = null;
	BufferedImage imgPaper2 = null;
	BufferedImage imgPaper3 = null;
	BufferedImage imgPaper4 = null;
	int intPaper1X = 800;
	int intPaper1Y = 510;
	int intPaper2X = 800;
	int intPaper2Y = 270;
	int intPaper3X = 800;
	int intPaper3Y = 150;
	int intPaper4X = 800;
	int intPaper4Y = 390;
	boolean blnShowPaper1 = false;
	boolean blnShowPaper2 = false;
	boolean blnShowPaper3 = false;
	boolean blnShowPaper4 = false;

	// Methods
	// Paint the screen
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		// Create the screen
		g.drawImage(imgBackground, 0, 0, null);
		g.setColor(Color.RED);
		g.drawRect(50, 90, 325, 488);
		if (blnShowPaper1 == true){
			g.drawImage(imgPaper1, 50, 90, null);
		} else {
			g.drawImage(imgPaperSmall1, intPaper1X, intPaper1Y, null);
		}		
		if (blnShowPaper2 == true){
			g.drawImage(imgPaper2, 196, 90, null);
		} else {
			g.drawImage(imgPaperSmall2, intPaper2X, intPaper2Y, null);
		}
		if (blnShowPaper3 == true){
			g.drawImage(imgPaper3, 50, 321, null);
		} else {
			g.drawImage(imgPaperSmall3, intPaper3X, intPaper3Y, null);
		}
		if (blnShowPaper4 == true){
			g.drawImage(imgPaper4, 188, 358, null);
		} else {
			g.drawImage(imgPaperSmall4, intPaper4X, intPaper4Y, null);
		}
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 30));  
		g.drawString("Put the four pieces of newpaper back together.", 10, 50);
		if (blnShowPaper1 == true && blnShowPaper2 == true && blnShowPaper3 == true && blnShowPaper4 == true){
			g.drawString("You have got the new clue.", 410, 280);
		}
	}
	
	// Constructor
	public Game6(){
		super();
		try{
			imgBackground = ImageIO.read(new File("./Images/Game2Background.png"));
			imgPaperSmall1 = ImageIO.read(new File("./Images/Paper1Small.png"));
			imgPaperSmall2 = ImageIO.read(new File("./Images/Paper2Small.png"));
			imgPaperSmall3 = ImageIO.read(new File("./Images/Paper3Small.png"));
			imgPaperSmall4 = ImageIO.read(new File("./Images/Paper4Small.png"));
			imgPaper1 = ImageIO.read(new File("./Images/Paper1.png"));
			imgPaper2 = ImageIO.read(new File("./Images/Paper2.png"));
			imgPaper3 = ImageIO.read(new File("./Images/Paper3.png"));
			imgPaper4 = ImageIO.read(new File("./Images/Paper4.png"));
		}catch(IOException e){
			System.out.println("Unable to Game2 Background image.");
		}
	}
}
