import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class ChatClient extends Frame {
	Socket s = null;
	DataOutputStream dos = null;

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
	
	//���ÿͻ���Sokcet�ķ���asdf
	public void connect() {
		try {
			s = new Socket("127.0.0.1", 8888);
			dos = new DataOutputStream(s.getOutputStream());
System.out.println("connected!");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//�ر���Դ�ķ���
	public void disconnect() {
		try {
			dos.close();
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//�������ݵ��¼���
	private class TFListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String str = tfTxt.getText().trim();
			taContent.setText(str);
			tfTxt.setText("");
			
			try {
				dos.writeUTF(str);
				dos.flush();

			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}
		
	}

}
