package request.test5;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * EServlet会把请求转发到这里，由这里完成响应体
 * */
public class FServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("FServlet..");
		
		//在EServlet中设置的响应头会被保留，在这里也可以继续设置响应头
		response.setHeader("dd","DD");		
		
		//因为FServlet中设置的响应体不会被保留，所以我们这里设置的响应体才会被客户端收到
		response.getWriter().print("FServletResponse");
		
		//获取EServlet中设置的request域属性
		String username = (String) request.getAttribute("username");
		System.out.println(username);
	}
}
