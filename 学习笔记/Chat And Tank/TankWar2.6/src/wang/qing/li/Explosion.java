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
	
	//爆炸的直径
	int[] diameter = {4, 7, 14, 28, 32, 40, 25, 5, 2};
	//爆炸画到了第几步
	int pos = 0;
		
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
		//如果爆炸效果没有活着
		if(!live) {
			//直接从爆炸集合中去除
			tc.explosions.remove(this);
			return;
		}
		
		//如果爆炸效果画到最后一步
		if(pos == diameter.length) {
			//爆炸效果死亡
			live = false;
			//爆炸步数复位
			pos = 0;
			return;
		}
		
		Color c = g.getColor();
		g.setColor(Color.GRAY);
		g.fillOval(x, y, diameter[pos], diameter[pos]);
		g.setColor(c);
		
		//爆炸每画一次加一次，就是画了一次之后调整到画下一个的直径
		pos++;
	}
}
