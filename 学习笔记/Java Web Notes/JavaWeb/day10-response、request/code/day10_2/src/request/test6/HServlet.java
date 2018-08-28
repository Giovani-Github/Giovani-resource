package request.test6;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 会与GSevlet一起完成一个客户端请求
 * 在GServlet里面设置的响应头和响应体都会被保留下来
 * */
public class HServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("HServlet...");
		
		//设置响应头
		response.setHeader("bb", "BB");
		//设置响应体
		response.getWriter().print("HServletResponse");
	}
}
