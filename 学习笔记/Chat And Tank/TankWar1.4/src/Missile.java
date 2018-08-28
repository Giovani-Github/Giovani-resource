import java.awt.Color;
import java.awt.Graphics;

public class Missile {

	int x, y;//子弹出现的位置
	Tank.Direction dir;//代表八个方向
	
	public static final int XSPEED = 10;//x的速度
	public static final int YSPEED = 10;//y的速度
	
	public static final int WIDTH = 10;//子弹的宽度
	public static final int HEIGHT = 10;//子弹的高度
	
	private boolean live = true;//炮弹是否死亡，true活着
	private TankClient tc;
	
	public Missile(int x, int y, Tank.Direction dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
	}

	public Missile(int x, int y, Tank.Direction dir ,TankClient tc) {
		this(x ,y, dir);
		this.tc = tc;
	}
	
	//画出子弹
	public void draw(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.BLACK);
		g.fillOval(x, y, WIDTH, HEIGHT);
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
		
		//子弹是否出界
		if(x < 0 || y < 0 || x > TankClient.GAME_WIDTH || y > TankClient.GAME_HEIGHT) {
			//出界了子弹就死亡
			live = false;
			tc.missiles.remove(this);
		}
	}

	//炮弹是否存活
	public boolean isLive() {
		return live;
	}
}
