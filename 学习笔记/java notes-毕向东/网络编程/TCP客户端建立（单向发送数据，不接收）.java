/*TCP�ͻ��˽�����
		ͨ������socket���󣬷����ڸö�����ʱ���Ϳ���ȥ����ָ��������
		��Ϊtcp���������ӵġ������ڽ���socket����ʱ��
		��Ҫ�з���˴��ڣ������ӳɹ����γ�ͨ·���ڸ�ͨ���������ݵĴ��䡣

		���裺	
		1������Socket���񡣲�ָ��Ҫ���ӵ������Ͷ˿ڡ�
		2��Ϊ�˷������ݣ���Ҫ��ȡSocket�������
		3��ͨ���������������
		4���ر�Socket
*/

import java.net.*;
import java.io.*;

class Kehu
{
	public static void main(String[] args)
	{
		try
		{
			//����Socket�������ӵ�ָ���������Ķ˿�
			Socket s = new Socket(InetAddress.getLocalHost(), 10003);
			
			//Ϊ�˷������ݣ���ȡ�ͻ��˵������
			OutputStream os = s.getOutputStream();
			//��������
			os.write("���ǣ���".getBytes());

			//�رտͻ���
			s.close();
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}		
	}
}

//����¼�뷢������
/*
try
		{
			//����Socket�������ӵ�ָ���������Ķ˿�
			Socket s = new Socket(InetAddress.getLocalHost(), 10003);
			
			//Ϊ�˷������ݣ���ȡ�ͻ��˵������
			OutputStream os = s.getOutputStream();
			
			//����¼�뷢������
			BufferedReader br = 
				new BufferedReader(new InputStreamReader(System.in));

			String bus = null;
			while((bus=br.readLine()) != null)
			{
				os.write(bus.getBytes());

				if(bus.equals("886"))
					break;
			}
			

			//�رտͻ���
			s.close();
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
*/