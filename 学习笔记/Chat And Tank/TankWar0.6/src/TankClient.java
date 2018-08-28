import java.awt.*;
import java.awt.event.*;

public class TankClient extends Frame{

	public static final int GAME_WIDTH = 800;//���ڿ��
	public static final int GAME_HEIGHT = 600;//���ڸ߶�
	
	int x = 50, y = 50;//Բ��λ�����꣬�ı�������ػ�������ʵ��Բ��������Ч��
	Image offScreenImage = null;//Ҫ�ڴ��ں���һ����ʾ��ͼƬ
	
	//�ڴ����л�Բ
	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.RED);
		g.fillOval(x, y, 30, 30);
		g.setColor(c);
	}
	
	/*
	 * û��ʹ��˫����֮ǰ����������ػ���ƽ��̫�ߵĻ��������ͼ����˸������
	 * ����ԭ����Ϊ��Ƶ���ػ����ڴ��ڻ�û�ػ���ʱ���ֵ�����һ���ػ�������������˸
	 * ����������ڻ��Ƴ��´��ڵ�ǰһ�����ڴ��ں���һ���Ȼ��Ƴ�Ҫ��ʾ��ͼƬ��Ȼ����һ������ʾ�ڴ��ڣ�����ͼ����˸�����⣬�����˫���塣
	 * �μ����µ�update����
	 * */
	public void update(Graphics g) {
		if(offScreenImage == null) {
			offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);//�����ͼƬ�������
		}
		Graphics gOffScreen = offScreenImage.getGraphics();//�õ�����ͼƬ�Ļ���
		Color c = gOffScreen.getColor();//�õ�������ɫ
		gOffScreen.setColor(Color.GREEN);//���û�����ɫΪ��ɫ
		gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);//������ͼƬ�����һ�ž���ͼ��
		gOffScreen.setColor(c);//�ָ�������ɫ
		paint(gOffScreen);//����ֻ����ʹ��paint�еĲ������Ƶ�offScreenImageͼƬ�У���Ϊ��ֻ������offScreenImage��
		g.drawImage(offScreenImage, 0, 0, null);//��paint�Ļ��ʣ�Ҳ���Ǵ��ڵĻ��ʻ�������ͼƬoffScreenImage����Ϊ��ֻ�����Ǵ��ڵģ����Ի���Ƶ�������
	}
	
	//��ʾ����
	public void lauchFrame() {
		setBounds(400, 300, GAME_WIDTH, GAME_HEIGHT);
		setResizable(false);//�������û����������С
		
		addWindowListener(new WindowAdapter() {//����������Ӵ��ڼ���
			
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
		});
		
		//���ü��̼���
		addKeyListener(new KeyTank());
		
		setVisible(true);
		
		//�ô���ÿ��һ��ʱ���ػ�
		new Thread(new PaintThread()).start();
	}
	
	//�ػ����ڵ��߳�
	private class PaintThread implements Runnable {
		
		public void run() {
			while(true) {
				repaint();//�Զ�����update����
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	//�������̵��ࣨ����̹���ƶ���
	private class KeyTank extends KeyAdapter {

		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			switch(key) {
				//���ϼ�
				case KeyEvent.VK_UP:
					y -= 5;
					break;
				//���¼�
				case KeyEvent.VK_DOWN  :
					y += 5;
					break;
				//�����
				case KeyEvent.VK_LEFT:
					x -= 5;
					break;
				//���Ҽ�
				case KeyEvent.VK_RIGHT:
					x += 5;
					break;
			}
		}
		
	}
	
	public static void main(String[] args) {
		new TankClient().lauchFrame();
	}
}
