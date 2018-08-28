import java.awt.*;
import java.awt.event.*;

public class TankClient extends Frame{

	int x = 50, y = 50;//Բ��λ�����꣬�ı�������ػ�������ʵ��Բ��������Ч��
	Image offScreenImage = null;//Ҫ�ڴ��ں���һ����ʾ��ͼƬ
	
	//�ڴ����л�Բ
	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.RED);
		g.fillOval(x, y, 30, 30);
		g.setColor(c);
		
		y += 5;
	}
	
	/*
	 * û��ʹ��˫����֮ǰ����������ػ���ƽ��̫�ߵĻ��������ͼ����˸������
	 * ����ԭ����Ϊ��Ƶ���ػ����ڴ��ڻ�û�ػ���ʱ���ֵ�����һ���ػ�������������˸
	 * ����������ڻ��Ƴ��´��ڵ�ǰһ�����ڴ��ں���һ���Ȼ��Ƴ�Ҫ��ʾ��ͼƬ��Ȼ����һ������ʾ�ڴ��ڣ�����ͼ����˸�����⣬�����˫���塣
	 * �μ����µ�update����
	 * */
	public void update(Graphics g) {
		if(offScreenImage == null) {
			offScreenImage = this.createImage(800, 600);//�����ͼƬ�������
		}
		Graphics gOffScreen = offScreenImage.getGraphics();//�õ�����ͼƬ�Ļ���
		Color c = gOffScreen.getColor();//�õ�������ɫ
		gOffScreen.setColor(Color.GREEN);//���û�����ɫΪ��ɫ
		gOffScreen.fillRect(0, 0, 800, 600);//������ͼƬ�����һ�ž���ͼ��
		gOffScreen.setColor(c);//�ָ�������ɫ
		paint(gOffScreen);//����ֻ����ʹ��paint�еĲ������Ƶ�offScreenImageͼƬ�У���Ϊ��ֻ������offScreenImage��
		g.drawImage(offScreenImage, 0, 0, null);//��paint�Ļ��ʣ�Ҳ���Ǵ��ڵĻ��ʻ�������ͼƬoffScreenImage����Ϊ��ֻ�����Ǵ��ڵģ����Ի���Ƶ�������
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
					Thread.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
