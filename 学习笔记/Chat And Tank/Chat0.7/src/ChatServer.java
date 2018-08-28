import java.io.IOException;
import java.net.*;
import java.io.*;

public class ChatServer {

	public static void main(String[] args) {
		BufferedReader br = null;
		try {
			ServerSocket ss = new ServerSocket(20142);		
			while(true) {
				Socket s = ss.accept();		
System.out.println("a client connected");

				br = new BufferedReader(new InputStreamReader(s.getInputStream()));
				
				String line = null;
				while((line=br.readLine()) != null) {
					System.out.println(line);
				}
			
			
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
