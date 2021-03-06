import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;


public class MissileNewMsg implements Msg {
	int msgType = Msg.MISSILE_NEW_MSG;
	
	Missile m;
	TankClient tc;
	
	public MissileNewMsg(Missile m) {
		this.m = m;
	}
	
	public MissileNewMsg(TankClient tc) {
		this.tc = tc;
	}

	public void send(DatagramSocket ds, String ip, int udpPort) {
		//首先要有一个存储数据的字节数组，用这个字节数组写入流代替，它里面封装了一个字节数组
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		
		//往流中写入数据
		try {
			dos.writeInt(msgType);
			dos.writeInt(m.tankId);
			dos.writeInt(m.x);
			dos.writeInt(m.y);
			dos.writeInt(m.dir.ordinal());
			dos.writeBoolean(m.good);
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
			int tankId = dis.readInt();
			if(tankId == tc.myTank.id) {
				return;
			}
			
			int x = dis.readInt();
			int y = dis.readInt();
			Dir dir = Dir.values()[dis.readInt()];
			boolean good = dis.readBoolean();
			
			Missile m = new Missile(tankId, x, y, good, dir, tc);
			tc.missiles.add(m);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
}
