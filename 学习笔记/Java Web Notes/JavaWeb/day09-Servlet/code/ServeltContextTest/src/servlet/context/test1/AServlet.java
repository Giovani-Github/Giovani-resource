package servlet.context.test1;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
 * ��ȡServletContext
 * */
public class AServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//��һ�ַ�ʽ
		ServletContext context = this.getServletConfig().getServletContext();
		
		//�ڶ��з�ʽ
		ServletContext context1 = this.getServletContext();
	}

}
