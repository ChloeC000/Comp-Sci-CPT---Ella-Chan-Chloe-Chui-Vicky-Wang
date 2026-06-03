import java.io.*;

/*
 * Game 1 Model
 */
 
public class Game1Model{
	public void GetData(){
		BufferedReader data = null;
		try{
				data = new BufferedReader(new FileReader("Game1Data.csv"));
		}catch(FileNotFoundException e){
				System.out.println("Game1Data.csv not found");
		}
	}

  //constructor
  public Game1Model(){
  } 
}
