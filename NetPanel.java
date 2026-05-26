import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.net.*;

public class NetPanel implements ActionListener{
  JFrame theFrame;
  JPanel thePanel;
  SuperSocketMaster SuperSocket;
  JButton serverBut;
  JButton clientBut;
  JButton grinningBut;
  JButton squintingBut;
  JButton laughingBut;
  JButton hearteyesBut;
  JButton hundredBut;
  JButton thumbsBut;
  JTextField IPAddressText;
  JTextField portText;  
  JTextField sendText;
  JTextArea recievedText;
  JLabel ipaddressLabel;
  JLabel portlabel;
  JLabel sendlabel;
  JLabel recievedLabel;  
  JLabel theRole;
  JButton discBut; 
  JScrollPane theScroll;
  ImageIcon grinningIcon;
  ImageIcon squintingIcon;
  ImageIcon laughingIcon;
  ImageIcon hearteyesIcon;
  ImageIcon hundredIcon;
  ImageIcon thumbsIcon;
  
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
        theRole.setText("I am the cleint.");
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
        theRole.setText("I am the server.");
      }else{
        serverBut.setEnabled(true); 
        clientBut.setEnabled(true); 
        IPAddressText.setEnabled(true);
        portText.setEnabled(true);
      }      
    }else if(evt.getSource() == sendText){
      if(SuperSocket != null){
        SuperSocket.sendText(sendText.getText());   // Send the text to the other parties (not yourself) belong to the server IP address and port no.
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
    }else if(evt.getSource() == grinningBut){
	  AddEmoji("😀");
    }else if(evt.getSource() == squintingBut){
	  AddEmoji("😆");
    }else if(evt.getSource() == laughingBut){
	  AddEmoji("🤣");
    }else if(evt.getSource() == hearteyesBut){
	  AddEmoji("😍");
    }else if(evt.getSource() == hundredBut){
	  AddEmoji("💯");
    }else if(evt.getSource() == thumbsBut){
	  AddEmoji("👍");
    }
  }
  
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
        
        // IP label
        ipaddressLabel = new JLabel("IP Address");
        ipaddressLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ipaddressLabel.setBounds(0, 0, 300, 25);
        thePanel.add(ipaddressLabel);
        
        // IP field
        IPAddressText = new JTextField("localhost");
        IPAddressText.setBounds(0, 25, 300, 25);
        thePanel.add(IPAddressText);
        
        // Port label
        portlabel = new JLabel("Port");
        portlabel.setHorizontalAlignment(SwingConstants.CENTER);
        portlabel.setBounds(0, 50, 300, 25);
        thePanel.add(portlabel);

        // Port field
        portText = new JTextField("6112");
        portText.setBounds(0, 75, 300, 25);
        thePanel.add(portText);

        // Receive label
        recievedLabel = new JLabel("Received Text");
        recievedLabel.setHorizontalAlignment(SwingConstants.CENTER);
        recievedLabel.setBounds(0, 100, 300, 25);
        thePanel.add(recievedLabel);

        // Receive area
        recievedText = new JTextArea();
        recievedText.setFont(recievedText.getFont().deriveFont(16f));
        recievedText.setEditable(false);

        // Scroll pane
        theScroll = new JScrollPane(recievedText);
        theScroll.setBounds(0, 125, 300, 410);
        thePanel.add(theScroll);
        
        // Send label
        sendlabel = new JLabel("Text to Send");
        sendlabel.setHorizontalAlignment(SwingConstants.CENTER);
        sendlabel.setBounds(0, 540, 300, 25);
        thePanel.add(sendlabel);
        
        // 😀 Grinning button
        grinningBut = new JButton();        
        grinningIcon = new ImageIcon("Grinning.png");
		grinningBut.setIcon(grinningIcon);       
        grinningBut.setBounds(0, 560, 50, 25);
        grinningBut.addActionListener(this);
        thePanel.add(grinningBut);
        
        // 😆 Squinting button
        squintingBut = new JButton();
        squintingIcon = new ImageIcon("Squinting.png");
		squintingBut.setIcon(squintingIcon);      
        squintingBut.setBounds(50, 560, 50, 25);
        squintingBut.addActionListener(this);
        thePanel.add(squintingBut);
        
        // 🤣 Laughing button
        laughingBut = new JButton();
        laughingIcon = new ImageIcon("Laughing.png");
        laughingBut.setIcon(laughingIcon); 
        laughingBut.setBounds(100, 560, 50, 25);
        laughingBut.addActionListener(this);
        thePanel.add(laughingBut);
        
        // 😍 Heart-eyes button
        hearteyesBut = new JButton();
        hearteyesIcon = new ImageIcon("Heart-eyes.png");
        hearteyesBut.setIcon(hearteyesIcon); 
        hearteyesBut.setBounds(150, 560, 50, 25);
        hearteyesBut.addActionListener(this);
        thePanel.add(hearteyesBut);
        
        // 💯 Hundred points button
        hundredBut = new JButton();
        hundredIcon = new ImageIcon("Hundred.png");
        hundredBut.setIcon(hundredIcon); 
        hundredBut.setBounds(200, 560, 50, 25);
        hundredBut.addActionListener(this);
        thePanel.add(hundredBut);
        
        // 👍 Thumbs button
        thumbsBut = new JButton();
        thumbsIcon = new ImageIcon("Thumbs.png");
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
        
        // Role label
        theRole = new JLabel("No connection.");
        theRole.setHorizontalAlignment(SwingConstants.CENTER);
        theRole.setBounds(0, 650, 300, 25);
        thePanel.add(theRole);
  }
}
