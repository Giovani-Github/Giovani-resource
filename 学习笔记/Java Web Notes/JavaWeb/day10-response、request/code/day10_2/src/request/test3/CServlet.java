package request.test3;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 获取请求路径(RUL)相关的方法
 * */
public class CServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.getWriter().print(request.getScheme() + "\n");//获取协议
		response.getWriter().print(request.getServerName() + "\n");//获取服务器名
		response.getWriter().print(request.getServerPort() + "\n");//获取服务器端口
		response.getWriter().print(request.getContextPath() + "\n");//获取项目名
		response.getWriter().print(request.getServletPath() + "\n");//获取Servlet路径
		response.getWriter().print(request.getQueryString() + "\n");///获取参数部分，即问号后面的部分。如果没有问号就是null
		response.getWriter().print(request.getRequestURI() + "\n");//获取请求URI
		response.getWriter().print(request.getRequestURL() + "\n");//获取请求URL
	}
}
