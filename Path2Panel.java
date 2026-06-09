//Path 2 View

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

public class Path2Panel extends JPanel implements ActionListener, MouseListener{
	
	//Properties
	BufferedImage imgPath2Background = null;
	PathModel Model2 = new PathModel("Path2.csv");
	Timer theTimer = new Timer(1000/60, this);
	
	String strNextObject = "";
	int intObjectRow;
	
	//Allows objects to be clicked only when no puzzle is open
	boolean ActionEnabled = true;
	
	//Unlockables
	boolean blnStatue1 = false;
	boolean blnStatue2 = false;
	boolean blnStatue3 = false;
	boolean blnFlute = false;
	boolean blnOwl = false;
	boolean blnNest = false;
	
	//Marks completed objects
	boolean Statue1Complete = false;
	boolean Statue2Complete = false;
	boolean Statue3Complete = false;
	boolean FluteComplete = false;
	boolean OwlComplete = false;
	boolean NestComplete = false;
	boolean Path2Complete = false;
	
	//Objects, using placeholder image for now
	JButton btnStatue1 = new JButton(new ImageIcon("images/Laughing.png"));
	JButton btnStatue2 = new JButton(new ImageIcon("images/Laughing.png"));
	JButton btnStatue3 = new JButton(new ImageIcon("images/Laughing.png"));
	JButton btnFlute = new JButton(new ImageIcon("images/Laughing.png"));
	JButton btnOwl = new JButton(new ImageIcon("images/Laughing.png"));
	JButton btnNest = new JButton(new ImageIcon("images/Laughing.png"));
	
	//Text area where clues are shown
	JTextArea TheTextArea = new JTextArea("");
	JLabel imgTextBox = new JLabel(new ImageIcon("images/Text Box.png"));
	
	
	//Methods
	
	//Draw background image
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(imgPath2Background, 0, 0, null);
	}
	
	public void actionPerformed(ActionEvent evt){
		this.repaint();
        //If Statue 1 is clicked
        if(evt.getSource() == btnStatue1 && ActionEnabled == true){
            intObjectRow = Model2.AssignRow("statue1");
            
            if(blnStatue1 == false){
                blnStatue1 = Model2.AssignAction(intObjectRow);
            }
            
            if(blnStatue1 == true){
                TheTextArea.setText(Model2.strSequence[intObjectRow][5]);
                imgTextBox.setVisible(true);
                TheTextArea.setVisible(true);
                
                if(Statue1Complete == false){
                    strNextObject = Model2.strSequence[intObjectRow][6];
                    Statue1Complete = true;
                }
            }
        }
        //If Statue 2 is clicked
        if(evt.getSource() == btnStatue2 && ActionEnabled == true){
            if(strNextObject.equals("statue2") || blnStatue2 == true){
                intObjectRow = Model2.AssignRow("statue2");
                blnStatue2 = Model2.AssignAction(intObjectRow);
                
                TheTextArea.setText(Model2.strSequence[intObjectRow][5]);
                imgTextBox.setVisible(true);
                TheTextArea.setVisible(true);
                
                if(Statue2Complete == false){
                    strNextObject = Model2.strSequence[intObjectRow][6];
                    Statue2Complete = true;
                }
            }else{
                TheTextArea.setText("The second statue stays silent. Maybe another statue must speak first.");
                imgTextBox.setVisible(true);
                TheTextArea.setVisible(true);
            }
        }
        //If Statue 3 is clicked
        if(evt.getSource() == btnStatue3 && ActionEnabled == true){
            if(strNextObject.equals("statue3") || blnStatue3 == true){
                intObjectRow = Model2.AssignRow("statue3");
                
                if(blnStatue3 == false){
                    TheTextArea.setText(Model2.strSequence[intObjectRow][5]);
                    imgTextBox.setVisible(true);
                    TheTextArea.setVisible(true);
                    
                    //Puzzle will go here later
                    //SymbolPuzzle();
                }
                
                if(blnStatue3 == true){
                    TheTextArea.setText(Model2.strSequence[intObjectRow][5]);
                    imgTextBox.setVisible(true);
                    TheTextArea.setVisible(true);
                    
                    if(Statue3Complete == false){
                        strNextObject = Model2.strSequence[intObjectRow][6];
                        Statue3Complete = true;
                    }
                }
            }else{
                TheTextArea.setText("The third statue feels locked in place. Maybe the first two statues are part of the order.");
                imgTextBox.setVisible(true);
                TheTextArea.setVisible(true);
            }
        }
	}
	
	public void mouseClicked(MouseEvent evt){
		if(evt.getSource() == this && imgTextBox.isShowing()){
			TheTextArea.setText("");
			imgTextBox.setVisible(false);
			TheTextArea.setVisible(false);
		}
	}
	
	public void mousePressed(MouseEvent evt){
	}
	
	public void mouseReleased(MouseEvent evt){
	}
	
	public void mouseEntered(MouseEvent evt){
	}
	
	public void mouseExited(MouseEvent evt){
	}
	
	
	//Constructor
	public Path2Panel(){
		
		//Set panel parameters
		this.setLayout(null);
		this.setPreferredSize(new Dimension(1280, 720));
		this.addMouseListener(this);
		
		//Load background
		try{
			imgPath2Background = ImageIO.read(new File("images/Path2 Background.png"));
		}catch(IOException e){
			System.out.println("Unable to load image");
		}
		
		//Load array from Path2.csv
		Model2.loadArray();
		
		//Text Area
		imgTextBox.setBounds(50, 520, 908, 155);
		TheTextArea.setBounds(100, 535, 800, 120);
		TheTextArea.setFont(new Font("Arial", Font.PLAIN, 24));
		TheTextArea.setForeground(Color.WHITE);
		TheTextArea.setEditable(false);
		TheTextArea.setLineWrap(true);
		TheTextArea.setWrapStyleWord(true);
		TheTextArea.setOpaque(false);
		imgTextBox.setVisible(false);
		TheTextArea.setVisible(false);
		this.add(imgTextBox);
		this.add(TheTextArea);

        //think ill need a transition screen
        // later will do

        //////////////////////////////
        //the objects ill be drawing//
        //////////////////////////////
        //Statue 1
        btnStatue1.setBounds(Integer.parseInt(Model2.strSequence[0][1]), Integer.parseInt(Model2.strSequence[0][2]), 100, 100);
        btnStatue1.setContentAreaFilled(false);
        btnStatue1.setBorderPainted(false);
        btnStatue1.addActionListener(this);
        this.add(btnStatue1);

        //Statue 2
        btnStatue2.setBounds(Integer.parseInt(Model2.strSequence[1][1]), Integer.parseInt(Model2.strSequence[1][2]), 100, 100);
        btnStatue2.setContentAreaFilled(false);
        btnStatue2.setBorderPainted(false);
        btnStatue2.addActionListener(this);
        this.add(btnStatue2);

        //Statue 3
        btnStatue3.setBounds(Integer.parseInt(Model2.strSequence[2][1]), Integer.parseInt(Model2.strSequence[2][2]), 100, 100);
        btnStatue3.setContentAreaFilled(false);
        btnStatue3.setBorderPainted(false);
        btnStatue3.addActionListener(this);
        this.add(btnStatue3);

        //Flute
        btnFlute.setBounds(Integer.parseInt(Model2.strSequence[3][1]), Integer.parseInt(Model2.strSequence[3][2]), 100, 100);
        btnFlute.setContentAreaFilled(false);
        btnFlute.setBorderPainted(false);
        btnFlute.addActionListener(this);
        this.add(btnFlute);

        //Owl
        btnOwl.setBounds(Integer.parseInt(Model2.strSequence[4][1]), Integer.parseInt(Model2.strSequence[4][2]), 100, 100);
        btnOwl.setContentAreaFilled(false);
        btnOwl.setBorderPainted(false);
        btnOwl.addActionListener(this);
        this.add(btnOwl);

        //Nest
        btnNest.setBounds(Integer.parseInt(Model2.strSequence[5][1]), Integer.parseInt(Model2.strSequence[5][2]), 100, 100);
        btnNest.setContentAreaFilled(false);
        btnNest.setBorderPainted(false);
        btnNest.addActionListener(this);
        this.add(btnNest);
            }
        }
