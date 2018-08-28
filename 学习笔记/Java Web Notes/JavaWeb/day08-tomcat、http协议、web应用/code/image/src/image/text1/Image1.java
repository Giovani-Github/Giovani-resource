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
		//得到图片可访问缓冲区的图片
		BufferedImage bi = new BufferedImage(70,35,BufferedImage.TYPE_INT_RGB);
		
		//得到绘制环境(得到这张图片的画笔)
		Graphics2D g2 = (Graphics2D)bi.getGraphics();
		
		//设置画笔颜色
		g2.setColor(Color.blue);
		//填充整张图片（其实就是设置背景颜色）
		g2.fillRect(0, 0, 70, 35);
		
		//设置画笔字体
		g2.setFont(new Font("楷体",Font.BOLD, 20));
		//设置画笔颜色
		g2.setColor(Color.red);
		
		//向图片上画字符串
		g2.drawString("abc", 15, 10);
		
		//保存图片
		ImageIO.write(bi, "JPEG", new FileOutputStream("E:/JAVA WEB笔记/day08/a.jpg"));
		
	}
}
