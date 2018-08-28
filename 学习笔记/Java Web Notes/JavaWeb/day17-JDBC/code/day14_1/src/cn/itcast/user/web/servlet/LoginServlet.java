package cn.itcast.user.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.commons.CommonUtils;
import cn.itcast.user.domain.User;
import cn.itcast.user.service.UserException;
import cn.itcast.user.service.UserService;

public class LoginServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//处理编码
		request.setCharacterEncoding("utf-8");//请求编码（post）
		response.setContentType("text/html;charset=utf-8");//响应编码
		
		//用来UserService
		UserService userService = new UserService();
		
		/*
		 * 1.获取表单数据，封装到User对象中 form
		 * 2.使用UserService的login(User user)方法，传递form过去
		 * 3.没异常：保存返回的user到sessin域中，重定向到welcome.jsp（ 显示当前用户信息）
		 * 	4.有异常：获取异常信息，保存到request域中，保存form到request域中，转发回login.jsp	
		 * */
		
		//1.获取表单数据，封装到User对象中 form
		User form = CommonUtils.toBean(request.getParameterMap(), User.class);
		// 2.使用UserService的login(User user)方法，传递form过去
		try {
			User user = userService.login(form);
			
			//3.没异常：保存返回的user到sessin域中
			request.getSession().setAttribute("user", user);
			//重定向到welcome.jsp（ 显示当前用户信息）
			response.sendRedirect(request.getContextPath() + "/users/welcome.jsp");
		} catch (UserException e) {
			//4.有异常：获取异常信息，保存到request域中，保存form到request域中，转发回login.jsp	
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("user", form);
			
			request.getRequestDispatcher("/users/login.jsp").forward(request, response);
		}
		
		
	}
}
