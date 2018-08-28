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
		
		//生成图片，这张图片的字体颜色，字体样式等等，都是随机生成的
		BufferedImage bi = verifyCode.getImage();
		
		//打印出这张图片上的文本
		System.out.println(verifyCode.getText());
		
		//保存这张图片
		VerifyCode.output(bi, new FileOutputStream("E:/JAVA WEB笔记/day08/b.jpg"));
	}
}
