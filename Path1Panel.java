//Path 1 View

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

public class Path1Panel extends JPanel{
	//Properties
	BufferedImage imgPath1Background = null;
	Path1Model Model1 = new Path1Model();
	
	//Objects
	JButton btnDeerBody = new JButton(new ImageIcon());
	
	//Methods
	//Draw Background image
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(imgPath1Background, 0, 0, null);
	}
	
	//Constructor
	public Path1Panel(){
		//Set panel parameters
		this.setLayout(null);
		this.setPreferredSize(new Dimension(1280, 720));
		
		//Load array
		Model1.loadArray();
		
		//Load background
		try{
			imgPath1Background = ImageIO.read(new File("images/Path1 Background.png"));
		}catch(IOException e){
			System.out.println("Unable to load image");
		}
	}
}
