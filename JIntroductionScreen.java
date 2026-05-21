import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;

public class JIntroductionScreen extends JPanel{
	//Properties
	BufferedImage imgIntroduction = null;
	
	BufferedImage imgMouse = null;
	double dblMouseX;
	double dblMouseY;
	
	//Methods
	public void paintComponent(Graphics g){
        super.paintComponent(g);
			//Draw Background
			g.drawImage(imgIntroduction, 0, 0, null);
			g.drawImage(imgMouse,700, 500, null);
    }
    
    //Constructor
    public JIntroductionScreen(){
		super();
		try{
			imgIntroduction = ImageIO.read(new File("images/Introduction Background.png"));
			imgMouse = ImageIO.read(new File("images/Target Cursor"));
		}catch(IOException e){
			System.out.println("Unable to load image");
		}
	}
}
