package cn.itcast.user.web.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.commons.CommonUtils;
import cn.itcast.user.domain.User;
import cn.itcast.user.service.UserException;
import cn.itcast.user.service.UserService;

public class RegistServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//处理编码
		request.setCharacterEncoding("utf-8");//请求编码（post）
		response.setContentType("text/html;charset=utf-8");//响应编码
		//依赖UserServlice
		UserService userService = new UserService();
	
		/*
		 * 1.封装表单数据到User对象中
		 * 2.调用UserServcie的regist方法，传递user对象
		 * 	3.如果没有抛出异常，表示注册成功。向页面输出注册成功
		 * 	4.如果抛出异常，表示注册失败。获取异常信息，保存到request域中，转发回regist.jsp
		 *	
		 * */
		
		
		/*
		 * 1.封装表单数据到User对象中
		 * 	使用itcast-tools-1.4.2.jar中的CommonUtils.toBean()方法
		 * 	toBean这个方法我们曾经写过：在第12天，code\day12_2\src\tools\CommonUtils.java
		 */
		User form = CommonUtils.toBean(request.getParameterMap(), User.class);
		
		
		/*
		 * 添加新任务2：服务器端表单（输入）校验
		 * 	要校验的有两种：非空校验，长度校验
		 * 	1.创建一个map，使用map来装载错误信息
		 * 		key：表单项名称，例如：username、password、verifyCode
		 * 		value:错误信息
		 *	2.获取用户输入的用户名，进行校验
		 *		如发现不符合规定的条件,即校验为通过。
		 *		保存错误信息到map中，key为正在校验的用户名。
		 *		错误信息为：用户名不能为空，或者长度必须为3~15
		 *	3.获取用户输入的密码，进行校验
		 *	4.获取用胡输入的验证码，进行校验
		 *
		 *	5.判断map是否为空，和map长度是否为0
		 *		为0：表示没有错误发生
		 *		不为0：表示有错误，把map保存到reuqest域中，转发回regist.jsp
		 * */
		
		//1.创建一个map，使用map来装载错误信息
		Map<String, String> errors = new HashMap<String, String>();
		
		//2.获取用户输入的用户名，进行校验
		String username = form.getUsername();
		if(username == null || username.trim().isEmpty()) {
			errors.put("username", "用户名不能为空");
		} else if(username.length() < 3 || username.length() > 15) {
			errors.put("username", "用户名必须为：3~15个字符");
		}
		
		//3.获取用户输入的密码，进行校验
		String password = form.getPassword();
		if(password == null || password.trim().isEmpty()) {
			errors.put("password", "密码不能为空");
		} else if(password.length() < 3 || password.length() > 15) {
			errors.put("password", "密码必须为：3~15个字符");
		}
		
		//4.获取用胡输入的验证码，进行校验
		String verifyCode = form.getVerifyCode();//获取用户输入的验证码
		String sessionVerifyCode = (String) request.getSession().getAttribute("sessionVerifyCode");//获取正确的验证码
		if(verifyCode == null || verifyCode.trim().isEmpty()) {
			errors.put("verifyCode", "验证码不能为空");
		} else if(verifyCode.length() != 4) {
			errors.put("verifyCode", "验证码必须为4位字符");
		} else if(!verifyCode.equalsIgnoreCase(sessionVerifyCode)) {//校验用户输入的验证码与正确的验证码是否相同
			errors.put("verifyCode", "验证码输入错误");
		}
		
		//5.判断map是否为空，即：是否存在错误信息
		if(errors != null && errors.size() > 0) {
			/*
			 * 1.保存map到request域中，保存form到request域中(回显)
			 * 2.转发回regist.jsp
			 * */
			
			request.setAttribute("errors", errors);
			request.setAttribute("user", form);
			
			request.getRequestDispatcher("/users/regist.jsp").forward(request, response);
			
			return;
		}
		
		
		
//		/*
		
			//在添加了表单校验后，这个没有作用了
		
		
//		 * 添加新任务，验证码校验
//		 * 	用户填入的验证码信息，已经在上一步被保存到User对象中了
//		 * 	1.获取user对象中的验证码
//		 * 	2.获取真正的验证码信息，在session域中，名为sessionVerifyCode
//		 * 	3.比较两者：
//		 * 		如果不相同：保存错误信息，和用户输入的信息，到request域中，转发会regist.jsp
//		 * 		如果相同：继续向下执行原来的代码
//		 * */
//		
//		//1.获取user对象中的验证码
//		String uVerify = form.getVerifyCode();
//		//2.获取真正的验证码信息，在session域中，名为sessionVerifyCode
//		String sessionVerifyCode = (String) request.getSession().getAttribute("sessionVerifyCode");
//		//3.比较两者
//		if(!uVerify.equalsIgnoreCase(sessionVerifyCode)) {
//			//如果不相同：保存错误信息，和用户输入的信息，到request域中，转发会regist.jsp
//			request.setAttribute("msg", "验证码错误");
//			request.setAttribute("user", form);
//			
//			request.getRequestDispatcher("/users/regist.jsp").forward(request, response);
//			return;
//		}
		
		
		//2.调用UserServcie的regist方法，传递user对象
		try {
			userService.regist(form);
			
			//3.如果没有抛出异常，表示注册成功。向页面输出注册成功
			response.getWriter().print("<h1>注册成功!</h1>");
			response.getWriter().print("<a href='"+ request.getContextPath() + "/users/login.jsp" + "'>点击这里去登录</a>");
		} catch (UserException e) {
			//4.如果抛出异常，表示注册失败。获取异常信息，保存到request域中，转发回regist.jsp
			request.setAttribute("msg", e.getMessage());
			//还要把出错的数据保存到request域中，一并转发回去。然后在username和password输入框中，显示我们输入错误的数据。这叫回显
			request.setAttribute("user", form);
			
			request.getRequestDispatcher("/users/regist.jsp").forward(request, response);
		}
	}
}
