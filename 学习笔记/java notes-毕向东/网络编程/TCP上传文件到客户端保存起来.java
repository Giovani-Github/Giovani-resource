/*
���󣺴ӿͻ����ϴ�һ���ļ�������ˣ�ͨ������˱��浽Ӳ���ϣ��൱���ϴ����ϵ�����
*/


import java.io.*;
import java.net.*;


//�ͻ���
class  TextClient
{
	public static void main(String[] args) throws Exception
	{
		Socket s = new Socket("192.168.1.254",10006);

		BufferedReader bufr = 
			new BufferedReader(new FileReader("IPDemo.java"));//Ҫ�ϴ����ļ���ͨ����ȡ����ȡ

		PrintWriter out = new PrintWriter(s.getOutputStream(),true);//ͨ����ӡ���ϴ��������


		String line = null;
		while((line=bufr.readLine())!=null)
		{
			//��������
			out.println(line);
		}

		s.shutdownOutput();//�رտͻ��˵���������൱�ڸ����м���һ���������-1.

		//��ȡ����˷�������Ϣ
		BufferedReader bufIn = new BufferedReader(new InputStreamReader(s.getInputStream()));

		String str = bufIn.readLine();
		System.out.println(str);

		bufr.close();

		s.close();
	}
}


class  TextServer
{
	public static void main(String[] args) throws Exception
	{
		ServerSocket ss = new ServerSocket(10006);

		Socket s = ss.accept();
		String ip = s.getInetAddress().getHostAddress();
		System.out.println(ip+"....connected");

		//��ȡ�ͻ��˷��͹��������ݵ���
		BufferedReader bufIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
		
		//�������ݵ���
		PrintWriter out  = new PrintWriter(new FileWriter("server.txt"),true);

		String line = null;

		//��ȡ�ͻ��˷��͹���������
		while((line=bufIn.readLine())!=null)
		{
			//���浽�ļ���
			out.println(line);
		}

		//���ͻ��˷�����Ϣ
		PrintWriter pw = new PrintWriter(s.getOutputStream(),true);
		pw.println("�ϴ��ɹ�");


		out.close();
		s.close();
		ss.close();

	}
}
