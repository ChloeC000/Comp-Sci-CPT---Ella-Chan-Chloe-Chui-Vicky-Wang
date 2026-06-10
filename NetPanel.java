/*
Course: ICS4U1b Computer Science
Teacher: Mr. Alfred Ron Cadawas
Memebers: Chloe Chui, Ella Chan, Vicky Wang
Assignment Name: CPT

This is the network message panel.
*/

// Import the swing, JComponents and timestamp libaraies
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.net.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NetPanel implements ActionListener{  
  String strRole;   // strRole will be used to show the player role (Server or Clinet)
  JFrame theFrame;  // Create the network message frame
  JPanel thePanel;  // Create the network message panel
  SuperSocketMaster SuperSocket;  // Create the SuperSocketMaster object
  // Create the UI objects
  JButton serverBut;
  JButton clientBut;
  JButton grinningBut;
  JButton squintingBut;
  JButton laughingBut;
  JButton hearteyesBut;
  JButton hundredBut;
  JButton thumbsBut;
  JButton discBut; 
  JButton helpBut; 
  JTextField IPAddressText;
  JTextField portText;  
  JTextField sendText;
  JTextArea recievedText;
  JLabel ipaddressLabel;
  JLabel portlabel;
  JLabel sendlabel;
  JLabel recievedLabel;  
  JLabel theRole;
  JScrollPane theScroll;
  ImageIcon grinningIcon;
  ImageIcon squintingIcon;
  ImageIcon laughingIcon;
  ImageIcon hearteyesIcon;
  ImageIcon hundredIcon;
  ImageIcon thumbsIcon;
  // Show the network message timestamp
  LocalDateTime dtNow;
  DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("dd-MM HH:mm");
  
  public void actionPerformed(ActionEvent evt){
    if(evt.getSource() == clientBut){   // The action is from a button click event, it will not execute this actionPerformed twice unless the user click the button again.
      serverBut.setEnabled(false); 
      clientBut.setEnabled(false); 
      IPAddressText.setEnabled(false);
      portText.setEnabled(false);      
      SuperSocket = new SuperSocketMaster(IPAddressText.getText(), Integer.parseInt(portText.getText()), this);   // Provide the server IP address and portText no. to connect. To get the connection, you must provide the server IP address and its portText no.
      boolean gotConnect = SuperSocket.connect();   // If it returns true, it connects.
      if(gotConnect){
        discBut.setEnabled(true);
        recievedText.append("My Address: "+SuperSocket.getMyAddress()+"\n");
        recievedText.append("My Hostname: "+SuperSocket.getMyHostname()+"\n");
        strRole = "Client";
        theRole.setText("I am the " + strRole + ".");
      }else{
        serverBut.setEnabled(true); 
        clientBut.setEnabled(true); 
        IPAddressText.setEnabled(true);
        portText.setEnabled(true);
      }      
    }else if(evt.getSource() == serverBut){
      serverBut.setEnabled(false); 
      clientBut.setEnabled(false); 
      IPAddressText.setEnabled(false);      
      portText.setEnabled(false);     
      SuperSocket = new SuperSocketMaster(Integer.parseInt(portText.getText()), this);    // Provide portText no. to activate the server. The client must use this portText no. to connect. The port no. is like a room no. in Project SEKAI: Colorful Stage!.
      boolean gotConnect = SuperSocket.connect();
      if(gotConnect){
        discBut.setEnabled(true);
        recievedText.append("My Address: "+SuperSocket.getMyAddress()+"\n");
        recievedText.append("My Hostname: "+SuperSocket.getMyHostname()+"\n");
        strRole = "Server";
        theRole.setText("I am the " + strRole + ".");
      }else{
        serverBut.setEnabled(true); 
        clientBut.setEnabled(true); 
        IPAddressText.setEnabled(true);
        portText.setEnabled(true);
      }      
    }else if(evt.getSource() == sendText){
      if(SuperSocket != null){
		dtNow = LocalDateTime.now();  // Create the timestamp before sending the network message
        String timestamp = dtNow.format(dtFormat);
        SuperSocket.sendText(strRole + " " + dtNow.format(dtFormat) + ": " + sendText.getText());   // Send the text to the other parties (not yourself) belong to the server IP address and port no.
        // Add the player's message to his/her own message received textarea
        recievedText.append(strRole + " " + dtNow.format(dtFormat) + ": " + sendText.getText() + "\n");     // Get the text message sent from SuperSocket.sendText(sendText.getText());
        recievedText.setCaretPosition(recievedText.getDocument().getLength());   // The caret position (setCaretPosition) is the location of the cursor inside a text component. This line set the cursor to the last position of the text field. recievedText.getDocument().getLength() returns the no. of char of recievedText. Use this no. with setCaretPosition means set the cursor to the position of the last char.
        sendText.setText("");        
      }
    }else if(evt.getSource() == SuperSocket){         // The defualt event of the SuperSocketMaster is receiving message
      recievedText.append(SuperSocket.readText() + "\n");     // Get the text message sent from SuperSocket.sendText(sendText.getText());
      recievedText.setCaretPosition(recievedText.getDocument().getLength());   // The caret position (setCaretPosition) is the location of the cursor inside a text component. This line set the cursor to the last position of the text field. recievedText.getDocument().getLength() returns the no. of char of recievedText. Use this no. with setCaretPosition means set the cursor to the position of the last char.
    }else if(evt.getSource() == discBut){
      serverBut.setEnabled(true); 
      clientBut.setEnabled(true); 
      IPAddressText.setEnabled(true);
      theRole.setText("No connection.");
      portText.setEnabled(true); 
      SuperSocket.disconnect();
      discBut.setEnabled(false);
    // Send emoji below by calling AddEmoji method
    }else if(evt.getSource() == grinningBut){	
	  AddEmoji("😀");        // For the grinning emoji
    }else if(evt.getSource() == squintingBut){
	  AddEmoji("😆");        // For the squinting emoji
    }else if(evt.getSource() == laughingBut){
	  AddEmoji("🤣");        // For the laughing emoji
    }else if(evt.getSource() == hearteyesBut){
	  AddEmoji("😍");        // For the hearteyes emoji
    }else if(evt.getSource() == hundredBut){
	  AddEmoji("💯");        // For the hundred emoji
    }else if(evt.getSource() == thumbsBut){
	  AddEmoji("👍");        // For the thumbs emoji
    }
  }
  
  // Send emoji method
  private void AddEmoji(String strEmoji){
	int intPos = sendText.getCaretPosition();
	sendText.setText(sendText.getText().substring(0, intPos) + strEmoji + sendText.getText().substring(intPos));
	sendText.requestFocusInWindow();
  }
  
  public NetPanel(){
	 // Main reusable panel
	thePanel = new JPanel();
	thePanel.setLayout(null);
	thePanel.setPreferredSize(new Dimension(300, 720));
	
	// Help button
	helpBut = new JButton("Help");
	helpBut.setBounds(0, 0, 300, 25);
	thePanel.add(helpBut);
	
	// IP label
	ipaddressLabel = new JLabel("IP Address");
	ipaddressLabel.setHorizontalAlignment(SwingConstants.CENTER);
	ipaddressLabel.setBounds(0, 30, 300, 25);
	thePanel.add(ipaddressLabel);
	
	// IP field
	IPAddressText = new JTextField("localhost");
	IPAddressText.setBounds(0, 55, 300, 25);
	thePanel.add(IPAddressText);
	
	// Port label
	portlabel = new JLabel("Port");
	portlabel.setHorizontalAlignment(SwingConstants.CENTER);
	portlabel.setBounds(0, 80, 300, 25);
	thePanel.add(portlabel);

	// Port field
	portText = new JTextField("6112");
	portText.setBounds(0, 105, 300, 25);
	thePanel.add(portText);

	// Receive label
	recievedLabel = new JLabel("Received Text");
	recievedLabel.setHorizontalAlignment(SwingConstants.CENTER);
	recievedLabel.setBounds(0, 140, 300, 25);
	thePanel.add(recievedLabel);

	// Receive area
	recievedText = new JTextArea();
	recievedText.setFont(recievedText.getFont().deriveFont(16f));
	recievedText.setEditable(false);

	// Scroll pane
	theScroll = new JScrollPane(recievedText);
	theScroll.setBounds(0, 160, 300, 380);
	thePanel.add(theScroll);
	
	// Send label
	sendlabel = new JLabel("Text to Send");
	sendlabel.setHorizontalAlignment(SwingConstants.CENTER);
	sendlabel.setBounds(0, 540, 300, 25);
	thePanel.add(sendlabel);
	
	// 😀 Grinning button
	grinningBut = new JButton();        
	grinningIcon = new ImageIcon("./Images/Grinning.png");
	grinningBut.setIcon(grinningIcon);       
	grinningBut.setBounds(0, 560, 50, 25);
	grinningBut.addActionListener(this);
	thePanel.add(grinningBut);
	
	// 😆 Squinting button
	squintingBut = new JButton();
	squintingIcon = new ImageIcon("./Images/Squinting.png");
	squintingBut.setIcon(squintingIcon);      
	squintingBut.setBounds(50, 560, 50, 25);
	squintingBut.addActionListener(this);
	thePanel.add(squintingBut);
	
	// 🤣 Laughing button
	laughingBut = new JButton();
	laughingIcon = new ImageIcon("./Images/Laughing.png");
	laughingBut.setIcon(laughingIcon); 
	laughingBut.setBounds(100, 560, 50, 25);
	laughingBut.addActionListener(this);
	thePanel.add(laughingBut);
	
	// 😍 Heart-eyes button
	hearteyesBut = new JButton();
	hearteyesIcon = new ImageIcon("./Images/Heart-eyes.png");
	hearteyesBut.setIcon(hearteyesIcon); 
	hearteyesBut.setBounds(150, 560, 50, 25);
	hearteyesBut.addActionListener(this);
	thePanel.add(hearteyesBut);
	
	// 💯 Hundred points button
	hundredBut = new JButton();
	hundredIcon = new ImageIcon("./Images/Hundred.png");
	hundredBut.setIcon(hundredIcon); 
	hundredBut.setBounds(200, 560, 50, 25);
	hundredBut.addActionListener(this);
	thePanel.add(hundredBut);
	
	// 👍 Thumbs button
	thumbsBut = new JButton();
	thumbsIcon = new ImageIcon("./Images/Thumbs.png");
	thumbsBut.setIcon(thumbsIcon); 
	thumbsBut.setBounds(250, 560, 50, 25);
	thumbsBut.addActionListener(this);
	thePanel.add(thumbsBut);

	// Send field
	sendText = new JTextField();
	sendText.setBounds(0, 590, 300, 25);
	sendText.addActionListener(this);
	thePanel.add(sendText);

	// Server button
	serverBut = new JButton("Server");
	serverBut.setBounds(0, 620, 100, 25);
	serverBut.addActionListener(this);
	thePanel.add(serverBut);

	// Client button
	clientBut = new JButton("Client");
	clientBut.setBounds(100, 620, 100, 25);
	clientBut.addActionListener(this);
	thePanel.add(clientBut);                

	// Disconnect button
	discBut = new JButton("Disconnect");
	discBut.setBounds(200, 620, 100, 25);
	discBut.addActionListener(this);
	discBut.setEnabled(false);
	thePanel.add(discBut);
	
	// Role label (shows who is the server and client)
	theRole = new JLabel("No connection.");
	theRole.setHorizontalAlignment(SwingConstants.CENTER);
	theRole.setBounds(0, 650, 300, 25);
	thePanel.add(theRole);
  }
}
