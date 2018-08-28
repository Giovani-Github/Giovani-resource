import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 坦克的服务端类
 * @author Administrator
 *
 */
public class TankServer {
	//服务端监听的端口号
	public static final int TCP_PORT = 8889;
	
	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(TCP_PORT);
			
			while(true) {
				Socket s = ss.accept();
System.out.println("A Clinet Connect！ Addr-" + s.getInetAddress() + ":" + s.getPort());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
