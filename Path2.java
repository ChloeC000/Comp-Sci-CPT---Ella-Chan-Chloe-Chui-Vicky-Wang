import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;

public class Path2 extends JPanel implements ActionListener{
	// Properties
	int intGameWidth = 955;
	int intGameHeight = 720;
	String strObjName = "";
	boolean blnRightFlow;
	boolean blnResetFlow;
	boolean blnGame3Completed;
	boolean blnGame4Completed;
	BufferedImage imgPath2Background = null;
	JButton Game3But;
	JButton Game4But;
	JButton NestBut;
	JButton OwlBut;
	JButton ClueBut;
	JButton Statue1But;
	JButton Statue2But;
	JButton Statue3But;
	JButton Flute1But;
	JButton BackBut;
	JTextArea TheTextArea = new JTextArea("");
	JLabel imgTextBox = new JLabel(new ImageIcon("./images/Text Box.png"));
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
		g.drawImage(imgPath2Background, 0, 0, null);
	}	
	public void actionPerformed(ActionEvent evt){
		if (evt.getSource() == NestBut || evt.getSource() == OwlBut || evt.getSource() == ClueBut || evt.getSource() == Statue1But || evt.getSource() == Statue2But || evt.getSource() == Statue3But || evt.getSource() == Flute1But) {
			if (evt.getSource() == NestBut) {
				strObjName = "nest";
				blnRightFlow = true;			
				blnResetFlow = true;
			} else if (evt.getSource() == OwlBut) {
				CheckClickFlow("owl");
			} else if (evt.getSource() == ClueBut) {
				CheckClickFlow("clue");
			} else if (evt.getSource() == Statue1But) {
				CheckClickFlow("statue1");
			} else if (evt.getSource() == Statue2But) {
				CheckClickFlow("statue2");
			} else if (evt.getSource() == Statue3But) {
				CheckClickFlow("statue3");
			} else if (evt.getSource() == Flute1But) {
				CheckClickFlow("flute");
			}
			TheTextArea.setText(Path2Data.GetData(strObjName, 5)); 		
			imgTextBox.setVisible(true);				
			TheTextArea.setVisible(true);
		}
		
		if (evt.getSource() == Game4But) {
			// Play path 2 game 4
			System.out.println("Game 4");	
			strObjName = "owl";
			Game4Panel.strChoice = Path2Data.GetData(strObjName, 4).split("\\|");
			Game4Panel.strRiddle = Path2Data.GetData(strObjName, 5);
			Game4Panel.Init();
		    Game4Panel.radioChoice[0].addActionListener(this);
			Game4Panel.radioChoice[1].addActionListener(this);
			Game4Panel.radioChoice[2].addActionListener(this);
			Game4Panel.radioChoice[3].addActionListener(this);
			Game4Panel.setVisible(true);
			Game4MessageTimer.start();			
		} else if (evt.getSource() == Game4MessageTimer) {
			if (Game4Panel.intPosX <= 100){
				Game4Panel.intPosX = Game4Panel.intPosX + 5;
				repaint();
			} else{
				Game4MessageTimer.stop();
			}		
		} else if (evt.getSource() == Game4Panel.radioChoice[0]) {
			CheckGame4Answer(0);
		} else if (evt.getSource() == Game4Panel.radioChoice[1]) {
			CheckGame4Answer(1);
		} else if (evt.getSource() == Game4Panel.radioChoice[2]) {
			CheckGame4Answer(2);
		} else if (evt.getSource() == Game4Panel.radioChoice[3]) {
			CheckGame4Answer(3);
		} else if (evt.getSource() == Game4Panel.CloseBut) {
			Game4Panel.setVisible(false);
		}
	}
	public void CheckClickFlow(String strObj) {
	if (Path2Data.GetData(strObjName, 6).equals(strObj)) {
		if (blnResetFlow == true){
				blnRightFlow = true;
			}
		} else {
			blnResetFlow = false;
			blnRightFlow = false;
		}
		strObjName = strObj;
	}
	private void CheckGame4Answer(int intChoice){
		int intCorrectAnswer = Integer.parseInt(Game4Panel.strChoice[4]);

		if (intChoice == intCorrectAnswer){
			Game4Panel.strResult = "You have got a new clue.";
			blnGame4Completed = true;
		}else{
			Game4Panel.strResult = "Your answer is not correct.";
			blnGame4Completed = false;
		}
		Game4Panel.repaint();
	}	
	public boolean Path2Complete() {
		if (blnGame3Completed == true && blnGame4Completed == true) {
			return true;
		} else {
			return false;
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
        Path2Data = new Path2Model();
		setLayout(null);
		
		try{
			imgPath2Background = ImageIO.read(new File("./images/Path2 Background.png"));
		}catch(IOException e){
			System.out.println("Unable to load the Path 2 image");
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
		
		Game4Panel = new Game4();
        Game4Panel.setBounds(0, 0, intGameWidth, intGameHeight);
        Game4Panel.setVisible(false);
        Game4Panel.CloseBut.addActionListener(this);
        Game4Panel.setOpaque(false);
        add(Game4Panel);     
		
		NestBut = new JButton(new ImageIcon("./images/Nest.png"));
        NestBut.setBounds(Integer.parseInt(Path2Data.GetData("nest", 1)), Integer.parseInt(Path2Data.GetData("nest", 2)), 90, 75);
        NestBut.addActionListener(this);
        NestBut.setVisible(true);
        NestBut.setContentAreaFilled(false);		//Make the button background invisible
		NestBut.setBorderPainted(false);
        add(NestBut);
        OwlBut = new JButton(new ImageIcon("./images/Owl.png"));
        OwlBut.setBounds(Integer.parseInt(Path2Data.GetData("owl", 1)), Integer.parseInt(Path2Data.GetData("owl", 2)), 110, 183);
        OwlBut.addActionListener(this);
        OwlBut.setVisible(true);
        OwlBut.setContentAreaFilled(false);		//Make the button background invisible
		OwlBut.setBorderPainted(false);
        add(OwlBut);
        ClueBut = new JButton(new ImageIcon("./images/Clue.png"));
        ClueBut.setBounds(Integer.parseInt(Path2Data.GetData("clue", 1)), Integer.parseInt(Path2Data.GetData("clue", 2)), 97, 98);
        ClueBut.addActionListener(this);
        ClueBut.setVisible(true);
        ClueBut.setContentAreaFilled(false);		//Make the button background invisible
		ClueBut.setBorderPainted(false);
        add(ClueBut);
        Statue1But = new JButton(new ImageIcon("./images/Statue1.png"));
        Statue1But.setBounds(Integer.parseInt(Path2Data.GetData("statue1", 1)), Integer.parseInt(Path2Data.GetData("statue1", 2)), 110, 146);
        Statue1But.addActionListener(this);
        Statue1But.setVisible(true);
        Statue1But.setContentAreaFilled(false);		//Make the button background invisible
		Statue1But.setBorderPainted(false);
        add(Statue1But);
        Statue2But = new JButton(new ImageIcon("./images/Statue2.png"));
        Statue2But.setBounds(Integer.parseInt(Path2Data.GetData("statue2", 1)), Integer.parseInt(Path2Data.GetData("statue2", 2)), 115, 286);
        Statue2But.addActionListener(this);
        Statue2But.setVisible(true);
        Statue2But.setContentAreaFilled(false);		//Make the button background invisible
		Statue2But.setBorderPainted(false);
        add(Statue2But);
        Statue3But = new JButton(new ImageIcon("./images/Statue3.png"));
        Statue3But.setBounds(Integer.parseInt(Path2Data.GetData("statue3", 1)), Integer.parseInt(Path2Data.GetData("statue3", 2)), 100, 150);
        Statue3But.addActionListener(this);
        Statue3But.setVisible(true);
        Statue3But.setContentAreaFilled(false);		//Make the button background invisible
		Statue3But.setBorderPainted(false);
        add(Statue3But);
        Flute1But = new JButton(new ImageIcon("./images/Flute.png"));
        Flute1But.setBounds(Integer.parseInt(Path2Data.GetData("flute", 1)), Integer.parseInt(Path2Data.GetData("flute", 2)), 86, 98);
        Flute1But.addActionListener(this);
        Flute1But.setVisible(true);
        Flute1But.setContentAreaFilled(false);		//Make the button background invisible
		Flute1But.setBorderPainted(false);
        add(Flute1But);
        
        Game3Panel = new Game3();
        Game3Panel.setBounds(0, 0, intGameWidth, intGameHeight);
        Game3Panel.setVisible(false);
        add(Game3Panel);     
        
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
        //setComponentZOrder(Game3But, 0);  
        //setComponentZOrder(Game4But, 0); 
        
        Path2Data = new Path2Model();
   	}
}
