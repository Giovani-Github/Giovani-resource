import java.awt.Graphics;
import java.awt.Rectangle;

public class Wall {
	int x, y, w, h;//出现的位置和大小
	TankClient tc;
	
	public Wall(int x, int y, int w, int h, TankClient tc) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.tc = tc;
	}
	
	public void draw(Graphics g) {
		g.fillRect(x, y, w, h);
	}
	
	//拿到墙所在的位置
	public Rectangle getRect() {
		return new Rectangle(x, y, w, h);
	}
}

