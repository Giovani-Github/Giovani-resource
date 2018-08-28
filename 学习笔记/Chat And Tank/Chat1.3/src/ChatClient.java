import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class ChatClient extends Frame {
	Socket s = null;
	DataOutputStream dos = null;
	DataInputStream dis = null;
	Boolean receB = false;//控制接收线程的变量

	TextField tfTxt = new TextField();
	TextArea taContent = new TextArea();

	public static void main(String[] args) {
		//启动窗口
		new ChatClient().launchFrame();
	}

	//窗口初始化
	public void launchFrame() {
		setLocation(400, 300);
		this.setSize(300, 300);
		add(tfTxt, BorderLayout.SOUTH);
		add(taContent, BorderLayout.NORTH);
		pack();
		
		//窗口关闭事件
		this.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent arg0) {
				disconnect();
				System.exit(0);
			}
			
		});
		
		//监听输入框的事件，用于发送数据到服务端
		tfTxt.addActionListener(new TFListener());
		setVisible(true);
		connect();
	}
	
	//启用客户端Sokcet的方法
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
	
	//关闭资源的方法
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
	
	//发送数据的事件类
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

	//接收数据的线程类
	private class Rece implements Runnable {		
		public void run() {
			try {
				while(receB) {
					String str = dis.readUTF();
					taContent.append(str + "\r\n");
				}
			} catch (SocketException e) {
				System.out.println("退出了");
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
