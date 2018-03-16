import java.io.IOException;

public class Main {
	public static void main(String args[]) throws IOException 
	{	
		System.out.print("Client standing up!");
		
		Client c;
		
		
		try {
			c= new Client("localhost",25565,5000);

			c.start();

			while (c.isAlive()) 
			{
				int i=-1;
				i = ((int) System.in.read()) - 48;
				if (i > 1 ) 
				{
					c.send(i);					
				}
				if (i == 1 ) {c.read();}
				if (i == 0 ) {c.quit(); return;}
			}
		} catch (Exception e) {
			System.out.println("haltam");}
	

	}
}
