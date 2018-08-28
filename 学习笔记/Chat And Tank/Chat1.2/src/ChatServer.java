import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
	
	boolean started = false;//记录服务器是否启动，默认没启动
	boolean bConnected = false;//是否有客户端连接上
	ServerSocket ss = null;//服务端的Socket
	ArrayList<Client> clients =  new ArrayList<Client>();//存储处理客户端线程的集合
	
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
System.out.println("a client connected!");	

				Client c = new Client(s);
				new Thread(c).start();
		
				//把处理这个客户端的线程存储起来
				clients.add(c);
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
		private DataOutputStream dos = null;
		
		public Client(Socket s) {
			this.s = s;
			
			try {
				dis = new DataInputStream(s.getInputStream());
				dos = new DataOutputStream(s.getOutputStream());
			} catch(IOException e) {
				e.printStackTrace();
			}
			
			
		}
		
		public void run() {		
			try {						
				while(bConnected) {
					String str = dis.readUTF();
System.out.println(str);

					for(int i=0; i<clients.size(); i++) {
						Client c = clients.get(i);//获取处理客户端的线程
						c.send(str);//用这个线程给客户端自身发数据
					}
				}
			} catch (EOFException e) { //客户端被关闭会发生的异常
				System.out.println("Client closed!");
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if(dis != null) 
						dis.close();
					if(dos != null)
						dos.close();
					if(s != null) 
						s.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		
		//发送到客户端
		public void send(String str) {
			try {
				dos.writeUTF(str);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
