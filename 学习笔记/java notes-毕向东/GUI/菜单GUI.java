import java.awt.*;
import java.awt.event.*;

public class Test
{
	private Frame f;
	private MenuBar mb;//�˵���
	private Menu m, subM;//�˵�
	private MenuItem mi, subMi;//�˵���
	
	
	Test()
	{
		init();
	}

	public void init()
	{
		f = new Frame("�ҵĴ���");
		f.setBounds(200, 150, 500, 600);
		f.setLayout(new FlowLayout());

		mb = new MenuBar();//�˵���

		m = new Menu("�ļ�");//�ļ��˵�
		
		mi = new MenuItem("�˳�");//�˳���

		subM = new Menu("�Ӳ˵�");

		subMi = new MenuItem("����Ŀ");

		f.setMenuBar(mb);//������Ӳ˵���
		mb.add(m);//�˵�������ļ��˵�
		m.add(subM);//�ļ��˵�����Ӳ˵�
		m.add(mi);//�ļ��˵�����˳���
		subM.add(subMi);//�Ӳ˵��������Ŀ

		myEvent();//���ü�����

		f.setVisible(true);//��ʾ����
	}

	public void myEvent()
	{
		//���ڼ�����
		f.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent we)
			{
				System.exit(0);
			}
		});

		//�˳��������
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