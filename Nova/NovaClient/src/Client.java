import java.io.*;
import java.net.*;
import java.net.Socket;

public class Client extends Thread{
	Socket socket;
	String ip;
	ObjectOutputStream out;
	ObjectInputStream in;
	int port;
	int maxConnTime;
	boolean alive;
	Reader r;
	
	public Client(String ip, int port, int max) {
		alive=true;
		this.ip=ip;
		this.port=port;
		maxConnTime=max;
		

		try {
			socket = new Socket();
			socket.connect(new InetSocketAddress(ip,port),maxConnTime);
			
			System.out.println("[Client]: Connecting to server...");
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
			System.out.println("[Client]: Connected to server!");

			r=new Reader(in, this);
			r.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void quit() {alive=false; r.quit();}
	
	public void send(int i) 
	{
		try {
			out.writeObject(i);
			out.flush();
			
			System.out.println("[Client]: sent: " + i);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
	public void read() 
	{
		r.read();
	}
		
	public void run() 
	{
		while (alive)
		{
			try {
				sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Client stopped!");
	}
	
}
