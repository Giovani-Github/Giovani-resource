package response.test3;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 五秒过后，重定向到此Servlet
 * */
public class EServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
		/*
		 * 下面是响应体
		 * */
		
		PrintWriter writer = response.getWriter();
		writer.print("还是乱码，后期解决");
	}
}
