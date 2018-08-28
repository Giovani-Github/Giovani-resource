import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * ̹�����ӷ���˵Ŀͻ�����
 * @author Administrator
 *
 */

public class NetClient {
	//udp�ĳ�ʼ�˿�
	private static int UDP_PORT_START = 8899;
	
	//��ֹ����ͻ��˲�������¶˿ںų�ͻ���ڽ�һ���˿ںű���
	private int udpPort;
	
	//���д�ܼ�����
	TankClient tc;
	
	public NetClient(TankClient tc) {
		udpPort = UDP_PORT_START++;
		this.tc = tc;
	}
	
	/**
	 * ���ӷ���
	 * @param IP Ҫ���ӵ�ip��ַ
	 * @param port Ҫ���ӵĶ˿�
	 */
	public void connect(String IP, int port) {
		Socket s = null;
		try {
			//���ip�Ķ˿ڽ�������
			s = new Socket(IP, port);
			
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			//��tcp���������˷���������ͻ��������ӵ�udp�˿ڣ��Ա���շ�������ҷ��͹���udp��Ϣ
			dos.writeInt(udpPort);
			
			DataInputStream dis = new DataInputStream(s.getInputStream());
			//��tcp���շ���˷�������Ϣ
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
