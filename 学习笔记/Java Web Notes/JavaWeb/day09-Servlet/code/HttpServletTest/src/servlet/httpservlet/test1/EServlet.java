package servlet.httpservlet.test1;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class EServlet extends HttpServlet {
	
	/*
	 * Ҫ��ʾdopsot��������Ҫ��index.jsp�����޸�(Ҳ���Զ�index.html�����޸ģ��޸ĵķ���һ��)
	 * Ȼ�������������:http://localhost:8080/day09-HttpServletTest/index.html
	 * */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			
		System.out.println("doPost()....");
	}
}
