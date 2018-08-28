import java.io.*;
import java.net.*;

public class ChatServer {

	public static void main(String[] args) {
		boolean started = false;//记录服务器是否启动
		ServerSocket ss = null;
		Socket s = null;
		DataInputStream dis = null;
		try {
			ss = new ServerSocket(8888);
		} catch (BindException e) {
			System.out.println("端口使用中....");
			System.out.println("请关掉相关程序并重新运行服务器！");
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			started = true;
			while(started) {
				boolean bConnected = false;//是否有客户端连接上
				s = ss.accept();
				bConnected = true;
System.out.println("a client connected!");
				
				dis = new DataInputStream(s.getInputStream());
				while(bConnected) {
					String str = dis.readUTF();
					System.out.println(str);
				}
				//dis.close();
			}
		} catch (EOFException e) {
			System.out.println("Client closed!");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(dis != null) 
					dis.close();
				if(s != null) 
					s.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

}
