/*
需求：从客户端上传一个文件到服务端，通过服务端保存到硬盘上，相当许上传资料到网上
*/


import java.io.*;
import java.net.*;


//客户端
class  TextClient
{
	public static void main(String[] args) throws Exception
	{
		Socket s = new Socket("192.168.1.254",10006);

		BufferedReader bufr = 
			new BufferedReader(new FileReader("IPDemo.java"));//要上传的文件，通过读取流读取

		PrintWriter out = new PrintWriter(s.getOutputStream(),true);//通过打印流上传到服务端


		String line = null;
		while((line=bufr.readLine())!=null)
		{
			//发送数据
			out.println(line);
		}

		s.shutdownOutput();//关闭客户端的输出流。相当于给流中加入一个结束标记-1.

		//读取服务端反馈的信息
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

		//读取客户端发送过来的数据的流
		BufferedReader bufIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
		
		//保存数据的流
		PrintWriter out  = new PrintWriter(new FileWriter("server.txt"),true);

		String line = null;

		//可取客户端发送过来的数据
		while((line=bufIn.readLine())!=null)
		{
			//保存到文件中
			out.println(line);
		}

		//给客户端反馈信息
		PrintWriter pw = new PrintWriter(s.getOutputStream(),true);
		pw.println("上传成功");


		out.close();
		s.close();
		ss.close();

	}
}
