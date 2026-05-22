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
import java.awt.event.*;

public class StartingView extends JPanel implements ActionListener{
	
	//Properties
	BufferedImage imgStartScreen = null;	//Draw Background
	
	JButton btnJoin = new JButton("Join");
	JButton btnHost = new JButton("Host");
	JButton btnHelp = new JButton("Help");
	SuperSocketMaster ssm = null;
	Timer theTimer = new Timer(1000/60, this);
	
	//The chat box
	JTextField ChatInput = new JTextField("For Joining: Input host address");
	JTextArea ChatArea = new JTextArea();
	JScrollPane ChatScroll = new JScrollPane(ChatArea);
	
	//To trigger other events
	boolean blnHostConnected = false;
	boolean blnClientConnected = false;
	
	//Button to move on from this screen
	JButton btnConnect = new JButton("Connect");
	
	//Methods
	public void paintComponent(Graphics g){
        super.paintComponent(g);
			//Draw Background
			g.drawImage(imgStartScreen, 0, 0, null);
    }
    
    public void actionPerformed(ActionEvent evt){
		// Host/Join buttons
		if(evt.getSource() == btnHost){
			System.out.println("Creating game...");
			
			// Host uses only the port number
			ssm = new SuperSocketMaster(6112, this);
			ssm.connect();
			
			btnJoin.setText(ssm.getMyAddress());	// Show what the address of the host computer is
			blnHostConnected = true;				// Let code know that host has connected
			
			//Set host button to false
			btnHost.setEnabled(false);
			System.out.println("Address: "+ssm.getMyAddress());
			
		}else if(evt.getSource() == btnJoin){
			//Enable host button in case they change their mind
			btnHost.setEnabled(true);
			System.out.println("Looking for lobby...");
			
			//Get the IpAddress from the chat input
			String strIpAddress = ChatInput.getText();
			
			// Join uses the IP address and the port number
			ssm = new SuperSocketMaster(strIpAddress, 6112, this);
			ssm.connect();
			
			//Let code know that the client has connected
			blnClientConnected = true;
			System.out.println("Joined game on localhost.");
		}
		
		//Chat related action listeners
		if(evt.getSource() == ChatInput){
			System.out.println("Field event triggered");
			ssm.sendText(ChatInput.getText());
			ChatInput.setText("");
		}else if(evt.getSource() == ssm){
			String strLine = ssm.readText();
			ChatArea.append(strLine + "\n");
		}
		
		//Check if both users have connected
		if (evt.getSource() == theTimer && blnHostConnected == true && blnClientConnected == true){
			
		}
			
	}
    
    //Constructor
    public StartingView(){
		super();
		//Set Panel Size
		this.setLayout(null);
		this.setPreferredSize(new Dimension(1280, 720));
		
		// Help button from the top right of the sketch
		btnHelp.setBounds(1010,20,250,50);
		btnHelp.setBackground(Color.GRAY);
		btnHelp.addActionListener(this);
		this.add(btnHelp);
		
		// Host button from the middle right of the sketch
		btnHost.setBounds(1010, 480, 230, 65);
		btnHost.setBackground(Color.GRAY);
		btnHost.addActionListener(this);
		this.add(btnHost);
		
		// Join button from the bottom right of the sketch
		btnJoin.setBounds(1010, 590, 230, 65);
		btnJoin.setBackground(Color.GRAY);
		btnJoin.addActionListener(this);
		this.add(btnJoin);
		
		// Text Field for whoever is joining to input the host address + chat
		ChatInput.setBounds(1010, 410, 230, 50);
		ChatInput.setBackground(new Color(211,211,211));
		ChatInput.addActionListener(this);
		this.add(ChatInput);
		
		// Text Area where chat messages are displayed
		ChatScroll.setBounds(1010, 100, 230, 300);
		ChatArea.setBackground(new Color (211,211,211));
		this.add(ChatScroll);
		
		//Load the background image
		try{
			imgStartScreen = ImageIO.read(new File("images/Starting Screen.png"));
		}catch(IOException e){
			System.out.println("Unable to load image");
		}
	}
}
