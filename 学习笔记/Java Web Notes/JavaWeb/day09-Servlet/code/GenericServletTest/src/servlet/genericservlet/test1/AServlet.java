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
 * 自己来简单模拟一个GenericServlet
 * */
public abstract class AServlet implements Servlet {
	//把ServletConfig通过init(ServletConfig arg0)方法保存起来，方便其他方法的使用
	private ServletConfig config;

	/*
	 * servlet死亡的时候回调用的方法，子类需要就覆盖，不需要就不用覆盖。tomcat自动调用
	 * */
	@Override
	public void destroy() {
		
	}

	/*
	 * 可以用来获取Servlet的配置信息，自己调用
	 * */
	@Override
	public ServletConfig getServletConfig() {
		return this.config;
	}

	/*
	 * 基本无用的方法，需要自己调用
	 * */
	@Override
	public String getServletInfo() {
		return ".....";
	}

	/*
	 * 在Servlet被构造之后立马被tomcat自动调用的方法，可以通过这个方法，把ServletConfig保存起来，方便使用
	 * 因为有一些在init(ServletConfig arg0)中无法实现，而需要让子类自己实现的操作，可以在覆盖本类中的init()方法，
	 * 然后在init(ServletConfig arg0)中调用init()方法
	 * 这样做是为了避免子类覆盖init(ServletConfig arg0)方法，导致其他需要依赖Config的方法无法使用
	 * 这样做相当于init()方法绑定在了init(ServletConfig arg0)中，就是init()方法必然会在init(ServletConfig arg0)调用后被第一个调用
	 * */
	@Override
	public void init(ServletConfig arg0) throws ServletException {
		//把ServletConfig保存起来
		this.config = arg0;
		init();
	}
	
	/*
	 * 本类中自己定义的init()方法，与init(ServletConfig arg0)方法关联在一起
	 * 实现一些无法在本类init(ServletConfig arg0)方法中完成的操作，让子类完成
	 * */
	public void init() {
		
	}

	/*
	 * Servlet每次被访问时都会被调用的方法，tomcat自动调用。
	 * 因为servlet每次被访问都会有请求
	 * 这些请求该如何做出响应需要我们自己来写，所以这个方法是抽象的，由子类自己实现
	 * */
	@Override
	public abstract void service(ServletRequest arg0, ServletResponse arg1)
			throws ServletException, IOException;
	
	//模拟一些config的方法，方便子类调用
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
