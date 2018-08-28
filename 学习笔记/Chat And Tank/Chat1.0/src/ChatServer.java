import java.io.*;
import java.net.*;

public class ChatServer {
	
	boolean started = false;//记录服务器是否启动，默认没启动
	boolean bConnected = false;//是否有客户端连接上
	ServerSocket ss = null;//服务端的Socket
	
	public static void main(String[] args) {
		new ChatServer().start();
	}
	
	//启动服务器的方法
	public void start() {
		
		try {
			ss = new ServerSocket(8888);
			started = true;//表示客户端已经启动
			
			//每有一个客户端连接上来，就启动一个线程处理这个客户端
			while(started) {
				Socket s = ss.accept();
				bConnected = true;
				new Thread(new Client(s)).start();
				
System.out.println("a client connected!");

			}
		} catch (BindException e) {
			System.out.println("端口使用中....");
			System.out.println("请关掉相关程序并重新运行服务器！");
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				//关闭服务端
				ss.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	//处理客户端数据的线程
	class Client implements Runnable {

		private Socket s;
		private DataInputStream dis = null;
		
		public Client(Socket s) {
			this.s = s;
			
			try {
				dis = new DataInputStream(s.getInputStream());
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		public void run() {
			
			try {						
				while(bConnected) {
					String str = dis.readUTF();
					System.out.println(str);
				}
			} catch (EOFException e) { //客户端被关闭会发生的异常
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
}
