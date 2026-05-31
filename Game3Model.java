import java.io.*;

/*
 * Game 3 Model
 */
 
public class Game3Model{
	public void GetData(){
		BufferedReader data = null;
		try{
				data = new BufferedReader(new FileReader("Game3Data.txt"));
		}catch(FileNotFoundException e){
				System.out.println("Game3Data.txt not found");
		}
	}

  //constructor
  public Game3Model(){
  } 
}
