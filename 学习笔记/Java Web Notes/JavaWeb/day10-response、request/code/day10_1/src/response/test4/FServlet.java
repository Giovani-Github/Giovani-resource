package response.test4;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 禁用浏览器缓存，使用Cache-Control、pragma、expires头
 * */
public class FServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("pargma", "no-cache");
		response.setDateHeader("expires", -1);
	}
}
