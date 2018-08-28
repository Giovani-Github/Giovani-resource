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
	public static void main(String[] args)/*

���󣺽���һ���ı�ת����������
�ͻ��˸�����˷����ı������񵥻Ὣ�ı�ת�ɴ�д�ڷ��ظ��ͻ��ˡ�
���ҿͻ��ȿ��Բ��ϵĽ����ı�ת�������ͻ�������overʱ��ת��������

������
�ͻ��ˣ�
��Ȼ�ǲ����豸�ϵ����ݣ���ô�Ϳ���ʹ��io������������io�Ĳ���������˼����
Դ������¼�롣
Ŀ�ģ������豸�������������
���Ҳ��������ı����ݡ�����ѡ���ַ�����

����
1����������
2����ȡ����¼�롣
3�������ݷ�������ˡ�
4����ȥ����˷��صĴ�д���ݡ�
5������������Դ��

�����ı����ݣ�����ʹ���ַ������в�����ͬʱ���Ч�ʣ����뻺�塣


*/
import java.io.*;
import java.net.*;

class  TransClient
{
	public static void main(String[] args) throws Exception
	{
		Socket s = new Socket("192.168.1.254",10005);


		//�����ȡ�������ݵ�������
		BufferedReader bufr = 
			new BufferedReader(new InputStreamReader(System.in));


		//����Ŀ�ģ�������д�뵽socket���������������ˡ�
		//BufferedWriter bufOut = 
			//new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
		PrintWriter out = new PrintWriter(s.getOutputStream(),true);



		//����һ��socket��ȡ������ȡ����˷��صĴ�д��Ϣ��
		BufferedReader bufIn = 
			new BufferedReader(new InputStreamReader(s.getInputStream()));

		String line = null;
		
		//��ȡ����¼�������
		while((line=bufr.readLine())!=null)
		{
			if("over".equals(line))
				break;
			
			//�����˷�������
			out.println(line);
//			bufOut.write(line);
//			bufOut.newLine();//���б��
//			bufOut.flush();

			//��ȡ����˷������վ�
			String str =bufIn.readLine();
			System.out.println("server:"+str);
			
		}

		bufr.close();
		s.close();


	}
}
/*

����ˣ�
Դ��socket��ȡ����
Ŀ�ģ�socket�������
�����ı���װ�Ρ�



*/

class  TransServer
{
	public static void main(String[] args) throws Exception
	{
		ServerSocket ss = new ServerSocket(10005);

		Socket s = ss.accept();
		String ip = s.getInetAddress().getHostAddress();
		System.out.println(ip+"....connected");

		//��ȡsocket��ȡ���е����ݡ�
		BufferedReader bufIn =
			new BufferedReader(new InputStreamReader(s.getInputStream()));

		//Ŀ�ġ�socket�����������д����д�뵽socket������������͸��ͻ��ˡ�
		//BufferedWriter bufOut = 
			//new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

		PrintWriter out = new PrintWriter(s.getOutputStream(),true);


		//��ȡ�ӿͻ��˽��յ�����
		String line = null;
		while((line=bufIn.readLine())!=null)//��Ҫ��ȡ�����б��
		{

			//��Ӧ�ͻ��˵�����
			System.out.println(line);
		

			//��ͻ��˷��͸ı�������
			out.println(line.toUpperCase());
//			bufOut.write(line.toUpperCase());
//			bufOut.newLine();//���б��
//			bufOut.flush();
		}

		s.close();
		ss.close();

	}
}
/*
�����ӳ��ֵ����⡣
���󣺿ͻ��˺ͷ���˶���Ī���ĵȴ���
Ϊʲô�أ�
��Ϊ�ͻ��˺ͷ���˶�������ʽ��������Щ����ôû�ж���������ǡ���ô��һֱ��
���������ˣ����ڵȴ���


*/
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
class Test
{
	public static void main(String[] args)
	{
		try
		{
			//���������Socket���񣬼���10003�˿�
			ServerSocket ss = new ServerSocket(10003);
			
			//�����һֱ����
		
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
			//����ַ�����ӡ������̨��
			System.out.println(new String(bus, 0, len));

			//�������ݣ�Ҫ��ȡ�ͻ��˵������
			OutputStream os = s.getOutputStream();
			//ͨ������������͵��ͻ���
			os.write("�յ���".getBytes());
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
	}
}