package image.text2;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;

import cn.itcast.vcode.utils.VerifyCode;

public class Image2 {
	@Test
	public void run() throws Exception, IOException {
		VerifyCode verifyCode = new VerifyCode();
		
		//����ͼƬ������ͼƬ��������ɫ��������ʽ�ȵȣ�����������ɵ�
		BufferedImage bi = verifyCode.getImage();
		
		//��ӡ������ͼƬ�ϵ��ı�
		System.out.println(verifyCode.getText());
		
		//��������ͼƬ
		VerifyCode.output(bi, new FileOutputStream("E:/JAVA WEB�ʼ�/day08/b.jpg"));
	}
}
