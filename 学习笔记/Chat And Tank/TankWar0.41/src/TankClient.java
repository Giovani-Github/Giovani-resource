import java.awt.*;
import java.awt.event.*;

public class TankClient extends Frame{

	int x = 50, y = 50;//圆的位置坐标，改变坐标后重画窗口来实现圆动起来的效果
	Image offScreenImage = null;//要在窗口后面一层显示的图片
	
	//在窗口中画圆
	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.RED);
		g.fillOval(x, y, 30, 30);
		g.setColor(c);
		
		y += 5;
	}
	
	/*
	 * 没有使用双缓冲之前，如果窗口重画的平率太高的话，会出现图形闪烁的问题
	 * 问题原因：因为高频率重画，在窗口还没重画完时，又调用了一次重画方法，导致闪烁
	 * 解决方案：在绘制出新窗口的前一步，在窗口后面一层先绘制出要显示的图片，然后在一次性显示在窗口，减少图形闪烁的问题，这就是双缓冲。
	 * 参见以下的update方法
	 * */
	public void update(Graphics g) {
		if(offScreenImage == null) {
			offScreenImage = this.createImage(800, 600);//把这个图片定义出来
		}
		Graphics gOffScreen = offScreenImage.getGraphics();//拿到这张图片的画笔
		Color c = gOffScreen.getColor();//拿到画笔颜色
		gOffScreen.setColor(Color.GREEN);//设置画笔颜色为绿色
		gOffScreen.fillRect(0, 0, 800, 600);//在这张图片中填充一张矩形图形
		gOffScreen.setColor(c);//恢复画笔颜色
		paint(gOffScreen);//让这只画笔使用paint中的参数绘制到offScreenImage图片中，因为这只画笔是offScreenImage的
		g.drawImage(offScreenImage, 0, 0, null);//用paint的画笔，也就是窗口的画笔绘制这张图片offScreenImage，因为这只画笔是窗口的，所以会绘制到窗口上
	}

	public static void main(String[] args) {
		new TankClient().lauchFrame();
	}
	
	public void lauchFrame() {
		setBounds(400, 300, 800, 600);
		setResizable(false);//不可由用户调整界面大小
		
		addWindowListener(new WindowAdapter() {//用匿名类添加窗口监听
			
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
		});
		
		//setBackground(Color.GREEN);
		setVisible(true);
		
		//让窗口每隔一段时间重画
		new Thread(new PaintThread()).start();
	}
	
	//重画窗口的线程
	private class PaintThread implements Runnable {
		
		public void run() {
			while(true) {
				repaint();
				
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
