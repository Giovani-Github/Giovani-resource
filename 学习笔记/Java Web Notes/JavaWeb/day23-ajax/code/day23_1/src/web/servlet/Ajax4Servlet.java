package web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Ajax4Servlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String xml = "<students>" +
				"<student number='01'>" +
				"<name>zhangsna</name>" +
				"<age>18</age>" +
				"</student>" +
				"</students>";
		//设置响应头：ContentType，其值为：text/xml;charset=utf-8
		response.setContentType("text/xml;cahrset=utf-8");
		//把xml响应给客户端
		response.getWriter().print(xml);
	}
}
