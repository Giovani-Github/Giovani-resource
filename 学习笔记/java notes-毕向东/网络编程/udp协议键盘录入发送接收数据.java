import java.net.*;
import java.io.*;

//发送端
public class Test
{
	public static void main(String[] args)
	{
		//建立Socket服务
		DatagramSocket ds = null;
		try
		{
			ds = new DatagramSocket();
		}
		catch(SocketException se)
		{
			System.out.println(se.toString());
		}

		//键盘录入
		BufferedReader br = null;
		try
		{
			br = new BufferedReader(new InputStreamReader(System.in));

			String line = null;
			while((line=br.readLine()) != null)
			{
				if(line.equals("886"))
					break;
				byte[] bus = line.getBytes();
				//ip是192.168.1.255的话，发送的数据在192.168.1.0 -- 192.168.1.254都能收到
				DatagramPacket dp = new DatagramPacket(bus, bus.length, InetAddress.getByName("192.168.1.106"), 10022);

				ds.send(dp);
			}
		}
		catch(IOException e)
		{
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
		finally
		{
			try
			{
				if(br != null)
					br.close();
			}
			catch(IOException e)
			{
				System.out.println(e.toString());
			}

			ds.close();

		}
	}
}


//接收端
class Demo
{
	public static void main(String[] args) throws Exception
	{
		//建立Socket服务
		DatagramSocket ds = null;
		try
		{
			ds = new DatagramSocket(10022);
		}
		catch(SocketException se)
		{
			System.out.println(se.toString());
		}

		//一直在接收
		while(true)
		{
			byte[] bus = new byte[1024];
			DatagramPacket dp = new DatagramPacket(bus, bus.length);

			ds.receive(dp);

			String ip = dp.getAddress().getHostAddress();
			String data = new String(dp.getData(), 0, dp.getLength());

			System.out.println("ip:" + ip + "\n" + "data:" + data);
		}
	}
}
