import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ̹�˵ķ������
 * @author Administrator
 *
 */
public class TankServer {
	//����˼����Ķ˿ں�
	public static final int TCP_PORT = 8889;
	
	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(TCP_PORT);
			
			while(true) {
				Socket s = ss.accept();
System.out.println("A Clinet Connect�� Addr-" + s.getInetAddress() + ":" + s.getPort());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
