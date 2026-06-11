/*
Course: ICS4U1b Computer Science
Teacher: Mr. Alfred Ron Cadawas
Memebers: Chloe Chui, Ella Chan, Vicky Wang
Assignment Name: CPT

This is the Controller for Path 3 program. This program links the View and Model.
*/

// Import the swing and IO libraries
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;

public class Path3 extends JPanel implements ActionListener, MouseListener, MouseMotionListener{
	// Properties
	// Create the path 3 game play panel	
	int intGameWidth = 955;
	int intGameHeight = 720;
	// Initialize the help screen index as 3
	int intCurrentScreen = 3;
	// Set the game objects
	String strObjName = "";
	boolean blnCompleteClick;          // Indicates if the player clicks the all the object and complete the whole flow 
	boolean blnRightFlow;              // Indicates if the player clicks the right object
	boolean blnResetFlow;              // Indicates the player clicking a wrong object and restarting the sequence again
	boolean blnGame5Completed;         // Indicates if the game 5 (beat) is completes correctly
	boolean blnGame6Completed;         // Indicates if the game 5 (puzzle) is completes correctly
	// Create game background image and the onscreen objects
	BufferedImage imgPath3Background = null;
	JButton CampfireBut;
	JButton RadioBut;
	JButton JacketBut;
	JButton PhoneBut;
	JButton JournalBut;
	JButton KnifeBut;
	JButton FlowerBut;
	JTextArea TheTextArea = new JTextArea("");
	JLabel imgTextBox = new JLabel(new ImageIcon("./images/Text Box.png"));
	// Create the game 5 and 6 Views
	Game5 Game5Panel;
	Game6 Game6Panel;			
	// Create the Model
    PathModel Path3Data = new PathModel("Path3.csv");
    
	// Game 5 properties
	// Create the beat patten timer to record the created beats
	Timer patternTimer = new Timer(1000, this);
	// Create the timer for the animation of the View
	Timer Game5MessageTimer = new Timer(1000/60, this);
	// Varibales for recording the created beats
	int[] intTarget = new int[3];
	int[] intBeat = new int[3];
	int intCurrentPattern = 0;
	int intCurrentClicks = 0;
	boolean blnCorrect = true;
	
	// Game 6 properties
	// The x, y coordination of the 4 pieces of the newspaper
	int intPaperX = 0;
	int intPaperY = 0;
	int intPaper1X = 800;
	int intPaper1Y = 510;
	int intPaper2X = 800;
	int intPaper2Y = 270;
	int intPaper3X = 800;
	int intPaper3Y = 150;
	int intPaper4X = 800;
	int intPaper4Y = 390;
	int intPaperIndex = 0;
	boolean blnDraggingPaper = false;
	
	// Display the path 3 background image
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		// Create the screen
		g.drawImage(imgPath3Background, 0, 0, null);
	}	    
	public void mouseExited(MouseEvent evt){
	}
	public void mouseEntered(MouseEvent evt){
	}
	public void mouseReleased(MouseEvent evt){	
		blnDraggingPaper = false;
	}
	public void mousePressed(MouseEvent evt){	
		if (Game5Panel.isVisible()) {
			// For game 5 (beat)
			// Change the colors of the DJ panel lights when the player clicks the right jog wheel
			// This is done by showing another png on top of the existing DJ panel
			Game5Panel.blnShowWheel = true;
			repaint();
		} else if(Game6Panel.isVisible()){
			// For Game 6 (puzzle)
			// Check which piece of newspapar is selected and get its x, y coordination for checking if the player puts it to the right position
			int x = evt.getX();
			int y = evt.getY();
			// Use the mouse x, y coordination to verify if the small newspaper image is in the correct area or not. If it is, set the boolean variable to true to show the large newspaper images
			if(x >= intPaper1X && x <= intPaper1X + Game6Panel.imgPaperSmall1.getWidth() && y >= intPaper1Y && y <= intPaper1Y + Game6Panel.imgPaperSmall1.getHeight()){
				intPaperIndex = 1;
				blnDraggingPaper = true;
			} else if(x >= intPaper2X && x <= intPaper2X + Game6Panel.imgPaperSmall2.getWidth() && y >= intPaper2Y && y <= intPaper2Y + Game6Panel.imgPaperSmall2.getHeight()){
				intPaperIndex = 2;
				blnDraggingPaper = true;
			} else if(x >= intPaper3X && x <= intPaper3X + Game6Panel.imgPaperSmall3.getWidth() && y >= intPaper3Y && y <= intPaper3Y + Game6Panel.imgPaperSmall3.getHeight()){
				intPaperIndex = 3;
				blnDraggingPaper = true;
			} else if(x >= intPaper4X && x <= intPaper4X + Game6Panel.imgPaperSmall4.getWidth() && y >= intPaper4Y && y <= intPaper4Y + Game6Panel.imgPaperSmall4.getHeight()){
				intPaperIndex = 4;
				blnDraggingPaper = true;
			}
		}
	}
	public void mouseClicked(MouseEvent evt){
		// Get the mouse x, y coordination
		int x = evt.getX();
		int y = evt.getY();
    
		// Game 5 (beat)
		if (Game5Panel.isVisible()) {
			Game5Panel.blnShowWheel = false;
			// Check if the mouse cursor is within the right jog wheel region
			// If it is, count the no. of the player's clicks as the beat
			// The recording period is 1 second for each beat
			// There is 1 second idle time between each beat
			if (x >= 620 && x <= 880 && y >= 240 && y <= 400){
				// Count the no. of player's click within 1 second
				intCurrentClicks++;
			}			
			if (patternTimer != null) {
				// Stop recording the beats
				patternTimer.stop();
			}		
			patternTimer.setRepeats(false);
			patternTimer.start();
			repaint();
			if (Game5Panel.strResult != ""){
				// Close the game 5 View and return to the path 3
				if (x >= 760 && x <= 787 && y >= 310 && y <= 339){
					Game5Panel.setVisible(false);
					// Set the help screen index as 3
					intCurrentScreen = 3;
					repaint();
				}
			}
		} else if (Game6Panel.isVisible()) {
			// Close the game 6 View and return to the path 3
			if (Game6Panel.blnShowPaper1 == true && Game6Panel.blnShowPaper2 == true && Game6Panel.blnShowPaper3 == true && Game6Panel.blnShowPaper4 == true){
				if (x >= 760 && x <= 782 && y >= 310 && y <= 330){
					Game6Panel.setVisible(false);
					intCurrentScreen = 3;	
					repaint();
				}	
			}
		}
	}	
	public void mouseDragged(MouseEvent evt){
		// Game 6 (puzzle)
		// Verify each piece of paper location. If it is in the right area, show the larger image and snap the image to the frame.
		if(Game6Panel.isVisible() && blnDraggingPaper){
			intPaperX = evt.getX();
			intPaperY = evt.getY();
			if (intPaperIndex == 1){				
				// Paper 1
				if (intPaperX >= 50 && intPaperX <= 80 && intPaperY >= 90 && intPaperY <= 120) {
					// When it is in the right area, set the x, y coordination of the snap large image
					intPaperX = 50;
					intPaperY = 50;
					Game6Panel.blnShowPaper1 = true;
				} else {
					// When it is not in the right area, update the x, y coordination of the piece of newspaper
					Game6Panel.intPaper1X = intPaperX;
					Game6Panel.intPaper1Y = intPaperY;
					intPaper1X = intPaperX;
					intPaper1Y = intPaperY;
				}
			} else if (intPaperIndex == 2){
				// Paper 2
				if (intPaperX >= 196 && intPaperX <= 226 && intPaperY >= 90 && intPaperY <= 120) {
					// When it is in the right area, set the x, y coordination of the snap large image
					intPaperX = 196;
					intPaperY = 50;
					Game6Panel.blnShowPaper2 = true;
				} else {
					// When it is not in the right area, update the x, y coordination of the piece of newspaper
					Game6Panel.intPaper2X = intPaperX;
					Game6Panel.intPaper2Y = intPaperY;
					intPaper2X = intPaperX;
					intPaper2Y = intPaperY;
				}
			}  else if (intPaperIndex == 3){
				// Paper 3
				if (intPaperX >= 50 && intPaperX <= 80 && intPaperY >= 321 && intPaperY <= 351) {
					// When it is in the right area, set the x, y coordination of the snap large image
					intPaperX = 50;
					intPaperY = 281;
					Game6Panel.blnShowPaper3 = true;
				} else {
					// When it is not in the right area, update the x, y coordination of the piece of newspaper
					Game6Panel.intPaper3X = intPaperX;
					Game6Panel.intPaper3Y = intPaperY;
					intPaper3X = intPaperX;
					intPaper3Y = intPaperY;
				}
			}  else if (intPaperIndex == 4){
				// Paper 4
				if (intPaperX >= 188 && intPaperX <= 218 && intPaperY >= 358 && intPaperY <= 388) {
					// When it is in the right area, set the x, y coordination of the snap large image
					intPaperX = 188;
					intPaperY = 318;
					Game6Panel.blnShowPaper4 = true;
				} else {
					// When it is not in the right area, update the x, y coordination of the piece of newspaper
					Game6Panel.intPaper4X = intPaperX;
					Game6Panel.intPaper4Y = intPaperY;
					intPaper4X = intPaperX;
					intPaper4Y = intPaperY;
				}
			}
			if (Game6Panel.blnShowPaper1 == true && Game6Panel.blnShowPaper2 == true && Game6Panel.blnShowPaper3 == true && Game6Panel.blnShowPaper4 == true){
				// All 4 pieces of paper are located correctly. Set the Game 6 is completed.
				blnGame6Completed = true;
			} else {
				// All 4 pieces of paper are not located correctly
				blnGame6Completed = false;
			}			
			repaint();
		}
	}
	public void mouseMoved(MouseEvent evt){
	}  
	public void actionPerformed(ActionEvent evt){
		// Check if the player clicks the objects and verify the click sequence only if the player has not completed the click sequence
		if ((evt.getSource() == CampfireBut || evt.getSource() == RadioBut || evt.getSource() == JacketBut || evt.getSource() == PhoneBut || evt.getSource() == JournalBut || evt.getSource() == KnifeBut || evt.getSource() == FlowerBut) && blnCompleteClick == false) {			
			if (evt.getSource() == CampfireBut) {
				strObjName = "Campfire";
				CampfireBut.setIcon(new ImageIcon("./images/Campfire2.png"));
				blnRightFlow = true;			
				blnResetFlow = true;
			} else if (evt.getSource() == RadioBut) {
				CheckClickFlow("Radio");
			} else if (evt.getSource() == JacketBut) {
				CheckClickFlow("Jacket");
			} else if (evt.getSource() == PhoneBut) {
				CheckClickFlow("Phone");
			} else if (evt.getSource() == JournalBut) {
				CheckClickFlow("Journal");
			} else if (evt.getSource() == KnifeBut) {
				CheckClickFlow("Knife");
			} else if (evt.getSource() == FlowerBut) {
				CheckClickFlow("Flower");
				// To complete the click sequence, the player must follow the right flow to click and complete the 2 minigames
				if (blnRightFlow == true && blnGame5Completed == true && blnGame6Completed == true) {
					blnCompleteClick = true;
				}				
			}
			// Get and display the hint from the 2D array in the Model
			if (blnRightFlow == true){
				TheTextArea.setText(Path3Data.GetData(strObjName, 5));
			} else {
				TheTextArea.setText("The campfire keeps us warm.");
			}				
			imgTextBox.setVisible(true);				
			TheTextArea.setVisible(true);
			
			// If the object indicates a game, show the corresponding game panel
			if ((Path3Data.GetData(strObjName, 3).equals("beat") && blnRightFlow == true)) {
				// Show the Game 5 (beat) and link the data from the Model
				// Assign the help screen index as 8
				intCurrentScreen = 8;	
				Path3Data.Game5Beat();	
				Game5MessageTimer.start();	
				ResetView();				
				Game5Panel.setVisible(true);	
				Game5Panel.strBeat = "Press the right jog wheel to create patterns of " + Path3Data.intBeat1 + ", " + Path3Data.intBeat2 + ", " + Path3Data.intBeat3 + " beats.";
				Game5Panel.strResult = "";			
				intTarget[0] = Path3Data.intBeat1;
				intTarget[1] = Path3Data.intBeat2;
				intTarget[2] = Path3Data.intBeat3;
				intCurrentPattern = 0;
				intCurrentClicks = 0;
				repaint();
			} else if ((Path3Data.GetData(strObjName, 3).equals("puzzle") && blnRightFlow == true)) {
				// Show the Game 6 (puzzle) and link the data from the Model
				// Assign the help screen index as 9
				intCurrentScreen = 9;		
				ResetView();
				Game6Panel.strMessage = Path3Data.GetData(strObjName, 5);	
				Game6Panel.setVisible(true);
				intPaper1X = Game6Panel.intPaper1X;
				intPaper1Y = Game6Panel.intPaper1Y;
				intPaper2X = Game6Panel.intPaper2X;
				intPaper2Y = Game6Panel.intPaper2Y;
				intPaper3X = Game6Panel.intPaper3X;
				intPaper3Y = Game6Panel.intPaper3Y;
				intPaper4X = Game6Panel.intPaper4X;
				intPaper4Y = Game6Panel.intPaper4Y;
				repaint();
			}			
		} else if (evt.getSource() == patternTimer) {
			// Game 5 (beat) timer. This is used for recording the beats created by the player.
			intBeat[intCurrentPattern] = intCurrentClicks;
			intCurrentPattern++;
			intCurrentClicks = 0;
			if (intCurrentPattern == 3) {
				// Check if all the 3 beats are records
				// Check if each second has correct beat created
				blnCorrect = true;
				for (int i = 0; i < 3; i++) {
					if (intTarget[i] != intBeat[i]) {
						blnCorrect = false;
					}
				}
				// Display the result
				if (blnCorrect) {
					Game5Panel.strResult = "You are a genius DJ!";
					blnGame5Completed = true;
				} else {
					Game5Panel.strResult = "Try again next time...";
					blnGame5Completed = false;
				}
				patternTimer.stop();
				intCurrentPattern = 0;
				intCurrentClicks = 0;
				repaint();
			}
		} else if (evt.getSource() == Game5MessageTimer) {
			// Game 5 (beat) message animation timer
			if (Game5Panel.intPosY > 50){
				Game5Panel.intPosY = Game5Panel.intPosY - 2;
				repaint();
			} else {
				// Stop the timer when the message is located at the right position
				Game5MessageTimer.stop();
			}
		}
	}
	public void CheckClickFlow(String strObj) {
		// Verify the click sequence of the objects
		// Compare the object with the 2D array in the Model
		if (Path3Data.GetData(strObjName, 6).equals(strObj)) {
			if (blnResetFlow == true){
				blnRightFlow = true;
			}
		} else {
			blnResetFlow = false;
			blnRightFlow = false;
			blnCompleteClick = false;
		}
		strObjName = strObj;
	}
	public int GetCurrentHelpPage() {
		// Return the help screen index to the main window and display the corresponding help page based on the index value
		return intCurrentScreen;
	}
	public boolean Path3Complete() {
		// Return the result of path 3 if it has corect click sequence, the beat and puzzle games are completed
		if (blnGame5Completed == true && blnGame6Completed == true && blnCompleteClick == true) {
			return true;
		} else {
			return false;
		}		
	}
	  
	public Path3(){
		// Controller links the View
        setLayout(null);
        Path3Data.CreateArray();
        
        // Load the background image file
        try{
			imgPath3Background = ImageIO.read(new File("images/Path3 Background.png"));
		}catch(IOException e){
			System.out.println("Unable to load the Path 3 image");
		}   
		
		// Create the Game 5 beat game View
		Game5Panel = new Game5();
        Game5Panel.setBounds(0, 0, intGameWidth, intGameHeight);
        Game5Panel.addMouseListener(this);
        add(Game5Panel);  
        
        // Create the Game 5 puzzle game View
        Game6Panel = new Game6();
        Game6Panel.setBounds(0, 0, intGameWidth, intGameHeight);
        Game6Panel.addMouseListener(this);
		Game6Panel.addMouseMotionListener(this);
        add(Game6Panel); 
             
		// Create the hint message box on the screen. This will be displayed when the player clicks an object
		TheTextArea.setBounds(220, 335, 610, 100);
		imgTextBox.setBounds(200, 320, 647, 111);		//Draw of the text box		
		TheTextArea.setFont(new Font("Arial", Font.PLAIN, 24));
		TheTextArea.setForeground(Color.WHITE);
		TheTextArea.setEditable(false);
		TheTextArea.setLineWrap(true);
		TheTextArea.setWrapStyleWord(true);
		TheTextArea.setOpaque(false); 
		imgTextBox.setVisible(false);			//Make both invisible until they are needed
		TheTextArea.setVisible(false);
		add(TheTextArea);
		add(imgTextBox);		
        
        // Show the onscreen object based on the location in the 2D array from the Model
        // Create the clickable campfire object
        CampfireBut = new JButton(new ImageIcon("./images/Campfire.png"));
        CampfireBut.setBounds(Integer.parseInt(Path3Data.GetData("Campfire", 1)), Integer.parseInt(Path3Data.GetData("Campfire", 2)), 121, 226);
        CampfireBut.addActionListener(this);
        CampfireBut.setVisible(true);
        CampfireBut.setContentAreaFilled(false);		//Make the button background invisible
		CampfireBut.setBorderPainted(false);
        add(CampfireBut);
        
        // Create the clickable radio object
        RadioBut = new JButton(new ImageIcon("./images/Radio.png"));
        RadioBut.setBounds(Integer.parseInt(Path3Data.GetData("Radio", 1)), Integer.parseInt(Path3Data.GetData("Radio", 2)), 105, 88);
        RadioBut.addActionListener(this);
        RadioBut.setVisible(true);
        RadioBut.setContentAreaFilled(false);		
		RadioBut.setBorderPainted(false);        
        add(RadioBut);
        
        // Create the clickable jacket object
        JacketBut = new JButton(new ImageIcon("./images/Jacket.png"));
        JacketBut.setBounds(Integer.parseInt(Path3Data.GetData("Jacket", 1)), Integer.parseInt(Path3Data.GetData("Jacket", 2)), 147, 262);
        JacketBut.addActionListener(this);
        JacketBut.setVisible(true);
        JacketBut.setContentAreaFilled(false);		
		JacketBut.setBorderPainted(false);        
        add(JacketBut);
        
        // Create the clickable phone object
        PhoneBut = new JButton(new ImageIcon("./images/Phone.png"));
        PhoneBut.setBounds(Integer.parseInt(Path3Data.GetData("Phone", 1)), Integer.parseInt(Path3Data.GetData("Phone", 2)), 78, 82);
        PhoneBut.addActionListener(this);
        PhoneBut.setVisible(true);
        PhoneBut.setContentAreaFilled(false);		
		PhoneBut.setBorderPainted(false);        
        add(PhoneBut);
        
        // Create the clickable journal object
        JournalBut = new JButton(new ImageIcon("./images/Journal.png"));
        JournalBut.setBounds(Integer.parseInt(Path3Data.GetData("Journal", 1)), Integer.parseInt(Path3Data.GetData("Journal", 2)), 97, 119);
        JournalBut.addActionListener(this);
        JournalBut.setVisible(true);
        JournalBut.setContentAreaFilled(false);		
		JournalBut.setBorderPainted(false);        
        add(JournalBut);
        
        // Create the clickable knife object
        KnifeBut = new JButton(new ImageIcon("./images/Knife.png"));
        KnifeBut.setBounds(Integer.parseInt(Path3Data.GetData("Knife", 1)), Integer.parseInt(Path3Data.GetData("Knife", 2)), 77, 64);
        KnifeBut.addActionListener(this);
        KnifeBut.setVisible(true);
        KnifeBut.setContentAreaFilled(false);	
		KnifeBut.setBorderPainted(false);        
        add(KnifeBut);
        
        // Create the clickable flower object
        FlowerBut = new JButton(new ImageIcon("./images/Flower.png"));
        FlowerBut.setBounds(Integer.parseInt(Path3Data.GetData("Flower", 1)), Integer.parseInt(Path3Data.GetData("Flower", 2)), 209, 194);
        FlowerBut.addActionListener(this);
        FlowerBut.setVisible(true);
        FlowerBut.setContentAreaFilled(false);
		FlowerBut.setBorderPainted(false);           
        add(FlowerBut);       
	}
	
	public void ResetView(){
		Game5Panel.setVisible(false);
		Game6Panel.setVisible(false);
	}
}
