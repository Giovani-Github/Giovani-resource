package test.javamail;

import java.io.File;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.junit.Test;

public class Demo2 {
	@Test
	public void fun1() throws Exception, MessagingException {
		//得到sessin，需要两个参数Properties和Authenticator
		//得到Properties
		Properties props = new Properties();
		props.setProperty("mail.host", "smtp.163.com");//设置服务器主机名
		props.setProperty("mail.smtp.auth", "true");//设置需要认证
		
		//得到Authenticator
		//Authenticator是一个抽象类，要实现它的抽象方法getPasswordAuthentication()
		//写一个类继承Authenticator，事项它的方法getPasswordAuthentication()
		//这里我们使用内部类的形式实现
		Authenticator auth = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				//返回一个PasswordAuthentication，包含用户名和密码
				return new PasswordAuthentication("15219331778", "1758678887.w");
			}
		};
		
		//1.得到session
		Session session = Session.getInstance(props, auth);
		
		//2.得到MimeMessage，表示一个邮件对象
		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress("15219331778@163.com"));//设置发件人
		msg.setRecipients(RecipientType.TO, "1758678887@qq.com");//设置收件人
		
		msg.setSubject("这是测试邮件-带附件");//设置邮件主题

		
		/////////////////////////////////////////////////////////////////////////
		/*
		 * 当发送包含附件的邮件时，邮件体就为多部件形式！
		 * 1. 创建一个多部件的部件内容！MimeMultipart
		 *   MimeMultipart就是一个集合，用来装载多个主体部件！
		 * 2. 我们需要创建两个主体部件，一个是文本内容的，另一个是附件的。
		 *   主体部件叫MimeBodyPart
		 * 3. 把MimeMultipart设置给MimeMessage的内容！
		 */
		
		//创建邮件内容，包含多个部分的内容部件。文本部件，和附件部件
		MimeMultipart list = new MimeMultipart();
		
		//创建文本部件，添加到邮件内容中
		MimeBodyPart part1 = new MimeBodyPart();
		//设置文本部件的内容
		part1.setContent("这是一个风附件", "text/html;charset=utf-8");
		//把文本部件添加到邮件内容中
		list.addBodyPart(part1);
		
		//创建附件部件，添加到邮件内容中
		MimeBodyPart part2 = new MimeBodyPart();
		//设置附件部件所带的文件
		part2.attachFile(new File("e:/a.jpg"));
		String filename = MimeUtility.encodeText("张三.jpg");//处理乱码
		part2.setFileName(filename);//设置显示的文件名称
		//添加到邮件内容中
		list.addBodyPart(part2);
		
		//把邮件内容设置到邮件中
		msg.setContent(list);
		/////////////////////////////////////////////////////////////////////////

		//3.得到TransPort，发送邮件
		Transport.send(msg);
	}	
}
