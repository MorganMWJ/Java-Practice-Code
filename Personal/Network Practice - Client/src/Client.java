import java.net.*;
import java.util.Scanner;
import java.io.*;


public class Client {

	public static void main(String[] args) {
				
		String serverName = args[0];
		int port = 6066;
		
		try{
			
			Socket client = new Socket(serverName, port);
			
			//get message to send to the server
			System.out.println("Please enter the message to send to the server: ");
			Scanner reader = new Scanner(System.in); 
			String message = reader.nextLine();
			reader.close();
			
			//OutputStream out = client.getOutputStream(); - for bytes
			//ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream()); - for objects
			DataOutputStream out = new DataOutputStream(client.getOutputStream()); //- primitives
			out.writeUTF("Message from client program " + 
					client.getLocalSocketAddress() + ": " + message);
			
			//code to send object via socket
			Student s = new Student("Morgan", 21);
			ObjectOutputStream objectOut = new ObjectOutputStream(client.getOutputStream());
			objectOut.writeObject(s);
			
			DataInputStream in = new DataInputStream(client.getInputStream());
			System.out.println("Server says: " + in.readUTF());
			
			client.close();
			
		}catch(IOException e){
			e.printStackTrace();
		}

	}

}
