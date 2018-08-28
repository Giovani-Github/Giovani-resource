import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

/**
 * 坦克的服务端类
 * @author Administrator
 *
 */
public class TankServer {
	//服务端监听的TCP端口号
	public static final int TCP_PORT = 9889;
	
	//服务端监听的UDP端口号
	public static final int UDP_PORT = 6656;
	
	//保存客户端信息的集合
	private List<Client> clients = new ArrayList<Client>();
	
	//给客户端分配的额id号
	private static int ID = 100;
	
	//服务端启动方法
	public void start() {
		//启用udp服务线程
		new Thread(new UDPThread()).start();
		
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
				clients.add(c);//保存到客户端集合中
				DataOutputStream dos = new DataOutputStream(s.getOutputStream());
				dos.writeInt(ID++);//给每个客户端的主战坦克分配一个id号
System.out.println("A Clinet Connect！ Addr-" + s.getInetAddress() + "tcpPort:" + s.getPort() + "udpPort:" + port);
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

	/**
	 * 接收客户端UDP数据的线程类
	 * @author Administrator
	 *
	 */
	private class UDPThread implements Runnable {
		//存储数据
		byte[] bus = new byte[1024];
		
		public void run() {
			DatagramSocket ds = null;
			try {
				//建立udp服务
				ds = new DatagramSocket(UDP_PORT);
			} catch (SocketException e) {
				e.printStackTrace();
			}
System.out.println("UDP thread started at port:" + UDP_PORT);			

			//接收客户端udp发送过来的数据
			while(ds != null) {
				DatagramPacket dp = new DatagramPacket(bus, bus.length); 
				try {
					//把接收进来的数据放进包裹，包裹再放到bus中
					ds.receive(dp);
System.out.println("a packet received!");					

					//把其他坦克发送过来的消息转发给每一个坦克
					for(int i=0; i<clients.size(); i++) {
						Client c = clients.get(i);
						//设置要将此数据报发往的远程主机的 SocketAddress（通常为 IP 地址 + 端口号）
						dp.setSocketAddress(new InetSocketAddress(c.ip, c.port));
						ds.send(dp);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
