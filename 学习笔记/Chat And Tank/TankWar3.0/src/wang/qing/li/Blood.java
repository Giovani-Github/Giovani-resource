package wang.qing.li;
import java.awt.*;

/**
 * 血块类
 * @author 李庆旺
 *
 */

public class Blood {
	//血块出现的位置和大小
	private int x, y, w, h;
	//大管家引用
	private TankClient tc;
	
	//出现的位置规律
	private int[][] pos = {{200, 200}, {300, 250}, {100, 500}, {250, 350}, {401, 360}};
	//出现到第几次
	private int step = 0;
	
	//是否活着
	private boolean live = true;
	
	/**
	 * 够着一个空参数的血块
	 */
	public Blood() {
		x = pos[0][0];
		y = pos[0][1];
		w = h = 15;
	}
	
	/**
	 * 画出血块
	 * @param g 大管家传递过来的画笔
	 */
	public void draw(Graphics g) {
		//如果坦克没活着就不画
		if(!live) return;
		
		Color c = g.getColor();
		g.setColor(Color.MAGENTA);
		g.fillRect(x, y, w, h);
		g.setColor(c);
	
		//每画一次移动一次
		move();
	}
	
	/**
	 * 血块的移动方法
	 */
	private void move() {
		//一直改变步数
		step ++;
		//如果移动到最后一步
		if(step == pos.length){
			//复原
			step = 0;
		}
		x = pos[step][0];
		y = pos[step][1];
	}
	
	/**
	 * 血块是否活着
	 * @return 活着返回true，否则返回false
	 */
	public boolean isLive() {
		return live;
	}

	/**
	 * 设置血块生死
	 * @param live 要设置的生死的变量
	 */
	public void setLive(boolean live) {
		this.live = live;
	}
	
	/**
	 * 返回血块所在区域的区域对象
	 * @return 返回区域对象
	 */
	public Rectangle getRect() {
		return new Rectangle(x, y, w, h);
	}

}
