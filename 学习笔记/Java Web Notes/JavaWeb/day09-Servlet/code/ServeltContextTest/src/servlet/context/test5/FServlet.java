package servlet.context.test5;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 访问量累计
 * */
public class FServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		/*
		 * 1.首先获取servletCongtext
		 * 2.在获取servletContext中的cont属性
		 * 	3.如果没有这个属性表示本站还没被访问，就创建一个count并赋值为1
		 * 	4.如果有这个属性，就count加1 
		 * */
		
		ServletContext application = this.getServletContext();
		Integer count = (Integer)application.getAttribute("count");
		
		if(count == null) {
			count = 1;
		} else {
			count = count+1;
		}
		
		/*
		 * 向浏览器输出，需要用到响应对象
		 * */
		PrintWriter pw = response.getWriter();
		pw.print("<h1>" + count + "</h1>");
		
		application.setAttribute("count", count);
	}
}
