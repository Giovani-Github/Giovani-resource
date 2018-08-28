import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Tank {	
	public static final int XSPEED = 5;//x���ٶ�
	public static final int YSPEED = 5;//y���ٶ�
	
	public static final int WIDTH = 30;//̹�˵Ŀ��
	public static final int HEIGHT = 30;//̹�˵ĸ߶�
	
	private boolean live = true;//����̹����������
	
	TankClient tc;
	
	private boolean good;//����̹���ǵ����ѵ���
		
	private int x,y;//̹�˵�λ�����꣬�ı�������ػ�������ʵ��Բ��������Ч��
	
	private static Random r = new Random();//�����������
		
	private boolean bL=false, bU=false, bR=false, bD = false;//��¼�ĸ��������״̬
	enum Direction {L, LU, U, RU, R, RD, D, LD, STOP};//����˸�����
	
	private Direction dir = Direction.STOP;//����ʵ�ʷ��򣬸տ�ʼ���ƶ�
	private Direction ptDir = Direction.D;//��Ͳ����
	
	private int step = r.nextInt(12) + 3;//�����ƶ�����	
		
	public Tank(int x, int y, boolean good) {
		this.x = x;
		this.y = y;
		this.good =good;
	}
	
	public Tank(int x, int y, boolean good,  Direction dir, TankClient tc) {
		this(x, y,good);
		this.dir = dir;
		this.tc = tc;
		
	}
	
	//��̹�˵ķ���
	public void draw(Graphics g) {
		//���̹��û����
		if(!live) {
			//���ʱ����̹��
			if(!good) {
				tc.tanks.remove(this);
			}			
			return;
		}
		
		Color c = g.getColor();
		
		//�ж��ǵ��˵�̹�˻����Լ���̹��
		if(good) g.setColor(Color.RED);
		else g.setColor(Color.BLUE);
		
		g.fillOval(x, y, WIDTH, HEIGHT);
		g.setColor(c);
		
		//������Ͳ���򻭳���Ͳ
		switch(ptDir) {
		case L:
			g.drawLine(x + Tank.WIDTH/2, y + Tank.HEIGHT/2, x, y + Tank.HEIGHT/2);
			break;
		case LU:
			g.drawLine(x + Tank.WIDTH/2, y + Tank.HEIGHT/2, x, y);
			break;
		case U:
			g.drawLine(x + Tank.WIDTH/2, y + Tank.HEIGHT/2, x + Tank.WIDTH/2, y);
			break;
		case RU:
			g.drawLine(x + Tank.WIDTH/2, y + Tank.HEIGHT/2, x + Tank.WIDTH, y);
			break;
		case R:
			g.drawLine(x + Tank.WIDTH/2, y + Tank.HEIGHT/2, x + Tank.WIDTH, y + Tank.HEIGHT/2);
			break;
		case RD:
			g.drawLine(x + Tank.WIDTH/2, y + Tank.HEIGHT/2, x + Tank.WIDTH, y + Tank.HEIGHT);
			break;
		case D:
			g.drawLine(x + Tank.WIDTH/2, y + Tank.HEIGHT/2, x + Tank.WIDTH/2, y + Tank.HEIGHT);
			break;
		case LD:
			g.drawLine(x + Tank.WIDTH/2, y + Tank.HEIGHT/2, x, y + Tank.HEIGHT);
			break;
		}
		
		move();
	}
	
	//�ƶ�������dir�������ƶ�
	void move() {
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
		
		if(this.dir != Direction.STOP) {
			this.ptDir = this.dir;
		}
		
		//����̹���ҳ���
		if(x<0) x = 0;
		if(y<30) y = 30;
		if(x+Tank.WIDTH > TankClient.GAME_WIDTH) x = TankClient.GAME_WIDTH - Tank.WIDTH;
		if(y+Tank.HEIGHT > TankClient.GAME_HEIGHT) y = TankClient.GAME_HEIGHT - Tank.HEIGHT;
	
		if(!good) {
			Direction[] dirs = Direction.values();//�÷���������
			if(step == 0) {//ֻҪ�̶�һ������Ĳ�������Ż�����
				step = r.nextInt(12) + 3;
				int rn = r.nextInt(dirs.length);//����һ����0��dir���ȵ�����
				dir = dirs[rn];
			}			
			step --;
			
			if(r.nextInt(40) > 38) this.fire();
		}
	}
	
	//�ĸ����������ʱ��״̬
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch(key) {
		case KeyEvent.VK_LEFT :
			bL = true;
			break;
		case KeyEvent.VK_UP :
			bU = true;
			break;
		case KeyEvent.VK_RIGHT :
			bR = true;
			break;
		case KeyEvent.VK_DOWN :
			bD = true;
			break;
		}
			
		locateDirection();
	}
	
	

	

	//���ݷ������״̬�ı�dir����
	void locateDirection() {
		if(bL && !bU && !bR && !bD) dir = Direction.L;
		else if(bL && bU && !bR && !bD) dir = Direction.LU;
		else if(!bL && bU && !bR && !bD) dir = Direction.U;
		else if(!bL && bU && bR && !bD) dir = Direction.RU;
		else if(!bL && !bU && bR && !bD) dir = Direction.R;
		else if(!bL && !bU && bR && bD) dir = Direction.RD;
		else if(!bL && !bU && !bR && bD) dir = Direction.D;
		else if(bL && !bU && !bR && bD) dir = Direction.LD;
		else if(!bL && !bU && !bR && !bD) dir = Direction.STOP;
	}

	//�ĸ�������ͷ�ʱ��״̬
	public void KeyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		switch(key) {
		case KeyEvent.VK_CONTROL :
			fire();
			break;
		case KeyEvent.VK_LEFT :
			bL = false;
			break;
		case KeyEvent.VK_UP :
			bU = false;
			break;
		case KeyEvent.VK_RIGHT :
			bR = false;
			break;
		case KeyEvent.VK_DOWN :
			bD = false;
			break;
		}
		
		locateDirection();
		
	}
	
	//����
	public Missile fire() {
		if(!live) return null;
		int x = this.x + Tank.WIDTH/2 - Missile.WIDTH/2;
		int y = this.y + Tank.HEIGHT/2 - Missile.HEIGHT/2;
		Missile m = new Missile(x, y, good, ptDir, this.tc);//������Ͳ�ķ��򿪻�
		tc.missiles.add(m);
		return m;
	}
	
	//�õ�̹�����ڵ�����
	public Rectangle getRect() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}
	
	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}
	
	public boolean isGood() {
		return good;
	}
}
