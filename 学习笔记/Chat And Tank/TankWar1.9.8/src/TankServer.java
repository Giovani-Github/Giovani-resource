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
 * ̹�˵ķ������
 * @author Administrator
 *
 */
public class TankServer {
	//����˼�����TCP�˿ں�
	public static final int TCP_PORT = 9889;
	
	//����˼�����UDP�˿ں�
	public static final int UDP_PORT = 6656;
	
	//����ͻ�����Ϣ�ļ���
	private List<Client> clients = new ArrayList<Client>();
	
	//���ͻ��˷���Ķ�id��
	private static int ID = 100;
	
	//�������������
	public void start() {
		//����udp�����߳�
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
				String ip = s.getInetAddress().getHostAddress();//�õ��ͻ���ip
				int port = dis.readInt();//�õ��ͻ���udp�˿�
				Client c  = new Client(ip, port);//�����Ͷ���Ϣ��
				clients.add(c);//���浽�ͻ��˼�����
				DataOutputStream dos = new DataOutputStream(s.getOutputStream());
				dos.writeInt(ID++);//��ÿ���ͻ��˵���ս̹�˷���һ��id��
System.out.println("A Clinet Connect�� Addr-" + s.getInetAddress() + "tcpPort:" + s.getPort() + "udpPort:" + port);
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
	 * �ͻ�����Ϣ��
	 * @author ������
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
	 * ���տͻ���UDP���ݵ��߳���
	 * @author Administrator
	 *
	 */
	private class UDPThread implements Runnable {
		//�洢����
		byte[] bus = new byte[1024];
		
		public void run() {
			DatagramSocket ds = null;
			try {
				//����udp����
				ds = new DatagramSocket(UDP_PORT);
			} catch (SocketException e) {
				e.printStackTrace();
			}
System.out.println("UDP thread started at port:" + UDP_PORT);			

			//���տͻ���udp���͹���������
			while(ds != null) {
				DatagramPacket dp = new DatagramPacket(bus, bus.length); 
				try {
					//�ѽ��ս��������ݷŽ������������ٷŵ�bus��
					ds.receive(dp);
System.out.println("a packet received!");					

					//������̹�˷��͹�������Ϣת����ÿһ��̹��
					for(int i=0; i<clients.size(); i++) {
						Client c = clients.get(i);
						//����Ҫ�������ݱ�������Զ�������� SocketAddress��ͨ��Ϊ IP ��ַ + �˿ںţ�
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
