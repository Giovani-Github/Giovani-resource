package my.web.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Servlet Filter implementation class AFilter
 */
public class AFilter implements Filter {
	private FilterConfig fConfig;//用来获取ServletContext
	
	public void init(FilterConfig fConfig) throws ServletException {
		this.fConfig = fConfig;
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		/*
		 * 1.获取ServletContext
		 * 2.通过ServletContext获取map
		 * 3.获取用户ip
		 * 4.判断用户ip是否在map中存在
		 * 	5.如果存在：修改原来ip的次数，在原来的次数上+1
		 * 	6.如果不存在：在map中插入ip，并且值为1
		 * 7.放行
		 * */
		ServletContext application = fConfig.getServletContext();
		Map<String, Integer> map = (Map<String, Integer>)application.getAttribute("map");
		String ip = request.getRemoteAddr();
		
		if(map.containsKey(ip)) {//如果ip在map中存在，表示不是第一次访问，在原来的次数上加一
			int count = map.get(ip);
			map.put(ip, count+1);
		} else {//如果ip没有在map中存在，表示是第一次访问，再map中添加值：key：本ip， value：1
			map.put(ip, 1);
		}
		
		chain.doFilter(request, response);
	}

	public void destroy() {
	}

}
