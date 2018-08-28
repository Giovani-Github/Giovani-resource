import java.awt.Button;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class TankClient extends Frame{

	public static final int GAME_WIDTH = 800;//窗口宽度
	public static final int GAME_HEIGHT = 600;//窗口高度
	
	Tank myTank = new Tank(50, 50, true, Dir.STOP, this);//创建一辆坦克
	
	List<Explosion> explosions = new ArrayList<Explosion>();//存储每辆坦克的爆炸效果
	List<Missile> missiles = new ArrayList<Missile>();//装炮弹的数组
	List<Tank> tanks = new ArrayList<Tank>();//存放除自身外，其他的坦克
	Image offScreenImage = null;//要在窗口后面一层显示的图片
	
	NetClient nc = new NetClient(this);
	
	ConnDialog dialog = new ConnDialog();
	
	//在窗口中画圆
	public void paint(Graphics g) {
		g.drawString("missiles count:" + missiles.size(), 10, 40);//显示炮弹数量
		g.drawString("explosions count:" + explosions.size(), 10, 55);
		g.drawString("tank count:" + tanks.size(), 10, 65);
		
		for(int i=0; i<missiles.size(); i++) {
			Missile missile = missiles.get(i);
//			missile.hitTanks(tanks);//子弹是否打中其中一个坦克
			
			if(missile.hitTank(myTank)) {
				TankDeadMsg msg = new TankDeadMsg(myTank.id);
				nc.send(msg);
			}
			
			missile.draw(g);
			/*if(!(missile.isLive()))
				missiles.remove(missile);
			else
				missile.draw(g);
				*/
		}
		
		//把爆炸画出来
		for(int i=0; i<explosions.size(); i++) {
			Explosion explosion = explosions.get(i);
			explosion.draw(g);
		}
		
		//把多辆坦克画出来
		for(int i=0; i<tanks.size(); i++) {
			Tank tank = tanks.get(i);
			tank.draw(g);
		}
		
		myTank.draw(g);
	}
	
	/*
	 * 没有使用双缓冲之前，如果窗口重画的平率太高的话，会出现图形闪烁的问题
	 * 问题原因：因为高频率重画，在窗口还没重画完时，又调用了一次重画方法，导致闪烁
	 * 解决方案：在绘制出新窗口的前一步，在窗口后面一层先绘制出要显示的图片，然后在一次性显示在窗口，减少图形闪烁的问题，这就是双缓冲。
	 * 参见以下的update方法
	 * */
	public void update(Graphics g) {
		if(offScreenImage == null) {
			offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);//把这个图片定义出来
		}
		Graphics gOffScreen = offScreenImage.getGraphics();//拿到这张图片的画笔
		Color c = gOffScreen.getColor();//拿到画笔颜色
		gOffScreen.setColor(Color.GREEN);//设置画笔颜色为绿色
		gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);//在这张图片中填充一张矩形图形
		gOffScreen.setColor(c);//恢复画笔颜色
		paint(gOffScreen);//让这只画笔使用paint中的参数绘制到offScreenImage图片中，因为这只画笔是offScreenImage的
		g.drawImage(offScreenImage, 0, 0, null);//用paint的画笔，也就是窗口的画笔绘制这张图片offScreenImage，因为这只画笔是窗口的，所以会绘制到窗口上
	}
	
	//显示窗口
	public void lauchFrame() {		
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
		
	/*	//连接上服务端
		try {
			nc.connect(InetAddress.getLocalHost().getHostAddress(), TankServer.TCP_PORT);
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}*/
	}
	
	//重画窗口的线程
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

	//监听键盘的类（控制坦克移动）
	private class KeyTank extends KeyAdapter {

		public void keyReleased(KeyEvent e) {
			myTank.KeyReleased(e);
		}

		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			if(key == KeyEvent.VK_C) {
				dialog.setVisible(true);
			} else {
				myTank.keyPressed(e);
		
			}
		}	
	}
	
	public static void main(String[] args) {
		new TankClient().lauchFrame();
	}

	/**
	 * 设置连接服务器端的ip和端口，还有客户端监听的udp端口的窗口
	 * @author Administrator
	 *
	 */
	class ConnDialog extends Dialog {
		TextField tfIP;
		TextField tfPort = new TextField(""+TankServer.TCP_PORT, 4);
		TextField tfMyUDPPort = new TextField("2223", 4);
		Button b = new Button("确定");
				
		public ConnDialog() {
			super(TankClient.this, true);
			
			this.setLayout(new FlowLayout());
			this.add(new Label("IP:"));
			try {
				this.add(tfIP =  new TextField(InetAddress.getLocalHost().getHostAddress(), 12));
			} catch (HeadlessException e1) {
				e1.printStackTrace();
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			}
			this.add(new Label("Port:"));
			this.add(tfPort);
			this.add(new Label("My UDP Port；"));
			this.add(tfMyUDPPort);
			this.add(b);
			this.setLocation(300, 300);
			this.pack();
			
			this.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent w) {
					setVisible(false);
				}
			});
			
			b.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String IP = tfIP.getText().trim();
					int port = Integer.parseInt(tfPort.getText().trim());
					int myUDPPort = Integer.parseInt(tfMyUDPPort.getText().trim());
					
					nc.setUdpPort(myUDPPort);
					nc.connect(IP, port);
					setVisible(false);
				}
			});
		}
	}
}
