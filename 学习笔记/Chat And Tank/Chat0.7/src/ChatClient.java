import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

public class ChatClient extends Frame {

	TextField tfTxt = new TextField();
	TextArea taContent = new TextArea();
	Socket s = null;

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
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
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
			}
		});
		
		tfTxt.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae) {
				String bus = tfTxt.getText();
				taContent.append(bus + "\r\n");				
				tfTxt.setText("");
			
				try {
					BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
					
					bw.write(bus);
					bw.newLine();
					bw.flush();
					bw.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
				
		});
	}
}
