package servlet.genericservlet.test2;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import servlet.genericservlet.test1.AServlet;

public class BServlet extends AServlet {

	@Override
	public void service(ServletRequest arg0, ServletResponse arg1)
			throws ServletException, IOException {

		//ͨ�����servlet�����ò��������ƣ�����ȡ�����ò�����ֵ
		System.out.println(getInitParameter("P1"));
		System.out.println(getInitParameter("p2"));
		
		//��ȡ�����servlet���������ò���������
		Enumeration<String> names = getInitParameterNames();
		while(names.hasMoreElements()) {
			System.out.println(names.nextElement());
		}
	}
}
