package servlet.Base;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseServlet extends HttpServlet{
	/***
	 * 1. 我们希望在一个Servlet中可以有多个请求处理方法！
	 * 2. 客户端发送请求时，必须多给出一个参数，用来说明要调用的方法
  		请求处理方法的签名必须与service相同，即返回值和参数，以及声明的异常都相同！
	 * 3. 客户端必须传递名为method的参数！
	 * 
	 * 他会根据请求中的method，来决定调用本类的哪个方法
	 * 例如：http://localhost:8080/demo1/xxx?method=add，表示调用add方法
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//处理编码问题
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		//获取method参数的值
		String methodName = request.getParameter("method");
		
		//当没有指定method的参数值时，抛出异常
		if(methodName == null || methodName.isEmpty()) {
			throw new RuntimeException("您没有指定要调用的方法是哪个: ..../xxx?method=您要调用的方法");
		}
		
		//======通过反射来调用method指定的方法======
		/*
		 * 得到方法名称，是否可通过反射来调用方法？
		 * 1. 得到方法名，通过方法名再得到Method类的对象！
		 *   * 需要得到Class，然后调用它的方法进行查询！得到Method
		 *   * 我们要查询的是当前类的方法，所以我们需要得到当前类的Class
		 */
		
		//获取本类的Class对象
		Class clazz = this.getClass();
		
		//得到methodName所表示的方法的对象
		Method method = null;
		try {
			method = clazz.getMethod(methodName, 
					HttpServletRequest.class, HttpServletResponse.class);
		} catch (Exception e) {
			throw new RuntimeException("您要调用的方法: " + methodName + "(HttpServletRequest, HttpServletResponse)不存在");
		}
		
		//调用method所表示的方法
		try {
			String result = (String)method.invoke(this, request, response);
			/*
			 * 获取请求处理方法执行后返回的字符串，它表示转发或重定向的路径！
			 * 帮它完成转发或重定向！
			 */
			/*
			 * 如果用户返回的是字符串为null，或为""，那么我们什么也不做！
			 */
			if(result == null || result.trim().isEmpty()) {
				return;
			}
			
			/*
			 * 查看返回的字符串中是否包含冒号，如果没有，表示转发
			 * 如果有，使用冒号分割字符串，得到前缀和后缀！
			 * 其中前缀如果是f，表示转发，如果是r表示重定向，后缀就是要转发或重定向的路径了！
			 */
			if(result.contains(":")) {
				//使用冒号分割字符串，得到前缀和后缀
				int index = result.indexOf(":");//冒号所在的位置
				String s = result.substring(0, index);//截取出前缀，表示要操作的是转发还是重定向
				String path = result.substring(index + 1);//截取出后缀，表示路径
				
				if(s.equalsIgnoreCase("r")) {//如果前缀是r，表示重定向
					response.sendRedirect(request.getContextPath() + path);
				} else if(s.equalsIgnoreCase("f")) {//去过前缀是f，表示转发
					request.getRequestDispatcher(path).forward(request, response);
				} else {
					throw new RuntimeException("你指定的操作：" + s + "，当前版本还不支持！");
				}
				
			} else {//没有冒号，默认为转发！
				request.getRequestDispatcher(result).forward(request, response);
			}
		} catch (Exception e) {
			System.out.println("您调用的方法 ：" + methodName + "，内部抛出了异常");
			throw new RuntimeException(e);
		}
	}
}
