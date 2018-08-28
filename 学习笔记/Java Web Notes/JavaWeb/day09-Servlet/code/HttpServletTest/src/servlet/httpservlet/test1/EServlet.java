package servlet.httpservlet.test1;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class EServlet extends HttpServlet {
	
	/*
	 * 要演示dopsot方法，需要对index.jsp进行修改(也可以对index.html进行修改，修改的方法一样)
	 * 然后在浏览器访问:http://localhost:8080/day09-HttpServletTest/index.html
	 * */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			
		System.out.println("doPost()....");
	}
}
