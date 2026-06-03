import java.io.*;

/*
 * Game 3 Model
 */
 
public class Game3Model{
	public void GetData(){
		BufferedReader data = null;
		try{
				data = new BufferedReader(new FileReader("Game3Data.csv"));
		}catch(FileNotFoundException e){
				System.out.println("Game3Data.csv not found");
		}
	}

  //constructor
  public Game3Model(){
  } 
}
