import java.io.*;

/*
 * Game 2 Model
 */
 
public class Game2Model{
	public void GetData(){
		BufferedReader data = null;
		try{
				data = new BufferedReader(new FileReader("Game2Data.csv"));
		}catch(FileNotFoundException e){
				System.out.println("Game2Data.csv not found");
		}
	}

  //constructor
  public Game2Model(){
  } 
}
