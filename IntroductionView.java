//Introduction View
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class IntroductionView implements ActionListener, MouseListener, MouseMotionListener{
	//Properties to build the panel
	JIntroductionScreen IntroPanel = new JIntroductionScreen();
	Timer theTimer = new Timer(1000/60, this);
	
	// Buttons
	JButton HelpButton = new JButton("Help");
	JButton btnJoin = new JButton("Join");
	JButton btnHost = new JButton("Host");
	SuperSocketMaster ssm = null;
	
	//The chat box
	JTextField ChatInput = new JTextField("For Joining: Input host address");
	JTextArea ChatArea = new JTextArea();
	JScrollPane ChatScroll = new JScrollPane(ChatArea);	
	
	//To trigger other events
	boolean blnHostConnected = false;
	boolean blnClientConnected = false;
	boolean blnConnectionOccured = false;
	boolean blnGameBegin = false;
	
	//Methods
	public void actionPerformed (ActionEvent evt){
		if(evt.getSource() == theTimer){
			IntroPanel.repaint();
			//Check if connection has occured
			if (blnHostConnected == true || blnClientConnected == true){
				blnConnectionOccured = true;
			}
			if(blnConnectionOccured == true){
				//Trigger pop up
			}
		}
		
		if(evt.getSource() == btnHost){
			System.out.println("Creating game...");
			
			// Host uses only the port number
			ssm = new SuperSocketMaster(6112, this);
			ssm.connect();
			
			btnJoin.setText(ssm.getMyAddress());	// Show what the address of the host computer is
			blnHostConnected = true;				// Let code know that host has connected
			
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
			System.out.println("Joined game.");
		}
		
		//Chat related action listeners
		if(evt.getSource() == ChatInput){
			System.out.println("Field event triggered");
			ssm.sendText(ssm.getMyHostname()+": "+ChatInput.getText());
			ChatArea.append(ssm.getMyHostname()+": "+ChatInput.getText()+"\n");
			ChatInput.setText("");

		}else if(evt.getSource() == ssm){
			System.out.println("recieved text");
			String strLine = ssm.readText();
			ChatArea.append(strLine + "\n");
		}
	}
	public void mousePressed(MouseEvent evt) {
		
	}
	
	public void mouseDragged(MouseEvent evt) {
      
    }

    public void mouseReleased(MouseEvent evt) {
    
    } 
    
    public void mouseClicked(MouseEvent evt) {
		System.out.println(evt.getX()+" , "+evt.getY());
		//When ever user Clicks, check if they are overlaying the deer
		if(evt.getX() >= 510 && evt.getX() <= 539 && evt.getY() >= 460 && evt.getY()<= 520 && blnConnectionOccured == true){			//Check if it overalays the head and if the players have connected
			blnGameBegin = true;
			System.out.println("Game Begin");
		}else if(evt.getX() >=510 && evt.getX() <= 656 && evt.getY() >= 541 && evt.getY() < 595 && blnConnectionOccured == true){		//Check if it overlays the body and if the players have connected
			blnGameBegin = true;
			System.out.println("Game Begin");
		}
	}
    public void mouseEntered(MouseEvent evt) {
		theTimer.start();
	}
    public void mouseExited(MouseEvent evt){
		theTimer.stop();
	}
    public void mouseMoved(MouseEvent evt) {
		//Have the target follow the mouse as long as it is inside the bounds of the black frame
		if(evt.getX() < 936 && evt.getX() > 74 && evt.getY() > 77 && evt.getY() < 648){
			IntroPanel.intMouseX = evt.getX()- 60;
			IntroPanel.intMouseY = evt.getY()- 50;
		}
	}
	
	//Constructor
	public IntroductionView(){
		IntroPanel.setLayout(null);
		IntroPanel.setPreferredSize(new Dimension(1280, 720));
		
		//Have the panel sense the motion and actions of the mouse
		IntroPanel.addMouseMotionListener(this);
		IntroPanel.addMouseListener(this);
		
		//Draw a help button
		HelpButton.setBounds(1000,20,250,50);
		HelpButton.setBackground(Color.GRAY);
		HelpButton.addActionListener(this);
		IntroPanel.add(HelpButton);
		
		// Host button from the middle right of the sketch
		btnHost.setBounds(1010, 480, 230, 65);
		btnHost.setBackground(Color.GRAY);
		btnHost.addActionListener(this);
		IntroPanel.add(btnHost);
		
		// Join button from the bottom right of the sketch
		btnJoin.setBounds(1010, 590, 230, 65);
		btnJoin.setBackground(Color.GRAY);
		btnJoin.addActionListener(this);
		IntroPanel.add(btnJoin);
		
		// Text Area where chat messages are displayed
		ChatScroll.setBounds(1010, 100, 230, 300);
		ChatArea.setBackground(new Color (211,211,211));
		IntroPanel.add(ChatScroll);
		
		// Text Field for whoever is joining to input the host address + chat
		ChatInput.setBounds(1010, 400, 230, 50);
		ChatInput.setBackground(new Color(211,211,211));
		ChatInput.addActionListener(this);
		IntroPanel.add(ChatInput);
		
		
		theTimer.start();
		
		
	}
	
	
}
