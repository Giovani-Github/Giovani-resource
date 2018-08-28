import java.io.*;
import java.net.*;

public class ChatServer {
	
	boolean started = false;//��¼�������Ƿ�������Ĭ��û����
	boolean bConnected = false;//�Ƿ��пͻ���������
	ServerSocket ss = null;//����˵�Socket
	
	public static void main(String[] args) {
		new ChatServer().start();
	}
	
	//�����������ķ���
	public void start() {
		
		try {
			ss = new ServerSocket(8888);
			started = true;//��ʾ�ͻ����Ѿ�����
			
			//ÿ��һ���ͻ�������������������һ���̴߳�������ͻ���
			while(started) {
				Socket s = ss.accept();
				bConnected = true;
				new Thread(new Client(s)).start();
				
System.out.println("a client connected!");

			}
		} catch (BindException e) {
			System.out.println("�˿�ʹ����....");
			System.out.println("��ص���س����������з�������");
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				//�رշ����
				ss.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	//����ͻ������ݵ��߳�
	class Client implements Runnable {

		private Socket s;
		private DataInputStream dis = null;
		
		public Client(Socket s) {
			this.s = s;
			
			try {
				dis = new DataInputStream(s.getInputStream());
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		public void run() {
			
			try {						
				while(bConnected) {
					String str = dis.readUTF();
					System.out.println(str);
				}
			} catch (EOFException e) { //�ͻ��˱��رջᷢ�����쳣
				System.out.println("Client closed!");
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if(dis != null) 
						dis.close();
					if(s != null) 
						s.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}
