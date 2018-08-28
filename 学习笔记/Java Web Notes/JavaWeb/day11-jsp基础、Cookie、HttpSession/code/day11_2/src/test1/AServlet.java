package test1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//获取请求参数int1，int2，转换成int类型
		int int1 = Integer.parseInt(request.getParameter("int1"));
		int int2 = Integer.parseInt(request.getParameter("int2"));
		//运算
		int sum = int1 + int2;
		
		//运算结果保存到request域中，这里sum做了个自动装箱，变成integer
		request.setAttribute("sum", sum);
		
		//转发到result
		request.getRequestDispatcher("/text1/result.jsp").forward(request, response);
	}
}
