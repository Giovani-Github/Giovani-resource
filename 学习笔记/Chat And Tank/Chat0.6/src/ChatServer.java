import java.io.IOException;
import java.net.*;

public class ChatServer {

	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(10002);		
			while(true) {
				Socket s = ss.accept();
				
System.out.println("a client connected");
			
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
