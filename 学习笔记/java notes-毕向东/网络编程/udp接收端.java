/*
����
����һ��Ӧ�ó������ڽ���udpЭ�鴫������ݲ�����ġ�


����udp�Ľ��նˡ�
˼·��
1������udpsocket����ͨ�������һ���˿ڡ���ʵ���Ǹ������������Ӧ�ó��������ֱ�ʶ��
	��������ȷ��Щ���ݹ�����Ӧ�ó�����Դ���

2������һ�����ݰ�����ΪҪ�洢���յ����ֽ����ݡ�
��Ϊ���ݰ��������и��๦�ܿ�����ȡ�ֽ������еĲ�ͬ������Ϣ��
3��ͨ��socket�����receive�������յ������ݴ����Ѷ���õ����ݰ��С�
4��ͨ�����ݰ���������й��ܡ�����Щ��ͬ������ȡ������ӡ�ڿ���̨�ϡ�
5���ر���Դ��

*/

import java.net.*;
class  UdpRece
{
	public static void main(String[] args) throws Exception
	{
		//1,����udp socket�������˵㡣
		DatagramSocket ds = new DatagramSocket(10000);

		//2,�������ݰ������ڴ洢���ݡ�
		byte[] buf = new byte[1024];
		DatagramPacket dp = new DatagramPacket(buf,buf.length);

		//3��ͨ�������receive�������յ����ݴ������ݰ��С�
		ds.receive(dp);//����ʽ������
		

		//4��ͨ�����ݰ��ķ�����ȡ���е����ݡ�
		String ip = dp.getAddress().getHostAddress();

		String data = new String(dp.getData(),0,dp.getLength());

		int port = dp.getPort();

		System.out.println(ip+"::"+data+"::"+port);

		//5���ر���Դ
		ds.close();

	}
}