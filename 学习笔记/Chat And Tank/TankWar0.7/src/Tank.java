import java.awt.*;
import java.awt.event.*;


public class Tank {
	int x,y;//坦克的位置坐标，改变坐标后重画窗口来实现圆动起来的效果

	public Tank(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	//画坦克的方法
	public void draw(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.RED);
		g.fillOval(x, y, 30, 30);
		g.setColor(c);
	}
	
	//控制坦克的方法
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch(key) {
			//按上键
			case KeyEvent.VK_UP:
				y -= 5;
				break;
			//按下键
			case KeyEvent.VK_DOWN  :
				y += 5;
				break;
			//按左键
			case KeyEvent.VK_LEFT:
				x -= 5;
				break;
			//按右键
			case KeyEvent.VK_RIGHT:
				x += 5;
				break;
		}
	}
}
