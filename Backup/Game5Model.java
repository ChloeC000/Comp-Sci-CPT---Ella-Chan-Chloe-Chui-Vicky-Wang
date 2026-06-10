import java.io.*;

/*
 * Game 5 Model
 */
 
public class Game5Model{
	int intBeat1;
	int intBeat2;
	int intBeat3;
	
	public void GetData(){
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

  //constructor
  public Game5Model(){
  } 
}
