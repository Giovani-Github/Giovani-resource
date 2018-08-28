package servlet.context.test1;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
 * 获取ServletContext
 * */
public class AServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//第一种方式
		ServletContext context = this.getServletConfig().getServletContext();
		
		//第二中方式
		ServletContext context1 = this.getServletContext();
	}

}
