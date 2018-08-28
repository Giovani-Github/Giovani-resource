package web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 	1.	获取用户名
			2.	判断用户名是否包含itcast
				a)	如果包含，就是管理员，设置到session的时候，名为admin，值为用户名
				b)	如果不包含，就是普通会员，设置到session的时候，名为user，值为用户名
			3.	把用户名保存到session中
			4.	转发回index.jsp
		 * */
		
		String username = request.getParameter("username");
		
		if(username.contains("itcast")) {
			request.getSession().setAttribute("admin", username);
		} else {
			request.getSession().setAttribute("user", username);
		}
		
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

}
