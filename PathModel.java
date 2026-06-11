/*
Course: ICS4U1b Computer Science
Teacher: Mr. Alfred Ron Cadawas
Memebers: Ella Chan, Chloe Chui, Vicky Wang
Assignment Name: CPT

This is the common Model for the whole game.
*/

// Import the swing and IO libraries
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.io.*;
import java.util.*;

public class PathModel{
	// Properties
	// Variables for the extracting and storing the data from the CSV files
	String strSequence[][] = new String[7][7];
	String strSplit[];
	String strLine = "";
	String[][] strData;
	BufferedReader SequenceFile;	
	int intCount1;
	int intCount2;
	// Check the click sequence and activating the game
	boolean blnMatch = false;
	boolean blnPuzzle = false;
	// For the beat game to check if the beats are created correctly
	int intBeat1;
	int intBeat2;
	int intBeat3;
	
	//Methods
	//Load csv file into array
	/**Load csv file into array*/
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
			}
		}
	}
	
	//Find the row of the object based of its name and return the value
	/**Find the row of the object based of its name and return the value*/
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
	/**Assigns Action based of the row of the object*/
	public boolean AssignAction(int intRow){
		//Look if there is a puzzle assigned for that row
		if(!strSequence[intRow][4].equals("none")){
			blnPuzzle = false;
		}else{
			blnPuzzle = true;
		}
		return blnPuzzle;
	}
	/**Read the whole CSV file based on the path and store the data in the 2D array for path 3*/
	public void CreateArray(){
		try{
			ArrayList<String[]> rows = new ArrayList<String[]>();
            String strLine;            				

            // Read each line and split the content of CSV by the ","
            while ((strLine = SequenceFile.readLine()) != null) {
                String[] temp = strLine.split(",");
                rows.add(temp);
            }
            SequenceFile.close();

			// Create 2D array from the CSV file
			strData = new String[rows.size()][7];
			for (int r = 0; r < rows.size(); r++) {
				String[] strTemp = rows.get(r);
				for (int c = 0; c < 7; c++) {
					if (c < strTemp.length) {
						strData[r][c] = strTemp[c];
					} else {
						strData[r][c] = "";
					}
				}
			}
		} catch(FileNotFoundException e){
			System.out.println("CSV not found");
		} catch(IOException e2){
			System.out.println("CSV has reading error");
		}
	}
	// Return the value of the specific column of the 2D array based on the object name. The first parameter is the object name, the second parameter is the column ID that needs to check
	/**Return the value of the specific column of the 2D array based on the object name. The first parameter is the object name, the second parameter is the column ID that needs to check*/
	public String GetData(String strObj, int intReturnColID){
		for (int r = 0; r < strData.length; r++) {
			if (strData[r][0].equals(strObj)){
				return strData[r][intReturnColID];
			}
		}
		return "";
	}
	// Generate the random no. for the 3 beats in the beat game
	/**Generate the random number for the 3 beats in the beat game*/
	public void Game5Beat(){		
		intBeat1 = (int)(Math.random() * 5 + 1); 
		intBeat2 = (int)(Math.random() * 5 + 1); 
		intBeat3 = (int)(Math.random() * 5 + 1); 
	}	
	
	//Constructor
	/**Constructor that takes the name of the csv file to run the methods*/
	public PathModel(String strCurrentFile){		
		// Read the CSV file passing from the Controller. The parameter is the CSV filename.
		try{
			SequenceFile = new BufferedReader(new FileReader(strCurrentFile));
		}catch(IOException e){
			System.out.println("Could not load the CSV " + strCurrentFile + ", IOException");
		}
	
	}
	
}
