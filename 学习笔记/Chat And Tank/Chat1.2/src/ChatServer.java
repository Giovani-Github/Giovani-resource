import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
	
	boolean started = false;//��¼�������Ƿ�������Ĭ��û����
	boolean bConnected = false;//�Ƿ��пͻ���������
	ServerSocket ss = null;//����˵�Socket
	ArrayList<Client> clients =  new ArrayList<Client>();//�洢����ͻ����̵߳ļ���
	
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
System.out.println("a client connected!");	

				Client c = new Client(s);
				new Thread(c).start();
		
				//�Ѵ�������ͻ��˵��̴߳洢����
				clients.add(c);
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
		private DataOutputStream dos = null;
		
		public Client(Socket s) {
			this.s = s;
			
			try {
				dis = new DataInputStream(s.getInputStream());
				dos = new DataOutputStream(s.getOutputStream());
			} catch(IOException e) {
				e.printStackTrace();
			}
			
			
		}
		
		public void run() {		
			try {						
				while(bConnected) {
					String str = dis.readUTF();
System.out.println(str);

					for(int i=0; i<clients.size(); i++) {
						Client c = clients.get(i);//��ȡ����ͻ��˵��߳�
						c.send(str);//������̸߳��ͻ�����������
					}
				}
			} catch (EOFException e) { //�ͻ��˱��رջᷢ�����쳣
				System.out.println("Client closed!");
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if(dis != null) 
						dis.close();
					if(dos != null)
						dos.close();
					if(s != null) 
						s.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		
		//���͵��ͻ���
		public void send(String str) {
			try {
				dos.writeUTF(str);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
