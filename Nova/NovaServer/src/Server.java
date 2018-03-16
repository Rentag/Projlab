import java.io.IOException;
import java.net.*;
import java.util.HashMap;;

public class Server extends Thread {
	ServerSocket socket;
	String ip;
	int port;
	
	int number;
	
	boolean alive=true;
	
	
	HashMap<Integer, Connection> clients = new HashMap<Integer, Connection>();
	
	public Server(String ip, int port) {
		this.ip=ip;
		this.port=port;
		

		try {
			
			socket = new ServerSocket();
			socket.bind(new InetSocketAddress(ip,port));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setnumber(int n) {System.out.println("[SERVER]: SET TO:" + number); number = n;}
	
	public int getnumber() {return number;}

	public void removeConn(int i) {clients.remove(i);}
	
	public void run() 
	{
		while (alive) 
		{
			try {
				Socket connection = socket.accept();
				
				Connection c = new Connection(this, connection);
				
				this.addClient(c);
				c.start();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void addClient(Connection c) 
	{
		int id = -1;
		while (id == -1 || clients.containsKey(id))
			id=(int) (Math.random()*1000)+1;
		clients.put(id, c);
		c.setId(id);
		System.out.println("[SERVER]: Connection id set to: "+id);
	}
	
	public void quit() {alive=false;}
}
