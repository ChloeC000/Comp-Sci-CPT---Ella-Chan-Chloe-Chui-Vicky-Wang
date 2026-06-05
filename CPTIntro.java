import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CPTIntro extends JPanel implements ActionListener{
	// Properties
	public BufferedImage imgBackground = null;
	public BufferedImage imgTitle = null;
	Timer theTimer = new Timer(1000/48, this);
	private int intImageY;
		
	// Methods
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(imgBackground, 0, 0, null);
		g.drawImage(imgTitle, 0, intImageY, null);
	}
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource() == theTimer){
			if (intImageY > -210) {
				intImageY = intImageY - 1;
				repaint();
			} else {
				theTimer.stop();
			}			
		}	
	}
	
	// Constructors
	public CPTIntro(){
		super();
		intImageY = -30;
		theTimer.start();
		// Load the background image
		try{
			imgBackground = ImageIO.read(new File("Introduction Background.png"));
			imgTitle = ImageIO.read(new File("Game Title.png"));
		}catch(IOException e){
			System.out.println("Unable to load image");
		}		
	}
}
