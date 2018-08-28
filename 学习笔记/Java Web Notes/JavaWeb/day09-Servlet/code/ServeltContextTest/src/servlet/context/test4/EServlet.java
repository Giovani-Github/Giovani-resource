package servlet.context.test4;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//获取真实路径
		String path1 = this.getServletContext().getRealPath("/index.jsp");
		System.out.println(path1);
		
		//获取资源流，原理是：获取资源路径后，在创建出读取流对象
		InputStream input = this.getServletContext().getResourceAsStream("/index.jsp");
		
		//获取指定目录下所有资源路径
		Set<String> set = this.getServletContext().getResourcePaths("/WEB-INF");
		System.out.println(set);
	}
}
