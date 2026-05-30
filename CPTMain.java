import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.io.*;
import javax.imageio.*;



public class CPTMain implements ActionListener{
	JFrame theFrame;
    JPanel thePanel;
	JButton StartButton = new JButton("Start");
    NetPanel netPanel;
    
    CPTIntro IntroPanel = new CPTIntro();
	CPTHelp HelpPanel = new CPTHelp();
	JButton HelpButton = new JButton("Help");
	JButton HelpBackButton = new JButton("Back");
	BufferedImage imgIntroduction = null;
	BufferedImage imgMouse = null;
	int intMouseX = 470;
	int intMouseY = 430;
		
	
	
	// Methods 
	public void actionPerformed(ActionEvent evt){
		// Main program JComponent actionPerformed
            if(evt.getSource() == StartButton){
			//JIntroductionScreen
			thePanel.revalidate();
			thePanel.repaint();
			theFrame.setContentPane(IntroPanel);	
			
			//theFrame.setContentPane(TeachingPanel);
		} else if(evt.getSource() == HelpButton){
			thePanel.revalidate();
			thePanel.repaint();
			HelpPanel.Reset();
			theFrame.setContentPane(HelpPanel);	
	}
}
	

  
      //Constructor

	public CPTMain(){
		
		 // Main window
        theFrame = new JFrame("Main Program");
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theFrame.setSize(1280, 720);

        // Main panel
        thePanel = new JPanel();
        thePanel.setLayout(null);

        // Add this block to create NetPanel
        netPanel = new NetPanel();
        netPanel.thePanel.setBounds(955, 5, 300, 715);   // The network message panel always takes 300 pixel width and 715 pixel height
        thePanel.add(netPanel.thePanel);
        theFrame.setContentPane(thePanel);
        // Need to also close the NetPanel from the main program
        theFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                if (netPanel.SuperSocket != null) {
                    netPanel.SuperSocket.disconnect();
                }
            }
        }
        );
        
        ///////////////////////////////
        // Add other JComponent here //
        // Available width 0 - 950   //        
        ///////////////////////////////
       	
	
        StartButton = new JButton("Start");
        StartButton.setBounds(20, 20, 120, 30);
        StartButton.addActionListener(this);
        thePanel.add(StartButton);
        
        IntroPanel.setLayout(null);
		IntroPanel.setPreferredSize(new Dimension(1280, 720));
		StartButton.setBounds(420, 300, 130, 50);
		StartButton.addActionListener(this);
		theFrame.add(StartButton);	
        
        HelpPanel.setLayout(null);
		HelpPanel.setPreferredSize(new Dimension(1280, 720));
		HelpButton.setBounds(560, 300, 100, 100);
		HelpButton.addActionListener(this);
		thePanel.add(HelpButton);
		HelpBackButton.setBounds(10, 10, 80, 30);
		HelpBackButton.addActionListener(this);
		HelpPanel.add(HelpBackButton);
		
        
        theFrame.setVisible(true);
	}

  
    public static void main(String[] args) {
        new CPTMain();
    }
}

