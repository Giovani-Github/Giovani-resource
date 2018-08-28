import java.net.*;
import java.io.*;

//���Ͷ�
public class Test
{
	public static void main(String[] args)
	{
		//����Socket����
		DatagramSocket ds = null;
		try
		{
			ds = new DatagramSocket();
		}
		catch(SocketException se)
		{
			System.out.println(se.toString());
		}

		//����¼��
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
				//ip��192.168.1.255�Ļ������͵�������192.168.1.0 -- 192.168.1.254�����յ�
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


//���ն�
class Demo
{
	public static void main(String[] args) throws Exception
	{
		//����Socket����
		DatagramSocket ds = null;
		try
		{
			ds = new DatagramSocket(10022);
		}
		catch(SocketException se)
		{
			System.out.println(se.toString());
		}

		//һֱ�ڽ���
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
