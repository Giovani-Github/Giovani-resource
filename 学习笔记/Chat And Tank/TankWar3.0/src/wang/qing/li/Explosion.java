package wang.qing.li;
import java.awt.*;

/**
 * 爆炸效果类
 * @author 李庆旺
 *
 */
public class Explosion {
	int x, y;//爆炸出现的位置
	private boolean live = true;//爆炸效果的存活状态
	
	//持有大管家引用
	private TankClient tc;
	
	//操作系统中数据的工具类
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	
	//图片数组
	private static Image[] imgs = {
		/**
		 * 1.拿到一张URL地址形式的图片
		 * 2.Explosion.class是一个Explosion的类对象
		 * 3.用这个类对象拿到这个类的类装载器
		 * 4.用这个装载器拿到这张图片，返回一个URL
		 */
		tk.getImage(Explosion.class.getClassLoader().getResource("imgs/0.gif")),
		tk.getImage(Explosion.class.getClassLoader().getResource("imgs/1.gif")),
		tk.getImage(Explosion.class.getClassLoader().getResource("imgs/2.gif")),
		tk.getImage(Explosion.class.getClassLoader().getResource("imgs/3.gif")),
		tk.getImage(Explosion.class.getClassLoader().getResource("imgs/4.gif")),
		tk.getImage(Explosion.class.getClassLoader().getResource("imgs/5.gif")),
		tk.getImage(Explosion.class.getClassLoader().getResource("imgs/6.gif")),
		tk.getImage(Explosion.class.getClassLoader().getResource("imgs/7.gif")),
		tk.getImage(Explosion.class.getClassLoader().getResource("imgs/8.gif")),
		tk.getImage(Explosion.class.getClassLoader().getResource("imgs/9.gif")),
		tk.getImage(Explosion.class.getClassLoader().getResource("imgs/10.gif")),

	};
	
	//爆炸画到了第几步
	int pos = 0;
	
	private static boolean init = false;//爆炸图片是否初始化了
		
	/**
	 * 根据坐标x，y和大管家引用构造一个爆炸效果类
	 * @param x 爆炸效果出现的横坐标
	 * @param y 爆炸效果出现的纵坐标
	 * @param tc 大管家引用
	 */
	public Explosion(int x, int y, TankClient tc) {
		this.x = x;
		this.y = y;
		this.tc = tc;
	}
	
	/**
	 * 画出爆炸效果
	 * @param g 大管家传递过来的引用
	 */
	public void draw(Graphics g) {
		//如果爆炸没有初始化，就先初始化
		if(!init) {
			for (int j = 0; j < imgs.length; j++) {
				g.drawImage(imgs[j], -100, -100, null);
			}
			init = true;
		}
		
		//如果爆炸效果没有活着
		if(!live) {
			//直接从爆炸集合中去除
			tc.explosions.remove(this);
			return;
		}
		
		//如果爆炸效果画到最后一步
		if(pos == imgs.length) {
			//爆炸效果死亡
			live = false;
			//爆炸步数复位
			pos = 0;
			return;
		}
		
		//画出这种爆炸图片
		g.drawImage(imgs[pos], x, y, null);
		
		//爆炸每画一次加一次，就是画了一次之后调整到画下一个的直径
		pos++;
	}
}
