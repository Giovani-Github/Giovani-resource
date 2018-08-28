import java.awt.*;
import java.awt.event.*;

public class TankClient extends Frame{

	int x = 50, y = 50;//圆的位置坐标，改变坐标后重画窗口来实现圆动起来的效果
	
	//在窗口中画圆
	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.RED);
		g.fillOval(x, y, 30, 30);
		g.setColor(c);
		
		y += 5;
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
		
		//让窗口每隔一段时间重画
		new Thread(new PaintThread()).start();
	}
	
	//重画窗口的线程
	private class PaintThread implements Runnable {
		
		public void run() {
			while(true) {
				repaint();
				
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
