import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * 坦克连接服务端的客户端类
 * @author Administrator
 *
 */

public class NetClient {
	//防止多个客户端并起而导致端口号冲突，在建一个端口号变量
	private int udpPort;
	
	//持有大管家引用
	TankClient tc;
	
	//客户端的udp服务	
	private DatagramSocket ds;
	
	public NetClient(TankClient tc) {
		this.tc = tc;
	}
	
	/**
	 * 连接方法
	 * @param IP 要连接的ip地址
	 * @param port 要连接的端口
	 */
	public void connect(String IP, int port) {
		try {
			ds = new DatagramSocket(udpPort);
		} catch (SocketException e) {
			e.printStackTrace();
		}		
		
		Socket s = null;
		try {
			//与此ip的端口进行连接
			s = new Socket(IP, port);
			
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			//用tcp服务向服务端发送我这个客户端所监视的udp端口，以便接收服务端想我发送过来udp信息
			dos.writeInt(udpPort);
			
			DataInputStream dis = new DataInputStream(s.getInputStream());
			//用tcp接收服务端发来的信息
			int id = dis.readInt();
			tc.myTank.id = id;
			
System.out.println("Connected to Server! and Server give me id" + id);
		} catch (UnknownHostException e) {
			e.printStackTrace();
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
		
		//在坦克客户端连接上坦克服务端的时候，用udp服务把坦克诞生的消息发送过去
		Msg msg = new TankNewMsg(tc.myTank);
		send(msg);
		
		//启动接收服务端发送过来的udp数据的线程
		new Thread(new UDPRecvThread()).start();
	}

	//发送udp数据的方法法
	public void send(Msg msg) {
		//调用坦克消息类自身的发送方法，把udp服务的引用传递给他
		try {
			msg.send(ds, InetAddress.getLocalHost().getHostAddress(), TankServer.UDP_PORT);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	private class UDPRecvThread implements Runnable {
		byte[] bus = new byte[1024];
		
		public void run() {
			//接收服务端udp发送过来的数据
			while(ds != null) {
				DatagramPacket dp = new DatagramPacket(bus, bus.length); 
				try {
					//把接收进来的数据放进包裹，包裹再放到bus中
					ds.receive(dp);
System.out.println("a packet received! from server!");

					parse(dp);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		//解析服务端upd发过来的数据
		private void parse(DatagramPacket dp) {
			ByteArrayInputStream bais = new ByteArrayInputStream(bus, 0, dp.getLength());
			DataInputStream dis = new DataInputStream(bais);
			
			//什么消息类型就调用什么类型的解析方法
			int msgType = 0;
			try {
				msgType = dis.readInt();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			Msg msg;
			switch(msgType) {
			case Msg.TANK_NEW_MSG :
				msg = new TankNewMsg(NetClient.this.tc);
				msg.parse(dis);
				break;
			case Msg.TANK_MOVE_MSG :
				msg = new TankMoveMsg(NetClient.this.tc);
				msg.parse(dis);
				break;
			case Msg.MISSILE_NEW_MSG :
				msg = new MissileNewMsg(NetClient.this.tc);
				msg.parse(dis);
				break;
			}
			

		}
		
	}
	
	public int getUdpPort() {
		return udpPort;
	}

	public void setUdpPort(int udpPort) {
		this.udpPort = udpPort;
	}
}
