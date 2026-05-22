//Introduction View
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class IntroductionView implements ActionListener, MouseListener, MouseMotionListener{
	//Properties to build the panel
	JIntroductionScreen IntroPanel = new JIntroductionScreen();
	Timer theTimer = new Timer(1000/60, this);
	
	JButton HelpButton = new JButton("Help");
	
	//Do I need this or should it go in the main?
	//JTextArea theChat = new JTextArea();
	//JScrollPane theChatScroll = new JScrollPane(theChat);
	//JTextField SendChat = new JTextField();
	
	boolean blnGameBegin = false;
	
	//Methods
	public void actionPerformed (ActionEvent evt){
		IntroPanel.repaint();
	}
	public void mousePressed(MouseEvent evt) {
		
	}
	
	public void mouseDragged(MouseEvent evt) {
      
    }

    public void mouseReleased(MouseEvent evt) {
    
    } 
    
    public void mouseClicked(MouseEvent evt) {
		System.out.println(evt.getX()+" , "+evt.getY());
		//When ever user Clicks, check if they are overlaying the deer
		if(evt.getX() >= 510 && evt.getX() <= 539 && evt.getY() >= 460 && evt.getY()<= 520){			//Check if it overalays the head
			blnGameBegin = true;
			System.out.println("Game Begin");
		}else if(evt.getX() >=510 && evt.getX() <= 656 && evt.getY() >= 541 && evt.getY() < 595){		//Check if it overlays the body
			blnGameBegin = true;
			System.out.println("Game Begin");
		}
	}
    public void mouseEntered(MouseEvent evt) {
		theTimer.start();
	}
    public void mouseExited(MouseEvent evt){
		theTimer.stop();
	}
    public void mouseMoved(MouseEvent evt) {
		//Have the target follow the mouse as long as it is inside the bounds of the black frame
		if(evt.getX() < 936 && evt.getX() > 74 && evt.getY() > 77 && evt.getY() < 648){
			IntroPanel.intMouseX = evt.getX()- 60;
			IntroPanel.intMouseY = evt.getY()- 50;
		}
	}
	
	//Constructor
	public IntroductionView(){
		IntroPanel.setLayout(null);
		IntroPanel.setPreferredSize(new Dimension(1280, 720));
		
		IntroPanel.addMouseMotionListener(this);
		IntroPanel.addMouseListener(this);
		
		HelpButton.setBounds(1000,20,250,50);
		HelpButton.setBackground(Color.GRAY);
		HelpButton.addActionListener(this);
		IntroPanel.add(HelpButton);
		
		theTimer.start();
		
		
	}
}
