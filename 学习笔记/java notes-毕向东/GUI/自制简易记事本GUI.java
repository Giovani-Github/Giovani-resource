import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Test
{
	private Frame f;
	private TextArea ta;//�༭��
	private MenuBar mb;//�˵���
	private Menu m, subM;//�˵�
	private MenuItem closeItem, openItem, saveItem;//�˵���
	
	private FileDialog openDia, saveDia;

	private File fle;//�������ļ�����

	Test()
	{
		init();
	}

	public void init()
	{
		f = new Frame("�ҵĴ���");
		f.setBounds(200, 150, 500, 600);

		ta = new TextArea();//�ı��༭��
		mb = new MenuBar();//�˵���
		m = new Menu("�ļ�");//�ļ��˵�	
		openItem = new MenuItem("��");//����
		saveItem = new MenuItem("����");//������
		closeItem = new MenuItem("�˳�");//�˳���

		openDia = new FileDialog(f, "�ҵĴ�", FileDialog.LOAD);//���ļ��Ի���
		saveDia = new FileDialog(f,"�ҵı���", FileDialog.SAVE);//�����ļ��Ի���

		f.add(ta);
		f.setMenuBar(mb);//������Ӳ˵���
		mb.add(m);//�˵�������ļ��˵�
		m.add(openItem);//�ļ��˵���Ӵ���
		m.add(saveItem);//�ļ��˵���ӱ�����
		m.add(closeItem);//�ļ��˵�����˳���

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

		//���ļ�������
		openItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				openDia.setVisible(true);
	
				String dir = openDia.getDirectory();
				String file = openDia.getFile();
				
				if(dir == null || file == null)
					return;
				ta.setText("");
				fle = new File(dir, file);

				BufferedReader br = null;
				try
				{
					br = new BufferedReader(new FileReader(fle));

					String line = null;
					while((line = br.readLine()) != null)
					{
						ta.append(line+"\r\n");
					}
				}
				catch(IOException ex)
				{
					throw new RuntimeException("��ʧ��");
				}
				finally
				{
					try
					{
						if(br != null)
							br.close();
					}
					catch(IOException ie)
					{
						System.out.println(ie.toString());
					}
				}
			}
		});

		//�����ļ�������
		saveItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if(fle == null)
				{
					saveDia.setVisible(true);

					String dir = saveDia.getDirectory();
					String file = saveDia.getFile();

					if(dir == null || file == null)
						return;
					fle = new File(dir, file);
				}
				
				BufferedWriter bw = null;
				try
				{
					bw = new BufferedWriter(new FileWriter(fle));

					String savetext = ta.getText();

					bw.write(savetext);
					bw.close();
				}
				catch(IOException ex)
				{
					throw new RuntimeException("����ʧ��");
				}
				finally
				{
					try
					{
						if(bw != null)
							bw.close();
					}
					catch(IOException ie)
					{
						System.out.println(ie.toString());
					}
				}
			}
		});

		//�˳��������
		closeItem.addActionListener(new ActionListener()
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