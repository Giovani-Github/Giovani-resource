import java.net.*;

/*
����ͨ��udp���䷽ʽ����һ���������ݷ��ͳ�ȥ����
����һ��udp���Ͷˡ�
˼·��
1������updsocket����
2���ṩ���ݣ��������ݷ�װ�����ݰ��С�
3��ͨ��socket����ķ��͹��ܣ������ݰ�����ȥ��
4���ر���Դ��

*/


class  UdpSend
{
	public static void main(String[] args) throws Exception
	{
		//1������udp����ͨ��DatagramSocket����
		DatagramSocket ds = new DatagramSocket(8888);

		//2��ȷ�����ݣ�����װ�����ݰ���DatagramPacket(byte[] buf, int length, InetAddress address, int port) 

		byte[] buf = "udp ge men lai le ".getBytes();
		DatagramPacket dp = 
			new DatagramPacket(buf,buf.length,InetAddress.getByName("192.168.1.254"),10000);

		//3��ͨ��socket���񣬽����е����ݰ����ͳ�ȥ��ͨ��send������
		ds.send(dp);

		//4���ر���Դ��

		ds.close();

	}
}