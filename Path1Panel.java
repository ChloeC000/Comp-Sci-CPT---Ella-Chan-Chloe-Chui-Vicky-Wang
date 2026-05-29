//Path 1 View

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

public class Path1Panel extends JPanel implements ActionListener{
	//Properties
	BufferedImage imgPath1Background = null;
	Path1Model Model1 = new Path1Model();
	Timer theTimer = new Timer(1000/60, this);
	
	//Objects
	JButton btnDeerBody = new JButton(new ImageIcon("images/Deer Body.png"));
	
	//Text Area where clues are given
	JTextArea TheTextArea = new JTextArea("");
	JLabel imgTextBox = new JLabel(new ImageIcon("images/Text Box.png"));
	
	//Methods
	//Draw Background image
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(imgPath1Background, 0, 0, null);
	}
	
	public void actionPerformed(ActionEvent evt){
		this.repaint();
		if(evt.getSource() == btnDeerBody){
			
		}
	}
	
	//Constructor
	public Path1Panel(){
		//Set panel parameters
		this.setLayout(null);
		this.setPreferredSize(new Dimension(1280, 720));
		
		//Load background
		try{
			imgPath1Background = ImageIO.read(new File("images/Path1 Background.png"));
		}catch(IOException e){
			System.out.println("Unable to load image");
		}
		
		//Load array
		Model1.loadArray();
		
		//Text Area
		imgTextBox.setBounds(50,520,908, 155);		//Draw of the text box
		TheTextArea.setBounds(90,535,800,120);
		TheTextArea.setFont(new Font("Arial", Font.PLAIN, 24));
		TheTextArea.setForeground(Color.WHITE);
		TheTextArea.setEditable(false);
		TheTextArea.setOpaque(false); 
		//imgTextBox.setVisible(false);			//Make both invisible until they are needed
		//TheTextArea.setVisible(false);
		this.add(imgTextBox);
		this.add(TheTextArea);
		
		//Draw the objects
		//Deer Body
		btnDeerBody.setBounds(Integer.parseInt(Model1.strSequence[0][1]), Integer.parseInt(Model1.strSequence[0][2]), 502, 138);
		btnDeerBody.setContentAreaFilled(false);
		btnDeerBody.setBorderPainted(false);
		btnDeerBody.addActionListener(this);
		this.add(btnDeerBody);
		
	}
}
