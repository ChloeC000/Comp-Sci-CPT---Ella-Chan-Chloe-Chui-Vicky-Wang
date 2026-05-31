//Path 1 View

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

public class Path1Panel extends JPanel implements ActionListener, MouseListener{
	//Properties
	BufferedImage imgPath1Background = null;
	Path1Model Model1 = new Path1Model();
	Timer theTimer = new Timer(1000/60, this);
	
	String strNextObject = "";		//To know what object to unlock next
	int intObjectRow;
	
	//Unlockables
	boolean blnSolved = false;
	boolean blnDeerBody = false;
	boolean blnHunting = false;
	boolean blnBag = false;

	
	//Objects
	JButton btnDeerBody = new JButton(new ImageIcon("images/Deer Body.png"));
	JButton btnHunting = new JButton(new ImageIcon("images/Hunting Silhoutte.png"));
	JButton btnBag = new JButton(new ImageIcon("images/Bag1.png"));
	
	//Text Area where clues are given
	JTextArea TheTextArea = new JTextArea("");
	JLabel imgTextBox = new JLabel(new ImageIcon("images/Text Box.png"));
	
	//Methods
	//Draw Background image
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(imgPath1Background, 0, 0, null);
	}
	
	public void actionPerformed(ActionEvent evt){
		this.repaint();
		//If the deer body button is clicked: This is the first object
		if(evt.getSource() == btnDeerBody){
			intObjectRow = Model1.AssignRow("deer body");		//Figure out on which line of the csv file the object is on
			System.out.println(intObjectRow);
			
			if(blnDeerBody == false){
				blnDeerBody = Model1.AssignAction(intObjectRow); 	//Check if the object has a puzzle if it has never been clicked before
				System.out.println(blnDeerBody);
			}
			if(blnDeerBody == true){ 							//After getting a up to date status, check if it is okay to display the clue
				TheTextArea.setText(Model1.strSequence[intObjectRow][5]); 		//If so, get clue for the array
				imgTextBox.setVisible(true);				
				TheTextArea.setVisible(true);
				//Unlock the next object
				strNextObject = Model1.strSequence[intObjectRow][6];
			}
			
		}
		//If the rifle is clicked: This depends on if the object has been unlocked
		if(evt.getSource() == btnHunting){
			if(strNextObject.equals("rifle") || blnHunting == true){	//What to do if the object has been unlocked
				intObjectRow = Model1.AssignRow("rifle");
				blnHunting = Model1.AssignAction(intObjectRow);
				TheTextArea.setText(Model1.strSequence[intObjectRow][5]); 		//If so, get clue for the array
				imgTextBox.setVisible(true);				
				TheTextArea.setVisible(true);
				strNextObject = Model1.strSequence[intObjectRow][6];			//Get next object
			}else{														//If the object has not been unlocked, give alternate clue
				TheTextArea.setText("Looking at the rifle, you find nothing of note. Better not touch it for now, \nit could be dangerous"); 		
				imgTextBox.setVisible(true);				
				TheTextArea.setVisible(true);
			}			
		 }
		 //If the rifle is clicked: This depends on if the object has been unlocked
		if(evt.getSource() == btnBag){
			if(strNextObject.equals("bag") || blnBag == true){	//What to do if the object has been unlocked
				intObjectRow = Model1.AssignRow("bag");
				blnBag = Model1.AssignAction(intObjectRow);
				TheTextArea.setText(Model1.strSequence[intObjectRow][5]); 		//If so, get clue for the array
				imgTextBox.setVisible(true);				
				TheTextArea.setVisible(true);
				strNextObject = Model1.strSequence[intObjectRow][6];			//Get next object
			}else{														//If the object has not been unlocked, give alternate clue
				TheTextArea.setText("You spot a bag in the distant fog, it doesn't seem worth it to go for yet."); 		
				imgTextBox.setVisible(true);				
				TheTextArea.setVisible(true);
			}			
		 }
		
	}
	public void mouseClicked(MouseEvent evt){
		if (evt.getSource() == this && imgTextBox.isShowing()){	//If you click the mouse while the textbox is showing, make it invisible again
			TheTextArea.setText(""); 		
			imgTextBox.setVisible(false);				
			TheTextArea.setVisible(false);
		}
	}
	public void mousePressed(MouseEvent evt){
	}
	public void mouseReleased(MouseEvent evt){
	}
	public void mouseEntered(MouseEvent evt){	
	}
	public void mouseExited(MouseEvent evt){
	}
	
	//Constructor
	public Path1Panel(){
		//Set panel parameters
		this.setLayout(null);
		this.setPreferredSize(new Dimension(1280, 720));
		this.addMouseListener(this);
		
		//Load background
		try{
			imgPath1Background = ImageIO.read(new File("images/Path1 Background.png"));
		}catch(IOException e){
			System.out.println("Unable to load image");
		}
		
		//Load array
		Model1.loadArray();
		
		//Text Area
		imgTextBox.setBounds(50,520,908, 155);		//Draw of the text box
		TheTextArea.setBounds(100,535,800,120);
		TheTextArea.setFont(new Font("Arial", Font.PLAIN, 24));
		TheTextArea.setForeground(Color.WHITE);
		TheTextArea.setEditable(false);
		TheTextArea.setLineWrap(true);
		TheTextArea.setOpaque(false); 
		imgTextBox.setVisible(false);			//Make both invisible until they are needed
		TheTextArea.setVisible(false);
		this.add(imgTextBox);
		this.add(TheTextArea);
		
		//Draw the objects
		//Deer Body
		btnDeerBody.setBounds(Integer.parseInt(Model1.strSequence[0][1]), Integer.parseInt(Model1.strSequence[0][2]), 502, 138);
		btnDeerBody.setContentAreaFilled(false);
		btnDeerBody.setBorderPainted(false);
		btnDeerBody.addActionListener(this);
		this.add(btnDeerBody);
		//Rifle
		btnHunting.setBounds(Integer.parseInt(Model1.strSequence[1][1]), Integer.parseInt(Model1.strSequence[1][2]),87, 239);
		btnHunting.setContentAreaFilled(false);
		btnHunting.setBorderPainted(false);
		btnHunting.addActionListener(this);
		this.add(btnHunting);
		//Bag
		btnBag.setBounds(Integer.parseInt(Model1.strSequence[2][1]), Integer.parseInt(Model1.strSequence[2][2]),35, 95);
		btnBag.setContentAreaFilled(false);
		btnBag.setBorderPainted(false);
		btnBag.addActionListener(this);
		this.add(btnBag);
	}
}
