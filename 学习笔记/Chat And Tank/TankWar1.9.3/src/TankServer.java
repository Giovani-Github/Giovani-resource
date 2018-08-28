import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * ̹�˵ķ������
 * @author Administrator
 *
 */
public class TankServer {
	//����˼����Ķ˿ں�
	public static final int TCP_PORT = 8889;
	
	//����ͻ�����Ϣ�ļ���
	private List<Client> list = new ArrayList<Client>();
	
	//���ͻ��˷���Ķ�id��
	private static int ID = 100;
	
	public void start() {
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
				list.add(c);//���浽�ͻ��˼�����
				DataOutputStream dos = new DataOutputStream(s.getOutputStream());
				dos.writeInt(ID++);//��ÿ���ͻ��˵���ս̹�˷���һ��id��
System.out.println("A Clinet Connect�� Addr-" + s.getInetAddress() + ":" + s.getPort() + "udp port:" + port);
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
}
