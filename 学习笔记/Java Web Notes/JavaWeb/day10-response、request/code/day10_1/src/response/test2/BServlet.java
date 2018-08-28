package response.test2;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/*
 * 演示重定向
 * 
 * 浏览器请求BServlet，BServlet处理不了，响应302，并且设置Location响应头
 * */
public class BServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("BServlet");
		
		/*
		 * 重定向：
		 * 1.发送响应码302
		 * 2.发送响应头：Location	
		 * 	值为："/项目名/重定向到的Servlet的路径名，即：web.xml里配置的serlvet路径"，这个值统称为“请求uri”
		 * */
		
		response.setStatus(302);
		response.setHeader("Location", "/day10_1/CServlet");
	}
}
