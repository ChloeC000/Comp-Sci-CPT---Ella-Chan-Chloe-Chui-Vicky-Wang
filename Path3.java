import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;

/*
 * Path 3 with game 5 and 6
 * Game 5 is the musical beat game
 * Game 6 is the newpaper puzzle game
 * Combination of View and Controller
 */
public class Path3 extends JPanel implements ActionListener, MouseListener, MouseMotionListener{
	int intGameWidth = 955;
	int intGameHeight = 720;
	int intCurrentScreen = 3;
	String strObjName = "";
	boolean blnRightFlow;
	boolean blnResetFlow;
	BufferedImage imgPath3Background = null;
	JButton CampfireBut;
	JButton RadioBut;
	JButton JacketBut;
	JButton PhoneBut;
	JButton JournalBut;
	JButton KnifeBut;
	JButton FlowerBut;
	JTextArea TheTextArea = new JTextArea("");
	JLabel imgTextBox = new JLabel(new ImageIcon("./images/Text Box.png"));
	Game5 Game5Panel;
	Game6 Game6Panel;	
		
	// Add game model
	Path3Model Path3Data;
    
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
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(imgPath3Background, 0, 0, null);
	}	    
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
			repaint();
		} else if(Game6Panel.isVisible()){
			// Game 6
			// Check which newspapar is selected 
			int x = evt.getX();
			int y = evt.getY();
			if(x >= intPaper1X && x <= intPaper1X + Game6Panel.imgPaperSmall1.getWidth() && y >= intPaper1Y && y <= intPaper1Y + Game6Panel.imgPaperSmall1.getHeight()){
				intPaperIndex = 1;
				blnDraggingPaper = true;
			} else if(x >= intPaper2X && x <= intPaper2X + Game6Panel.imgPaperSmall2.getWidth() && y >= intPaper2Y && y <= intPaper2Y + Game6Panel.imgPaperSmall2.getHeight()){
				intPaperIndex = 2;
				blnDraggingPaper = true;
			} else if(x >= intPaper3X && x <= intPaper3X + Game6Panel.imgPaperSmall3.getWidth() && y >= intPaper3Y && y <= intPaper3Y + Game6Panel.imgPaperSmall3.getHeight()){
				intPaperIndex = 3;
				blnDraggingPaper = true;
			} else if(x >= intPaper4X && x <= intPaper4X + Game6Panel.imgPaperSmall4.getWidth() && y >= intPaper4Y && y <= intPaper4Y + Game6Panel.imgPaperSmall4.getHeight()){
				intPaperIndex = 4;
				blnDraggingPaper = true;
			}
		}
	}
	public void mouseClicked(MouseEvent evt){
		int x = evt.getX();
		int y = evt.getY();
    
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
			repaint();
			if (Game5Panel.strResult != ""){
				if (x >= 760 && x <= 787 && y >= 310 && y <= 339){
					Game5Panel.setVisible(false);
					repaint();
				}
			}
		} else if (Game6Panel.isVisible()) {
			if (Game6Panel.blnShowPaper1 == true && Game6Panel.blnShowPaper2 == true && Game6Panel.blnShowPaper3 == true && Game6Panel.blnShowPaper4 == true){
				if (x >= 760 && x <= 782 && y >= 310 && y <= 330){
					Game6Panel.setVisible(false);
					repaint();
				}	
			}
		}
	}	
	public void mouseDragged(MouseEvent evt){
		// Game 6
		if(Game6Panel.isVisible() && blnDraggingPaper){
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
			repaint();
		}
	}
	public void mouseMoved(MouseEvent evt){
	}  
	public void actionPerformed(ActionEvent evt){
		// Main program JComponent actionPerformed
		// Set game panel visibility and game play here		
		if (evt.getSource() == CampfireBut || evt.getSource() == RadioBut || evt.getSource() == JacketBut || evt.getSource() == PhoneBut || evt.getSource() == JournalBut || evt.getSource() == KnifeBut || evt.getSource() == FlowerBut) {			
			if (evt.getSource() == CampfireBut) {
				strObjName = "Campfire";
				CampfireBut.setIcon(new ImageIcon("./images/Campfire2.png"));
				blnRightFlow = true;			
				blnResetFlow = true;
			} else if (evt.getSource() == RadioBut) {
				CheckClickFlow("Radio");
			} else if (evt.getSource() == JacketBut) {
				CheckClickFlow("Jacket");
			} else if (evt.getSource() == PhoneBut) {
				CheckClickFlow("Phone");
			} else if (evt.getSource() == JournalBut) {
				CheckClickFlow("Journal");
			} else if (evt.getSource() == KnifeBut) {
				CheckClickFlow("Knife");
			} else if (evt.getSource() == FlowerBut) {
				CheckClickFlow("Flower");
			}
			TheTextArea.setText(Path3Data.GetData(strObjName, 5)); 		
			imgTextBox.setVisible(true);				
			TheTextArea.setVisible(true);
			
			if ((Path3Data.GetData(strObjName, 3).equals("beat") && blnRightFlow == true)) {
				System.out.println("Game 5");	
				intCurrentScreen = 8;	
				Path3Data.Game5Beat();	
				Game5MessageTimer.start();	
				ResetView();				
				Game5Panel.setVisible(true);	
				imgTextBox.setVisible(false);				
				TheTextArea.setVisible(false);
				Game5Panel.strBeat = "Press the right jog wheel to create patterns of " + Path3Data.intBeat1 + ", " + Path3Data.intBeat2 + ", " + Path3Data.intBeat3 + " beats.";
				Game5Panel.strResult = "";			
				intTarget[0] = Path3Data.intBeat1;
				intTarget[1] = Path3Data.intBeat2;
				intTarget[2] = Path3Data.intBeat3;
				intCurrentPattern = 0;
				intCurrentClicks = 0;
				repaint();
			} else if ((Path3Data.GetData(strObjName, 3).equals("puzzle") && blnRightFlow == true)) {
				System.out.println("Game 6");		
				intCurrentScreen = 9;		
				ResetView();
				Game6Panel.strMessage = Path3Data.GetData(strObjName, 5);	
				Game6Panel.setVisible(true);
				imgTextBox.setVisible(false);				
				TheTextArea.setVisible(false);
				intPaper1X = Game6Panel.intPaper1X;
				intPaper1Y = Game6Panel.intPaper1Y;
				intPaper2X = Game6Panel.intPaper2X;
				intPaper2Y = Game6Panel.intPaper2Y;
				intPaper3X = Game6Panel.intPaper3X;
				intPaper3Y = Game6Panel.intPaper3Y;
				intPaper4X = Game6Panel.intPaper4X;
				intPaper4Y = Game6Panel.intPaper4Y;
				repaint();
			}			
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
					Game5Panel.strResult = "Try again next time...";
				}
				intCurrentPattern = 0;
				intCurrentClicks = 0;
				repaint();
			}
		} else if (evt.getSource() == Game5MessageTimer) {
			// Play path 3 game 5 timer
			if (Game5Panel.intPosY > 50){
				Game5Panel.intPosY = Game5Panel.intPosY - 2;
				repaint();
			} else {
				Game5MessageTimer.stop();
			}
		}
	}
	public void CheckClickFlow(String strObj) {
	if (Path3Data.GetData(strObjName, 6).equals(strObj)) {
		if (blnResetFlow == true){
				blnRightFlow = true;
			}
		} else {
			blnResetFlow = false;
			blnRightFlow = false;
		}
		strObjName = strObj;
	}
	public int GetCurrentHelpPage() {
		return intCurrentScreen;
	}
	  
	public Path3(){
		// Controller links the View
		// Main window
        
        ///////////////////////////////
        // Add other JComponent here //
        // Available width 0 - 950   //        
        ///////////////////////////////            
        
        // Controller links the Model
        Path3Data = new Path3Model();
          
        setLayout(null);
        
        try{
			imgPath3Background = ImageIO.read(new File("images/Path3 Background.png"));
		}catch(IOException e){
			System.out.println("Unable to load the Path 3 image");
		}        
		TheTextArea.setBounds(220, 335, 610, 100);
		imgTextBox.setBounds(200, 320, 647, 111);		//Draw of the text box		
		TheTextArea.setFont(new Font("Arial", Font.PLAIN, 24));
		TheTextArea.setForeground(Color.WHITE);
		TheTextArea.setEditable(false);
		TheTextArea.setLineWrap(true);
		TheTextArea.setWrapStyleWord(true);
		TheTextArea.setOpaque(false); 
		imgTextBox.setVisible(false);			//Make both invisible until they are needed
		TheTextArea.setVisible(false);
		add(TheTextArea);
		add(imgTextBox);		
        
        Game5Panel = new Game5();
        Game5Panel.setBounds(0, 0, intGameWidth, intGameHeight);
        Game5Panel.addMouseListener(this);
        add(Game5Panel);  
        Game6Panel = new Game6();
        Game6Panel.setBounds(0, 0, intGameWidth, intGameHeight);
        Game6Panel.addMouseListener(this);
		Game6Panel.addMouseMotionListener(this);
        add(Game6Panel); 
        
        CampfireBut = new JButton(new ImageIcon("./images/Campfire.png"));
        CampfireBut.setBounds(Integer.parseInt(Path3Data.GetData("Campfire", 1)), Integer.parseInt(Path3Data.GetData("Campfire", 2)), 121, 226);
        CampfireBut.addActionListener(this);
        CampfireBut.setVisible(true);
        CampfireBut.setContentAreaFilled(false);		//Make the button background invisible
		CampfireBut.setBorderPainted(false);
        add(CampfireBut);
        RadioBut = new JButton(new ImageIcon("./images/Radio.png"));
        RadioBut.setBounds(Integer.parseInt(Path3Data.GetData("Radio", 1)), Integer.parseInt(Path3Data.GetData("Radio", 2)), 105, 88);
        RadioBut.addActionListener(this);
        RadioBut.setVisible(true);
        RadioBut.setContentAreaFilled(false);		//Make the button background invisible
		RadioBut.setBorderPainted(false);        
        add(RadioBut);
        JacketBut = new JButton(new ImageIcon("./images/Jacket.png"));
        JacketBut.setBounds(Integer.parseInt(Path3Data.GetData("Jacket", 1)), Integer.parseInt(Path3Data.GetData("Jacket", 2)), 147, 262);
        JacketBut.addActionListener(this);
        JacketBut.setVisible(true);
        JacketBut.setContentAreaFilled(false);		//Make the button background invisible
		JacketBut.setBorderPainted(false);        
        add(JacketBut);
        PhoneBut = new JButton(new ImageIcon("./images/Phone.png"));
        PhoneBut.setBounds(Integer.parseInt(Path3Data.GetData("Phone", 1)), Integer.parseInt(Path3Data.GetData("Phone", 2)), 78, 82);
        PhoneBut.addActionListener(this);
        PhoneBut.setVisible(true);
        PhoneBut.setContentAreaFilled(false);		//Make the button background invisible
		PhoneBut.setBorderPainted(false);        
        add(PhoneBut);
        JournalBut = new JButton(new ImageIcon("./images/Journal.png"));
        JournalBut.setBounds(Integer.parseInt(Path3Data.GetData("Journal", 1)), Integer.parseInt(Path3Data.GetData("Journal", 2)), 97, 119);
        JournalBut.addActionListener(this);
        JournalBut.setVisible(true);
        JournalBut.setContentAreaFilled(false);		//Make the button background invisible
		JournalBut.setBorderPainted(false);        
        add(JournalBut);
        KnifeBut = new JButton(new ImageIcon("./images/Knife.png"));
        KnifeBut.setBounds(Integer.parseInt(Path3Data.GetData("Knife", 1)), Integer.parseInt(Path3Data.GetData("Knife", 2)), 77, 64);
        KnifeBut.addActionListener(this);
        KnifeBut.setVisible(true);
        KnifeBut.setContentAreaFilled(false);		//Make the button background invisible
		KnifeBut.setBorderPainted(false);        
        add(KnifeBut);
        FlowerBut = new JButton(new ImageIcon("./images/Flower.png"));
        FlowerBut.setBounds(Integer.parseInt(Path3Data.GetData("Flower", 1)), Integer.parseInt(Path3Data.GetData("Flower", 2)), 209, 194);
        FlowerBut.addActionListener(this);
        FlowerBut.setVisible(true);
        FlowerBut.setContentAreaFilled(false);		//Make the button background invisible
		FlowerBut.setBorderPainted(false);           
        add(FlowerBut);       
	}
	
	public void ResetView(){
		Game5Panel.setVisible(false);
		Game6Panel.setVisible(false);
	}
}
