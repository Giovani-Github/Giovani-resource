/*
编写一个聊天程序。
有收数据的部分，和发数据的部分。
这两部分需要同时执行。
那就需要用到多线程技术。
一个线程控制收，一个线程控制发。

因为收和发动作是不一致的，所以要定义两个run方法。
而且这两个方法要封装到不同的类中。

*/

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;

//发送线程
class Send implements Runnable
{
	//初始化就建立Socket服务
	private DatagramSocket ds;
	public Send(DatagramSocket ds)
	{
		this.ds = ds;
	}

	public void run()
	{
		try
		{
			//接收用户输入的数据
			BufferedReader br = 
				new BufferedReader(new StringReader(Test.getSend().getText()));

			String line = null;
			while((line = br.readLine()) != null)
			{
				//将数据变成数组
				byte[] bus = line.getBytes();

				//打包成数据报包
				DatagramPacket dp = 
					new DatagramPacket(bus, bus.length, InetAddress.getLocalHost(), 10001);
	
				//发送
				ds.send(dp);
				
				if(line.equals("886"))
					break;
			}
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
	}
}


//接收线程
class Rece implements Runnable
{
	private DatagramSocket ds;
	public Rece(DatagramSocket ds)
	{
		this.ds = ds;
	}

	public void run()
	{
		try
		{
			while(true)
			{
				//缓冲区
				byte[] bus = new byte[1204];
				DatagramPacket dp = new DatagramPacket(bus, bus.length);

				//接收到的数据打包成数据报包
				ds.receive(dp);

				//取出ip
				String ip = dp.getAddress().getHostAddress();
				//取出数据
				String data = new String(dp.getData(), 0, dp.getLength());

				if(data.equals("886"))
					break;
				
				//打印到接收文本框里
				Test.getRece().append(ip + "::" + data + "\r\n");
			}
		}
		catch(Exception e)
		{
			throw new RuntimeException("接收端失败");
		}
	}
}


public class Test
{
	private Frame f;//聊天窗口
	private static TextArea send;//发送文本框
	private static TextArea rece;//接收文本框
	private Button b;//发送按钮

	public Test()
	{
		init();
	}

	//获取发送文本框
	public static TextArea getSend()
	{
		return send;
	}

	//获取接收文本框
	public static TextArea getRece()
	{
		return rece;
	}

	public void init()
	{
		f = new Frame("聊天窗口");
		f.setBounds(400, 200, 400,450);
		f.setLayout(new FlowLayout());
		
		rece = new TextArea(10,50);
		//聊天窗口添加接收文本框
		f.add(rece);
		
		send = new TextArea(10,50);
		//聊天窗口添加发送文本框
		f.add(send);

		b = new Button("发送");
		//天加发送按钮
		f.add(b);

		//调用事件监听
		myEvent();

		f.setVisible(true);
	}

	public void myEvent()
	{
		//关闭窗口事件
		f.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent we)
			{
				System.exit(0);
			}
		});

		//发送事件
		b.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				try
				{
					//开启发送线程
					DatagramSocket sendSocket = new DatagramSocket();
					new Thread(new Send(sendSocket)).start();
				}
				catch(Exception e)
				{
					System.out.println(e.toString());
				}
				
				//发送的数据显示到自己的接收文本框内
				rece.append(getSend().getText() + "\r\n");
				send.setText("");
			}
		});
	}

	public static void main(String[] args)
	{
		try
		{
			//开启接收线程
			DatagramSocket receSocket = new DatagramSocket(10002);
			new Thread(new Rece(receSocket)).start();
			
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}

		new Test();

	}
}