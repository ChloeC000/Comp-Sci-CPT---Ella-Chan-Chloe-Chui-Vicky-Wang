import java.io.*;

/*
 * Game 6 Model
 */
 
public class Game6Model{
	public void GetData(){
		BufferedReader data = null;
		try{
				data = new BufferedReader(new FileReader("Game6Data.csv"));
		}catch(FileNotFoundException e){
				System.out.println("Game6Data.csv not found");
		}
	}

  //constructor
  public Game6Model(){
  } 
}
