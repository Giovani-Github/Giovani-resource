/*
�ͻ���ͨ������¼���û�����
����˶�����û�������У�顣

������û����ڣ��ڷ������ʾxxx���ѵ�½��
���ڿͻ�����ʾ xxx����ӭ���١�

������û����ڣ��ڷ������ʾxxx�����Ե�½��
���ڿͻ�����ʾ xxx�����û������ڡ�

���͵�¼���Ρ�
*/

import java.net.*;
import java.io.*;

class Fas
{
	public static void main(String[] args)
	{
		try
		{
			Socket s = new Socket(InetAddress.getLocalHost(), 10007);
			//���ռ���¼�����
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			//�����˷��͵���
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
			//���շ���˷�������
			BufferedReader brSocket = new BufferedReader(new InputStreamReader(s.getInputStream()));

			//ֻ������������
			for(int i = 0; i < 3; i++)
			{
				String line = br.readLine();
				bw.write(line);
				bw.newLine();
				bw.flush();
				//���û�������ֱ������
				if(line == null)
					break;
		
				//���շ���˵ķ���
				String jie = brSocket.readLine();
				System.out.println(jie);
				if(jie.contains("��ӭ"))
					break;
			}

			br.close();
			s.close();
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
		
	}
}


//������߳�
class SerThread implements Runnable
{
	Socket s = null;
	SerThread(Socket s)
	{
		this.s = s;	
	}	

	public void run()
	{
		try
		{
			//���տͻ��˵����ݵ���
			BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			//��ȡ�û����ļ�����
			BufferedReader bufr = new BufferedReader(new FileReader("F:\\JAVA\\daima\\io\\wenben.txt"));
			//��ͻ��˷�������
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

			//ֻ�����½����,���ι���ص��ͻ���
			for(int i = 0; i < 3; i++)
			{
				//��ȡ�ͻ��˷��͹������û���
				String line = br.readLine();
				if(line == null)
					break;

				//��洢�û����ļ�����������Ƚ�
				String bus = null;
				boolean b = false;
				while((bus=bufr.readLine()) != null)
				{
					if(line.equals(bus))
					{
						b = true;
						break;
					}
				}

				//��������־͵�½�ɹ�
				if(b)
				{
					System.out.println(line + "�ѵ�¼");
					bw.write(line + "��ӭ����");
					bw.newLine();
					bw.flush();
					break;
				}
				//û�оͲ���½
				else
				{
					System.out.println(line + "���Ե�½");
					bw.write(line + "������");
					bw.newLine();
					bw.flush();
				}
			}

			s.close();
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
	}
}


class Jies
{
	public static void main(String[] args)
	{
		//���÷����
		try
		{
			ServerSocket ss = new ServerSocket(10007);
			
			while(true)
			{
				Socket s = ss.accept();

				new Thread(new SerThread(s)).start();
			}
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
	}
}