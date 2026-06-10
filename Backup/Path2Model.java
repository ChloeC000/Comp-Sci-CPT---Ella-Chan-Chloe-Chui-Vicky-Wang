import java.io.*;
import java.util.*;

/*
 * Path 2 Model
 */
public class Path2Model{
	String[][] strData;
		
	public void Init(){
		BufferedReader data = null;
		try{
			data = new BufferedReader(new FileReader("Path2.csv"));
			ArrayList<String[]> rows = new ArrayList<String[]>();
            String line;            				

            while ((line = data.readLine()) != null) {
                String[] temp = line.split(",");
                rows.add(temp);
            }
            data.close();

			// Create 2D array for the Path 2 csv file
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
			System.out.println("Path2.csv not found");
		} catch(IOException e2){
			System.out.println("Path2.csv has reading error");
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
	public Path2Model(){
		Init();
	}
}
