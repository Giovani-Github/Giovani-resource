package request.test5;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 请求转发：例如：一个项目，我完成不了，所以我就请帮手，让他帮我完成。而我不用工作，由别人代替我完成
 * 演示请求转发，留头不留体。
 * 在这里设置的响应头，会在转发后被留下来。但是响应体不会留下来
 * */
public class EServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("EServlet....");
		
		response.setHeader("aa", "AA");//设置响应头
		response.getWriter().print("ESevletResponse...");//设置响应体
		
		//如果设置的响应体内容太大，会抛异常
//		for(int i = 0; i < 1024 * 24 + 1; i++) {
//			response.getWriter().print("a");
//		}

		/*
		 * 可以通过request域来完成请求转发和请求包含中，几个servlet之间的数据传递
		 * 向request域中添加一个属性
		 * */
		request.setAttribute("username","zhangsan");
		
		//等同于调用FServlet的service()方法
		request.getRequestDispatcher("/FServlet").forward(request, response);//请求转发
	}
}
