import java.net.*;

/*
需求：通过udp传输方式，将一段文字数据发送出去。，
定义一个udp发送端。
思路：
1，建立updsocket服务。
2，提供数据，并将数据封装到数据包中。
3，通过socket服务的发送功能，将数据包发出去。
4，关闭资源。

*/


class  UdpSend
{
	public static void main(String[] args) throws Exception
	{
		//1，创建udp服务。通过DatagramSocket对象。
		DatagramSocket ds = new DatagramSocket(8888);

		//2，确定数据，并封装成数据包。DatagramPacket(byte[] buf, int length, InetAddress address, int port) 

		byte[] buf = "udp ge men lai le ".getBytes();
		DatagramPacket dp = 
			new DatagramPacket(buf,buf.length,InetAddress.getByName("192.168.1.254"),10000);

		//3，通过socket服务，将已有的数据包发送出去。通过send方法。
		ds.send(dp);

		//4，关闭资源。

		ds.close();

	}
}