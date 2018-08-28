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
 * ̹�����ӷ���˵Ŀͻ�����
 * @author Administrator
 *
 */

public class NetClient {
	//udp�ĳ�ʼ�˿�
	private static int UDP_PORT_START = 8859;
	
	//��ֹ����ͻ��˲�������¶˿ںų�ͻ���ڽ�һ���˿ںű���
	private int udpPort;
	
	//���д�ܼ�����
	TankClient tc;
	
	//�ͻ��˵�udp����	
	private DatagramSocket ds;
	
	public NetClient(TankClient tc) {
		udpPort = UDP_PORT_START++;
		this.tc = tc;
		
		try {
			ds = new DatagramSocket(udpPort);
		} catch (SocketException e) {
			e.printStackTrace();
		}
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
		
		//��̹�˿ͻ���������̹�˷���˵�ʱ����udp�����̹�˵�������Ϣ���͹�ȥ
		TankNewMsg msg = new TankNewMsg(tc.myTank);
		send(msg);
		
		//�������շ���˷��͹�����udp���ݵ��߳�
		new Thread(new UDPRecvThread()).start();
	}

	//����udp���ݵķ�����
	public void send(TankNewMsg msg) {
		//����̹����Ϣ������ķ��ͷ�������udp��������ô��ݸ���
		try {
			msg.send(ds, InetAddress.getLocalHost().getHostAddress(), TankServer.UDP_PORT);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	private class UDPRecvThread implements Runnable {
		byte[] bus = new byte[1024];
		
		public void run() {
			//���շ����udp���͹���������
			while(ds != null) {
				DatagramPacket dp = new DatagramPacket(bus, bus.length); 
				try {
					//�ѽ��ս��������ݷŽ������������ٷŵ�bus��
					ds.receive(dp);
System.out.println("a packet received! from server!");

					parse(dp);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		//���������upd������������
		private void parse(DatagramPacket dp) {
			ByteArrayInputStream bais = new ByteArrayInputStream(bus, 0, dp.getLength());
			DataInputStream dis = new DataInputStream(bais);
			TankNewMsg msg = new TankNewMsg(NetClient.this.tc);
			msg.parse(dis);
		}
		
	}
}
