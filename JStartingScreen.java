/* 
Program: Draw Starting Screen 
Created By: Ella Chan, Chloe Chui, Vicky Wang
Date: May 20 2026
Version: 1.0

Draw the images required for the starting screen
 */
 
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;

public class JStartingScreen extends JPanel{
	//Properties
	BufferedImage imgStartScreen = null;
	
	//Methods
	public void paintComponent(Graphics g){
        super.paintComponent(g);
			//Draw Background
			g.drawImage(imgStartScreen, 0, 0, null);
    }
    
    //Constructor
    public JStartingScreen(){
		super();
		try{
			imgStartScreen = ImageIO.read(new File("images/Starting Screen.png"));
		}catch(IOException e){
			System.out.println("Unable to load image");
		}
	}
}
