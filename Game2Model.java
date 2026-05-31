import java.io.*;

/*
 * Game 2 Model
 */
 
public class Game2Model{
	int intBeat1;
	int intBeat2;
	int intBeat3;
	
	public void GetData(){
		BufferedReader data = null;
		try{
				data = new BufferedReader(new FileReader("Game2Data.txt"));
		}catch(FileNotFoundException e){
				System.out.println("Game2Data.txt not found");
		}
		intBeat1 = (int)(Math.random() * 5 + 1); 
		intBeat2 = (int)(Math.random() * 5 + 1); 
		intBeat3 = (int)(Math.random() * 5 + 1); 
	}

  //constructor
  public Game2Model(){
  } 
}
