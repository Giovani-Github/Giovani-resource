package request.test4;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 获取请求参数
 * 分为get和post两种
 * 需要在a.html中编写链接，和表单
 * */
public class DServlet extends HttpServlet {
	
	/*
	 * 获取get方式请求参数
	 * */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//获取单值请求参数值
		System.out.println("GET:xx=" + request.getParameter("xx"));
		System.out.println("GET:yy=" + request.getParameter("yy"));
	}

	/*
	 * 获取get方式请求参数
	 * */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//获取单值请求参数值
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//获取多值请求参数值
		String[] hobby = request.getParameterValues("hobby");
		System.out.println(username + "," + password + "," + Arrays.toString(hobby));
		
		//获取所有请求参数名称
		Enumeration<String> names = request.getParameterNames();
		while(names.hasMoreElements()) {
			System.out.println(names.nextElement());
		}
		
		//获取所有请求参数
		Map<String,String[]> map = request.getParameterMap();
		for(String name : map.keySet()) {
			String[] values = map.get(name);
			System.out.println(name + "=" + Arrays.toString(values));
		}
	}
}
