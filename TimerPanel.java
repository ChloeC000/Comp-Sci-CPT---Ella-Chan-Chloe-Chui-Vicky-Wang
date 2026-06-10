import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Create a custom panel for the timer
public class TimerPanel extends JPanel implements ActionListener{
	//Properties
	JPanel buttonPanel = new JPanel();
    JLabel timeLabel = new JLabel("20:00", SwingConstants.CENTER);;
    Timer Timer= new Timer(1000,this);   
	int intremainingSeconds = 1200; // Initial countdown time
	int intminutes;
	int intseconds;
	boolean blnTimerEnd = false;
	
	//Methods
	public void actionPerformed(ActionEvent evt){
		intremainingSeconds = intremainingSeconds - 1;
		//format time into MM:SS
        intminutes = intremainingSeconds / 60;
        intseconds = intremainingSeconds % 60;
        timeLabel.setText(String.format("%02d:%02d", intminutes, intseconds));
        if(intremainingSeconds <= 0) {
			Timer.stop();
			blnTimerEnd = true;
		}
	}


	//Constructor
    public TimerPanel() {
        //Setup the layout and components
        this.setPreferredSize(new Dimension(100,50));

        //Show the timer
        timeLabel.setFont(new Font("Arial", Font.BOLD, 36));
        this.add(timeLabel);
     

    }
}
