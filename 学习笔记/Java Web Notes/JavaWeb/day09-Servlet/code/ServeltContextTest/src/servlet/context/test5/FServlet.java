package servlet.context.test5;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * �������ۼ�
 * */
public class FServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		/*
		 * 1.���Ȼ�ȡservletCongtext
		 * 2.�ڻ�ȡservletContext�е�cont����
		 * 	3.���û��������Ա�ʾ��վ��û�����ʣ��ʹ���һ��count����ֵΪ1
		 * 	4.�����������ԣ���count��1 
		 * */
		
		ServletContext application = this.getServletContext();
		Integer count = (Integer)application.getAttribute("count");
		
		if(count == null) {
			count = 1;
		} else {
			count = count+1;
		}
		
		/*
		 * ��������������Ҫ�õ���Ӧ����
		 * */
		PrintWriter pw = response.getWriter();
		pw.print("<h1>" + count + "</h1>");
		
		application.setAttribute("count", count);
	}
}
