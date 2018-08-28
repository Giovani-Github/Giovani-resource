package web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class EncodingFilter implements Filter {

	public void destroy() {
	}

	/*
	 * 处理全站编码问题
	 * */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//处理响应编码问题
		response.setContentType("text/html;charset=utf-8");
		HttpServletRequest req = (HttpServletRequest)request;
	
		
		if(req.getMethod().equalsIgnoreCase("POST")) {//处理post请求编码
			req.setCharacterEncoding("UTF-8");
			chain.doFilter(request, response);	
		} else if(req.getMethod().equalsIgnoreCase("GET")) {//处理get请求编码
			/*
			 * get请求处理编码原理是：
			 * 	String name = req.getParameter("username");获取属性值
				byte[] bytes = name.getBytes("ISO-8859-1");进行解码
				name = new String(bytes, "UTF-8");进行编码
				
				但是，我们在这里处理了编码后，在servlet中使用getParameter("username")获取到的还是没有处理编码的属性值
				所以，我们可以把处理编码的代码，放到getParameter("username")里面进行处理
				这时候我们写个装饰类EncodingRequest，装饰request，对他的getParameter("")方法进行增强
				然后，在放行的时候把request调包换成我们的这个EncodingRequest即可
			 * */
			EncodingRequest er = new EncodingRequest(req);//对request进行装饰
			chain.doFilter(er, response);//调包reqeust为我们的er
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}
}
