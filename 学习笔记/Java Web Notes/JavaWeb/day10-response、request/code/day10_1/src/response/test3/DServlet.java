package response.test3;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 定时刷新
 * 
 * 设置Refresh响应头，值为："秒;RUL=请求uri"
 * */
public class DServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*
		 * 设置Refresh
		 * */
		response.setHeader("Refresh", "5;URL=/day10_1/EServlet");
		
		/*
		 * 下面是响应体代码
		 * */
		PrintWriter writer = response.getWriter();
		writer.print("看到的是乱码，但是后期会解决这个问题");
	}
}
