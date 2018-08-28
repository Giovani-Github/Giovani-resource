package request.test2;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 防盗链
 * Referer这个请求头，里面有请求发出的地址
 * 如果请求是地址栏(Referer==null)或不是本机Localhost发出的，就发送404，或重定向到百度
 * */
public class BServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取Referer请求头
		String referer = request.getHeader("Referer");
		
		//只要请求不是本机发出的或地址栏发出的，就重定向到百度
		if(referer == null || referer.indexOf("localhost") == -1) {
			response.sendRedirect("http://www.baidu.com");
		} else { //想要执行到这个代码，那么请求就必须是本机且非地址栏发出的，我们需要在index.jsp中加个超链接，定位到这个Servlet
			response.getWriter().write("hello");
		}
	}
}
