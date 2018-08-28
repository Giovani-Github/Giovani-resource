package request.test6;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 请求包含：例如：一个项目，我一个人完成不了，需要别人帮忙，才能完成。由几个人一起完成这个项目
 * 演示请求包含，留头又留体
 * 在这里设置的响应头和响应体都会在调用请求包含方法后被保留下来，
 * 在另一个servlet中可以继续设置响应头和响应体
 * */
public class GServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("GServlet...");
		
		//设置响应头
		response.setHeader("aa", "AA");
		//设置响应体
		response.getWriter().print("GServletResponse");
		
		//在这里设置过大的响应体也是可以的，不会抛异常
		for(int i = 0; i < 1024 * 24 + 1; i++) {
			response.getWriter().print("a");
		}
		
		request.getRequestDispatcher("/HServlet").include(request, response);
	}
}
