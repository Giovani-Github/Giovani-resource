import java.net.*;
import java.io.*;


//�ͻ���
class Fas
{
	public static void main(String[] args) throws Exception
	{
		//�Ƿ�ѡ����һ��ͼƬ
		if(args.length != 1)
		{
			System.out.println("��ѡ��һ���ļ��ϴ�");
			return;
		}

		File file = new File(args[0]);
		if(!(file.exists() && file.isFile()))
		{
			System.out.println("�ļ�������");
			return;
		}

		if(!(file.getName().endsWith(".jpg")))
		{
			System.out.println("���ϴ�jpg��ʽ��ͼƬ");
			return;
		}

		if(file.length() > 1204*1204*5)
		{
			System.out.println("�ļ�����");
			return;
		}

		//����Scoket����
		Socket s = new Socket(InetAddress.getLocalHost(), 10006);
		//��ȡ�ļ�����
		BufferedInputStream br = new BufferedInputStream(new FileInputStream(file));
		//�ϴ��ļ�����
		BufferedOutputStream bw = new BufferedOutputStream(s.getOutputStream());
		//��ȡ����˷�������
		BufferedReader bws = new BufferedReader(new InputStreamReader(s.getInputStream()));

		//�ϴ�����
		byte[] bus = new byte[1024];
		int len = 0;
		while((len=br.read(bus)) != -1)
		{
			bw.write(bus, 0, len);
		}

		//������߷�����Ѿ��������
		s.shutdownOutput();
		
		//��ȡ�ͻ��˷�������Ϣ
		String line = bws.readLine();
		System.out.println(line);

		br.close();
		s.close();
	}
}


//�ѷ���˴���ͻ������ӵĴ����װ���߳�
class JieThread implements Runnable
{
	//�߳�һ�����͵���һ���ͻ��˵�Socket
	Socket s = null;
	JieThread(Socket s)
	{
		this.s = s;
	}
	
	public void run()
	{
		String ip = s.getLocalAddress().getHostAddress();
		//�ļ�������������ֹ�ļ����ظ�
		int count = 1;
		try
		{
			System.out.println(ip + "********������");

			File dir = new File("F:\\JAVA\\daima\\io");
			
			//�Ƚ���һ���ļ�
			File file = new File(dir, ip + "(" + count + ")" + ".jpg");

			//����ļ����ڣ����ں������(count)�ٴ���һ���ļ�
			while(file.exists())
				file = new File(dir, ip + "(" + (count++) + ")" + ".jpg");

			//���տͻ������ݵ���
			BufferedInputStream brs = new BufferedInputStream(s.getInputStream());
			//��ͻ��˷�������
			BufferedWriter bws = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
			//�����ļ�����
			BufferedOutputStream bw = new BufferedOutputStream(new FileOutputStream(file));

			//�����ļ�
			byte[] bus = new byte[1024];
			int len = 0;
			while((len=brs.read(bus)) != -1)
			{
				bw.write(bus, 0, len);
			}

			//��ͻ��˷���
			bws.write("�ϴ��ɹ�");
			bws.newLine();
			bws.flush();	

			bw.close();
		}
		catch(Exception e)
		{
			throw new RuntimeException(ip + "�ϴ�ʧ��");
		}
	}
}

//�����
class Jies
{
	public static void main(String[] args) throws Exception
	{
		ServerSocket ss = new ServerSocket(10006);
		
		//һ��һ���ͻ������ӣ��Ϳ���һ���̴߳�������ͻ��˵�����
		while(true)
		{
			Socket s= ss.accept();
			
			new Thread(new JieThread(s)).start();
		}
	}
}