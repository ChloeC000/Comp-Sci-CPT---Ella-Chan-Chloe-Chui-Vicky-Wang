import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Path2 extends JPanel implements ActionListener{
	// Properties
	int intGameWidth = 955;
	int intGameHeight = 720;
	JButton Game3But;
	JButton Game4But;
	JButton BackBut;
    Game3 Game3Panel;
    Game4 Game4Panel;
    
    // Add game model
    Path2Model Path2Data;
    
    // Game 4 properties
    Timer Game4MessageTimer = new Timer(1000/60, this);
    
	// Methods
	// Paint the screen
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		// Create the screen
		g.setColor(Color.YELLOW);
		g.fillRect(0, 0, 955, 720);
		g.setFont(new Font("Arial", Font.BOLD, 30));  
		g.setColor(Color.BLACK);
		g.drawString("Path 2", 100, 100);
	}	
	public void actionPerformed(ActionEvent evt){
		if (evt.getSource() == Game4But) {
			// Play path 2 game 4
			System.out.println("Game 4");	
			Game4Panel.strChoice = Path2Data.GetRiddle("C").split("\\|");
			Game4Panel.strRiddle = Path2Data.GetRiddle("Q");
			Game4Panel.Init();
			Game4Panel.setVisible(true);				
			Game4MessageTimer.start();			
		} else if (evt.getSource() == Game4MessageTimer) {
			if (Game4Panel.intPosX <= 10){
				Game4Panel.intPosX = Game4Panel.intPosX + 5;
				repaint();
			} else{
				Game4MessageTimer.stop();
			}		
		} else if (evt.getSource() == Game4Panel.SubmitBut) {
			if (Game4Panel.radioChoice[Integer.parseInt(Game4Panel.strChoice[4])].isSelected()){
				Game4Panel.strResult = "You have got a new clue.";
			} else {
				Game4Panel.strResult = "Your answer is not correct.";
			}
			repaint();
		}
	}
	
	// Constructor
	public Path2(){
		// Controller links the View
		// Main window
        
        ///////////////////////////////
        // Add other JComponent here //
        // Available width 0 - 950   //        
        /////////////////////////////// 
		setLayout(null);
        
        Game3Panel = new Game3();
        Game3Panel.setBounds(0, 0, intGameWidth, intGameHeight);
        Game3Panel.setVisible(false);
        add(Game3Panel);  
        Game4Panel = new Game4();
        Game4Panel.setBounds(0, 0, intGameWidth, intGameHeight);
        Game4Panel.SubmitBut.addActionListener(this);
        Game4Panel.setVisible(false);
        add(Game4Panel);        
        
        Game3But = new JButton("Game 3");
        Game3But.setBounds(500, 250, 120, 30);
        Game3But.addActionListener(this);
        Game3But.setVisible(true);
        add(Game3But);
        Game4But = new JButton("Game 4");
        Game4But.setBounds(570, 350, 120, 30);
        Game4But.addActionListener(this);
        Game4But.setVisible(true);        
        add(Game4But);
        setComponentZOrder(Game3But, 0);  
        setComponentZOrder(Game4But, 0); 
        
        Path2Data = new Path2Model();
   	}
}
