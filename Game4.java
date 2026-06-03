import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class Game4 extends JPanel {
	// Properties
	BufferedImage imgBackground = null;
	String strRiddle = "";
	String strResult = "";
	String[] strChoice;
	int intPosX;
	JButton SubmitBut;
	JRadioButton[] radioChoice = new JRadioButton[4];

	// Methods
	// Paint the screen
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		// Create the screen
		g.setColor(Color.WHITE);
		g.drawImage(imgBackground, 0, 0, null);
		g.setFont(new Font("Arial", Font.BOLD, 30));  
		g.drawString(strRiddle, intPosX, 250);		
		g.drawString(strResult, 10, 600);
	}
	public void Init(){
		ButtonGroup rbGroup = new ButtonGroup();
		int x = 20;
        int y = 520;
		for (int i = 0; i < 4; i++) {
			System.out.println(strChoice[i]);
            radioChoice[i] = new JRadioButton(strChoice[i]);
            radioChoice[i].setBounds(x, y + (i * 25), 150, 20);
            rbGroup.add(radioChoice[i]);
            add(radioChoice[i]);
        }
	}
	
	// Constructor
	public Game4(){
		super();		
		setLayout(null);
		intPosX = -200;
		SubmitBut = new JButton("Submit");
        SubmitBut.setBounds(800, 650, 100, 25);
        add(SubmitBut);  
        
		try{
			imgBackground = ImageIO.read(new File("./Images/Game4Background.png"));
		}catch(IOException e){
			System.out.println("Unable to game 4 background image");
		}
	}
}
