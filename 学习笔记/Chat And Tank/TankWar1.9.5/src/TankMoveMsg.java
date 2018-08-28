import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;


/**
 * ̹���ƶ�����Ϣ��
 * @author ������
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
		//����Ҫ��һ���洢���ݵ��ֽ����飬������ֽ�����д�������棬�������װ��һ���ֽ�����
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		
		//������д������
		try {
			dos.writeInt(msgType);
			dos.writeInt(id);
			dos.writeInt(x);
			dos.writeInt(y);
			dos.writeInt(dir.ordinal());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//�����е��ֽ������ó���
		byte[] bus = baos.toByteArray();
	
		//�����ݷ��͵������
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
			//�������˷������Ŀͻ�����Ϣ������������ͻ��˵���Ϣ
			if(tc.myTank.id == id) {
				return;
			}
			
			int x = dis.readInt();
			int y = dis.readInt();
			Dir dir = Dir.values()[dis.readInt()];
			
			//������̹���ڱ��ͻ����д���
			boolean exist = false;//�Ƿ����
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
