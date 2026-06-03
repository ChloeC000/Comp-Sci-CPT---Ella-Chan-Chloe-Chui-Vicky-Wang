import java.awt.*;
import javax.swing.*;

public class Path1 extends JPanel{
	// Properties

	// Methods
	// Paint the screen
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		// Create the screen
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, 955, 720);
		g.setFont(new Font("Arial", Font.BOLD, 30));  
		g.setColor(Color.BLACK);
		g.drawString("Path 1", 100, 100);
	}
	
	// Constructor
	public Path1(){
		super();
	}
}
