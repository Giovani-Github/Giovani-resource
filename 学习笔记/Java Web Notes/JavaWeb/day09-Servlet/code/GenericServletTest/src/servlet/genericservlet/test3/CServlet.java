package servlet.genericservlet.test3;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CServlet extends GenericServlet {

	@Override
	public void service(ServletRequest arg0, ServletResponse arg1)
			throws ServletException, IOException {
		
		//通过这个servlet的配置参数的名称，来获取到配置参数的值
		System.out.println(getInitParameter("P1"));
		System.out.println(getInitParameter("p2"));
		
		//获取到这个servlet中所有配置参数的名称
		Enumeration<String> names = getInitParameterNames();
		while(names.hasMoreElements()) {
			System.out.println(names.nextElement());
		}
	}

}
