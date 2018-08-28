import java.awt.Color;
import java.awt.Graphics;

public class Missile {
	
	int x, y;//子弹出现的位置
	Tank.Direction dir;//代表八个方向
	
	public static final int XSPEED = 10;//x的速度
	public static final int YSPEED = 10;//y的速度
	
	public Missile(int x, int y, Tank.Direction dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
	}

	//画出子弹
	public void draw(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.BLACK);
		g.fillOval(x, y, 10, 10);
		g.setColor(c);	
		
		move();
	}

	//根据dir移动子弹位置
	private void move() {
		switch(dir) {
		case L:
			x -= XSPEED;
			break;
		case LU:
			x -= XSPEED;
			y -= YSPEED;
			break;
		case U:
			y -= YSPEED;
			break;
		case RU:
			x += XSPEED;
			y -= YSPEED;
			break;
		case R:
			x += XSPEED;
			break;
		case RD:
			x += XSPEED;
			y += YSPEED;
			break;
		case D:
			y += YSPEED;
			break;
		case LD:
			x -= XSPEED;
			y += YSPEED;
			break;
		}
	}
}
