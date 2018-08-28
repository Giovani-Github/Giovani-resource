import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 坦克连接服务端的客户端类
 * @author Administrator
 *
 */

public class NetClient {
	//udp的初始端口
	private static int UDP_PORT_START = 8899;
	
	//防止多个客户端并起而导致端口号冲突，在建一个端口号变量
	private int udpPort;
	
	//持有大管家引用
	TankClient tc;
	
	public NetClient(TankClient tc) {
		udpPort = UDP_PORT_START++;
		this.tc = tc;
	}
	
	/**
	 * 连接方法
	 * @param IP 要连接的ip地址
	 * @param port 要连接的端口
	 */
	public void connect(String IP, int port) {
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
			tc.myTank.ID = id;
			
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
	}
}
