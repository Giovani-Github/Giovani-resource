package test.javamail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.junit.Test;

public class Demo1 {
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
		msg.setRecipients(RecipientType.CC, "957366764@qq.com");//设置抄送
		msg.setRecipients(RecipientType.BCC, "2867858456@qq.com");//设置暗送
		
		msg.setSubject("这是测试邮件");//设置邮件主题
		msg.setContent("我来啦", "text/html;charset=utf-8");//设置邮件内容，并且mime类型时文本
		
		//3.得到TransPort，发送邮件
		Transport.send(msg);
	}	
}
