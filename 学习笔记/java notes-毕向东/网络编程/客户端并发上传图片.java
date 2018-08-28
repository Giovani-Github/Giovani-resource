import java.net.*;
import java.io.*;


//客户端
class Fas
{
	public static void main(String[] args) throws Exception
	{
		//是否选择了一个图片
		if(args.length != 1)
		{
			System.out.println("请选择一个文件上传");
			return;
		}

		File file = new File(args[0]);
		if(!(file.exists() && file.isFile()))
		{
			System.out.println("文件不存在");
			return;
		}

		if(!(file.getName().endsWith(".jpg")))
		{
			System.out.println("请上传jpg格式的图片");
			return;
		}

		if(file.length() > 1204*1204*5)
		{
			System.out.println("文件过大");
			return;
		}

		//建立Scoket服务
		Socket s = new Socket(InetAddress.getLocalHost(), 10006);
		//读取文件的流
		BufferedInputStream br = new BufferedInputStream(new FileInputStream(file));
		//上传文件的流
		BufferedOutputStream bw = new BufferedOutputStream(s.getOutputStream());
		//读取服务端反馈的流
		BufferedReader bws = new BufferedReader(new InputStreamReader(s.getInputStream()));

		//上传数据
		byte[] bus = new byte[1024];
		int len = 0;
		while((len=br.read(bus)) != -1)
		{
			bw.write(bus, 0, len);
		}

		//传完告诉服务端已经传完结束
		s.shutdownOutput();
		
		//读取客户端反馈的信息
		String line = bws.readLine();
		System.out.println(line);

		br.close();
		s.close();
	}
}


//把服务端处理客户端连接的代码封装成线程
class JieThread implements Runnable
{
	//线程一开启就得有一个客户端的Socket
	Socket s = null;
	JieThread(Socket s)
	{
		this.s = s;
	}
	
	public void run()
	{
		String ip = s.getLocalAddress().getHostAddress();
		//文件名计数器，防止文件名重复
		int count = 1;
		try
		{
			System.out.println(ip + "********连接上");

			File dir = new File("F:\\JAVA\\daima\\io");
			
			//先建立一个文件
			File file = new File(dir, ip + "(" + count + ")" + ".jpg");

			//如果文件存在，就在后面加上(count)再创建一个文件
			while(file.exists())
				file = new File(dir, ip + "(" + (count++) + ")" + ".jpg");

			//接收客户端数据的流
			BufferedInputStream brs = new BufferedInputStream(s.getInputStream());
			//向客户端反馈的流
			BufferedWriter bws = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
			//保存文件的流
			BufferedOutputStream bw = new BufferedOutputStream(new FileOutputStream(file));

			//保存文件
			byte[] bus = new byte[1024];
			int len = 0;
			while((len=brs.read(bus)) != -1)
			{
				bw.write(bus, 0, len);
			}

			//向客户端反馈
			bws.write("上传成功");
			bws.newLine();
			bws.flush();	

			bw.close();
		}
		catch(Exception e)
		{
			throw new RuntimeException(ip + "上传失败");
		}
	}
}

//服务端
class Jies
{
	public static void main(String[] args) throws Exception
	{
		ServerSocket ss = new ServerSocket(10006);
		
		//一有一个客户端连接，就开启一个线程处理这个客户端的数据
		while(true)
		{
			Socket s= ss.accept();
			
			new Thread(new JieThread(s)).start();
		}
	}
}