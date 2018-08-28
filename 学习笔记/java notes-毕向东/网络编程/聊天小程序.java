/*
��дһ���������
�������ݵĲ��֣��ͷ����ݵĲ��֡�
����������Ҫͬʱִ�С�
�Ǿ���Ҫ�õ����̼߳�����
һ���߳̿����գ�һ���߳̿��Ʒ���

��Ϊ�պͷ������ǲ�һ�µģ�����Ҫ��������run������
��������������Ҫ��װ����ͬ�����С�

*/

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;

//�����߳�
class Send implements Runnable
{
	//��ʼ���ͽ���Socket����
	private DatagramSocket ds;
	public Send(DatagramSocket ds)
	{
		this.ds = ds;
	}

	public void run()
	{
		try
		{
			//�����û����������
			BufferedReader br = 
				new BufferedReader(new StringReader(Test.getSend().getText()));

			String line = null;
			while((line = br.readLine()) != null)
			{
				//�����ݱ������
				byte[] bus = line.getBytes();

				//��������ݱ���
				DatagramPacket dp = 
					new DatagramPacket(bus, bus.length, InetAddress.getLocalHost(), 10001);
	
				//����
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


//�����߳�
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
				//������
				byte[] bus = new byte[1204];
				DatagramPacket dp = new DatagramPacket(bus, bus.length);

				//���յ������ݴ�������ݱ���
				ds.receive(dp);

				//ȡ��ip
				String ip = dp.getAddress().getHostAddress();
				//ȡ������
				String data = new String(dp.getData(), 0, dp.getLength());

				if(data.equals("886"))
					break;
				
				//��ӡ�������ı�����
				Test.getRece().append(ip + "::" + data + "\r\n");
			}
		}
		catch(Exception e)
		{
			throw new RuntimeException("���ն�ʧ��");
		}
	}
}


public class Test
{
	private Frame f;//���촰��
	private static TextArea send;//�����ı���
	private static TextArea rece;//�����ı���
	private Button b;//���Ͱ�ť

	public Test()
	{
		init();
	}

	//��ȡ�����ı���
	public static TextArea getSend()
	{
		return send;
	}

	//��ȡ�����ı���
	public static TextArea getRece()
	{
		return rece;
	}

	public void init()
	{
		f = new Frame("���촰��");
		f.setBounds(400, 200, 400,450);
		f.setLayout(new FlowLayout());
		
		rece = new TextArea(10,50);
		//���촰����ӽ����ı���
		f.add(rece);
		
		send = new TextArea(10,50);
		//���촰����ӷ����ı���
		f.add(send);

		b = new Button("����");
		//��ӷ��Ͱ�ť
		f.add(b);

		//�����¼�����
		myEvent();

		f.setVisible(true);
	}

	public void myEvent()
	{
		//�رմ����¼�
		f.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent we)
			{
				System.exit(0);
			}
		});

		//�����¼�
		b.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				try
				{
					//���������߳�
					DatagramSocket sendSocket = new DatagramSocket();
					new Thread(new Send(sendSocket)).start();
				}
				catch(Exception e)
				{
					System.out.println(e.toString());
				}
				
				//���͵�������ʾ���Լ��Ľ����ı�����
				rece.append(getSend().getText() + "\r\n");
				send.setText("");
			}
		});
	}

	public static void main(String[] args)
	{
		try
		{
			//���������߳�
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