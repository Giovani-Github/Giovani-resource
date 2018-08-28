/*TCP客户端建立：
		通过查阅socket对象，发现在该对象建立时，就可以去连接指定主机。
		因为tcp是面向连接的。所以在建立socket服务时，
		就要有服务端存在，并连接成功。形成通路后，在该通道进行数据的传输。

		步骤：	
		1，创建Socket服务。并指定要连接的主机和端口。
		2，为了发送数据，需要获取Socket的输出流
		3，通过输出流发送数据
		4，关闭Socket
*/

import java.net.*;
import java.io.*;

class Kehu
{
	public static void main(String[] args)/*

需求：建立一个文本转换服务器。
客户端给服务端发送文本，服务单会将文本转成大写在返回给客户端。
而且客户度可以不断的进行文本转换。当客户端输入over时，转换结束。

分析：
客户端：
既然是操作设备上的数据，那么就可以使用io技术，并按照io的操作规律来思考。
源：键盘录入。
目的：网络设备，网络输出流。
而且操作的是文本数据。可以选择字符流。

步骤
1，建立服务。
2，获取键盘录入。
3，将数据发给服务端。
4，后去服务端返回的大写数据。
5，结束，关资源。

都是文本数据，可以使用字符流进行操作，同时提高效率，加入缓冲。


*/
import java.io.*;
import java.net.*;

class  TransClient
{
	public static void main(String[] args) throws Exception
	{
		Socket s = new Socket("192.168.1.254",10005);


		//定义读取键盘数据的流对象。
		BufferedReader bufr = 
			new BufferedReader(new InputStreamReader(System.in));


		//定义目的，将数据写入到socket输出流。发给服务端。
		//BufferedWriter bufOut = 
			//new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
		PrintWriter out = new PrintWriter(s.getOutputStream(),true);



		//定义一个socket读取流，读取服务端返回的大写信息。
		BufferedReader bufIn = 
			new BufferedReader(new InputStreamReader(s.getInputStream()));

		String line = null;
		
		//读取键盘录入的数据
		while((line=bufr.readLine())!=null)
		{
			if("over".equals(line))
				break;
			
			//向服务端发送数据
			out.println(line);
//			bufOut.write(line);
//			bufOut.newLine();//换行标记
//			bufOut.flush();

			//读取服务端反馈的收据
			String str =bufIn.readLine();
			System.out.println("server:"+str);
			
		}

		bufr.close();
		s.close();


	}
}
/*

服务端：
源：socket读取流。
目的：socket输出流。
都是文本，装饰。



*/

class  TransServer
{
	public static void main(String[] args) throws Exception
	{
		ServerSocket ss = new ServerSocket(10005);

		Socket s = ss.accept();
		String ip = s.getInetAddress().getHostAddress();
		System.out.println(ip+"....connected");

		//读取socket读取流中的数据。
		BufferedReader bufIn =
			new BufferedReader(new InputStreamReader(s.getInputStream()));

		//目的。socket输出流。将大写数据写入到socket输出流，并发送给客户端。
		//BufferedWriter bufOut = 
			//new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

		PrintWriter out = new PrintWriter(s.getOutputStream(),true);


		//读取从客户端接收的数据
		String line = null;
		while((line=bufIn.readLine())!=null)//需要读取到换行标记
		{

			//答应客户端的数据
			System.out.println(line);
		

			//向客户端发送改变后的数据
			out.println(line.toUpperCase());
//			bufOut.write(line.toUpperCase());
//			bufOut.newLine();//换行标记
//			bufOut.flush();
		}

		s.close();
		ss.close();

	}
}
/*
该例子出现的问题。
现象：客户端和服务端都在莫名的等待。
为什么呢？
因为客户端和服务端都有阻塞式方法。这些方法么没有读到结束标记。那么就一直等
而导致两端，都在等待。


*/
	{
		try
		{
			//建立Socket服务连接到指定的主机的端口
			Socket s = new Socket(InetAddress.getLocalHost(), 10003);
			
			//为了发送数据，获取客户端的输出流
			OutputStream os = s.getOutputStream();
			//发送数据
			os.write("你好".getBytes());

			//获取输入流读取客户端反馈过来的信息
			InputStream is = s.getInputStream();
			byte[] bus = new byte[1024];
			//读取信息
			int len = is.read(bus);
			//打印到控制台
			System.out.println(new String(bus, 0, len));

			//关闭客户端
			s.close();
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
		
		
	}
}


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
class Test
{
	public static void main(String[] args)
	{
		try
		{
			//建立服务端Socket服务，监听10003端口
			ServerSocket ss = new ServerSocket(10003);
			
			//服务端一直开启
		
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
			//变成字符串打印到控制台上
			System.out.println(new String(bus, 0, len));

			//反馈数据，要获取客户端的输出流
			OutputStream os = s.getOutputStream();
			//通过输出方法发送到客户端
			os.write("收到了".getBytes());
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
	}
}