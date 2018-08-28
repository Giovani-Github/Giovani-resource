import java.io.IOException;
import java.net.*;
import java.io.*;

public class ChatServer {
	
	public static void main(String[] args) {
		boolean serb = false;//监听服务器启动的属性
		
		try {
			ServerSocket ss = new ServerSocket(20142);		
			serb = true;//服务器启动后设置为true
			
			while(serb) {//服务器启动就一直循环
				boolean socketb = false;//监听是否有客户端连接
				Socket s = ss.accept();
				socketb = true;//客户端连上只后设置为true
				
System.out.println("a client connected");

				DataInputStream br  = new DataInputStream(s.getInputStream());

				while(socketb) {//
					String line = br.readUTF();
					System.out.println(line);
				}
				br.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
