package web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class UserFilter
 */
public class UserFilter implements Filter {
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		
		/*
		 * 	1.	获取session
			2.	通过session获取用户名
				a)	获取admin
					i.	如果不为null，表示管理员已经登录，放行，并且不再继续执行下面代码
					ii.	如果为null，获取user
				b)	获取user
					i.	如果不为null，表示普通会员已经登录，放行,并且不再继续执行下面代码
					ii.	如果为null，表示管理员和普通会员都没有登录，保存错误信息到request域，打回到login.jsp
		 * */
		
		HttpServletRequest req = (HttpServletRequest)request;
		String name = (String) req.getSession().getAttribute("admin");
		if(name != null) {
			chain.doFilter(request, response);
			return;
		}
		
		name = (String) req.getSession().getAttribute("user");
		if(name != null) {
			chain.doFilter(request, response);
			return;
		}
		
		req.setAttribute("msg", "不是普通会员，不能访问，请先登录");
		req.getRequestDispatcher("/login.jsp").forward(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
