/*
客户端通过键盘录入用户名。
服务端对这个用户名进行校验。

如果该用户存在，在服务端显示xxx，已登陆。
并在客户端显示 xxx，欢迎光临。

如果该用户存在，在服务端显示xxx，尝试登陆。
并在客户端显示 xxx，该用户不存在。

最多就登录三次。
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
			//接收键盘录入的流
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			//向服务端发送的流
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
			//接收服务端反馈的流
			BufferedReader brSocket = new BufferedReader(new InputStreamReader(s.getInputStream()));

			//只允许输入三次
			for(int i = 0; i < 3; i++)
			{
				String line = br.readLine();
				bw.write(line);
				bw.newLine();
				bw.flush();
				//如果没有输入就直接跳出
				if(line == null)
					break;
		
				//接收服务端的反馈
				String jie = brSocket.readLine();
				System.out.println(jie);
				if(jie.contains("欢迎"))
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


//服务端线程
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
			//接收客户端的数据的流
			BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			//读取用户名文件的流
			BufferedReader bufr = new BufferedReader(new FileReader("F:\\JAVA\\daima\\io\\wenben.txt"));
			//向客户端反馈的流
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

			//只允许登陆三次,三次过后关掉客户端
			for(int i = 0; i < 3; i++)
			{
				//读取客户端发送过来的用户名
				String line = br.readLine();
				if(line == null)
					break;

				//与存储用户名文件里的名字做比较
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

				//有这个名字就登陆成功
				if(b)
				{
					System.out.println(line + "已登录");
					bw.write(line + "欢迎光临");
					bw.newLine();
					bw.flush();
					break;
				}
				//没有就不登陆
				else
				{
					System.out.println(line + "尝试登陆");
					bw.write(line + "不存在");
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
		//启用服务端
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