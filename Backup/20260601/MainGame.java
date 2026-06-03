import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*
 * Combination of View and Controller
 */
public class MainGame implements ActionListener, MouseListener, MouseMotionListener{
	int intGameWidth = 955;
	int intGameHeight = 720;
	JFrame theFrame;
    JPanel thePanel;
	JButton Game1But;
	JButton Game2But;
	JButton Game3But;
	JButton Game4But;
	JButton Game5But;
	JButton Game6But;
	JButton Path1But;
	JButton Path2But;
	JButton Path3But;
    NetPanel netPanel;
    Path1 Path1Panel;
    Path2 Path2Panel;
    Path3 Path3Panel;
    Game1 Game1Panel;
    Game2 Game2Panel;
    Game3 Game3Panel;
    Game4 Game4Panel;
    Game5 Game5Panel;
    Game6 Game6Panel;
    
    // Add game model
    Game1Model Game1Data;
    Game2Model Game2Data;
    Game3Model Game3Data;
    Game4Model Game4Data;
    Game5Model Game5Data;
    Game6Model Game6Data;
    
    // Game 5 properties
    Timer patternTimer = new Timer(1000, this);
    Timer Game5MessageTimer = new Timer(1000/60, this);
    int[] intTarget = new int[3];
	int[] intBeat = new int[3];
	int intCurrentPattern = 0;
	int intCurrentClicks = 0;
	boolean blnCorrect = true;
	
	// Game 6 properties
	int intPaperX = 0;
	int intPaperY = 0;
	int intPaper1X = 800;
	int intPaper1Y = 510;
	int intPaper2X = 800;
	int intPaper2Y = 270;
	int intPaper3X = 800;
	int intPaper3Y = 150;
	int intPaper4X = 800;
	int intPaper4Y = 390;
	int intPaperIndex = 0;
	boolean blnDraggingPaper = false;
	    
    public void mouseExited(MouseEvent evt){
	}
	public void mouseEntered(MouseEvent evt){
	}
	public void mouseReleased(MouseEvent evt){	
		blnDraggingPaper = false;
	}
	public void mousePressed(MouseEvent evt){	
		if (Game5Panel.isVisible()) {
			// Game 5
			// Change the panel color
			Game5Panel.blnShowWheel = true;
			thePanel.repaint();
		} else if(Game6Panel.isVisible()){
			// Game 6
			// Check which newspapar is selected 
			int x = evt.getX();
			int y = evt.getY();
			if(x >= intPaper1X && x <= intPaper1X + Game6Panel.imgPaperSmall1.getWidth() && y >= intPaper1Y && y <= intPaper1Y + Game6Panel.imgPaperSmall1.getHeight()){
				System.out.println("Mosue Press Paper 1.");
				intPaperIndex = 1;
				blnDraggingPaper = true;
			} else if(x >= intPaper2X && x <= intPaper2X + Game6Panel.imgPaperSmall2.getWidth() && y >= intPaper2Y && y <= intPaper2Y + Game6Panel.imgPaperSmall2.getHeight()){
				System.out.println("Mosue Press Paper 2.");
				intPaperIndex = 2;
				blnDraggingPaper = true;
			} else if(x >= intPaper3X && x <= intPaper3X + Game6Panel.imgPaperSmall3.getWidth() && y >= intPaper3Y && y <= intPaper3Y + Game6Panel.imgPaperSmall3.getHeight()){
				System.out.println("Mosue Press Paper 3.");
				intPaperIndex = 3;
				blnDraggingPaper = true;
			} else if(x >= intPaper4X && x <= intPaper4X + Game6Panel.imgPaperSmall4.getWidth() && y >= intPaper4Y && y <= intPaper4Y + Game6Panel.imgPaperSmall4.getHeight()){
				System.out.println("Mosue Press Paper 4.");
				intPaperIndex = 4;
				blnDraggingPaper = true;
			}
		}
	}
	public void mouseClicked(MouseEvent evt){
		int x = evt.getX();
		int y = evt.getY();
    
		System.out.println("Mosue Clicked.");
		// Game 5
		if (Game5Panel.isVisible()) {
			Game5Panel.blnShowWheel = false;
			// Check if the mouse cursor is within the right jog wheel region
			if (x >= 620 && x <= 880 && y >= 240 && y <= 400){
				intCurrentClicks++;
			}			
			if (patternTimer != null) {
				patternTimer.stop();
			}		
			patternTimer.setRepeats(false);
			patternTimer.start();
			thePanel.repaint();
		}
	}
	
	public void mouseDragged(MouseEvent evt){
		// Game 6
		if(Game6Panel.isVisible() && blnDraggingPaper){
			System.out.println("intPaperX = " + intPaperX + ", intPaperY = " + intPaperY);
			intPaperX = evt.getX();
			intPaperY = evt.getY();
			if (intPaperIndex == 1){
				if (intPaperX >= 50 && intPaperX <= 80 && intPaperY >= 90 && intPaperY <= 120) {
					intPaperX = 50;
					intPaperY = 50;
					Game6Panel.blnShowPaper1 = true;
				} else {
					Game6Panel.intPaper1X = intPaperX;
					Game6Panel.intPaper1Y = intPaperY;
					intPaper1X = intPaperX;
					intPaper1Y = intPaperY;
				}
			} else if (intPaperIndex == 2){
				if (intPaperX >= 196 && intPaperX <= 226 && intPaperY >= 90 && intPaperY <= 120) {
					intPaperX = 196;
					intPaperY = 50;
					Game6Panel.blnShowPaper2 = true;
				} else {
					Game6Panel.intPaper2X = intPaperX;
					Game6Panel.intPaper2Y = intPaperY;
					intPaper2X = intPaperX;
					intPaper2Y = intPaperY;
				}
			}  else if (intPaperIndex == 3){
				if (intPaperX >= 50 && intPaperX <= 80 && intPaperY >= 321 && intPaperY <= 351) {
					intPaperX = 50;
					intPaperY = 281;
					Game6Panel.blnShowPaper3 = true;
				} else {
					Game6Panel.intPaper3X = intPaperX;
					Game6Panel.intPaper3Y = intPaperY;
					intPaper3X = intPaperX;
					intPaper3Y = intPaperY;
				}
			}  else if (intPaperIndex == 4){
				if (intPaperX >= 188 && intPaperX <= 218 && intPaperY >= 358 && intPaperY <= 388) {
					intPaperX = 188;
					intPaperY = 318;
					Game6Panel.blnShowPaper4 = true;
				} else {
					Game6Panel.intPaper4X = intPaperX;
					Game6Panel.intPaper4Y = intPaperY;
					intPaper4X = intPaperX;
					intPaper4Y = intPaperY;
				}
			}			
			thePanel.repaint();
		}
	}

	public void mouseMoved(MouseEvent evt){
	}
  
	public void actionPerformed(ActionEvent evt){
		// Main program JComponent actionPerformed
		// Set game panel visibility and game play here		
		if (evt.getSource() == Path1But) {
			// Activate path 1
            System.out.println("Path 1");
            ResetView();
            Path1Panel.setVisible(true);
			Game1But.setVisible(true);
			Game2But.setVisible(true);
			thePanel.repaint();
        } else if (evt.getSource() == Path2But) {
			// Activate path 2
			System.out.println("Path 2");
			ResetView();
            Path2Panel.setVisible(true);
			Game3But.setVisible(true);
			Game4But.setVisible(true);
			thePanel.repaint();
		} else if (evt.getSource() == Path3But) {
			// Activate path 3
			System.out.println("Path 3");
			ResetView();
            Path3Panel.setVisible(true);
			Game5But.setVisible(true);
			Game6But.setVisible(true);
			thePanel.repaint();
		} else if (evt.getSource() == Game1But) {
			// Play path 1 game 1
			System.out.println("Game 1");
			ResetView();
			Game1Panel.setVisible(true);
			thePanel.repaint();
		} else if (evt.getSource() == Game2But) {
			// Play path 1 game 2
			System.out.println("Game 2");							
			ResetView();				
			Game2Panel.setVisible(true);			
			thePanel.repaint();
		} else if (evt.getSource() == Game3But) {
			// Play path 2 game 3
			System.out.println("Game 3");		
			ResetView();		
			Game3Panel.setVisible(true);	
			thePanel.repaint();
		} else if (evt.getSource() == Game4But) {
			// Play path 2 game 4
			System.out.println("Game 4");	
			ResetView();		
			Game4Panel.setVisible(true);		
			thePanel.repaint();
		} else if (evt.getSource() == Game5But) {
			// Play path 3 game 5
			System.out.println("Game 5");		
			Game5Data.GetData();	
			Game5MessageTimer.start();	
			ResetView();				
			Game5Panel.setVisible(true);	
			Game5Panel.strBeat = "Press the right jog wheel to create patterns of " + Game5Data.intBeat1 + ", " + Game5Data.intBeat2 + ", " + Game5Data.intBeat3 + " beats.";
			Game5Panel.strResult = "";			
			intTarget[0] = Game5Data.intBeat1;
			intTarget[1] = Game5Data.intBeat2;
			intTarget[2] = Game5Data.intBeat3;
			intCurrentPattern = 0;
			intCurrentClicks = 0;
			thePanel.repaint();
		} else if (evt.getSource() == Game6But) {
			// Play path 3 game 6
			System.out.println("Game 6");			
			ResetView();		
			Game6Panel.setVisible(true);
			intPaper1X = Game6Panel.intPaper1X;
			intPaper1Y = Game6Panel.intPaper1Y;
			intPaper2X = Game6Panel.intPaper2X;
			intPaper2Y = Game6Panel.intPaper2Y;
			intPaper3X = Game6Panel.intPaper3X;
			intPaper3Y = Game6Panel.intPaper3Y;
			intPaper4X = Game6Panel.intPaper4X;
			intPaper4Y = Game6Panel.intPaper4Y;
			thePanel.repaint();
		} else if (evt.getSource() == patternTimer) {
			// Play path 3 game 5 timer
			intBeat[intCurrentPattern] = intCurrentClicks;
			intCurrentPattern++;
			intCurrentClicks = 0;
			if (intCurrentPattern == 3) {
				blnCorrect = true;
				for (int i = 0; i < 3; i++) {
					if (intTarget[i] != intBeat[i]) {
						blnCorrect = false;
					}
				}
				if (blnCorrect) {
					Game5Panel.strResult = "You are a genius DJ!";
				} else {
					Game5Panel.strResult = "Hmmm... Try again....";
				}
				intCurrentPattern = 0;
				intCurrentClicks = 0;
				thePanel.repaint();
			}
		} else if (evt.getSource() == Game5MessageTimer) {
			// Play path 3 game 5 timer
			if (Game5Panel.intPosY > 50){
				Game5Panel.intPosY = Game5Panel.intPosY - 2;
				thePanel.repaint();
			} else {
				Game5MessageTimer.stop();
			}
		}
	}
  
	public MainGame(){
		// Controller links the View
		 // Main window
        theFrame = new JFrame("Main Program");
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theFrame.setSize(1280, intGameHeight);

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
        // Game 1
        Game1But = new JButton("Game 1");
        Game1But.setBounds(0, 250, 120, 30);
        Game1But.addActionListener(this);
        thePanel.add(Game1But);
        Game1Panel = new Game1();
        Game1Panel.setBounds(0, 0, intGameWidth, intGameHeight);
        thePanel.add(Game1Panel);
        
        // Game 2
        Game2But = new JButton("Game 2");
        Game2But.setBounds(120, 250, 120, 30);
        Game2But.addActionListener(this);
        thePanel.add(Game2But);
        Game2Panel = new Game2();
        Game2Panel.setBounds(0, 0, intGameWidth, intGameHeight);
        Game2Panel.addMouseListener(this);
        thePanel.add(Game2Panel);        
        
        // Game 3
        Game3But = new JButton("Game 3");
        Game3But.setBounds(50, 150, 120, 30);
        Game3But.addActionListener(this);
        Game3But.setVisible(false);			
        thePanel.add(Game3But);
        Game3Panel = new Game3();
        Game3Panel.setBounds(0, 0, intGameWidth, intGameHeight);
        thePanel.add(Game3Panel);   
        
        // Game 4
        Game4But = new JButton("Game 4");
        Game4But.setBounds(220, 150, 120, 30);
        Game4But.addActionListener(this);
        Game4But.setVisible(false);
        thePanel.add(Game4But);
        Game4Panel = new Game4();
        Game4Panel.setBounds(0, 0, intGameWidth, intGameHeight);
        thePanel.add(Game4Panel);   
        
        // Game 5
        Game5But = new JButton("Game 5");
        Game5But.setBounds(500, 250, 120, 30);
        Game5But.addActionListener(this);
        Game5But.setVisible(false);
        thePanel.add(Game5But);
        Game5Panel = new Game5();
        Game5Panel.setBounds(0, 0, intGameWidth, intGameHeight);
        Game5Panel.addMouseListener(this);
        thePanel.add(Game5Panel);  
        
        // Game 6
        Game6But = new JButton("Game 6");
        Game6But.setBounds(570, 350, 120, 30);
        Game6But.addActionListener(this);
        Game6But.setVisible(false);
        thePanel.add(Game6But);
        Game6Panel = new Game6();
        Game6Panel.setBounds(0, 0, intGameWidth, intGameHeight);
        Game6Panel.addMouseListener(this);
		Game6Panel.addMouseMotionListener(this);
        thePanel.add(Game6Panel);  
        
        // Add game panels
        Path1Panel = new Path1();
        Path1Panel.setBounds(0, 0, intGameWidth, intGameHeight);
        thePanel.add(Path1Panel);
        
        Path2Panel = new Path2();
        Path2Panel.setBounds(0, 0, intGameWidth, intGameHeight);
        thePanel.add(Path2Panel);
        
        Path3Panel = new Path3();
        Path3Panel.setBounds(0, 0, intGameWidth, intGameHeight);
        thePanel.add(Path3Panel);        
        
        // Path 1
        Path1But = new JButton("Path 1");
        Path1But.setBounds(0, 650, 120, 30);
        Path1But.addActionListener(this);
        thePanel.add(Path1But);
        
        // Path 2
        Path2But = new JButton("Path 2");
        Path2But.setBounds(120, 650, 120, 30);
        Path2But.addActionListener(this);
        thePanel.add(Path2But);
        
        // Path 3
        Path3But = new JButton("Path 3");
        Path3But.setBounds(240, 650, 120, 30);
        Path3But.addActionListener(this);
        thePanel.add(Path3But);
        
        theFrame.setVisible(true);
        
        // Controller links the Model
        Game1Data = new Game1Model();
        Game2Data = new Game2Model();        
        Game3Data = new Game3Model();
        Game4Data = new Game4Model();
        Game5Data = new Game5Model();
        Game6Data = new Game6Model();
        ResetView();
	}
	
	public void ResetView(){
		Path1Panel.setVisible(false);
		Path2Panel.setVisible(false);
		Path3Panel.setVisible(false);
		Game1Panel.setVisible(false);
		Game2Panel.setVisible(false);
		Game3Panel.setVisible(false);
		Game4Panel.setVisible(false);
		Game5Panel.setVisible(false);
		Game6Panel.setVisible(false);
		Game1But.setVisible(false);
		Game2But.setVisible(false);
		Game3But.setVisible(false);
		Game4But.setVisible(false);
		Game5But.setVisible(false);
		Game6But.setVisible(false);
		Path1But.setVisible(true);
		Path2But.setVisible(true);
		Path3But.setVisible(true);
		thePanel.setComponentZOrder(Path1But, 0);
		thePanel.setComponentZOrder(Path2But, 0);
		thePanel.setComponentZOrder(Path3But, 0);
	}
  
    public static void main(String[] args) {
        new MainGame();
    }
}
