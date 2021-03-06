import java.io.ObjectInputStream;

public class Reader extends Thread {
	ObjectInputStream in;
	Connection c;
	boolean alive;
	int id=0;
	Server server;

	public void setId(int id) {this.id=id;}
	
	public Reader(ObjectInputStream ois, Connection c, Server server) 
	{
		alive=true;
		in = ois;
		this.c=c;
		this.server=server;
	}
	
	@SuppressWarnings("static-access")
	public void run() 
	{
		while(alive) {
			try {
				Object i = in.readObject();		
				if (i!=null) 
				{
					int n = (int)i;
					System.out.println("[SERVER]: Got message: " + n);
					server.setnumber(n);
					System.out.println("[SERVER]: value set to: " + server.getnumber());					
					c.sleep(50);
				} 
	
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("[SERVER]: Client "+ id +" disconnected!");
				server.removeConn(id);
				alive=false;
				return;
			}		
		}
	}

}