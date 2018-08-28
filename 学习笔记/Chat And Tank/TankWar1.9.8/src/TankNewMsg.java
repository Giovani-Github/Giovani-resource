import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * ̹�˵�������Ϣ��
 * @author ������
 *
 */
public class TankNewMsg implements Msg {
	//���Ϊ̹�˵�����Ϣ��
	int msgType = Msg.TANK_NEW_MSG;
	
	Tank tank;
	TankClient tc;
	
	public TankNewMsg(Tank tank) {
		this.tank = tank;
	}
	
	public TankNewMsg(TankClient tc) {
		this.tc = tc;
	}

	//��udp���������ݸ��������ķ���
	public void send(DatagramSocket ds, String ip, int udpPort) {
		//����Ҫ��һ���洢���ݵ��ֽ����飬������ֽ�����д�������棬�������װ��һ���ֽ�����
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		
		//������д������
		try {
			dos.writeInt(msgType);
			dos.writeInt(tank.id);
			dos.writeInt(tank.x);
			dos.writeInt(tank.y);
			dos.writeInt(tank.dir.ordinal());
			dos.writeBoolean(tank.good);
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

	////���������upd������������
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
			boolean good = dis.readBoolean();
			
			boolean exist = false;
			for(int i=0; i<tc.tanks.size(); i++) {
				Tank tank = tc.tanks.get(i);
					exist = true;
					break;
			}
			
			//�������̹��������ͻ����в�����
			if(!exist) {
				
				//���Լ�����ս̹�˷��͸��¼ӽ����Ŀͻ���
				TankNewMsg tnMsg = new TankNewMsg(tc.myTank);
				tc.nc.send(tnMsg);
				
				//������ͻ����л�������ͻ���̹��
				Tank tank = new Tank(x, y, good, dir, tc);
				tank.id = id;
				tc.tanks.add(tank);
			}
			
System.out.println("x:"+x + " y:"+y + " id:"+id + " dir:"+dir + " good:"+good);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
