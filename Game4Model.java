import java.io.*;

/*
 * Game 4 Model
 */
 
public class Game4Model{
	public void GetData(){
		BufferedReader data = null;
		try{
				data = new BufferedReader(new FileReader("Game4Data.txt"));
		}catch(FileNotFoundException e){
				System.out.println("Game4Data.txt not found");
		}
	}

  //constructor
  public Game4Model(){
  } 
}
