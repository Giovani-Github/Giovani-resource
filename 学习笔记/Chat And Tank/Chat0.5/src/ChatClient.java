import java.awt.*;
import java.awt.event.*;

public class ChatClient extends Frame {

	TextField tfTxt = new TextField();
	TextArea taContent = new TextArea();

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
			public void actionPerformed(ActionEvent e) {
				String bus = tfTxt.getText();
				taContent.append(bus + "\r\n");
				tfTxt.setText("");
			}
				
		});
	}
}
