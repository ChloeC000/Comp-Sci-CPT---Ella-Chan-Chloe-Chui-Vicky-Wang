import java.io.*;
import java.util.*;

/*
 * Path 3 Model
 */
public class Path3Model{
	String[][] strData;	
	int intBeat1;
	int intBeat2;
	int intBeat3;
	
	public void Game5Beat(){
		BufferedReader data = null;
		try{
			data = new BufferedReader(new FileReader("Game5Data.csv"));
		}catch(FileNotFoundException e){
			System.out.println("Game5Data.csv not found");
		}
		intBeat1 = (int)(Math.random() * 5 + 1); 
		intBeat2 = (int)(Math.random() * 5 + 1); 
		intBeat3 = (int)(Math.random() * 5 + 1); 
	}		
	public void Init(){
		BufferedReader data = null;
		try{
			data = new BufferedReader(new FileReader("Path3.csv"));
			ArrayList<String[]> rows = new ArrayList<String[]>();
            String line;            				

            while ((line = data.readLine()) != null) {
                String[] temp = line.split(",");
                rows.add(temp);
            }
            data.close();

			// Create 2D array for the Path 3 csv file
			strData = new String[rows.size()][7];
			for (int r = 0; r < rows.size(); r++) {
				String[] temp = rows.get(r);
				for (int c = 0; c < 7; c++) {
					if (c < temp.length) {
						strData[r][c] = temp[c];
					} else {
						strData[r][c] = "";
					}
				}
			}
		} catch(FileNotFoundException e){
			System.out.println("Path3.csv not found");
		} catch(IOException e2){
			System.out.println("Path3.csv has reading error");
		}
	}
	public String GetData(String strObj, int intReturnColID){
		for (int r = 0; r < strData.length; r++) {
			if (strData[r][0].equals(strObj)){
				return strData[r][intReturnColID];
			}
		}
		return "";
	}
	public Path3Model(){
		Init();
	}
}
