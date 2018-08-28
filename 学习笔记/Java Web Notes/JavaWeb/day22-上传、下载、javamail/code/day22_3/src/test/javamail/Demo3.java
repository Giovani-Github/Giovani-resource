package test.javamail;

import java.io.File;

import javax.mail.Session;

import org.junit.Test;

import cn.itcast.mail.AttachBean;
import cn.itcast.mail.Mail;
import cn.itcast.mail.MailUtils;

public class Demo3 {
	@Test
	public void fun1() throws Exception {
		/*
		 * 1.得到session
		 * 使用MailUtils.createSession("邮件服务器主机名","用户名","密码");
		 * */
		Session session = MailUtils.createSession("smtp.163.com", 
				"15219331778", "1758678887.w");
		
		/*
		 * 2.创建邮件对象
		 * 	给出发送人，收件人（可以有多个，用","号隔开）, 邮件主题，邮件正文
		 * */
		Mail mail = new Mail("15219331778@163.com",
				"957366764@qq.com,1758678887@qq.com",
				"主题","正文");
		
		//设置附件
		//创建附件对象
		//给出文件，以及文件名称。乱码问题AttahBean已经处理
		AttachBean ab1 = new AttachBean(new File("e:/a.jpg"), "哈哈.jpg");
		AttachBean ab2 = new AttachBean(new File("e:/b.jpg"), "哈哈1.jpg");
		
		//附件添加到邮件对象中
		mail.addAttach(ab1);
		mail.addAttach(ab2);		
		
		/*
		 * 3.发送
		 * */
		MailUtils.send(session, mail);
	}	
}
