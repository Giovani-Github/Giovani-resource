import java.awt.*;
import java.awt.event.*;

public class TankClient extends Frame{

	public static void main(String[] args) {
		new TankClient().lauchFrame();
	}
	
	public void lauchFrame() {
		setBounds(400, 300, 800, 600);
		setResizable(false);//�������û����������С
		
		addWindowListener(new WindowAdapter() {//����������Ӵ��ڼ���
			
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
		});
		
		setVisible(true);
	}
}
