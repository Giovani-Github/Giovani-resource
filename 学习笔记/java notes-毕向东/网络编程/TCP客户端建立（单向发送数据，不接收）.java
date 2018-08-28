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
	public static void main(String[] args)
	{
		try
		{
			//建立Socket服务连接到指定的主机的端口
			Socket s = new Socket(InetAddress.getLocalHost(), 10003);
			
			//为了发送数据，获取客户端的输出流
			OutputStream os = s.getOutputStream();
			//发送数据
			os.write("你是？？".getBytes());

			//关闭客户端
			s.close();
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}		
	}
}

//键盘录入发送数据
/*
try
		{
			//建立Socket服务连接到指定的主机的端口
			Socket s = new Socket(InetAddress.getLocalHost(), 10003);
			
			//为了发送数据，获取客户端的输出流
			OutputStream os = s.getOutputStream();
			
			//键盘录入发送数据
			BufferedReader br = 
				new BufferedReader(new InputStreamReader(System.in));

			String bus = null;
			while((bus=br.readLine()) != null)
			{
				os.write(bus.getBytes());

				if(bus.equals("886"))
					break;
			}
			

			//关闭客户端
			s.close();
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
*/