import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CPTHelp extends JPanel implements ActionListener{
	// Properties
	BufferedImage imgBackground = null;
	BufferedImage imgTitle = null;
	Timer theTimer = new Timer(1000/48, this);
	private int intTitleX;
	
	// Methods
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(imgBackground, 0, 0, null);
		g.drawImage(imgTitle, intTitleX, 50, null);
	}
	// Change the text and logo X, Y location and animate the help description image
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource() == theTimer){
			intTitleX = intTitleX - 1;
			if(intTitleX == -1440){
				theTimer.stop();
			}
			repaint();
		}
	}
	public void Reset(){
		theTimer.start();
		intTitleX = 0;
	}
	
	// Constructors
	public CPTHelp(){
		super();
		// Load the backgroup image and the help description image
		try{
			imgBackground = ImageIO.read(new File("help.png"));
			imgTitle = ImageIO.read(new File("HelpTitle.png"));
		}catch(IOException e){
			System.out.println("Unable to load image");
		}		
	}
}
