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

public class HServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*
		 * 1.�ȵõ�Class
		 * 2.��ʹ��Calss��getResourceAsStream�������õ���ȡ������InputStream��
		 * 3.��ʹ�ö�ȡ�������ӡ��a.txt���������	
		 * 	> ʹ��commons-io-1.4.jar�����棬IOUtils�������toString������ȡa.txt���������
		 * */
		
		Class c = this.getClass();
		
		//�ӡ�/�����������classes·����
		//InputStream input = c.getResourceAsStream("/a.txt");
		
		//û�ӡ�/����������ڵ�ǰclass�ļ���·����
		InputStream input = c.getResourceAsStream("../../../a.txt");
		
		String a = IOUtils.toString(input);
		System.out.println(a);
	}
}
