package request.test1;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 通过User-Agent请求头，识别浏览器类型
 * */
public class AServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//获取客户端ip
		response.getWriter().write(request.getRemoteAddr());
		//获取客户端请求方式
		response.getWriter().write(request.getMethod());
		//获取Uset-Agent请求头
		String agent = request.getHeader("User-Agent");
		
		System.out.println(agent);
		
		//忽略大小写，是否包含Chrome(谷歌浏览器)
		if(agent.toLowerCase().indexOf("chrome") != -1) {
			 System.out.println("使用的是谷歌");					
		} else if(agent.toLowerCase().indexOf("firefox") != -1) {
			System.out.println("使用的是火狐");
		} else {
			System.out.println("使用的是其他");
		}
	
	}
}
