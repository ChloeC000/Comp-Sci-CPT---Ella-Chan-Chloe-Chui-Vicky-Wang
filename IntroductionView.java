//Introduction View
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class IntroductionView implements ActionListener, MouseListener, MouseMotionListener{
	//Properties to build the panel
	JIntroductionScreen IntroPanel = new JIntroductionScreen();
	
	JButton HelpButton = new JButton("Help");
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
