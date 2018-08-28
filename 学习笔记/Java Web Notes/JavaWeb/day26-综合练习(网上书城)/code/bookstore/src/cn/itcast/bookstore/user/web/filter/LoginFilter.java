package cn.itcast.bookstore.user.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.bookstore.user.domain.User;

/**
 * 过滤：
 * 	jsps/cart/*
 * 	jsps/order/*
 * 	CatrServlet
 * 	OrderSerlet
 * 即：购物车模块，和订单模块要进行过滤
 */
public class LoginFilter implements Filter {
	public void destroy() {
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		/*
		 * 从session中获取user
		 * 	如果为null（已经登录）：保存错误信息到request，转发到jsps/user/login.jsp
		 * 	不为null（没有登录）：放行
		 * */
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		User user = (User) req.getSession().getAttribute("user");
		if(user == null) {
			request.setAttribute("msg", "您还没有登录");
			request.getRequestDispatcher("/jsps/user/login.jsp").forward(req, res);
		} else {
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}
}
