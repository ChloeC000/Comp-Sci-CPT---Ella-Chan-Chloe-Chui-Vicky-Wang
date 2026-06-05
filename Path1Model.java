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
	
	int intCount1;
	int intCount2;
	boolean blnMatch = false;
	boolean blnPuzzle = false;
	
	//Methods
	//Load csv file into array
	public void loadArray(){
		for(intCount1 = 0; intCount1< 7; intCount1++){
			//Read each line once than split
			try{
				strLine = SequenceFile.readLine();
			}catch (IOException e){
				strLine = "blank,blank,blank, blank, blank, blank, blank";
				System.out.println("Could not read line");
			}
			strSplit = strLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
			//loop within a loop to run 7 times for each column
			for(intCount2 = 0; intCount2 < 7; intCount2++){
				strSequence[intCount1][intCount2] = strSplit[intCount2];
				System.out.println("Row "+intCount1 +": "+strSequence[intCount1][intCount2]);
			}
		}
	}
	
	//Read the row of the array and decide what puzzle/clue to do based on the csv file
	public int AssignRow(String strObject){
		//Read the first column of each row and match the object to it.
		intCount1 = -1;
		while(blnMatch == false){
			intCount1 = intCount1 + 1;
			if(strSequence[intCount1][0].equalsIgnoreCase(strObject)){
				blnMatch = true;
			}
		}
		blnMatch = false;
		return intCount1;
	}
	public boolean AssignAction(int intRow){
		//Look if there is a puzzle assigned for that row
		if(!strSequence[intRow][4].equals("none")){
			if (strSequence[intRow][4].equals("cards")){
				blnPuzzle = false;
			}
		}else{
			blnPuzzle = true;
		}
		return blnPuzzle;
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
