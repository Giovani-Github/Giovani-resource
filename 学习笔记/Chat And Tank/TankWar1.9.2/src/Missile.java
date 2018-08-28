import java.awt.*;
import java.util.List;


public class Missile {	
	public static final int XSPEED = 10;//x的速度
	public static final int YSPEED = 10;//y的速度
	
	public static final int WIDTH = 10;//子弹的宽度
	public static final int HEIGHT = 10;//子弹的高度
	
	int x, y;//子弹出现的位置
	Dir dir;//代表八个方向
	
	private boolean good;//子弹的好坏属性
	private boolean live = true;//炮弹是否死亡，true活着
	
	private TankClient tc;
	
	public Missile(int x, int y, Dir dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
	}

	public Missile(int x, int y, boolean good, Dir dir ,TankClient tc) {
		this(x ,y, dir);
		this.good = good;
		this.tc = tc;
	}
	
	//画出子弹
	public void draw(Graphics g) {
		if(!live) {
			tc.missiles.remove(this);
			return;
		}
		
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
		case STOP:
			break;
		}
		
		//子弹是否出界
		if(x < 0 || y < 0 || x > TankClient.GAME_WIDTH || y > TankClient.GAME_HEIGHT) {
			//出界了子弹就死亡
			live = false;
		}
	}

	//炮弹是否存活
	public boolean isLive() {
		return live;
	}
	
	//拿到子弹所在的区域
	public Rectangle getRect() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}
	
	//子弹打中这个坦克
	public boolean hitTank(Tank t) {
		if(this.live && this.getRect().intersects(t.getRect()) && t.isLive() && this.good != t.isGood()) {
			t.setLive(false);
			this.live = false;
			Explosion e = new Explosion(x, y, tc);
			tc.explosions.add(e);
			return true;
		}
		return false;
	}
	
	//子弹是否打中这一排坦克中的一个
	public boolean hitTanks(List<Tank> tanks) {
		for(int i=0; i<tanks.size(); i++) {
			if(hitTank(tanks.get(i))) {
				return true;
			}
		}
		return false;
	}
	
}
