import java.io.*;

/*
 * Game 5 Model
 */
 
public class Game5Model{
	public void GetData(){
		BufferedReader data = null;
		try{
				data = new BufferedReader(new FileReader("Game5Data.txt"));
		}catch(FileNotFoundException e){
				System.out.println("Game5Data.txt not found");
		}
	}

  //constructor
  public Game5Model(){
  } 
}
