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
    
	JTextArea theChat = new JTextArea();
	JScrollPane theChatScroll = new JScrollPane(theChat);
	JTextField SendChat = new JTextField();
	
	//Methods
	public void actionPerformed (ActionEvent evt){
		
	}
	public void mousePressed(MouseEvent evt) {
		
	}
	
	public void mouseDragged(MouseEvent evt) {
      
    }

    public void mouseReleased(MouseEvent evt) {
    
    } 
    
    public void mouseClicked(MouseEvent evt) {
		
	}
    public void mouseEntered(MouseEvent evt) {
		
	}
    public void mouseExited(MouseEvent evt){
		
	}
    public void mouseMoved(MouseEvent evt) {
	}
	
	//Constructor
	public IntroductionView(){
		IntroPanel.setLayout(null);
		IntroPanel.setPreferredSize(new Dimension(1280, 720));
		
		HelpButton.setBounds(1000,20,250,50);
		HelpButton.setBackground(Color.GRAY);
		HelpButton.addActionListener(this);
		IntroPanel.add(HelpButton);
		
		theChatScroll.setBounds (1000,100, 250, 500);
		
		
	}
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
