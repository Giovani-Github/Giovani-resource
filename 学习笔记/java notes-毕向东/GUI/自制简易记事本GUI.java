import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Test
{
	private Frame f;
	private TextArea ta;//编辑框
	private MenuBar mb;//菜单栏
	private Menu m, subM;//菜单
	private MenuItem closeItem, openItem, saveItem;//菜单项
	
	private FileDialog openDia, saveDia;

	private File fle;//操作的文件对象

	Test()
	{
		init();
	}

	public void init()
	{
		f = new Frame("我的窗口");
		f.setBounds(200, 150, 500, 600);

		ta = new TextArea();//文本编辑框
		mb = new MenuBar();//菜单栏
		m = new Menu("文件");//文件菜单	
		openItem = new MenuItem("打开");//打开项
		saveItem = new MenuItem("保存");//保存项
		closeItem = new MenuItem("退出");//退出项

		openDia = new FileDialog(f, "我的打开", FileDialog.LOAD);//打开文件对话框
		saveDia = new FileDialog(f,"我的保存", FileDialog.SAVE);//保存文件对话框

		f.add(ta);
		f.setMenuBar(mb);//窗口添加菜单栏
		mb.add(m);//菜单栏添加文件菜单
		m.add(openItem);//文件菜单添加打开项
		m.add(saveItem);//文件菜单添加保存项
		m.add(closeItem);//文件菜单添加退出项

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

		//打开文件监听器
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
					throw new RuntimeException("打开失败");
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

		//保存文件监听器
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
					throw new RuntimeException("保存失败");
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

		//退出项监听器
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