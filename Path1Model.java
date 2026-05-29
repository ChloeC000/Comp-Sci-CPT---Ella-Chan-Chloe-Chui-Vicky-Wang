//Path 1 Model

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.io.*;

public class Path1Model{
	//Properties
	String strSequence[][] = new String[7][7];
	String strSplit[];
	String strLine = "";
	BufferedReader SequenceFile;
	
	//Unlockables
	
	//Methods
	public void loadArray(){
		int intCount1;
		int intCount2;
		for(intCount1 = 0; intCount1< 7; intCount1++){
			//Read each line once than split
			try{
				strLine = SequenceFile.readLine();
			}catch (IOException e){
				strLine = "blank,blank,blank, blank, blank, blank, blank";
				System.out.println("Could not read line");
			}
			strSplit = strLine.split(",");
			//loop within a loop to run 20 times for each column
			for(intCount2 = 0; intCount2 < 7; intCount2++){
				strSequence[intCount1][intCount2] = strSplit[intCount2];
				System.out.println("Row "+intCount1 +": "+strSequence[intCount1][intCount2]);
			}
		}
	}
	
	
	
	//Constructor
	public Path1Model(){
		try{
			SequenceFile = new BufferedReader(new FileReader("Path1.csv"));
		}catch(IOException e){
			System.out.println("Could not load file, IOException");
		}
	
	}
	
}
