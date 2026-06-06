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
	String[] strChoice = {"", "", "", ""};
	int intPosX;
	JRadioButton[] radioChoice = new JRadioButton[4];
	JButton CloseBut = new JButton(new ImageIcon("./images/Exit Button.png"));

	// Methods
	// Paint the screen
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		// Create the screen
		g.setColor(Color.WHITE);
		g.drawImage(imgBackground, 0, 0, null);
		g.setFont(new Font("Arial", Font.BOLD, 30));  
		g.drawString(strRiddle, intPosX, 220);		
		g.drawString(strResult, 100, 500);
	}
	public void Init(){
		ButtonGroup rbGroup = new ButtonGroup();
		int x = 100;
        int y = 280;
        setFont(new Font("Arial", Font.BOLD, 30)); 
		for (int i = 0; i < 4; i++) {
            radioChoice[i] = new JRadioButton(strChoice[i]);
            radioChoice[i].setBounds(150, y + (i * 35), 500, 35);
            radioChoice[i].setForeground(Color.WHITE);
            radioChoice[i].setFont(new Font("Arial", Font.PLAIN, 30));
            radioChoice[i].setOpaque(false);
            rbGroup.add(radioChoice[i]);
            add(radioChoice[i]);
        }
	}
	
	// Constructor
	public Game4(){
		super();		
		setLayout(null);
		intPosX = -200;
		CloseBut.setBounds(800, 210, 32, 32);
		CloseBut.setContentAreaFilled(false);		//Make the button background invisible
		CloseBut.setBorderPainted(false);
		add(CloseBut);
		try{
			imgBackground = ImageIO.read(new File("./Images/Game4Background.png"));
		}catch(IOException e){
			System.out.println("Unable to game 4 background image");
		}
	}
}
