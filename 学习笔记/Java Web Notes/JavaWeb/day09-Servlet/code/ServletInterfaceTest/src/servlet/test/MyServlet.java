package servlet.test;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class MyServlet implements Servlet {

	/*
	 *生命周期方法、tomcat自动调用
	 *它会在Serverlet被销毁之前调用 ，并且只调用一次
	 * */
	@Override
	public void destroy() {
		System.out.println("destroy()....");
	}

	/*
	 * 可以用来获取Servlet的配置信息，自己调用
	 * */
	@Override
	public ServletConfig getServletConfig() {
		System.out.println("getServletConfig()....");
		return null;
	}

	/*
	 * 获取Servlet信息，自己调用
	 * 没有什么用的方法
	 * */
	@Override
	public String getServletInfo() {
		System.out.println("getServletInfo()....");
		return "我是一头Servlet";
	}

	/*
	 * 生命周期方法、tomcat自动调用
	 * 会在Servlet对象创建之后马上执行，并且只执行一次！（出生之后）
	 * */
	@Override
	public void init(ServletConfig arg0) throws ServletException {
		System.out.println("init()....");
	}

	/*
	 * 生命周期方法
	 * tomcat自动调用，会被调用多次
	 * 每次处理请求都是这个方法
	 * */
	@Override
	public void service(ServletRequest arg0, ServletResponse arg1)
			throws ServletException, IOException {
		System.out.println("service()....");
	}

}
