package image.text1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Image1 {
	public static void main(String[] args) throws Exception, IOException {
		//�õ�ͼƬ�ɷ��ʻ�������ͼƬ
		BufferedImage bi = new BufferedImage(70,35,BufferedImage.TYPE_INT_RGB);
		
		//�õ����ƻ���(�õ�����ͼƬ�Ļ���)
		Graphics2D g2 = (Graphics2D)bi.getGraphics();
		
		//���û�����ɫ
		g2.setColor(Color.blue);
		//�������ͼƬ����ʵ�������ñ�����ɫ��
		g2.fillRect(0, 0, 70, 35);
		
		//���û�������
		g2.setFont(new Font("����",Font.BOLD, 20));
		//���û�����ɫ
		g2.setColor(Color.red);
		
		//��ͼƬ�ϻ��ַ���
		g2.drawString("abc", 15, 10);
		
		//����ͼƬ
		ImageIO.write(bi, "JPEG", new FileOutputStream("E:/JAVA WEB�ʼ�/day08/a.jpg"));
		
	}
}
