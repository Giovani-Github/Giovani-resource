import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * 坦克的服务端类
 * @author Administrator
 *
 */
public class TankServer {
	//服务端监听的端口号
	public static final int TCP_PORT = 8889;
	
	//保存客户端信息的集合
	private List<Client> list = new ArrayList<Client>();
	
	//给客户端分配的额id号
	private static int ID = 100;
	
	public void start() {
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(TCP_PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}

		while(true) {
			Socket s = null;
			try {
				s = ss.accept();
				DataInputStream dis = new DataInputStream(s.getInputStream());
				String ip = s.getInetAddress().getHostAddress();//拿到客户单ip
				int port = dis.readInt();//拿到客户端udp端口
				Client c  = new Client(ip, port);//创建客端信息类
				list.add(c);//保存到客户端集合中
				DataOutputStream dos = new DataOutputStream(s.getOutputStream());
				dos.writeInt(ID++);//给每个客户端的主战坦克分配一个id号
System.out.println("A Clinet Connect！ Addr-" + s.getInetAddress() + ":" + s.getPort() + "udp port:" + port);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if(s != null) {
					try {
						s.close();
						s = null;
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new TankServer().start();
	}

	/**
	 * 客户端信息类
	 * @author 李庆旺
	 *
	 */
	private class Client {
		String ip;
		int port;
		
		public Client(String ip, int port) {
			this.ip = ip;
			this.port = port;
		}
	}
}
