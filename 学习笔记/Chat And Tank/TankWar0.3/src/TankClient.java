import java.awt.*;
import java.awt.event.*;

public class TankClient extends Frame{


	//在窗口中画圆
	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.RED);
		g.fillOval(50, 50, 30, 30);
		g.setColor(c);
		

	}

	public static void main(String[] args) {
		new TankClient().lauchFrame();
	}
	
	public void lauchFrame() {
		setBounds(400, 300, 800, 600);
		setResizable(false);//不可由用户调整界面大小
		
		addWindowListener(new WindowAdapter() {//用匿名类添加窗口监听
			
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
		});
		
		//setBackground(Color.GREEN);
		setVisible(true);
	}
}
