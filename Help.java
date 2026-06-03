import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class Help extends JPanel {
	// Properties
	BufferedImage imgBackground = null;
	BufferedImage imgHelpPage = null;
	JButton BackBut;

	// Methods
	// Paint the screen
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		// Create the screen
		g.setColor(Color.WHITE);
		g.drawImage(imgBackground, 0, 0, null);
		g.setFont(new Font("Arial", Font.BOLD, 30));  
	}
	public void ShowHelp(int intPageIndex){
		System.out.println("Help " + intPageIndex);
		try{
			if (intPageIndex == 0) {
				imgBackground = ImageIO.read(new File("./Images/IntroHelp.png"));
			} else if (intPageIndex == 1) {
				imgBackground = ImageIO.read(new File("./Images/Path1Help.png"));
			} else if (intPageIndex == 2) {
				imgBackground = ImageIO.read(new File("./Images/Path2Help.png"));
			} else if (intPageIndex == 3) {
				imgBackground = ImageIO.read(new File("./Images/Path3Help.png"));
			} else if (intPageIndex == 4) {
				imgBackground = ImageIO.read(new File("./Images/Game1Help.png"));
			} else if (intPageIndex == 5) {
				imgBackground = ImageIO.read(new File("./Images/Game2Help.png"));
			} else if (intPageIndex == 6) {
				imgBackground = ImageIO.read(new File("./Images/Game3Help.png"));
			} else if (intPageIndex == 7) {
				imgBackground = ImageIO.read(new File("./Images/Game4Help.png"));
			} else if (intPageIndex == 8) {
				imgBackground = ImageIO.read(new File("./Images/Game5Help.png"));
			} else if (intPageIndex == 9) {
				imgBackground = ImageIO.read(new File("./Images/Game6Help.png"));
			}
		}catch(IOException e){
			System.out.println("Unable to Help page image");
		}		
	}	
	// Constructor
	public Help(){
		super();
		setLayout(null);
		BackBut = new JButton("Back");
        BackBut.setBounds(10, 10, 120, 30);
        BackBut.setVisible(true);
        add(BackBut);
		try{
			imgBackground = ImageIO.read(new File("./Images/Help.png"));
		}catch(IOException e){
			System.out.println("Unable to Help background image");
		}
	}
}
