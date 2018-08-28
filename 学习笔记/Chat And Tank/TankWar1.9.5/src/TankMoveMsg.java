import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;


/**
 * 坦克移动的消息类
 * @author 李庆旺
 *
 */
public class TankMoveMsg implements Msg{
	int msgType = Msg.TANK_MOVE_MSG;
	int id;
	int x, y;
	Dir dir;
	TankClient tc;
	
	public TankMoveMsg(int id, int x, int y, Dir dir) {
		super();
		this.id = id;
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
	
	public TankMoveMsg(TankClient tc) {
		this.tc = tc;
	}

	public void send(DatagramSocket ds, String ip, int udpPort) {
		//首先要有一个存储数据的字节数组，用这个字节数组写入流代替，它里面封装了一个字节数组
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		
		//往流中写入数据
		try {
			dos.writeInt(msgType);
			dos.writeInt(id);
			dos.writeInt(x);
			dos.writeInt(y);
			dos.writeInt(dir.ordinal());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//把流中的字节数组拿出来
		byte[] bus = baos.toByteArray();
	
		//把数据发送到服务端
		try {
			DatagramPacket dp = new DatagramPacket(bus, bus.length, new InetSocketAddress(ip, udpPort));
			ds.send(dp);
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
	}

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
			
			//如果这个坦克在本客户端中存在
			boolean exist = false;//是否存在
			for(int i=0; i<tc.tanks.size(); i++) {
				Tank tank = tc.tanks.get(i);
				if(tank.id == id) {
					tank.x = x;
					tank.y = y;
					tank.dir = dir;
					exist = true;
					break;
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
	}
}
