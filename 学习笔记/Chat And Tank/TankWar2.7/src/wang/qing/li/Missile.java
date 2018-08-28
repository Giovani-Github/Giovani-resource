package wang.qing.li;
import java.awt.*;
import java.util.List;

/**
 * �ӵ���
 * @author ������
 *
 */

public class Missile {	
	/**
	 * �ӵ��ĺ�����
	 */
	public static final int XSPEED = 10;
	/**
	 * �ӵ���������
	 */
	public static final int YSPEED = 10;
	/**
	 * �ӵ��Ŀ��
	 */
	public static final int WIDTH = 10;
	/**
	 * �ӵ��ĸ߶�
	 */
	public static final int HEIGHT = 10;
	
	int x, y;//�ӵ����ֵ�λ��
	Direction dir;//�����ӵ��İ˸�����
	
	private boolean good;//�ӵ��ĺû�����
	private boolean live = true;//�ڵ��Ƿ�������true����
	
	//���д�ܼ�����
	private TankClient tc;
	
	/**
	 * ��������x��y�� �ͷ�����һ���ӵ�
	 * @param x �ӵ��ĺ�����
	 * @param y �ӵ���������
	 * @param dir ����
	 */
	public Missile(int x, int y, Direction dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
	
	/**
	 * ��������x�� y���û������򣬺ʹ�ܼ����ù���һ���ӵ�
	 * @param x ������
	 * @param y ������
	 * @param good �û�ֵ
	 * @param dir ����
	 * @param tc ��ܼ�����
	 */
	public Missile(int x, int y, boolean good, Direction dir ,TankClient tc) {
		this(x ,y, dir);
		this.good = good;
		this.tc = tc;
	}
	
	/**
	 * �����ӵ��ķ���
	 * @param g ��ܼҴ��ݹ����Ļ���
	 */
	public void draw(Graphics g) {
		//����ӵ�û����
		if(!live) {
			//ֱ�Ӵ�װ�ӵ��ļ�����ȥ��
			tc.missiles.remove(this);
			return;
		}
		
		Color c = g.getColor();
		if(good) 
			g.setColor(Color.RED);
		else 
			g.setColor(Color.BLACK);
		g.fillOval(x, y, WIDTH, HEIGHT);
		g.setColor(c);	
		
		move();
	}

	/**
	 * ����dir�����ƶ��ӵ�λ�ã� ͨ���ı�x��y����ʵ���ƶ�
	 */
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
		
		//�ӵ��Ƿ����
		if(x < 0 || y < 0 || x > TankClient.GAME_WIDTH || y > TankClient.GAME_HEIGHT) {
			//�������ӵ�������
			live = false;
		}
	}

	/**
	 * �ڵ��Ƿ���
	 * @return ���ŷ���true�����˷���false
	 */
	public boolean isLive() {
		return live;
	}
	
	/**
	 * �õ��ӵ�����������������
	 * @return �������
	 */
	public Rectangle getRect() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}
	
	/**
	 * �ӵ��Ƿ�̹�˺����̹����ײ
	 * @param t Ҫ��ײ��̹��
	 * @return ײ�Ϸ���true�� ûײ�Ϸ���false
	 */
	public boolean hitTank(Tank t) {
		//�ӵ��������,Ҫ��ײ��̹�˻��ţ� ��������ײ�ϣ������ӵ��ĺû���̹�˵ĺû�������ͬ
		if(this.live && t.isLive() && this.getRect().intersects(t.getRect()) && t.isLive() && this.good != t.isGood()) {
			//������û�̹��
			if(t.isGood()) {
				//��ײһ�Σ�����ֵ��20
				t.setLife(t.getLife()-20);
				//����ֵС��0���û�̹������
				if(t.getLife() <= 0) t.setLive(false);
			} else {
				//����ǵ���̹�ˣ�ֱ������
				t.setLive(false);
			}
			//�ӵ�����
			this.live = false;
			//����һ����ը����
			Explosion e = new Explosion(x, y, tc);
			//��ӵ���ը�����еȴ�����
			tc.explosions.add(e);
			return true;
		}
		return false;
	}
	
	/**
	 * �ӵ��Ƿ������һ�����е�һ��̹��
	 * @param tanks ̹�˼���
	 * @return ���з���true�����򷵻�false
	 */
	public boolean hitTanks(List<Tank> tanks) {
		//ȡ��̹��
		for(int i=0; i<tanks.size(); i++) {
			//���������ײ���
			if(hitTank(tanks.get(i))) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * �ӵ��Ƿ�ײ��ǽ
	 * @param w ǽ����
	 * @return ײ�Ϸ���true�����򷵻�fale
	 */
	public boolean hitWall(Wall w) {
		//�ӵ�����Ҫ���ţ�����������ײ
		if(this.live && this.getRect().intersects(w.getRect())) {
			live = false;//�ӵ�����
			return true;
		}
		return false;
	}
	
}
