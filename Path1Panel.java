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
	boolean ActionEnabled = true;
	boolean blnDeerBody = false;
	boolean blnHunting = false;
	boolean blnBag = false;
	boolean blnHare = false;
	
	//Mark that the object has been completed
	boolean DeerComplete = false;
	boolean HuntingComplete = false;
	boolean BagComplete = false;
	boolean blnHareComplete = false;

	//Objects
	JButton btnDeerBody = new JButton(new ImageIcon("images/Deer Body.png"));
	JButton btnHunting = new JButton(new ImageIcon("images/Hunting Silhoutte.png"));
	JButton btnBag = new JButton(new ImageIcon("images/Bag1.png"));
	JButton btnHare = new JButton(new ImageIcon("images/Hare.png"));
	JLabel HatLabel = new JLabel(new ImageIcon("images/Hat.png"));
	
	//Text Area where clues are given
	JTextArea TheTextArea = new JTextArea("");
	JLabel imgTextBox = new JLabel(new ImageIcon("images/Text Box.png"));
	
	
	JButton btnExitCards = new JButton(new ImageIcon("images/Exit Button.png"));
	//Cards Puzzle
	int intCardNum;
	JButton btnCard1 = new JButton(new ImageIcon("images/cards/Card1.png"));
	JButton btnCard2 = new JButton(new ImageIcon("images/cards/Card2.png"));
	JButton btnCard3 = new JButton(new ImageIcon("images/cards/Card3.png"));
	JButton btnCard4 = new JButton(new ImageIcon("images/cards/Card4.png"));
	JButton btnCard5 = new JButton(new ImageIcon("images/cards/Card5.png"));
	JButton btnCard6 = new JButton(new ImageIcon("images/cards/Card6.png"));
	JButton btnCard7 = new JButton(new ImageIcon("images/cards/Card7.png"));
	JButton btnCard8 = new JButton(new ImageIcon("images/cards/Card8.png"));
	JButton btnCard9 = new JButton(new ImageIcon("images/cards/Card9.png"));
	JButton btnCard10 = new JButton(new ImageIcon("images/cards/Card10.png"));
	JButton btnCard11 = new JButton(new ImageIcon("images/cards/Card11.png"));
	JButton btnCard12 = new JButton(new ImageIcon("images/cards/Card12.png"));
	ImageIcon CardBack = new ImageIcon("images/cards/Card Back.png");
	
	
	
	//Methods
	//Draw Background image
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(imgPath1Background, 0, 0, null);
	}
	
	//Puzzles
	//Reset Card Puzzle
	public void ResetCard(){
		btnCard1.setIcon(new ImageIcon("images/cards/Card1.png"));
		btnCard2.setIcon(new ImageIcon("images/cards/Card2.png"));
		btnCard3.setIcon(new ImageIcon("images/cards/Card3.png"));
		btnCard4.setIcon(new ImageIcon("images/cards/Card4.png"));
		btnCard5.setIcon(new ImageIcon("images/cards/Card5.png"));
		btnCard6.setIcon(new ImageIcon("images/cards/Card6.png"));
		btnCard7.setIcon(new ImageIcon("images/cards/Card7.png"));
		btnCard8.setIcon(new ImageIcon("images/cards/Card8.png"));
		btnCard9.setIcon(new ImageIcon("images/cards/Card9.png"));
		btnCard10.setIcon(new ImageIcon("images/cards/Card10.png"));
		btnCard11.setIcon(new ImageIcon("images/cards/Card11.png"));
		btnCard12.setIcon(new ImageIcon("images/cards/Card12.png"));
	}
	//Cards Puzzle
	public void CardPuzzle(){
		//Disable all other buttons temporarily
		ActionEnabled = false;
		btnExitCards.setVisible(true);
		btnExitCards.setEnabled(true);
		//Top Row
		btnCard6.setVisible(true);
		btnCard6.setEnabled(true);
		btnCard1.setVisible(true);
		btnCard1.setEnabled(true);
		btnCard9.setVisible(true);
		btnCard9.setEnabled(true);
		btnCard5.setVisible(true);
		btnCard5.setEnabled(true);
		//Second Row
		btnCard8.setVisible(true);
		btnCard8.setEnabled(true);
		btnCard10.setVisible(true);
		btnCard10.setEnabled(true);
		btnCard3.setVisible(true);
		btnCard3.setEnabled(true);
		btnCard12.setVisible(true);
		btnCard12.setEnabled(true);
		//Bottom Row
		btnCard2.setVisible(true);
		btnCard2.setEnabled(true);
		btnCard4.setVisible(true);
		btnCard4.setEnabled(true);
		btnCard11.setVisible(true);
		btnCard11.setEnabled(true);
		btnCard7.setVisible(true);
		btnCard7.setEnabled(true);
	}

	
	
	
	public void actionPerformed(ActionEvent evt){
		this.repaint();
		//If the deer body button is clicked: This is the first object
		if(evt.getSource() == btnDeerBody && ActionEnabled == true){
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
				if (DeerComplete == false){
					strNextObject = Model1.strSequence[intObjectRow][6];			//Get next object
					DeerComplete = true; 				//So the next object only gets update once if it has not been done before
				}
			}
			
		}
		//If the rifle is clicked: This depends on if the object has been unlocked
		if(evt.getSource() == btnHunting && ActionEnabled == true){
			if(strNextObject.equals("rifle") || blnHunting == true){	//What to do if the object has been unlocked
				intObjectRow = Model1.AssignRow("rifle");
				blnHunting = Model1.AssignAction(intObjectRow);
				TheTextArea.setText(Model1.strSequence[intObjectRow][5]); 		//If so, get clue for the array
				imgTextBox.setVisible(true);				
				TheTextArea.setVisible(true);
				if (HuntingComplete == false){
					strNextObject = Model1.strSequence[intObjectRow][6];			//Get next object
					HuntingComplete = true; 				//So the next object only gets update once if it has not been done before
				}
			}else{														//If the object has not been unlocked, give alternate clue
				TheTextArea.setText("Looking at the rifle, you find nothing of note. Better not touch it for now, \nit could be dangerous"); 		
				imgTextBox.setVisible(true);				
				TheTextArea.setVisible(true);
			}			
		 }
		 //If the bag is clicked: This depends on if the object has been unlocked
		if(evt.getSource() == btnBag && ActionEnabled == true){
			if(strNextObject.equals("bag") || blnBag == true){	//What to do if the object has been unlocked
				intObjectRow = Model1.AssignRow("bag");
				blnBag = Model1.AssignAction(intObjectRow);
				TheTextArea.setText(Model1.strSequence[intObjectRow][5]); 
				imgTextBox.setVisible(true);				
				TheTextArea.setVisible(true);
				if(BagComplete == false){
					strNextObject = Model1.strSequence[intObjectRow][6];	
					BagComplete = true;
				}	
			}else{														//If the object has not been unlocked, give alternate clue
				TheTextArea.setText("You spot a bag in the distant fog, it doesn't seem worth it to go for yet."); 		
				imgTextBox.setVisible(true);				
				TheTextArea.setVisible(true);
			}			
		 }
		 //If the hare is clicked: This depends on if the object has been unlocked and if the puzzle has been solved
		if(evt.getSource() == btnHare && ActionEnabled == true){
			intObjectRow = Model1.AssignRow("hare");			
			if(strNextObject.equals("hare") && blnHare != true){ 			//if they have not yet solved the puzzle yet, give them the puzzle by making it visible
				TheTextArea.setText("It's a rabbit! Or is it a hare? It's sitting on a pack of cards."); 		
				imgTextBox.setVisible(true);				
				TheTextArea.setVisible(true);
				CardPuzzle();
			}else{														
				TheTextArea.setText("It's a rabbit! Or is it a hare? It's sitting on a pack of cards."); 		
				imgTextBox.setVisible(true);				
				TheTextArea.setVisible(true);
			}			
			
			if(blnHare == true){		//If they have either just solved or previously solved the puzzle, they can get the clue
				TheTextArea.setText(Model1.strSequence[intObjectRow][5]); 
				imgTextBox.setVisible(true);				
				TheTextArea.setVisible(true);
				if (blnHareComplete == false){
					strNextObject = Model1.strSequence[intObjectRow][6];
					blnHareComplete = true;
				}
			}
		 }
		 
		 //If the cards are clicked
		 if(evt.getSource() == btnExitCards){
			 //Enable other buttons again and disable the card puzzle
			ActionEnabled = true;
			btnExitCards.setVisible(false);
			btnExitCards.setEnabled(false);
			//Top Row
			btnCard6.setVisible(false);
			btnCard6.setEnabled(false);
			btnCard1.setVisible(false);
			btnCard1.setEnabled(false);
			btnCard9.setVisible(false);
			btnCard9.setEnabled(false);
			btnCard5.setVisible(false);
			btnCard5.setEnabled(false);
			//Second Row
			btnCard8.setVisible(false);
			btnCard8.setEnabled(false);
			btnCard10.setVisible(false);
			btnCard10.setEnabled(false);
			btnCard3.setVisible(false);
			btnCard3.setEnabled(false);
			btnCard12.setVisible(false);
			btnCard12.setEnabled(false);
			//Bottom Row
			btnCard2.setVisible(false);
			btnCard2.setEnabled(false);
			btnCard4.setVisible(false);
			btnCard4.setEnabled(false);
			btnCard11.setVisible(false);
			btnCard11.setEnabled(false);
			btnCard7.setVisible(false);
			btnCard7.setEnabled(false);
		 }
		 if(evt.getSource() == btnCard1){
			 intCardNum = 1;
			 btnCard1.setIcon(CardBack);	//Flip the card over
			 
		 }
		 if(evt.getSource() == btnCard2){
			 if(intCardNum == 1){		//If the previous card was not clicked,  then the answer is not correct
				 intCardNum = 2;
				 btnCard2.setIcon(CardBack);
			 }else{
				 intCardNum = 0;
				 ResetCard();		//Reset cards if they got it wrong
			 }
		 }
		 if(evt.getSource() == btnCard3){
			  if(intCardNum == 2){
				 intCardNum = 3;
				 btnCard3.setIcon(CardBack);
			 }else{
				 intCardNum = 0;
				 ResetCard();
			 }
		 }
		 if(evt.getSource() == btnCard4){
			  if(intCardNum == 3){
				 intCardNum = 4;
				 btnCard4.setIcon(CardBack);
			 }else{
				 intCardNum = 0;
				 ResetCard();
			 }
		 }
		 if(evt.getSource() == btnCard5){
			 if(intCardNum == 4){
				 intCardNum = 5;
				 btnCard5.setIcon(CardBack);
			 }else{
				 intCardNum = 0;
				 ResetCard();
			 }
		 }
		 if(evt.getSource() == btnCard6){
			  if(intCardNum == 5){
				 intCardNum = 6;
				 btnCard6.setIcon(CardBack);
			 }else{
				 intCardNum = 0;
				 ResetCard();
			 }
		 }
		 if(evt.getSource() == btnCard7){
			  if(intCardNum == 6){
				 intCardNum = 7;
				 btnCard7.setIcon(CardBack);
			 }else{
				 intCardNum = 0;
				 ResetCard();
			 }
		 }
		 if(evt.getSource() == btnCard8){
			  if(intCardNum == 7){
				 intCardNum = 8;
				 btnCard8.setIcon(CardBack);
			 }else{
				 intCardNum = 0;
				 ResetCard();
			 }
		 }
		 if(evt.getSource() == btnCard9){
			  if(intCardNum == 8){
				 intCardNum = 9;
				 btnCard9.setIcon(CardBack);
			 }else{
				 intCardNum = 0;
				 ResetCard();
			 }
		 }
		 if(evt.getSource() == btnCard10){
			  if(intCardNum == 9){
				 intCardNum = 10;
				 btnCard10.setIcon(CardBack);
			 }else{
				 intCardNum = 0;
				 ResetCard();
			 }
		 }
		 if(evt.getSource() == btnCard11){
			  if(intCardNum == 10){
				 intCardNum = 11;
				 btnCard11.setIcon(CardBack);
			 }else{
				 intCardNum = 0;
				 ResetCard();
			 }
		 }
		 if(evt.getSource() == btnCard12){
			  if(intCardNum == 11){		//Puzzle is solved!
				 btnCard12.setIcon(CardBack);
				 blnHare = true;
				 TheTextArea.setText("In your minds eye, you see a pack of the cards. It appears you've put them in the correct order."); 		
				 imgTextBox.setVisible(true);				
				 TheTextArea.setVisible(true);
				 HatLabel.setVisible(true);
			 }else{
				 intCardNum = 0;
				 ResetCard();
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
		/////////////////////
		//Path 1 main panel//
		/////////////////////
		
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
		btnDeerBody.setBounds(Integer.parseInt(Model1.strSequence[0][1]), Integer.parseInt(Model1.strSequence[0][2]), 502, 138);	//Get the object location from the csv file
		btnDeerBody.setContentAreaFilled(false);		//Make the button background invisible
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
		//Hare
		btnHare.setBounds(Integer.parseInt(Model1.strSequence[3][1]), Integer.parseInt(Model1.strSequence[3][2]),56,71);
		btnHare.setContentAreaFilled(false);
		btnHare.setBorderPainted(false);
		btnHare.addActionListener(this);
		HatLabel.setBounds(Integer.parseInt(Model1.strSequence[3][1])+35, Integer.parseInt(Model1.strSequence[3][2])+7,12,15);
		HatLabel.setVisible(false);
		this.add(HatLabel);
		this.add(btnHare);
		
		////////////////////
		//The Cards Puzzle//
		////////////////////
		
		//Top Row
		btnCard6.setBounds(320,137,72,100);
		btnCard6.setContentAreaFilled(false);
		btnCard6.setBorderPainted(false);
		btnCard6.setVisible(false);
		btnCard6.setEnabled(false);
		btnCard6.addActionListener(this);
		this.add(btnCard6);
		
		btnCard1.setBounds(427,137,72,100);
		btnCard1.setContentAreaFilled(false);
		btnCard1.setBorderPainted(false);
		btnCard1.setVisible(false);
		btnCard1.setEnabled(false);
		btnCard1.addActionListener(this);
		this.add(btnCard1);
		
		btnCard9.setBounds(534,137,72,100);
		btnCard9.setContentAreaFilled(false);
		btnCard9.setBorderPainted(false);
		btnCard9.setVisible(false);
		btnCard9.setEnabled(false);
		btnCard9.addActionListener(this);
		this.add(btnCard9);
		
		btnCard5.setBounds(641,137,72,100);
		btnCard5.setContentAreaFilled(false);
		btnCard5.setBorderPainted(false);
		btnCard5.setVisible(false);
		btnCard5.setEnabled(false);
		btnCard5.addActionListener(this);
		this.add(btnCard5);
		
		//Second Row
		btnCard8.setBounds(320,272,72,100);
		btnCard8.setContentAreaFilled(false);
		btnCard8.setBorderPainted(false);
		btnCard8.setVisible(false);
		btnCard8.setEnabled(false);
		btnCard8.addActionListener(this);
		this.add(btnCard8);
		
		btnCard10.setBounds(427,272,72,100);
		btnCard10.setContentAreaFilled(false);
		btnCard10.setBorderPainted(false);
		btnCard10.setVisible(false);
		btnCard10.setEnabled(false);
		btnCard10.addActionListener(this);
		this.add(btnCard10);
		
		btnCard3.setBounds(534,272,72,100);
		btnCard3.setContentAreaFilled(false);
		btnCard3.setBorderPainted(false);
		btnCard3.setVisible(false);
		btnCard3.setEnabled(false);
		btnCard3.addActionListener(this);
		this.add(btnCard3);
		
		btnCard12.setBounds(641,272,72,100);
		btnCard12.setContentAreaFilled(false);
		btnCard12.setBorderPainted(false);
		btnCard12.setVisible(false);
		btnCard12.setEnabled(false);
		btnCard12.addActionListener(this);
		this.add(btnCard12);
		
		//Third row
		btnCard2.setBounds(320,407,72,100);
		btnCard2.setContentAreaFilled(false);
		btnCard2.setBorderPainted(false);
		btnCard2.setVisible(false);
		btnCard2.setEnabled(false);
		btnCard2.addActionListener(this);
		this.add(btnCard2);
		
		btnCard4.setBounds(427,407,72,100);
		btnCard4.setContentAreaFilled(false);
		btnCard4.setBorderPainted(false);
		btnCard4.setVisible(false);
		btnCard4.setEnabled(false);
		btnCard4.addActionListener(this);
		this.add(btnCard4);
		
		btnCard11.setBounds(534,407,72,100);
		btnCard11.setContentAreaFilled(false);
		btnCard11.setBorderPainted(false);
		btnCard11.setVisible(false);
		btnCard11.setEnabled(false);
		btnCard11.addActionListener(this);
		this.add(btnCard11);
		
		btnCard7.setBounds(641,407,72,100);
		btnCard7.setContentAreaFilled(false);
		btnCard7.setBorderPainted(false);
		btnCard7.setVisible(false);
		btnCard7.setEnabled(false);
		btnCard7.addActionListener(this);
		this.add(btnCard7);
		
		//Exit Button
		btnExitCards.setBounds(730,120,32,30);
		btnExitCards.setContentAreaFilled(false);
		btnExitCards.setBorderPainted(false);
		btnExitCards.setVisible(false);
		btnExitCards.setEnabled(false);
		btnExitCards.addActionListener(this);
		this.add(btnExitCards);
	}
}
