package filter.test;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class AFilter implements Filter {
	/**
	 * 创建之后，马上执行；Filter会在服务器启动时就创建！
	 */
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("Filter被创建");
	}
	
	/**
	 * 每次过滤时都会执行
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("拦截");
		//放行，相当于调用了一次目标Servlet的service()方法
		chain.doFilter(request, response);
		System.out.println("回来继续拦截");
	}
	
	/**
	 * 销毁之前执行！在服务器关闭时销毁
	 */
	public void destroy() {
		System.out.println("Filter被销毁");
	}
}
