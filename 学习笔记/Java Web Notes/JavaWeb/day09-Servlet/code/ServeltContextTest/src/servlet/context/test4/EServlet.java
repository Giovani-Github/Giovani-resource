package servlet.context.test4;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//��ȡ��ʵ·��
		String path1 = this.getServletContext().getRealPath("/index.jsp");
		System.out.println(path1);
		
		//��ȡ��Դ����ԭ���ǣ���ȡ��Դ·�����ڴ�������ȡ������
		InputStream input = this.getServletContext().getResourceAsStream("/index.jsp");
		
		//��ȡָ��Ŀ¼��������Դ·��
		Set<String> set = this.getServletContext().getResourcePaths("/WEB-INF");
		System.out.println(set);
	}
}
