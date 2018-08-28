package servlet.test2;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


public class BServlet implements Servlet {

	/*
	 *�������ڷ�����tomcat�Զ�����
	 *������Serverlet������֮ǰ���� ������ֻ����һ��
	 * */
	@Override
	public void destroy() {
		System.out.println("destroy()....");
	}

	/*
	 * ����������ȡServlet��������Ϣ���Լ�����
	 * */
	@Override
	public ServletConfig getServletConfig() {
		System.out.println("getServletConfig()....");
		return null;
	}

	/*
	 * ��ȡServlet��Ϣ���Լ�����
	 * û��ʲô�õķ���
	 * */
	@Override
	public String getServletInfo() {
		System.out.println("getServletInfo()....");
		return "����һͷServlet";
	}

	/*
	 * �������ڷ�����tomcat�Զ�����
	 * ����Servlet���󴴽�֮������ִ�У�����ִֻ��һ�Σ�������֮��
	 * */
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init()....");
		/*
		 * ������ȡ��web.xml�����õĳ�ʼ��������p1��p2��ֵ
		 * */
		System.out.println(config.getInitParameter("p1"));
		System.out.println(config.getInitParameter("p2"));
		
		/*
		 * ������ȡ��web.xml�����õ����г�ʼ����������
		 * */
		
		Enumeration ration = config.getInitParameterNames();
		while(ration.hasMoreElements()) {
			System.out.println(ration.nextElement());
		}
		
	}

	/*
	 * �������ڷ���
	 * tomcat�Զ����ã��ᱻ���ö��
	 * ÿ�δ����������������
	 * */
	@Override
	public void service(ServletRequest arg0, ServletResponse arg1)
			throws ServletException, IOException {
		System.out.println("service()....");
	}
}
