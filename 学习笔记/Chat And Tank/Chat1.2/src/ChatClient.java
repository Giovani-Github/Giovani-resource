import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class ChatClient extends Frame {
	Socket s = null;
	DataOutputStream dos = null;
	DataInputStream dis = null;
	Boolean receB = false;//���ƽ����̵߳ı���

	TextField tfTxt = new TextField();
	TextArea taContent = new TextArea();

	public static void main(String[] args) {
		//��������
		new ChatClient().launchFrame();
	}

	//���ڳ�ʼ��
	public void launchFrame() {
		setLocation(400, 300);
		this.setSize(300, 300);
		add(tfTxt, BorderLayout.SOUTH);
		add(taContent, BorderLayout.NORTH);
		pack();
		
		//���ڹر��¼�
		this.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent arg0) {
				disconnect();
				System.exit(0);
			}
			
		});
		
		//�����������¼������ڷ������ݵ������
		tfTxt.addActionListener(new TFListener());
		setVisible(true);
		connect();
	}
	
	//���ÿͻ���Sokcet�ķ���
	public void connect() {
		try {
			s = new Socket("127.0.0.1", 8888);
			dos = new DataOutputStream(s.getOutputStream());
			dis = new DataInputStream(s.getInputStream());
System.out.println("connected!");

			receB = true;
			
			new Thread(new Rece()).start();

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//�ر���Դ�ķ���
	public void disconnect() {
		try {
			receB = false;
			dos.close();
			dis.close();
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//�������ݵ��¼���
	private class TFListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String str = tfTxt.getText().trim();
			tfTxt.setText("");
			
			try {
				dos.writeUTF(str);
				dos.flush();

			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}
		
	}

	//�������ݵ��߳���
	private class Rece implements Runnable {		
		public void run() {
			try {
				while(receB) {
					String str = dis.readUTF();
					taContent.append(str + "\r\n");
				}
			} catch (SocketException e) {
				System.out.println("�˳���");
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
