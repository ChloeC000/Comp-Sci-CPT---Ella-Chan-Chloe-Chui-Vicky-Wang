//Path 2 Model

import java.io.*;

public class Path2Model{
	
	//Properties
	
	//Stores the contents of Path2.csv
	String strSequence[][] = new String[7][7];
	String strSplit[];
	String strLine = "";
	BufferedReader SequenceFile;
	
	int intCount1;
	int intCount2;
	
	//Used to locate objects and determine if clues can be shown
	boolean blnMatch = false;
	boolean blnPuzzle = false;
	
	
	//Methods
	
	//Loads the csv file into the 2D array
	public void loadArray(){
		
		for(intCount1 = 0; intCount1 < 6; intCount1++){
			
			try{
				strLine = SequenceFile.readLine();
			}catch(IOException e){
				strLine = "blank,blank,blank,blank,blank,blank,blank";
				System.out.println("Could not read line");
			}
			
			strSplit = strLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
			
			//Store each column into the array
			for(intCount2 = 0; intCount2 < 7; intCount2++){
				strSequence[intCount1][intCount2] = strSplit[intCount2];
				System.out.println("Row " + intCount1 + ": " + strSequence[intCount1][intCount2]);
			}
		}
	}
	
	
	//Searches the array and returns the row of the object
	public int AssignRow(String strObject){
		
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
	
	
	//Checks whether an object has a puzzle attached to it
	public boolean AssignAction(int intRow){
		
		if(!strSequence[intRow][3].equals("none")){
			blnPuzzle = false;
		}else{
			blnPuzzle = true;
		}
		
		return blnPuzzle;
	}
	
	
	//Constructor
	public Path2Model(){
		
		//Load the Path 2 csv file
		try{
			SequenceFile = new BufferedReader(new FileReader("Path2.csv"));
		}catch(IOException e){
			System.out.println("Could not load file");
		}
	}
}