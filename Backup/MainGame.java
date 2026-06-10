import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainGame implements ActionListener, MouseListener, MouseMotionListener {
	int intGameWidth = 955;
	int intGameHeight = 760;
	int intBuffer = 0;
	int intBufferMax = 150;
	JButton Path1But;
	JButton Path2But;
	JButton Path3But;
	JFrame theFrame;
    JPanel thePanel;
    Timer ImageTimer = new Timer(1000/60, this);
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
		// Main program JComponent actionPerformed
		if (evt.getSource() == Path1But) {
			// Activate path 1
            Tran1Pane1.setVisible(true);
            IntroPanel.setVisible(false);
            Path1Panel.setVisible(false);
            Path2Panel.setVisible(false);
            Path3Panel.setVisible(false);
			thePanel.revalidate();
			thePanel.repaint();
        } else if (evt.getSource() == Path2But) {
			// Activate path 2
			IntroPanel.setVisible(false);
			Tran1Pane1.setVisible(false);
            Path1Panel.setVisible(false);
            Tran2Pane1.setVisible(true);
            Path2Panel.setVisible(false);
            Path3Panel.setVisible(false);
			thePanel.revalidate();
			thePanel.repaint();
		} else if (evt.getSource() == Path3But) {
			// Activate path 3
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
			if (IntroPanel.isVisible() == true){
				HelpPanel.ShowHelp(0);
			} else if (Path1Panel.isVisible() == true){
				//HelpPanel.ShowHelp(Path1Panel.GetCurrentHelpPage());
			} else if (Path2Panel.isVisible() == true){
				HelpPanel.ShowHelp(2);
			} else if (Path3Panel.isVisible() == true){
				HelpPanel.ShowHelp(Path3Panel.GetCurrentHelpPage());
			}		
			HelpPanel.setVisible(true);
			thePanel.repaint();
		} else if (evt.getSource() == HelpPanel.BackBut) {
			HelpPanel.setVisible(false);
			thePanel.repaint();
		} else if (evt.getSource() == ImageTimer) {
			if (IntroPanel.isVisible() == true){				
				if (IntroPanel.intTitlePosY <= 150) {
					IntroPanel.intTitlePosY = IntroPanel.intTitlePosY + 1;				
				}			
				if (IntroPanel.intMessagePosY >= 630) {
					IntroPanel.intMessagePosY = IntroPanel.intMessagePosY - 2;
				}
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
			} else if (Path1Panel.isVisible() == true){
				if (Path1Panel.Path1Complete() == true){	////
					intBuffer = intBuffer + 1;
					if (intBuffer >= intBufferMax){
						System.out.println("Path 2");					
						Tran2Pane1.setVisible(true);
						thePanel.revalidate();
						thePanel.repaint();
					}	
				} else {
					intBuffer = 0;
				}			
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
			} else if (Path2Panel.isVisible() == true){
				if (Path2Panel.Path2Complete() == true){
					intBuffer = intBuffer + 1;
					if (intBuffer >= intBufferMax){
						System.out.println("Path 3");		
						Path2Panel.setVisible(false);			
						Tran3Pane1.setVisible(true);
						Path3Panel.ResetView();
						thePanel.revalidate();
						thePanel.repaint();
					}				
				} else {
					intBuffer = 0;
				}			
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
			} else if (Path3Panel.isVisible() == true){				
				if (Path3Panel.Path3Complete() == true){     ////
					intBuffer = intBuffer + 1;
					if (intBuffer >= intBufferMax){
						System.out.println("End Game");		
						Path3Panel.setVisible(false);			
						Tran4Pane1.setVisible(true);
						thePanel.repaint();
					}
				} else {
					intBuffer = 0;
				}								
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
		//Whenever user clicks, check if they are overlaying the deer's head and body
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
		//Have the target follow the mouse as long as it is inside the bounds of the black frame
		if(evt.getX() < 936 && evt.getX() > 74 && evt.getY() > 77 && evt.getY() < 648){
			IntroPanel.intMouseX = evt.getX() - 60;
			IntroPanel.intMouseY = evt.getY() - 50;
		}
		thePanel.repaint();
	}  
	public MainGame(){
		// Controller links the View
		 // Main window
        theFrame = new JFrame("Encroaching Mist");
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theFrame.setSize(1280, intGameHeight);

        // Main panel
        thePanel = new JPanel();
        thePanel.setLayout(null);

        // Add this block to create NetPanel
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
        
        ///////////////////////////////
        // Add other JComponent here //
        // Available width 0 - 950   //        
        ///////////////////////////////              
        
        // Add game panels
        HelpPanel = new Help();
        HelpPanel.setBounds(0, 0, intGameWidth, intGameHeight);
        HelpPanel.BackBut.addActionListener(this);
        thePanel.add(HelpPanel);
        
        IntroPanel = new Intro();
        IntroPanel.setBounds(0, 0, intGameWidth, intGameHeight);
        IntroPanel.addMouseListener(this);
        IntroPanel.addMouseMotionListener(this);
        thePanel.add(IntroPanel);
        
        Path1Panel = new Path1();
        Path1Panel.setBounds(0, 0, intGameWidth, intGameHeight);
        thePanel.add(Path1Panel);
        
        Path2Panel = new Path2();
        Path2Panel.setBounds(0, 0, intGameWidth, intGameHeight);
        thePanel.add(Path2Panel);
        
        Path3Panel = new Path3();
        Path3Panel.setBounds(0, 0, intGameWidth, intGameHeight);
        thePanel.add(Path3Panel);   
        
        Tran1Pane1 = new Transition1();
        Tran1Pane1.setBounds(0, 0, intGameWidth, intGameHeight);
        thePanel.add(Tran1Pane1);   
        
        Tran2Pane1 = new Transition2();
        Tran2Pane1.setBounds(0, 0, intGameWidth, intGameHeight);
        thePanel.add(Tran2Pane1); 
        
        Tran3Pane1 = new Transition3();
        Tran3Pane1.setBounds(0, 0, intGameWidth, intGameHeight);
        thePanel.add(Tran3Pane1); 
        
        Tran4Pane1 = new Transition4();
        Tran4Pane1.setBounds(0, 0, intGameWidth, intGameHeight);
        thePanel.add(Tran4Pane1);
             
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
