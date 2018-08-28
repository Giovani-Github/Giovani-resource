package servlet.genericservlet.test1;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/*
 * �Լ�����ģ��һ��GenericServlet
 * */
public abstract class AServlet implements Servlet {
	//��ServletConfigͨ��init(ServletConfig arg0)����������������������������ʹ��
	private ServletConfig config;

	/*
	 * servlet������ʱ��ص��õķ�����������Ҫ�͸��ǣ�����Ҫ�Ͳ��ø��ǡ�tomcat�Զ�����
	 * */
	@Override
	public void destroy() {
		
	}

	/*
	 * ����������ȡServlet��������Ϣ���Լ�����
	 * */
	@Override
	public ServletConfig getServletConfig() {
		return this.config;
	}

	/*
	 * �������õķ�������Ҫ�Լ�����
	 * */
	@Override
	public String getServletInfo() {
		return ".....";
	}

	/*
	 * ��Servlet������֮������tomcat�Զ����õķ���������ͨ�������������ServletConfig��������������ʹ��
	 * ��Ϊ��һЩ��init(ServletConfig arg0)���޷�ʵ�֣�����Ҫ�������Լ�ʵ�ֵĲ����������ڸ��Ǳ����е�init()������
	 * Ȼ����init(ServletConfig arg0)�е���init()����
	 * ��������Ϊ�˱������า��init(ServletConfig arg0)����������������Ҫ����Config�ķ����޷�ʹ��
	 * �������൱��init()����������init(ServletConfig arg0)�У�����init()������Ȼ����init(ServletConfig arg0)���ú󱻵�һ������
	 * */
	@Override
	public void init(ServletConfig arg0) throws ServletException {
		//��ServletConfig��������
		this.config = arg0;
		init();
	}
	
	/*
	 * �������Լ������init()��������init(ServletConfig arg0)����������һ��
	 * ʵ��һЩ�޷��ڱ���init(ServletConfig arg0)��������ɵĲ��������������
	 * */
	public void init() {
		
	}

	/*
	 * Servletÿ�α�����ʱ���ᱻ���õķ�����tomcat�Զ����á�
	 * ��Ϊservletÿ�α����ʶ���������
	 * ��Щ��������������Ӧ��Ҫ�����Լ���д��������������ǳ���ģ��������Լ�ʵ��
	 * */
	@Override
	public abstract void service(ServletRequest arg0, ServletResponse arg1)
			throws ServletException, IOException;
	
	//ģ��һЩconfig�ķ����������������
	public String getInitParameter(String name) {
		return config.getInitParameter(name);
	}
	
	public Enumeration getInitParameterNames() {
		return config.getInitParameterNames();
	}
	
	public ServletContext getServletContext() {
		return config.getServletContext();
	}
	
	public String getServletName() {
		return config.getServletName();
	}

}
