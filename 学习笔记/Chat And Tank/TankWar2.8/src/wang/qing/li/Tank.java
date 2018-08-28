package wang.qing.li;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * 坦克的类
 * @author 李庆旺
 *
 */

public class Tank {
	/**
	 * 坦克的出现的横坐标
	 */
	public static final int XSPEED = 5;
	/**
	 * 坦克出现的纵坐标
	 */
	public static final int YSPEED = 5;
	/**
	 * 坦克的宽度
	 */
	public static final int WIDTH = 30;
	/**
	 * 坦克的高度
	 */
	public static final int HEIGHT = 30;//坦克的高度
	
	private boolean live = true;//控制坦克生死的量
	
	//持有大管家的引用
	TankClient tc;
	
	private boolean good;//区分坦克是敌是友的量
		
	private int x,y;//坦克的位置坐标，改变坐标后重画窗口来实现圆动起来的效果
	
	private static Random r = new Random();//随机数产生器
		
	private boolean bL=false, bU=false, bR=false, bD = false;//记录四个方向键的状态
	
	private Direction dir = Direction.STOP;//代表向哪里移动的方向，刚开始不移动
	private Direction ptDir = Direction.D;//炮筒方向，刚开始朝下
	
	private int step = r.nextInt(12) + 3;//敌人坦克最少随机移动三步
	
	private int oldX, oldY;//坦克上一步出现的位置
			
	private int life = 100;//生命值
	
	private BloodBar bb = new BloodBar();//生命条
	
	//操作系统数据的工具类
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	
	//图片数组
	private static Image[] tankImgs = null;
	
	//键值对集合，用于存储坦克方向图片索引
	private static Map<String, Image> imgs = new HashMap<String, Image>();
	
	//静态代码块，最先执行
	static {
		tankImgs = new Image[] {
				tk.getImage(Tank.class.getClassLoader().getResource("imgs/tankD.gif")),
				tk.getImage(Tank.class.getClassLoader().getResource("imgs/tankL.gif")),
				tk.getImage(Tank.class.getClassLoader().getResource("imgs/tankLD.gif")),
				tk.getImage(Tank.class.getClassLoader().getResource("imgs/tankLU.gif")),
				tk.getImage(Tank.class.getClassLoader().getResource("imgs/tankR.gif")),
				tk.getImage(Tank.class.getClassLoader().getResource("imgs/tankRD.gif")),
				tk.getImage(Tank.class.getClassLoader().getResource("imgs/tankRU.gif")),
				tk.getImage(Tank.class.getClassLoader().getResource("imgs/tankU.gif")),
			
		};
		
		imgs.put("D", tankImgs[0]);
		imgs.put("L", tankImgs[1]);
		imgs.put("LD", tankImgs[2]);
		imgs.put("LU", tankImgs[3]);
		imgs.put("R", tankImgs[4]);
		imgs.put("RD", tankImgs[5]);
		imgs.put("RU", tankImgs[6]);
		imgs.put("U", tankImgs[7]);
	}
	
	/**
	 * 构造一个含有坐标和好坏的坦克
	 * @param x 坦克的横坐标
	 * @param y 坦克的纵坐标
	 * @param good 坦克的好坏
	 */
	public Tank(int x, int y, boolean good) {
		this.x = x;
		this.y = y;
		this.oldX = x;
		this.oldY = y;
		this.good =good;
	}
	
	/**
	 * 构造一个含有坐标，好坏，移动方向，和持有大管家引用的坦克
	 * @param x 坦克的横坐标
	 * @param y 坦克的纵坐标
	 * @param good 坦克的好坏
	 * @param dir 坦克移动的方向
	 * @param tc 大管家引用
	 */
	public Tank(int x, int y, boolean good,  Direction dir, TankClient tc) {
		this(x, y,good);
		this.dir = dir;
		this.tc = tc;
		
	}
	
	/**
	 * 画坦克的方法
	 * @param g 大管家传递过来的画笔
	 */
	public void draw(Graphics g) {
		//如果坦克没活着
		if(!live) {
			//如果是敌人坦克
			if(!good) {
				tc.tanks.remove(this);//将它从敌人坦克集合中去除
			}			
			return;
		}
		
		//画出生命条
		if(good)
			bb.draw(g);
		
		//根据炮筒方向画出炮筒
		switch(ptDir) {
		case L:
			g.drawImage(imgs.get("L"), x, y, null);
			break;
		case LU:
			g.drawImage(imgs.get("LU"), x, y, null);
			break;
		case U:
			g.drawImage(imgs.get("U"), x, y, null);
			break;
		case RU:
			g.drawImage(imgs.get("RU"), x, y, null);
			break;
		case R:
			g.drawImage(imgs.get("R"), x, y, null);
			break;
		case RD:
			g.drawImage(imgs.get("RD"), x, y, null);
			break;
		case D:
			g.drawImage(imgs.get("D"), x, y, null);
			break;
		case LD:
			g.drawImage(imgs.get("LD"), x, y, null);
			break;
		}
		
		//每画一次坦克移动一次
		move();
	}
	
	/**
	 * 移动方法， 根据移动方向dir改变x，y的坐标值移动
	 */
	void move() {
		//移动之前记录上一次移动过后的位置
		this.oldX = x;
		this.oldY = y;
		
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
		
		//如果坦克移动方向没有停止，就把坦克移动方向与炮筒方向关联
		if(this.dir != Direction.STOP) {
			this.ptDir = this.dir;
		}
		
		//不让坦克我出界
		if(x<0) x = 0;
		if(y<30) y = 30;
		if(x+Tank.WIDTH > TankClient.GAME_WIDTH) x = TankClient.GAME_WIDTH - Tank.WIDTH;
		if(y+Tank.HEIGHT > TankClient.GAME_HEIGHT) y = TankClient.GAME_HEIGHT - Tank.HEIGHT;
	
		//如果是敌人坦克， 让它随机移动
		if(!good) {
			Direction[] dirs = Direction.values();//让方向变成数组
			
			if(step == 0) {//只要固定一个方向的步数走完才换方向
				step = r.nextInt(12) + 3;
				int rn = r.nextInt(dirs.length);//产生一个从0到dir长度的数组
				dir = dirs[rn];
			}			
			step --;
			
			//随机开炮
			if(r.nextInt(40) > 38) this.fire();
		}
	}
	
	/**
	 * 四个方向键按下时的状态
	 * @param e 键盘事件对象
	 */
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch(key) {
		//如果按下F2， 让用户坦克满血活过来
		case KeyEvent.VK_F2 :
			if(!this.isLive()) {
				this.setLive(true);
				this.setLife(100);
			}
			break;
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
			
		//每按一次案件就根据方向键状态改变一次坦克方向
		locateDirection();
	}

	/**
	 * 根据方向键的是否被按下改变坦克移动方向dir
	 */
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

	/**
	 * 四个方向键释放时的状态
	 * @param e 键盘事件对象
	 */
	public void KeyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		switch(key) {
		//释放ctrl键就开炮
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
		case KeyEvent.VK_A :
			superFire();
			break;
		}
		
		//每按一次案件就根据方向键状态改变一次坦克方向
		locateDirection();
		
	}
	
	/**
	 * 开炮
	 * @return 返回一个子弹对象
	 */
	public Missile fire() {
		//如果坦克没活着就不开炮
		if(!live) return null;
		
		int x = this.x + Tank.WIDTH/2 - Missile.WIDTH/2;
		int y = this.y + Tank.HEIGHT/2 - Missile.HEIGHT/2;
		Missile m = new Missile(x, y, good, ptDir, this.tc);//根据炮筒的方向开火
		tc.missiles.add(m);//添加到子弹集合中等待画出
		return m;
	}
	
	/**
	 * 根据自定义方向开炮
	 * @param dir 自定义的方向
	 * @return 返回一个子弹对象
	 */
	public Missile fire(Direction dir) {
		if(!live) return null;
		int x = this.x + Tank.WIDTH/2 - Missile.WIDTH/2;
		int y = this.y + Tank.HEIGHT/2 - Missile.HEIGHT/2;
		Missile m = new Missile(x, y, good, dir, this.tc);//根据炮筒的方向开火
		tc.missiles.add(m);
		return m;
	}
	
	/**
	 * 超级炮弹,朝八个方向各打一发子弹
	 */
	private void superFire() {
		Direction[] dirs = Direction.values();
		for(int i=0; i<8; i++) {//不包括Stop方向
			fire(dirs[i]);
		}
	}
	
	/**
	 * 坦克是否撞上墙
	 * @param w 墙对象
	 * @return 撞上返回true，没撞上返回false
	 */
	public boolean hitWall(Wall w) {
		//自身坦克要活着， 并且两者相撞
		if(this.live && this.getRect().intersects(w.getRect())) {
			//撞上后让坦克回复上一步出现的位置
			this.stay();
			return true;
		}
		return false;
	}
	
	/**
	 * 是否和其他坦克相撞
	 * @param tanks 其他所有敌人坦克
	 * @return 撞上返回true，没撞上返回false
	 */
	public boolean hitTanks(java.util.List<Tank> tanks) {
		//取出其他所有敌人坦克
		for(int i=0; i<tanks.size(); i++) {
			Tank tank = tanks.get(i);
			//取出的敌人坦克不等于自身
			if(this != tank) {
				//坦克自身要活着，敌人坦克要活着，并且两者相撞
				if(this.live && tank.isLive() && this.getRect().intersects(tank.getRect())) {
					this.stay();
					tank.stay();
					return true;
				}
			}
		}		
		return false;
	}
	
	/**
	 * 和血块碰撞
	 * @param b 血块对象
	 * @return 撞上返回true，没撞上返回false
	 */
	public boolean hitBlood(Blood b) {
		//坦克本身要活着，血块要活着，并且两者相撞
		if(this.live && b.isLive() && this.getRect().intersects(b.getRect())) {
			this.setLife(100);//血量全部恢复
			b.setLive(false);//血块死亡
			return true;
		}
		return false;
	}
	
	/**
	 * 让坦克返回上一步的位置
	 */
	public void stay() {
		x = this.oldX;
		y = this.oldY;
	}
	
	/**
	 * 生命条类
	 * @author 李庆旺
	 *
	 */
	private class BloodBar {
		/**
		 * 画出生命条的方法
		 * @param g 大管家传来的画笔
		 */
		public void draw(Graphics g) {
			Color c = g.getColor();
			g.setColor(Color.RED);
			//外框空心的矩形
			g.drawRect(x, y-10, WIDTH, 10);
			//根据生命值画内条，实心的矩形
			int w = WIDTH * life/100 ;
			g.fillRect(x, y-10, w, 10);
			g.setColor(c);
		}
	}
	
	/**
	 * 拿到坦克所在的区域
	 * @return 坦克所在区域的区域对象
	 */
	public Rectangle getRect() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}
	
	/**
	 * 坦克是否活着
	 * @return 活着返回true，死了返回false
	 */
	public boolean isLive() {
		return live;
	}

	/**
	 * 设置坦克生死
	 * @param live 坦克生死的变量， 死了返回false， 活着返回true
	 */
	public void setLive(boolean live) {
		this.live = live;
	}
	
	/**
	 * 坦克是敌方还是我方
	 * @return 返回是敌是我的变量， 敌方返回false， 我方返回true
	 */
	public boolean isGood() {
		return good;
	}
	
	/**
	 * 返回生命值的方法
	 * @return 生命值
	 */
	public int getLife() {
		return life;
	}

	/**
	 * 设置生命值的方法
	 * @param life 要设置的生命值
	 */
	public void setLife(int life) {
		this.life = life;
	}
}
