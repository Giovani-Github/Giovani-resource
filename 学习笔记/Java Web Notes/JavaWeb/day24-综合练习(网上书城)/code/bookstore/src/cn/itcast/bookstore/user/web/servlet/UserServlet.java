package cn.itcast.bookstore.user.web.servlet;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.bookstore.user.domain.User;
import cn.itcast.bookstore.user.service.UserException;
import cn.itcast.bookstore.user.service.UserService;
import cn.itcast.commons.CommonUtils;
import cn.itcast.mail.Mail;
import cn.itcast.mail.MailUtils;
import cn.itcast.servlet.BaseServlet;

/*
 * User表述层
 * */
public class UserServlet extends BaseServlet {
	//依赖业务层
	private UserService userService = new UserService();
	
	//退出
	public String quit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//把session销毁
		request.getSession().invalidate();
		return "r:/index.jsp";
	}
	
	//登录
	public String login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1.一建封装表单数据(用户名，密码)
		 * 2.输入校验(略)
		 * 3.调用service的方法完成登录
		 * 	> 保存异常信息到request，保存form到request，转发回login.jsp
		 * 4.保存用户信息到session中
		 * 5.重定向到index.jsp
		 * */
		User form = CommonUtils.toBean(request.getParameterMap(), User.class);
		try {
			User user = userService.login(form);
			request.getSession().setAttribute("user", user);
			return "r:/index.jsp";
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("form", form);
			return "f:/jsps/user/login.jsp";
		}
		
	}
	
	//激活
	public String active(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1.获取激活码
		 * 2.使用激活码来调用service的active方法
		 * 	如果抛出异常，表示激活失败
		 * 	保存异常信息request，转发到msg.jsp
		 * 3.保存成功信息到reuquest，转发到msg.jsp
		 * */
		String code = request.getParameter("code");
		try {
			userService.active(code);
			request.setAttribute("msg", "恭喜，激活成功，可以立即登录！");
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
		}
		
		return "f:/jsps/msg.jsp";
	}
	
	//注册
	public String regist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1.封装表单数据到form
		 * 2.补全：uid、code（激活码）
		 * 3.输入校验
		 * 	> 保存错误信息到request
		 * 	> 保存form到request（回显）
		 * 	> 转发回regist.jsp
		 * 4.调用sevice的注册方法，进行注册
		 * 	> 保存异常信息到request
		 * 	> 保存form到request（回显）
		 * 	> 转发回regist.jsp
		 * 5.发送邮件
		 * 6.保存成功信息到request
		 * 7.转发到msg.jsp
		 * */
		User form = CommonUtils.toBean(request.getParameterMap(), User.class);
		form.setUid(CommonUtils.uuid());
		form.setCode(CommonUtils.uuid()+ CommonUtils.uuid());
		
		//输入校验
		Map<String, String> errors = inputVerify(form);
		//判断error中是否有错误信息，如果有，保存到request中，保存form到request、转发回regist.jsp
		if(errors.size() > 0) {
			request.setAttribute("errors", errors);
			request.setAttribute("form", form);
			return "f:/jsps/user/regist.jsp";
		}
		
		//调用service注册
		try {
			userService.regist(form);
		} catch (UserException e) {
			//如果注册抛出了异常
			//保存异常信息到request，保存form到request、转发回regist.jsp
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("form", form);
			return "f:/jsps/user/regist.jsp";
		}
		
		//发邮件
		sendMail(form.getEmail(), form.getCode());
		//保存成功信息到request
		request.setAttribute("msg", "注册成功，请到邮箱进行激活");
		//转发到msg.jsp
		return "f:/jsps/msg.jsp";
	}
	
	/**
	 * 发送邮件
	 * @param receiver 收件人
	 * @param activaCode 激活码
	 * @throws IOException
	 */
	private void sendMail(String receiver, String activaCode) throws IOException{
		/*
		 * 1.准备配置文件
		 * 2.得到配置文件
		 * 3.得到邮件主机、用户名、密码、发件人、主题、内容
		 * 4.内容是一个超链接，发送请求到激活方法，但是需要上激活码参数
		 * 	激活码在这里获得，然后设置进去
		 * 	配置文件中有一个站位符{0}, 和jdbc的sql语句中的?相同
		 * */
		//得到配置文件
		Properties props = new Properties();
		props.load(this.getClass().getClassLoader().
				getResourceAsStream("/email_template.properties"));
		//得到配置文件内容
		String host = props.getProperty("host");//邮件主机
		String uname = props.getProperty("uname");//用户名
		String pwd = props.getProperty("pwd");//密码
		String form = props.getProperty("form");//发件人
		String to = receiver;//收件人
		String subject = props.getProperty("subject");//主题
		String content = props.getProperty("content");//内容
		//设置内容，给占位符{0}赋值
		content = MessageFormat.format(content, activaCode);
		
		//得到sessio
		Session session = MailUtils.createSession(host, uname, pwd);
		//给出邮件对象
		Mail mail = new Mail(form, to, subject, content);
		//发送
		try {
			MailUtils.send(session, mail);
		} catch (MessagingException e) {

		}
	}
	
	//对输入进行校验
	private Map<String, String> inputVerify(User form) {
		/*
		 * 1.创建map，用来保存错误信息。key为字段名，value为错误信息
		 * 2.获取用户名、密码、email，进行校验
		 * 3.如果有错误，保存错误信息到map中
		 * */
		Map<String, String> errors = new HashMap<String, String>();
		
		String username = form.getUsername();
		String password = form.getPassword();
		String email = form.getEmail();
		
		if(username == null || username.trim().isEmpty()) {
			errors.put("username", "用户名不能为空");
		} else if(username.length() < 3 || username.length() > 10) {
			errors.put("username", "用户名长度必须在3-10之间");
		}
		
		if(password == null || password.trim().isEmpty()) {
			errors.put("password", "密码不能为空");
		} else if(password.length() < 3 || password.length() > 10) {
			errors.put("password", "密码长度必须在3-10之间");
		}
		
		if(email == null || email.trim().isEmpty()) {
			errors.put("email", "邮箱不能为空");
		} else if(!email.matches("\\w+@\\w+(\\.\\w+)+")) {
			errors.put("email", "邮箱格式错误");
		}
		
		return errors;
	}
}
