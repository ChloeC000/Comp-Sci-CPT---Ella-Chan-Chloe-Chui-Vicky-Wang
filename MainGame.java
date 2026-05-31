import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*
 * Combination of View and Controller
 */
public class MainGame implements ActionListener{
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
    
    //Add game model here
    Game1Model Game1Data;
    Game2Model Game2Data;
    Game3Model Game3Data;
    Game4Model Game4Data;
    Game5Model Game5Data;
    Game6Model Game6Data;
  
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
			ResetView();		
			Game5Panel.setVisible(true);	
			thePanel.repaint();
		} else if (evt.getSource() == Game6But) {
			// Play path 3 game 6
			System.out.println("Game 6");			
			ResetView();		
			Game6Panel.setVisible(true);
			thePanel.repaint();
		} 	    
	}
  
	public MainGame(){
		// Controller links the View
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
        // Game 1
        Game1But = new JButton("Game 1");
        Game1But.setBounds(0, 250, 120, 30);
        Game1But.addActionListener(this);
        thePanel.add(Game1But);
        Game1Panel = new Game1();
        Game1Panel.setBounds(0, 0, 980, 600);
        thePanel.add(Game1Panel);
        
        // Game 2
        Game2But = new JButton("Game 2");
        Game2But.setBounds(120, 250, 120, 30);
        Game2But.addActionListener(this);
        thePanel.add(Game2But);
        Game2Panel = new Game2();
        Game2Panel.setBounds(0, 0, 980, 600);
        thePanel.add(Game2Panel);        
        
        // Game 3
        Game3But = new JButton("Game 3");
        Game3But.setBounds(50, 150, 120, 30);
        Game3But.addActionListener(this);
        Game3But.setVisible(false);			
        thePanel.add(Game3But);
        Game3Panel = new Game3();
        Game3Panel.setBounds(0, 0, 980, 600);
        thePanel.add(Game3Panel);   
        
        // Game 4
        Game4But = new JButton("Game 4");
        Game4But.setBounds(220, 150, 120, 30);
        Game4But.addActionListener(this);
        Game4But.setVisible(false);
        thePanel.add(Game4But);
        Game4Panel = new Game4();
        Game4Panel.setBounds(0, 0, 980, 600);
        thePanel.add(Game4Panel);   
        
        // Game 5
        Game5But = new JButton("Game 5");
        Game5But.setBounds(500, 250, 120, 30);
        Game5But.addActionListener(this);
        Game5But.setVisible(false);
        thePanel.add(Game5But);
        Game5Panel = new Game5();
        Game5Panel.setBounds(0, 0, 980, 600);
        thePanel.add(Game5Panel);  
        
        // Game 6
        Game6But = new JButton("Game 6");
        Game6But.setBounds(570, 350, 120, 30);
        Game6But.addActionListener(this);
        Game6But.setVisible(false);
        thePanel.add(Game6But);
        Game6Panel = new Game6();
        Game6Panel.setBounds(0, 0, 980, 600);
        thePanel.add(Game6Panel);  
        
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
        
        // Add game panels
        Path1Panel = new Path1();
        Path1Panel.setBounds(0, 0, 980, 600);
        thePanel.add(Path1Panel);
        
        Path2Panel = new Path2();
        Path2Panel.setBounds(0, 0, 980, 600);
        thePanel.add(Path2Panel);
        
        Path3Panel = new Path3();
        Path3Panel.setBounds(0, 0, 980, 600);
        thePanel.add(Path3Panel);        
        
        theFrame.setVisible(true);
        
        // Controller links the Model
        Game1Data = new Game1Model();
        Game1Data.GetData();
        Game2Data = new Game2Model();
        Game2Data.GetData();
        Game3Data = new Game3Model();
        Game3Data.GetData();
        Game4Data = new Game4Model();
        Game4Data.GetData();
        Game5Data = new Game5Model();
        Game5Data.GetData();
        Game6Data = new Game6Model();
        Game6Data.GetData();
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
	}
  
    public static void main(String[] args) {
        new MainGame();
    }
}
