package wang.qing.li;
import java.awt.*;

/**
 * ��ըЧ����
 * @author ������
 *
 */
public class Explosion {
	int x, y;//��ը���ֵ�λ��
	private boolean live = true;//��ըЧ���Ĵ��״̬
	
	//���д�ܼ�����
	private TankClient tc;
	
	//����ϵͳ�����ݵĹ�����
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	
	//ͼƬ����
	private static Image[] imgs = {
		/**
		 * 1.�õ�һ��URL��ַ��ʽ��ͼƬ
		 * 2.Explosion.class��һ��Explosion�������
		 * 3.�����������õ���������װ����
		 * 4.�����װ�����õ�����ͼƬ������һ��URL
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
	
	//��ը�����˵ڼ���
	int pos = 0;
	
	private static boolean init = false;//��ըͼƬ�Ƿ��ʼ����
		
	/**
	 * ��������x��y�ʹ�ܼ����ù���һ����ըЧ����
	 * @param x ��ըЧ�����ֵĺ�����
	 * @param y ��ըЧ�����ֵ�������
	 * @param tc ��ܼ�����
	 */
	public Explosion(int x, int y, TankClient tc) {
		this.x = x;
		this.y = y;
		this.tc = tc;
	}
	
	/**
	 * ������ըЧ��
	 * @param g ��ܼҴ��ݹ���������
	 */
	public void draw(Graphics g) {
		//�����ըû�г�ʼ�������ȳ�ʼ��
		if(!init) {
			for (int j = 0; j < imgs.length; j++) {
				g.drawImage(imgs[j], -100, -100, null);
			}
			init = true;
		}
		
		//�����ըЧ��û�л���
		if(!live) {
			//ֱ�Ӵӱ�ը������ȥ��
			tc.explosions.remove(this);
			return;
		}
		
		//�����ըЧ���������һ��
		if(pos == imgs.length) {
			//��ըЧ������
			live = false;
			//��ը������λ
			pos = 0;
			return;
		}
		
		//�������ֱ�ըͼƬ
		g.drawImage(imgs[pos], x, y, null);
		
		//��ըÿ��һ�μ�һ�Σ����ǻ���һ��֮�����������һ����ֱ��
		pos++;
	}
}
