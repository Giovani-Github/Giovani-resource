import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * ̹�����ӷ���˵Ŀͻ�����
 * @author Administrator
 *
 */

public class NetClient {
	
	/**
	 * ���ӷ���
	 * @param IP Ҫ���ӵ�ip��ַ
	 * @param port Ҫ���ӵĶ˿�
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
