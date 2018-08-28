package test1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.Request;

/*
 * 在客户端访问index.html
 * 
 * 演示请求编码：分为get和post
 * 两个方式的编码是不一样的
 * */
public class AServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		/*
		 * 1.先获取参数
		 * 2.再使用iso-8859-1编码，来变成byte数组
		 * 3.再使用utr-8的形式，构造成字符串
		 * */
		
		String name = request.getParameter("username");
		byte[] b = name.getBytes("iso-8859-1");
		name = new String(b,"utf-8");
		
		System.out.println(name);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		/*
		 * 1.先调用request.setCharacterEncoding("utf-8")，设置request的编码为uft-8
		 * 2.再获取参数
		 * */
		
		req.setCharacterEncoding("utf-8");
		String name = req.getParameter("username");
		System.out.println(name);
		
	}
}
