/*
TCP服务端建立：
		1，建立服务端的socket服务。ServerSocket();
			并监听一个端口。
		2，获取连接过来的客户端对象。
			通过ServerSokcet的 accept方法。没有连接就会等，所以这个方法阻塞式的。
		3，客户端如果发过来数据，那么服务端要使用对应的客户端对象，
			并获取到该客户端对象的读取流来读取发过来的数据。
			并打印在控制台。

		4,关闭客户端（可选）
		5，关闭服务端。（可选）
*/


import java.net.*;
import java.io.*;
class Test
{
	public static void main(String[] args)
	{
		try
		{
			//建立服务端Socket服务，监听10003端口
			ServerSocket ss = new ServerSocket(10003);
			
			//服务端一直开启
			while(true)
			{
				//获取连接过来的客户端对象
				Socket s = ss.accept();

				//获取客户端的ip地址
				String ip = s.getLocalAddress().getHostAddress();
				System.out.println(ip + "--------来了");

				//为了读取到客户端发过来的数据，要获取客户端的读取流对象
				InputStream is = s.getInputStream();

				//读取数据
				byte[] bus = new byte[1024];
				int len = 0;
				while((len=is.read(bus)) != -1)
				{
					//变成字符串打印到控制台上
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