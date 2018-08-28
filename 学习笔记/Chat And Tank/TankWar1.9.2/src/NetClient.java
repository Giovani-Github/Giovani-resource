import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 坦克连接服务端的客户端类
 * @author Administrator
 *
 */

public class NetClient {
	
	/**
	 * 连接方法
	 * @param IP 要连接的ip地址
	 * @param port 要连接的端口
	 */
	public void connect(String IP, int port) {
		try {
			Socket s = new Socket(IP, port);
System.out.println("Connected to Server");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
