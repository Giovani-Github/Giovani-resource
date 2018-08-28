import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * 坦克诞生消息的类
 * @author 李庆旺
 *
 */
public class TankNewMsg {
	Tank tank;
	TankClient tc;
	
	public TankNewMsg(Tank tank) {
		this.tank = tank;
	}
	
	public TankNewMsg(TankClient tc) {
		this.tc = tc;
	}

	//用udp服务发送数据给服务器的方法
	public void send(DatagramSocket ds, String ip, int udpport) {
		//首先要有一个存储数据的字节数组，用这个字节数组写入流代替，它里面封装了一个字节数组
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		
		//往流中写入数据
		try {
			dos.writeInt(tank.id);
			dos.writeInt(tank.x);
			dos.writeInt(tank.y);
			dos.writeInt(tank.dir.ordinal());
			dos.writeBoolean(tank.good);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//把流中的字节数组拿出来
		byte[] bus = baos.toByteArray();
	
		//把数据发送到服务端
		try {
			DatagramPacket dp = new DatagramPacket(bus, bus.length, new InetSocketAddress(ip, udpport));
			ds.send(dp);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	////解析服务端upd发过来的数据
	public void parse(DataInputStream dis) {
		try {
			int id = dis.readInt();
			//如果服务端发过来的客户端信息，是自身这个客户端的信息
			if(tc.myTank.id == id) {
				return;
			}
			int x = dis.readInt();
			int y = dis.readInt();
			Dir dir = Dir.values()[dis.readInt()];
			boolean good = dis.readBoolean();
			
			//如果不是自身客户端的坦克信息，就在自身客户端中画出这个客户端坦克
			Tank tank = new Tank(x, y, good, dir, tc);
			tank.id = id;
			tc.tanks.add(tank);
			
System.out.println("x:"+x + " y:"+y + " id:"+id + " dir:"+dir + " good:"+good);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
