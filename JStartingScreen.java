/* 
Program: Draw Starting Screen 
Created By: Ella Chan, Chloe Chui, Vicky Wang
Date: May 20 2026
Version: 1.0

Draw the images required for the starting screen
 */
 
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

public class JStartingScreen extends JPanel implements ActionListener{
	//Properties
	BufferedImage imgStartScreen = null;
	JButton btnJoin = new JButton("Join");
	JButton btnHost = new JButton("Host");
	SuperSocketMaster ssm = null;
	String strLaptopName = "";
	
	//Methods
	public void paintComponent(Graphics g){
        super.paintComponent(g);
			//Draw Background
			g.drawImage(imgStartScreen, 0, 0, null);
    }
    
public void actionPerformed(ActionEvent evt){
		if(evt.getSource() == btnHost){
			System.out.println("Creating game...");
			
			// Host uses only the port number
			ssm = new SuperSocketMaster(6112, this);
			ssm.connect();
			
			System.out.println("Game hosted on localhost"+ strLaptopName);
			
			
		}else if(evt.getSource() == btnJoin){
			System.out.println(strLaptopName +"Looking for lobby...");
			
			// Localhost means the same computer
			String strIpAddress = "127.0.0.1";
			
			// Join uses the IP address and the port number
			ssm = new SuperSocketMaster(strIpAddress, 6112, this);
			ssm.connect();
			
			System.out.println(strLaptopName + "Joined game on localhost.");
		}
	}
    
    //Constructor
    public JStartingScreen(){
		super();
		this.setLayout(null);

		strLaptopName = System.getenv("COMPUTERNAME");
	
	if(strLaptopName == null){
		strLaptopName = System.getenv("HOSTNAME");
	}
	
	if(strLaptopName == null){
		strLaptopName = "Unknown Computer";
	}
		
		// Join button from the bottom right of the sketch
		btnJoin.setBounds(1010, 590, 210, 65);
		btnJoin.addActionListener(this);
		this.add(btnJoin);
		
		// Host button from the middle right of the sketch
		btnHost.setBounds(1010, 480, 210, 65);
		btnHost.addActionListener(this);
		this.add(btnHost);
		
		try{
			imgStartScreen = ImageIO.read(new File("images/Starting Screen.png"));
		}catch(IOException e){
			System.out.println("Unable to load image");
		}
	}
}
