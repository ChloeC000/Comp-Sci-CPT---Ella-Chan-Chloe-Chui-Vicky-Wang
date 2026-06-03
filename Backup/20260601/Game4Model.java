import java.io.*;

/*
 * Game 4 Model
 */
 
public class Game4Model{
	public void GetData(){
		BufferedReader data = null;
		try{
				data = new BufferedReader(new FileReader("Game4Data.csv"));
		}catch(FileNotFoundException e){
				System.out.println("Game4Data.csv not found");
		}
	}

  //constructor
  public Game4Model(){
  } 
}
