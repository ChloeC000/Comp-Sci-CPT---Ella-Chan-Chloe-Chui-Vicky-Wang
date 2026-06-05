import java.awt.*;
import javax.swing.*;

public class Game1 extends JPanel{
	// Properties

	// Methods
	// Paint the screen
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		// Create the screen
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, 955, 720);
		g.setFont(new Font("Arial", Font.BOLD, 30));  
		g.setColor(Color.BLACK);
		g.drawString("Game 1", 100, 100);
	}
	
	// Constructor
	public Game1(){
		super();
	}
}
