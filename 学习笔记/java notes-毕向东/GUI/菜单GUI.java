import java.awt.*;
import java.awt.event.*;

public class Test
{
	private Frame f;
	private MenuBar mb;//菜单栏
	private Menu m, subM;//菜单
	private MenuItem mi, subMi;//菜单项
	
	
	Test()
	{
		init();
	}

	public void init()
	{
		f = new Frame("我的窗口");
		f.setBounds(200, 150, 500, 600);
		f.setLayout(new FlowLayout());

		mb = new MenuBar();//菜单栏

		m = new Menu("文件");//文件菜单
		
		mi = new MenuItem("退出");//退出项

		subM = new Menu("子菜单");

		subMi = new MenuItem("子项目");

		f.setMenuBar(mb);//窗口添加菜单栏
		mb.add(m);//菜单栏添加文件菜单
		m.add(subM);//文件菜单添加子菜单
		m.add(mi);//文件菜单添加退出项
		subM.add(subMi);//子菜单添加子项目

		myEvent();//调用监听器

		f.setVisible(true);//显示窗口
	}

	public void myEvent()
	{
		//窗口监听器
		f.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent we)
			{
				System.exit(0);
			}
		});

		//退出项监听器
		mi.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				System.exit(0);
			}
		});
	}

	public static void main(String[] args)
	{
		new Test();
	}
}