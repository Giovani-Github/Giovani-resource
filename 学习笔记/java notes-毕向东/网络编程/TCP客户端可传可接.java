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
			os.write("���".getBytes());

			//��ȡ��������ȡ�ͻ��˷�����������Ϣ
			InputStream is = s.getInputStream();
			byte[] bus = new byte[1024];
			//��ȡ��Ϣ
			int len = is.read(bus);
			//��ӡ������̨
			System.out.println(new String(bus, 0, len));

			//�رտͻ���
			s.close();
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
		
		
	}
}