package wang.qing.li;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Properties;


/**
 * 坦克大战的主要类
 * @author 李庆旺
 *
 */

public class TankClient extends Frame{

	/**
	 * 坦克大战窗口的宽度
	 */
	public static final int GAME_WIDTH = 800;
	/**
	 * 坦克大战窗口的高度
	 */
	public static final int GAME_HEIGHT = 600;//窗口高度
	
	Tank myTank = new Tank(50, 50, true, Direction.STOP, this);//创建一辆坦克
	
	List<Explosion> explosions = new ArrayList<Explosion>();//存储每辆坦克的爆炸效果
	List<Missile> missiles = new ArrayList<Missile>();//装炮弹的数组
	List<Tank> tanks = new ArrayList<Tank>();//添加多辆敌人坦克
	Image offScreenImage = null;//要在窗口后面一层显示的图片
	
	Wall w1 = new Wall(100, 200, 20, 150, this), w2 = new Wall(300, 500, 20, 200, this); //两堵墙
	
	private Blood b = new Blood();//补血血块
	
	/**
	 * 画出各种元素，并进行碰撞检测。坦克，子弹等
	 */
	public void paint(Graphics g) {
		g.drawString("missiles count:" + missiles.size(), 10, 40);//显示子弹数量
		g.drawString("explosions count:" + explosions.size(), 10, 55);//显示爆炸数量
		g.drawString("tank count:" + tanks.size(), 10, 65);//显示坦克数量
		g.drawString("tank life:" + myTank.getLife(), 10, 75);//显示用户坦克血量
	
		/*
		 * 如果敌人坦克打完了，添加新的一批敌人坦克
		 */
		if(tanks.size() <= 0) {
			for(int i=0; i<Integer.parseInt(PropertiesMgr.getProperty("reTankCount")); i++) {
				tanks.add(new Tank(50+40*(i+1), 50, false,  Direction.D, this));
			}
		}
		
		/*
		 * 画出子弹，并进行子弹的碰撞检测
		 */
		for(int i=0; i<missiles.size(); i++) {
			Missile missile = missiles.get(i);//拿到一颗子弹
			missile.hitTanks(tanks);//子弹是否打中其中一个坦克
			missile.hitTank(myTank);//是否有子弹打中自己
			missile.hitWall(w1);//子弹是否与墙相撞
			missile.hitWall(w2);//子弹是否与墙相撞
			missile.draw(g);//画出子弹
			/*if(!(missile.isLive()))
				missiles.remove(missile);
			else
				missile.draw(g);
				*/
		}
		
		/*
		 * 画出爆炸效果
		 */
		for(int i=0; i<explosions.size(); i++) {
			Explosion explosion = explosions.get(i);
			explosion.draw(g);
		}
		
		/*
		 * 把敌人坦克画出来。并进行碰撞检测
		 */
		for(int i=0; i<tanks.size(); i++) {
			Tank tank = tanks.get(i);//拿到敌人坦克
			tank.draw(g);//画出敌人坦克
			//让敌人坦克不能穿墙，进行敌人坦克与墙相撞的检测
			tank.hitWall(w1);
			tank.hitWall(w2);
			//敌人坦克与敌人坦克相撞的检测
			tank.hitTanks(tanks);
			//用户坦克与敌人坦克相撞的检测
			myTank.hitTanks(tanks);
		}
		
		/*
		 * 画出用户坦克
		 */
		myTank.draw(g);
		/*
		 * 用户坦克的撞墙检测
		 */
		myTank.hitBlood(b);
		/*
		 * 画出墙
		 */
		w1.draw(g);
		w2.draw(g);
		/*
		 * 画出补血块
		 */
		b.draw(g);
	}
	
	/**
	 * 没有使用双缓冲之前，如果窗口重画的平率太高的话，会出现图形闪烁的问题
	 * 问题原因：因为高频率重画，在窗口还没重画完时，又调用了一次重画方法，导致闪烁
	 * 解决方案：在绘制出新窗口的前一步，在窗口后面一层先绘制出要显示的图片，然后在一次性显示在窗口，减少图形闪烁的问题，这就是双缓冲。
	 * 参见以下的update方法
	 */
	public void update(Graphics g) {
		if(offScreenImage == null) {
			offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);//把这个图片定义出来
		}
		Graphics gOffScreen = offScreenImage.getGraphics();//拿到这张图片的画笔
		Color c = gOffScreen.getColor();//拿到画笔颜色
		gOffScreen.setColor(Color.GRAY);//设置画笔颜色为黑色
		gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);//在这张图片中填充一张矩形图形
		gOffScreen.setColor(c);//恢复画笔颜色
		paint(gOffScreen);//让这只画笔使用paint中的参数绘制到offScreenImage图片中，因为这只画笔是offScreenImage的
		g.drawImage(offScreenImage, 0, 0, null);//用paint的画笔，也就是窗口的画笔绘制这张图片offScreenImage，因为这只画笔是窗口的，所以会绘制到窗口上
	}
	
	/**
	 * 显示坦克大战窗口的方法
	 */
	public void lauchFrame() {
		
		int initTankCount = Integer.parseInt(PropertiesMgr.getProperty("initTankCount"));
		
		//添加十辆敌人坦克
		for(int i=0; i<initTankCount; i++) {
			tanks.add(new Tank(50+40*(i+1), 50, false,  Direction.D, this));
		}
		
		setBounds(400, 300, GAME_WIDTH, GAME_HEIGHT);
		setResizable(false);//不可由用户调整界面大小
		
		addWindowListener(new WindowAdapter() {//用匿名类添加窗口监听
			
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
		});
		
		//启用键盘监听
		addKeyListener(new KeyTank());
		
		setVisible(true);
		
		//让窗口每隔一段时间重画
		new Thread(new PaintThread()).start();
	}
	
	/**
	 * 重画窗口的线程类，，每隔30毫秒重画一会坦克大战窗口
	 * @author 李庆旺
	 *
	 */
	private class PaintThread implements Runnable {
		
		public void run() {
			while(true) {
				repaint();//自动调用update方法
				try {
					Thread.sleep(30);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	/**
	 * 监听键盘的类，控制坦克移动
	 * @author 李庆旺
	 *
	 */
	private class KeyTank extends KeyAdapter {

		/**
		 * 键盘释放的监听
		 */
		public void keyReleased(KeyEvent e) {
			myTank.KeyReleased(e);
		}

		/**
		 * 键盘按下的监听
		 */
		public void keyPressed(KeyEvent e) {
			myTank.keyPressed(e);
		}
		
	
	}
	
	public static void main(String[] args) {
		new TankClient().lauchFrame();
	}
}
