package test.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.domain.User;
import test.service.UserService;
/*
 * web层的servlet，处理jsp发出的请求，然后响应给jsp
 * 依赖业务层，通过业务层对象UserService来得到数据对象User，
 * 然后保存到request域中，转发给web层的show.jsp
 * */
public class UserServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		UserService uservice = new UserService();
		User user = uservice.find();
		request.setAttribute("user", user);
		
		request.getRequestDispatcher("/show.jsp").forward(request, response);
	}
}
