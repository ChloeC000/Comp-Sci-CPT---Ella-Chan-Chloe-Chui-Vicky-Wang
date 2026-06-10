/*
Course: ICS4U1b Computer Science
Teacher: Mr. Alfred Ron Cadawas
Memebers: Chloe Chui, Ella Chan, Vicky Wang
Assignment Name: CPT

This is the Game 3 (word guessing) View.
*/

// Import the IO, swing and JComponents libaraies
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class Game3 extends JPanel{
	// Properties
	BufferedImage imgBackground = null;
	// Store the question and result
	String strQuestion = "";
	String strResult = "";
	// For the animation x position effect
	int intPosX;
	JButton CloseBut = new JButton(new ImageIcon("./images/Exit Button.png"));
	JButton OKBut = new JButton(new ImageIcon("./images/OKButton.png"));
	// Accept the player's answer
	TextField AnswerBox = new TextField();

	// Methods
	// Paint the screen
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		// Create the screen		
		g.drawImage(imgBackground, 0, 0, null);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.PLAIN, 23));  
		g.drawString(strQuestion, intPosX, 320);
		g.drawString(strResult, 180, 450);
	}
	
	// Constructor
	public Game3(){
		super();
		setLayout(null);
		// Create the close button
		CloseBut.setBounds(820, 270, 32, 32);
		CloseBut.setContentAreaFilled(false);		//Make the button background invisible
		CloseBut.setBorderPainted(false);
		add(CloseBut);
		
		// Create the text field for inputting the answer
		AnswerBox.setBounds(180, 370, 500, 50);
		AnswerBox.setFont(new Font("Arial", Font.BOLD, 30));
		add(AnswerBox);
		
		// Create the OK button
        OKBut.setBounds(700, 370, 128, 50);
        OKBut.setVisible(true);
        OKBut.setContentAreaFilled(false);	
		OKBut.setBorderPainted(false);
        add(OKBut);
        
        // Init the question x position
		intPosX = -50;
		// Create the game background image
		try{
			imgBackground = ImageIO.read(new File("./Images/Game3Background.png"));
		}catch(IOException e){
			System.out.println("Unable to game 4 background image");
		}
	}
}
