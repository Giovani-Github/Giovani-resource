import java.io.IOException;
import java.net.*;
import java.io.*;

public class ChatServer {
	
	public static void main(String[] args) {
		boolean serb = false;//��������������������
		
		try {
			ServerSocket ss = new ServerSocket(20142);		
			serb = true;//����������������Ϊtrue
			
			while(serb) {//������������һֱѭ��
				boolean socketb = false;//�����Ƿ��пͻ�������
				Socket s = ss.accept();
				socketb = true;//�ͻ�������ֻ������Ϊtrue
				
System.out.println("a client connected");

				DataInputStream br  = new DataInputStream(s.getInputStream());

				while(socketb) {//
					String line = br.readUTF();
					System.out.println(line);
				}
				br.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
