package servlet.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet.Base.BaseServlet;

/*
 * 测试BaseServlet
 * 我们只需要给出需要被请求处理的方法即可
 * 请求处理方法的签名必须与service相同，参数，以及声明的异常都相同！
 * */
public class AServlet extends BaseServlet {
	public String add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("add()...");
		
		return "r:/index.jsp";
	}
	
	public String delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("delete()...");
		
		return "/index.jsp";
	}
	
	public String enddd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("enddd()...");
		
		return "d:/index.jsp";
	}
}