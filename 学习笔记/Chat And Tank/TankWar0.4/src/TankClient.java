import java.awt.*;
import java.awt.event.*;

public class TankClient extends Frame{

	int x = 50, y = 50;//Բ��λ�����꣬�ı�������ػ�������ʵ��Բ��������Ч��
	
	//�ڴ����л�Բ
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
		setResizable(false);//�������û����������С
		
		addWindowListener(new WindowAdapter() {//����������Ӵ��ڼ���
			
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
		});
		
		//setBackground(Color.GREEN);
		setVisible(true);
		
		//�ô���ÿ��һ��ʱ���ػ�
		new Thread(new PaintThread()).start();
	}
	
	//�ػ����ڵ��߳�
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
