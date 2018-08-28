import java.io.DataInputStream;
import java.net.DatagramSocket;


public interface Msg {
	public static final int TANK_NEW_MSG = 1;
	public static final int TANK_MOVE_MSG = 2;
	
	/**
	 * 发送消息的方法
	 * @param ds 坦克客户端的udp服务
	 * @param ip 服务端的ip
	 * @param udpport 服务端的dup端口号
	 */
	public void send(DatagramSocket ds, String ip, int udpPort);
	
	/**
	 * 解析信息的方法
	 * @param dis 要读取的流
	 */
	public void parse(DataInputStream dis);
}
