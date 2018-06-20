import java.net.*;
import java.io.*;

public class Server extends Thread{

	private ServerSocket serverSocket;
	
	public Server(int port) throws IOException{
		serverSocket = new ServerSocket(port);
		//serverSocket.setSoTimeout(10000);
	}
	
	public void run(){
		while(true){
			try{
				
				System.out.println("Waiting on port " + 
				serverSocket.getLocalPort() + " for client... ");
				Socket server = serverSocket.accept();
				
				DataInputStream in = new DataInputStream(server.getInputStream());
			    System.out.println(in.readUTF());
			    
			    //this part to test passing objects through sockets
			    ObjectInputStream objectIn = new ObjectInputStream(server.getInputStream());
			    Student s = (Student) objectIn.readObject();
			    System.out.println("Object recieved = " + s.toString());
			    
			    DataOutputStream out = new DataOutputStream(server.getOutputStream());
			    out.writeUTF("Thanks for connecting to " + server.getLocalSocketAddress());
			    
			    server.close();
				
			}catch(SocketTimeoutException s){
				System.out.println("Socket timed out!");
				break;
			}catch(IOException e){
				e.printStackTrace();
			}catch(ClassNotFoundException cn){
				cn.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		int port = 6066;
		try {
			Thread t = new Server(port);
			t.start();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
