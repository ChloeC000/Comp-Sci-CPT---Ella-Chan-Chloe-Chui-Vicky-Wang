import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainTest implements ActionListener{
	JFrame thFrame;
    JPanel thePanel;
	JButton startBut;
    NetPanel netPanel;
  
	public void actionPerformed(ActionEvent evt){
		// Main program JComponent actionPerformed
		if (evt.getSource() == startBut) {
            System.out.println("Start");
        }  
	}
  
	public MainTest(){
		 // Main window
        thFrame = new JFrame("Main Program");
        thFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        thFrame.setSize(1280, 720);

        // Main panel
        thePanel = new JPanel();
        thePanel.setLayout(null);

        // Add this block to create NetPanel
        netPanel = new NetPanel();
        netPanel.thePanel.setBounds(955, 5, 300, 715);   // The network message panel always takes 300 pixel width and 715 pixel height
        thePanel.add(netPanel.thePanel);
        thFrame.setContentPane(thePanel);
        // Need to also close the NetPanel from the main program
        thFrame.addWindowListener(new java.awt.event.WindowAdapter() {
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
        startBut = new JButton("Start");
        startBut.setBounds(20, 20, 120, 30);
        startBut.addActionListener(this);
        thePanel.add(startBut);
        
        thFrame.setVisible(true);
	}
  
    public static void main(String[] args) {
        new MainTest();
    }
}
