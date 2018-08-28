import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

public class ChatClient extends Frame {

	TextField tfTxt = new TextField();
	TextArea taContent = new TextArea();
	Socket s = null;
	DataOutputStream bw = null;

	public static void main(String[] args) {
		new ChatClient().launchFrame();
	}

	public void launchFrame() {
		setLocation(400, 300);
		setSize(300, 300);
		add(tfTxt, BorderLayout.SOUTH);
		add(taContent, BorderLayout.NORTH);
		pack();
		myEvent();
		setVisible(true);
		
		connect();
	}

	public void connect() {
		
		try {
			s = new Socket(InetAddress.getLocalHost(), 20142);
System.out.println("connected!");
			bw = new DataOutputStream(s.getOutputStream());
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void closeClinet() {
		try {
			bw.close();
			s.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private void myEvent()
	{
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent we)
			{
				System.exit(0);
				closeClinet();
			}
		});
		
		tfTxt.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae) {
				String bus = tfTxt.getText();
				taContent.append(bus + "\r\n");				
				tfTxt.setText("");
			
				try {					
					bw.writeUTF(bus);
					bw.flush();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
				
		});
	}
}
