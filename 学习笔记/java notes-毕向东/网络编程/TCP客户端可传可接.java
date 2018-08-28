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