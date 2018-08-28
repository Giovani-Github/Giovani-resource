package servlet.test;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class MyServlet implements Servlet {

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
	public void init(ServletConfig arg0) throws ServletException {
		System.out.println("init()....");
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
