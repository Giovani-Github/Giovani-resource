/*
TCP����˽�����
		1����������˵�socket����ServerSocket();
			������һ���˿ڡ�
		2����ȡ���ӹ����Ŀͻ��˶���
			ͨ��ServerSokcet�� accept������û�����Ӿͻ�ȣ����������������ʽ�ġ�
		3���ͻ���������������ݣ���ô�����Ҫʹ�ö�Ӧ�Ŀͻ��˶���
			����ȡ���ÿͻ��˶���Ķ�ȡ������ȡ�����������ݡ�
			����ӡ�ڿ���̨��

		4,�رտͻ��ˣ���ѡ��
		5���رշ���ˡ�����ѡ��
*/


import java.net.*;
import java.io.*;
class Test
{
	public static void main(String[] args)
	{
		try
		{
			//���������Socket���񣬼���10003�˿�
			ServerSocket ss = new ServerSocket(10003);
			
			//�����һֱ����
			while(true)
			{
				//��ȡ���ӹ����Ŀͻ��˶���
				Socket s = ss.accept();

				//��ȡ�ͻ��˵�ip��ַ
				String ip = s.getLocalAddress().getHostAddress();
				System.out.println(ip + "--------����");

				//Ϊ�˶�ȡ���ͻ��˷����������ݣ�Ҫ��ȡ�ͻ��˵Ķ�ȡ������
				InputStream is = s.getInputStream();

				//��ȡ����
				byte[] bus = new byte[1024];
				int len = 0;
				while((len=is.read(bus)) != -1)
				{
					//����ַ�����ӡ������̨��
					System.out.println(new String(bus, 0, len));
				}
			}
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
	}
}