package web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class json1Servlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String personJson = "{\"name\":\"zhangsan\", \"age\":18, \"sex\":\"male\"}";
		//发送给客户端
		response.getWriter().print(personJson);
		System.out.println(personJson);
	}
}
