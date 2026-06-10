/*
Course: ICS4U1b Computer Science
Teacher: Mr. Alfred Ron Cadawas
Memebers: Chloe Chui, Ella Chan, Vicky Wang
Assignment Name: CPT

This is the Controller for Path 2 program. This program links the View and Model.
*/

// Import the IO, swing and JComponents libaraies
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;

public class Path2 extends JPanel implements ActionListener{
	// Properties
	// Create the path 2 game play panel
	int intGameWidth = 955;
	int intGameHeight = 720;
	// Set the game objects
	String strObjName = "";
	// Varibles for the game complete and click sequence flow checking
	boolean blnCompleteClick = false;   // Indicates if the player clicks the all the object and complete the whole flow 
	boolean blnRightFlow;               // Indicates if the player clicks the right object
	boolean blnResetFlow;               // Indicates the player clicking a wrong object and restarting the sequence again
	boolean blnResult;                  // Indicates if the players finishes the game result correctly
	boolean blnGame3Completed;          // Indicates if the game 3 (word guessing) is completes correctly
	boolean blnGame4Completed;          // Indicates if the game 4 (riddle) is completes correctly
	// Create game background image and the onscreen objects
	BufferedImage imgPath2Background = null;
	JButton NestBut;
	JButton OwlBut;
	JButton ClueBut;
	JButton Statue1But;
	JButton Statue2But;
	JButton Statue3But;
	JButton Flute1But;
	JButton BackBut;
	// Set the program to 60fps
	Timer GameMessageTimer = new Timer(1000/60, this);
	JTextArea TheTextArea = new JTextArea("");
	JLabel imgTextBox = new JLabel(new ImageIcon("./images/Text Box.png"));
	// Create the game 3 and 4 Views
    Game3 Game3Panel;
    Game4 Game4Panel;    
    // Create the Model
    PathModel Path2Data = new PathModel("Path2.csv");
    
	// Methods
	// Paint the background image
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		// Create the screen
		g.drawImage(imgPath2Background, 0, 0, null);
	}	
	public void actionPerformed(ActionEvent evt){
		// Check if the player clicks the objects and verify the click sequence only if the player has not completed the click sequence
		if ((evt.getSource() == NestBut || evt.getSource() == OwlBut || evt.getSource() == ClueBut || evt.getSource() == Statue1But || evt.getSource() == Statue2But || evt.getSource() == Statue3But || evt.getSource() == Flute1But) && blnCompleteClick == false) {
			if (evt.getSource() == NestBut) {
				strObjName = "nest";
				blnRightFlow = true;			
				blnResetFlow = true;
				blnCompleteClick = false;
			} else if (evt.getSource() == OwlBut) {
				CheckClickFlow("owl");
			} else if (evt.getSource() == Flute1But) {
				CheckClickFlow("flute");
			} else if (evt.getSource() == Statue1But) {
				CheckClickFlow("statue1");
			} else if (evt.getSource() == Statue2But) {
				CheckClickFlow("statue2");
			} else if (evt.getSource() == Statue3But) {
				CheckClickFlow("statue3");
			} else if (evt.getSource() == ClueBut) {
				CheckClickFlow("clue");
				// To complete the click sequence, the player must follow the right flow to click and complete the 2 minigames				
				if (blnRightFlow == true && blnGame3Completed == true && blnGame4Completed == true) {					
					blnCompleteClick = true;
				}				
			}
			// Get and display the hint from the 2D array in the Model
			TheTextArea.setText(Path2Data.GetData(strObjName, 5)); 		
			imgTextBox.setVisible(true);				
			TheTextArea.setVisible(true);
			
			// If the object indicates a game, show the corresponding game panel
			if ((Path2Data.GetData(strObjName, 3).equals("riddle") && blnRightFlow == true)) {
				// Show the riddle game
				Game4Panel.setVisible(true);
				GameMessageTimer.start();
			} else if ((Path2Data.GetData(strObjName, 3).equals("word") && blnRightFlow == true)) {
				// Show the word guessing game
				Game3Panel.strQuestion = Path2Data.GetData(strObjName, 5);
				Game3Panel.setVisible(true);
				GameMessageTimer.start();
			}
		} else if (evt.getSource() == GameMessageTimer) {
			// Control the View and activate the animation of the word guessing game
			if (Game4Panel.isVisible() == true){
				if (Game4Panel.intPosX <= 100){
					Game4Panel.intPosX = Game4Panel.intPosX + 5;
					repaint();
				} else {
					GameMessageTimer.stop();
				}	
			} else if (Game3Panel.isVisible() == true){
				// Control the View and activate the animation of the riddle
				if (Game3Panel.intPosX <= 100){
					Game3Panel.intPosX = Game3Panel.intPosX + 5;
					repaint();
				} else {
					GameMessageTimer.stop();
				}
			}	
		// Show the close button of the riddle game if the player choose an option and then check the answer
		} else if (evt.getSource() == Game4Panel.radioChoice[0]) {			
			Game4Panel.CloseBut.setVisible(true);
			CheckGame4Answer(0);
		} else if (evt.getSource() == Game4Panel.radioChoice[1]) {
			Game4Panel.CloseBut.setVisible(true);
			CheckGame4Answer(1);
		} else if (evt.getSource() == Game4Panel.radioChoice[2]) {
			Game4Panel.CloseBut.setVisible(true);
			CheckGame4Answer(2);
		} else if (evt.getSource() == Game4Panel.radioChoice[3]) {
			Game4Panel.CloseBut.setVisible(true);
			CheckGame4Answer(3);
		} else if (evt.getSource() == Game4Panel.CloseBut) {
			// Close the riddle game panel
			// Verify if the game is completed
			if (blnResult == true){
				blnGame4Completed = true;
			} else {
				blnGame4Completed = false;
			}
			Game4Panel.setVisible(false);
		} else if (evt.getSource() == Game3Panel.CloseBut) {
			// Close the word guessing game panel
			Game3Panel.setVisible(false);
		} else if (evt.getSource() == Game3Panel.OKBut) {
			// Check and display the word guessing result
			// Verify if the game is completed
			if (Game3Panel.AnswerBox.getText().toLowerCase().equals(Path2Data.GetData(strObjName, 4))){
				Game3Panel.strResult = "You are amazing!";
				blnGame3Completed = true;
			} else {
				Game3Panel.strResult = "Hm... Try again later...";
				blnGame3Completed = false;
			}
		}
	}
	public void CheckClickFlow(String strObj) {
		// Verify the click sequence of the objects
		// Compare the object with the 2D array in the Model
		if (Path2Data.GetData(strObjName, 6).equals(strObj)) {
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
	private void CheckGame4Answer(int intChoice){
		// Verify the riddle answer
		// Compare the selected option with the 2D array data in the Model
		int intCorrectAnswer = Integer.parseInt(Game4Panel.strChoice[4]);

		if (intChoice == intCorrectAnswer){
			Game4Panel.strResult = "You have got a new clue.";		
			blnResult = true;	
		}else{
			Game4Panel.strResult = "Your answer is not correct.";			
			blnResult = false;
		}
		Game4Panel.repaint();
	}	
	public boolean Path2Complete() {
		// Return the result of path 2 if it has corect click sequence, the riddle and word guessing games are completed
		if (blnGame3Completed == true && blnGame4Completed == true && blnCompleteClick == true) {
			return true;
		} else {
			return false;
		}				
	}

	// Constructor
	public Path2(){
		// Controller links the View
		setLayout(null);
		Path2Data.CreateArray();
		
		// Load the background image file
		try{
			imgPath2Background = ImageIO.read(new File("./images/Path2 Background.png"));
		}catch(IOException e){
			System.out.println("Unable to load the Path 2 image");
		}
		
		// Create the Game 3 word guessing game View
		Game3Panel = new Game3();
        Game3Panel.setBounds(0, 0, intGameWidth, intGameHeight);
        Game3Panel.setVisible(false);
        Game3Panel.setOpaque(false);
        Game3Panel.OKBut.addActionListener(this);
        Game3Panel.CloseBut.addActionListener(this);
        add(Game3Panel);     
        
        // Create the Game 3 riddle game View
        Game4Panel = new Game4();
        Game4Panel.setBounds(0, 0, intGameWidth, intGameHeight);
        Game4Panel.setVisible(false);
        Game4Panel.CloseBut.addActionListener(this);
        Game4Panel.CloseBut.setVisible(false);
        // Link the Model and get the riddle info from the 2D array 
        strObjName = "owl";
		Game4Panel.strChoice = Path2Data.GetData(strObjName, 4).split("\\|");
		Game4Panel.strRiddle = Path2Data.GetData(strObjName, 5);
        Game4Panel.Init();
        // Create the options by radio buttons
		Game4Panel.radioChoice[0].addActionListener(this);
		Game4Panel.radioChoice[1].addActionListener(this);
		Game4Panel.radioChoice[2].addActionListener(this);
		Game4Panel.radioChoice[3].addActionListener(this);
        Game4Panel.setOpaque(false);
        add(Game4Panel);
		
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
		// Create the clickable nest object
		NestBut = new JButton(new ImageIcon("./images/Nest.png"));
        NestBut.setBounds(Integer.parseInt(Path2Data.GetData("nest", 1)), Integer.parseInt(Path2Data.GetData("nest", 2)), 90, 75);
        NestBut.addActionListener(this);
        NestBut.setVisible(true);
        NestBut.setContentAreaFilled(false);
		NestBut.setBorderPainted(false);
        add(NestBut);
        
        // Create the clickable owl object
        OwlBut = new JButton(new ImageIcon("./images/Owl.png"));
        OwlBut.setBounds(Integer.parseInt(Path2Data.GetData("owl", 1)), Integer.parseInt(Path2Data.GetData("owl", 2)), 110, 183);
        OwlBut.addActionListener(this);
        OwlBut.setVisible(true);
        OwlBut.setContentAreaFilled(false);	
		OwlBut.setBorderPainted(false);
        add(OwlBut);
        
        // Create the clickable clue object
        ClueBut = new JButton(new ImageIcon("./images/Clue.png"));
        ClueBut.setBounds(Integer.parseInt(Path2Data.GetData("clue", 1)), Integer.parseInt(Path2Data.GetData("clue", 2)), 97, 98);
        ClueBut.addActionListener(this);
        ClueBut.setVisible(true);
        ClueBut.setContentAreaFilled(false);
		ClueBut.setBorderPainted(false);
        add(ClueBut);
        
        // Create the clickable statue 1 object
        Statue1But = new JButton(new ImageIcon("./images/Statue1.png"));
        Statue1But.setBounds(Integer.parseInt(Path2Data.GetData("statue1", 1)), Integer.parseInt(Path2Data.GetData("statue1", 2)), 110, 146);
        Statue1But.addActionListener(this);
        Statue1But.setVisible(true);
        Statue1But.setContentAreaFilled(false);
		Statue1But.setBorderPainted(false);
        add(Statue1But);
        
        // Create the clickable statue 2 object
        Statue2But = new JButton(new ImageIcon("./images/Statue2.png"));
        Statue2But.setBounds(Integer.parseInt(Path2Data.GetData("statue2", 1)), Integer.parseInt(Path2Data.GetData("statue2", 2)), 115, 286);
        Statue2But.addActionListener(this);
        Statue2But.setVisible(true);
        Statue2But.setContentAreaFilled(false);
		Statue2But.setBorderPainted(false);
        add(Statue2But);
        
        // Create the clickable statue 3 object
        Statue3But = new JButton(new ImageIcon("./images/Statue3.png"));
        Statue3But.setBounds(Integer.parseInt(Path2Data.GetData("statue3", 1)), Integer.parseInt(Path2Data.GetData("statue3", 2)), 100, 150);
        Statue3But.addActionListener(this);
        Statue3But.setVisible(true);
        Statue3But.setContentAreaFilled(false);
		Statue3But.setBorderPainted(false);
        add(Statue3But);
        
        // Create the clickable flute object
        Flute1But = new JButton(new ImageIcon("./images/Flute.png"));
        Flute1But.setBounds(Integer.parseInt(Path2Data.GetData("flute", 1)), Integer.parseInt(Path2Data.GetData("flute", 2)), 86, 98);
        Flute1But.addActionListener(this);
        Flute1But.setVisible(true);
        Flute1But.setContentAreaFilled(false);
		Flute1But.setBorderPainted(false);
        add(Flute1But);
   	}
}
