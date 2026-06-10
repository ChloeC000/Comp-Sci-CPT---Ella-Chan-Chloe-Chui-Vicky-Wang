/*
Course: ICS4U1b Computer Science
Teacher: Mr. Alfred Ron Cadawas
Memebers: Chloe Chui, Ella Chan, Vicky Wang
Assignment Name: CPT

This is the main program.
*/

// Import the swing and JComponents libaraies
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainGame implements ActionListener, MouseListener, MouseMotionListener {
	// Set the main window size. The game play size is 955 x 760. The network panel is 325 x 760. 760 = 720 (required height) + window title bar height.
	int intGameWidth = 955;
	int intGameHeight = 760;
	// Set the transition screen objects
	int intBuffer = 0;
	int intBufferMax = 150;
	JButton Path1But;
	JButton Path2But;
	JButton Path3But;
	// Create the main window and panel
	JFrame theFrame;
    JPanel thePanel;
    // Set the game to 60 frames per second
    Timer ImageTimer = new Timer(1000/60, this);    
    // Create the network message, intro, game path, help, and transition panels
    Intro IntroPanel;
    NetPanel netPanel;
    Path1 Path1Panel;
    Path2 Path2Panel;
    Path3 Path3Panel;
    Transition1 Tran1Pane1;
    Transition2 Tran2Pane1;
    Transition3 Tran3Pane1;
    Transition4 Tran4Pane1;
    Help HelpPanel;
    
	public void actionPerformed(ActionEvent evt){
		if (evt.getSource() == Path1But) {
			// Activate path 1 screen
            Tran1Pane1.setVisible(true);
            IntroPanel.setVisible(false);
            Tran1Pane1.setVisible(false);
            Path1Panel.setVisible(false);
            Tran2Pane1.setVisible(false);
            Path2Panel.setVisible(false);
            Tran3Pane1.setVisible(false);
            Path3Panel.setVisible(false);
			thePanel.revalidate();
			thePanel.repaint();
        } else if (evt.getSource() == Path2But) {
			// Activate path 2 screen
			IntroPanel.setVisible(false);
			Tran1Pane1.setVisible(false);
            Path1Panel.setVisible(false);
            Tran2Pane1.setVisible(true);
            Path2Panel.setVisible(false);
            Tran3Pane1.setVisible(false);
            Path3Panel.setVisible(false);
			thePanel.revalidate();
			thePanel.repaint();
		} else if (evt.getSource() == Path3But) {
			// Activate path 3 screen
			IntroPanel.setVisible(false);
			Tran1Pane1.setVisible(false);
            Path1Panel.setVisible(false);
            Tran2Pane1.setVisible(false);
            Path2Panel.setVisible(false);
            Tran3Pane1.setVisible(true);
            Path3Panel.setVisible(false);
            Path3Panel.ResetView();
			thePanel.revalidate();
			thePanel.repaint();
		} else if (evt.getSource() == netPanel.helpBut) {
			// Show the help page based on the current game screen
			if (IntroPanel.isVisible() == true){
				HelpPanel.ShowHelp(0);
			} else if (Path1Panel.isVisible() == true){
				// Show the Game 1 (card) help
				if (Path1Panel.btnCard1.isVisible() == true){
					HelpPanel.ShowHelp(4);
				// Show the Game 2 (direction) help
				} else if (Path1Panel.RiddleScroll.isVisible() == true || Path1Panel.btnDeerStand.isVisible() == true){
					HelpPanel.ShowHelp(5);
				} else {
					// Show the path 1 help
					HelpPanel.ShowHelp(1);
				}
			} else if (Path2Panel.isVisible() == true){
				// Path 2 has only one help screen and the help screen index is always 2
				HelpPanel.ShowHelp(2);
			} else if (Path3Panel.isVisible() == true){
				// Path 3 has multiple help screens and it needs to get the screen index from the current screen
				HelpPanel.ShowHelp(Path3Panel.GetCurrentHelpPage());
			}		
			HelpPanel.setVisible(true);
			thePanel.repaint();
		} else if (evt.getSource() == HelpPanel.BackBut) {
			// Close the help screen and return to the game screen
			HelpPanel.setVisible(false);
			thePanel.repaint();
		} else if (evt.getSource() == ImageTimer) {
			// Control the animation on the intro, transitions and open the correct game screen
			// Control the intro screen animation
			if (IntroPanel.isVisible() == true){				
				if (IntroPanel.intTitlePosY <= 150) {
					IntroPanel.intTitlePosY = IntroPanel.intTitlePosY + 1;				
				}			
				if (IntroPanel.intMessagePosY >= 630) {
					IntroPanel.intMessagePosY = IntroPanel.intMessagePosY - 2;
				}
			// Control the path 1 transition screen animation and then open path 1 screen
			} else if (Tran1Pane1.isVisible() == true){
				Tran1Pane1.intTranTime = Tran1Pane1.intTranTime + 1;
				if (Tran1Pane1.intTranTime <= 250){
					if (Tran1Pane1.intPos1X <= 150){
						Tran1Pane1.intPos1X = Tran1Pane1.intPos1X + 3;
					}
					if (Tran1Pane1.intPos2X >= 600){
						Tran1Pane1.intPos2X = Tran1Pane1.intPos2X - 2;						
					}	
				} else {
					Tran1Pane1.setVisible(false);
					Path1Panel.setVisible(true);
				}				
			// Check if the path 1 is compleated and open the path 2 transition screen
			} else if (Path1Panel.isVisible() == true){
				if (Path1Panel.Path1Complete == true){
					intBuffer = intBuffer + 1;
					if (intBuffer >= intBufferMax){
						Tran2Pane1.setVisible(true);
						thePanel.revalidate();
						thePanel.repaint();
					}	
				} else {
					intBuffer = 0;
				}			
			// Control the path 2 transition screen animation and then open path 2 screen
			} else if (Tran2Pane1.isVisible() == true){
				Tran2Pane1.intTranTime = Tran2Pane1.intTranTime + 1;
				if (Tran2Pane1.intTranTime <= 250){
					if (Tran2Pane1.intPos1X <= 150){
						Tran2Pane1.intPos1X = Tran2Pane1.intPos1X + 3;
					}
					if (Tran2Pane1.intPos2X >= 600){
						Tran2Pane1.intPos2X = Tran2Pane1.intPos2X -2;						
					}	
				} else {
					Tran2Pane1.setVisible(false);
					Path2Panel.setVisible(true);
				} 				
			// Check if the path 2 is compleated and open the path 3 transition screen
			} else if (Path2Panel.isVisible() == true){
				if (Path2Panel.Path2Complete() == true){
					intBuffer = intBuffer + 1;
					if (intBuffer >= intBufferMax){
						Path2Panel.setVisible(false);			
						Tran3Pane1.setVisible(true);
						Path3Panel.ResetView();
						thePanel.revalidate();
						thePanel.repaint();
					}				
				} else {
					intBuffer = 0;
				}			
			// Control the path 3 transition screen animation and then open path 3 screen
			} else if (Tran3Pane1.isVisible() == true){
				Tran3Pane1.intTranTime = Tran3Pane1.intTranTime + 1;
				if (Tran3Pane1.intTranTime <= 250){
					if (Tran3Pane1.intPos1X <= 150){
						Tran3Pane1.intPos1X = Tran3Pane1.intPos1X + 3;
					}
					if (Tran3Pane1.intPos2X >= 600){
						Tran3Pane1.intPos2X = Tran3Pane1.intPos2X -2;						
					}	
				} else {
					Tran3Pane1.setVisible(false);
					Path3Panel.setVisible(true);
				}				
			// Check if the path 3 is compleated and open the ending screen
			} else if (Path3Panel.isVisible() == true){				
				if (Path3Panel.Path3Complete() == true){
					intBuffer = intBuffer + 1;
					if (intBuffer >= intBufferMax){
						Path3Panel.setVisible(false);			
						Tran4Pane1.setVisible(true);
						thePanel.repaint();
					}
				} else {
					intBuffer = 0;
				}								
			// Control the ending transition screen animation
			} else if (Tran4Pane1.isVisible() == true){
				Tran4Pane1.intTranTime = Tran4Pane1.intTranTime + 1;
				if (Tran4Pane1.intTranTime <= 250){
					if (Tran4Pane1.intPos1X <= 150){
						Tran4Pane1.intPos1X = Tran4Pane1.intPos1X + 3;
					}
					if (Tran4Pane1.intPos2X >= 600){
						Tran4Pane1.intPos2X = Tran4Pane1.intPos2X -2;						
					}	
				}				
			}			
			thePanel.repaint();
		}
	}	
	public void mousePressed(MouseEvent evt) {
		
	}	
	public void mouseDragged(MouseEvent evt) {     
	 
    }
    public void mouseReleased(MouseEvent evt) {
    
    }     
    public void mouseClicked(MouseEvent evt) {
		// Whenever player clicks, check if they are overlaying the deer's head and body on the intro screen
		// If the player clicks the deer, close the intro and show the path 1 transition screen
		if (IntroPanel.isVisible() == true && HelpPanel.isVisible() == false) {
			if ((evt.getX() >= 472 && evt.getX() <= 522 && evt.getY() >= 434 && evt.getY() <= 589) || (evt.getX() >=500 && evt.getX() <= 620 && evt.getY() >= 491 && evt.getY() < 580)) {
				IntroPanel.setVisible(false);
				Tran1Pane1.setVisible(true);
			}		
		}
	}
    public void mouseEntered(MouseEvent evt) {

	}
    public void mouseExited(MouseEvent evt) {

	}
    public void mouseMoved(MouseEvent evt) {
		// Have the target follow the mouse as long as it is inside the bounds of the frame
		if(evt.getX() < 936 && evt.getX() > 74 && evt.getY() > 77 && evt.getY() < 648){
			IntroPanel.intMouseX = evt.getX() - 60;
			IntroPanel.intMouseY = evt.getY() - 50;
		}
		thePanel.repaint();
	}  
	public MainGame(){
		// Controller links the View
		// Create the main window
        theFrame = new JFrame("Encroaching Mist");
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theFrame.setSize(1280, intGameHeight);

        // Main panel
        thePanel = new JPanel();
        thePanel.setLayout(null);

        // Add this block to create the NetPanel
        netPanel = new NetPanel();
        netPanel.thePanel.setBounds(955, 5, 300, 715);   // The network message panel always takes 300 pixel width and 715 pixel height
        netPanel.helpBut.addActionListener(this);
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
        
        // Add the help panel
        HelpPanel = new Help();
        HelpPanel.setBounds(0, 0, intGameWidth, intGameHeight);
        HelpPanel.BackBut.addActionListener(this);
        thePanel.add(HelpPanel);
        
        // Add the intro panel
        IntroPanel = new Intro();
        IntroPanel.setBounds(0, 0, intGameWidth, intGameHeight);
        IntroPanel.addMouseListener(this);
        IntroPanel.addMouseMotionListener(this);
        thePanel.add(IntroPanel);
        
        // Add the path 1 panel
        Path1Panel = new Path1();
        Path1Panel.setBounds(0, 0, intGameWidth, intGameHeight);
        thePanel.add(Path1Panel);
        
        // Add the path 2 panel
        Path2Panel = new Path2();
        Path2Panel.setBounds(0, 0, intGameWidth, intGameHeight);
        thePanel.add(Path2Panel);
        
        // Add the path 3 panel
        Path3Panel = new Path3();
        Path3Panel.setBounds(0, 0, intGameWidth, intGameHeight);
        thePanel.add(Path3Panel);   
        
        // Add the transition 1 panel
        Tran1Pane1 = new Transition1();
        Tran1Pane1.setBounds(0, 0, intGameWidth, intGameHeight);
        thePanel.add(Tran1Pane1);   
        
        // Add the transition 2 panel
        Tran2Pane1 = new Transition2();
        Tran2Pane1.setBounds(0, 0, intGameWidth, intGameHeight);
        thePanel.add(Tran2Pane1); 
        
        // Add the transition 3 panel
        Tran3Pane1 = new Transition3();
        Tran3Pane1.setBounds(0, 0, intGameWidth, intGameHeight);
        thePanel.add(Tran3Pane1); 
        
        // Add the transition 4 (ending) panel
        Tran4Pane1 = new Transition4();
        Tran4Pane1.setBounds(0, 0, intGameWidth, intGameHeight);
        thePanel.add(Tran4Pane1);
             
        // Show the intro panel as the first screen
        HelpPanel.setVisible(false);
        IntroPanel.setVisible(true);
        Path1Panel.setVisible(false);
        Path2Panel.setVisible(false);
        Path3Panel.setVisible(false);        
        
        // Path 1
        Path1But = new JButton("Path 1");
        Path1But.setBounds(0, 700, 120, 30);
        Path1But.addActionListener(this);
        thePanel.add(Path1But);
        
        // Path 2
        Path2But = new JButton("Path 2");
        Path2But.setBounds(120, 700, 120, 30);
        Path2But.addActionListener(this);
        thePanel.add(Path2But);
        
        // Path 3
        Path3But = new JButton("Path 3");
        Path3But.setBounds(240, 700, 120, 30);
        Path3But.addActionListener(this);
        thePanel.add(Path3But);
        
        thePanel.setComponentZOrder(Path1But, 0);
		thePanel.setComponentZOrder(Path2But, 0);
		thePanel.setComponentZOrder(Path3But, 0);
        theFrame.setVisible(true);
        ImageTimer.start();
	}
  
    public static void main(String[] args) {
        new MainGame();
    }
}
