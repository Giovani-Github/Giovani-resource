package servlet.context.test6;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

/*
 * ��ʾ��ȡ��·���µ���Դ
 * ʹ��classLoader��ȡ
 * */
public class GServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		/*
		 * 1.�ȵõ�ClassLoader
		 * 	>�ȵõ�calss����ͨ��calss�õ�calssloader
		 * 2.��ʹ��CalssLoader��getResourceAsStream�������õ���ȡ������InputStream��
		 * 3.��ʹ�ö�ȡ�������ӡ��a.txt���������	
		 * 	> ʹ��commons-io-1.4.jar�����棬IOUtils�������toString������ȡa.txt���������
		 * 	
		 * */
		
		ClassLoader cl = this.getClass().getClassLoader();
//		//�����classes·��
//		InputStream input = cl.getResourceAsStream("a.txt");
		
		//�����classes·��
//		InputStream input = cl.getResourceAsStream("servlet/context/test6/b.txt");
	
		//�����classes·��
		InputStream input = cl.getResourceAsStream("../../index.jsp");
		
		String a = IOUtils.toString(input);
		System.out.println(a);
		
		
	}
}
