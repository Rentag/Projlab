import java.io.IOException;

public class Main {
	public static void main(String args[]) throws IOException 
	{
		System.out.println("Server standing up!");
		
		Server s= new Server("localhost", 25565);
		
		s.start();
		while (!s.isAlive()) 
		{
		}
	}
}
