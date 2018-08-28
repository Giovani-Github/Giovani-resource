import java.awt.*;
import java.awt.event.*;


public class Tank {
	int x,y;//̹�˵�λ�����꣬�ı�������ػ�������ʵ��Բ��������Ч��

	public Tank(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	//��̹�˵ķ���
	public void draw(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.RED);
		g.fillOval(x, y, 30, 30);
		g.setColor(c);
	}
	
	//����̹�˵ķ���
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
