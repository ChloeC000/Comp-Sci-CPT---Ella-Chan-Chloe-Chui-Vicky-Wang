import java.awt.*;
import javax.swing.*;

public class Game3 extends JPanel{
	// Properties

	// Methods
	// Paint the screen
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		// Create the screen
		g.setColor(Color.ORANGE);
		g.fillRect(0, 0, 955, 720);
		g.setFont(new Font("Arial", Font.BOLD, 30));  
		g.setColor(Color.BLACK);
		g.drawString("Game 3", 100, 100);
	}
	
	// Constructor
	public Game3(){
		super();
	}
}
