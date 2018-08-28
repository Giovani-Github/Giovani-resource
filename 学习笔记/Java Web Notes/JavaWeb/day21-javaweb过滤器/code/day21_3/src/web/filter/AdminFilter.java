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
 * Servlet Filter implementation class AdminFilter
 */
public class AdminFilter implements Filter {
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		/*
		 *	1.	获取session
			2.	通过session获取用户名
				a)	获取admin
					i.	如果不为null，表示管理员已经登录，放行，并且不再执行下面代码
					ii.	如果为null，表示管理员没有登录，保存错误信息到request域，打回到login.jspr

		 * */
		
		HttpServletRequest req = (HttpServletRequest)request;
		String name = (String) req.getSession().getAttribute("admin");
		if(name != null) {
			chain.doFilter(request, response);
			return;
		}
		
		req.setAttribute("msg", "不是管理员，不能访问，请先登录");
		req.getRequestDispatcher("/login.jsp").forward(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
