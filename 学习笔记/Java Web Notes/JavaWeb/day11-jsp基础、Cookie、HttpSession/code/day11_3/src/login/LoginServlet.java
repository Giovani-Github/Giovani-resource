package login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * 1.获取表单数据
 * 2.校验用户名和密码是否正确（我们这么做：用户名只要不是itcast，用户信息就正确）
 * 3.用户信息正确（成功）：保存用户信息到session域中，重定向到succ1.jsp
 * 4.用户信息不正确（失败）：保存错误信息到request域中，转发回login.jsp
 * 
 * 
 * 附加项：在login.jsp中的用户名文本框中，显示上次登录的用户名
 *	1.创建一个cookie，cookie中保存用户名
 *	2.设置cookie的生命为一天
 *	3.把cookie添加给客户端 	 	
 * */
public class LoginServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//处理乱码问题
		request.setCharacterEncoding("utf-8");
		
		/*
		 * 检查验证码是否正确
		 * 正确继续向下执行
		 * 不正确，在reqeust域中存入错误信息，转发回index.jsp
		 * */
		
		//获取登录页面输入的验证码
		String imgcheck = request.getParameter("imgcheck");
		//获取VerifyServlet中存入session的正确验证码信息
		String imgtext = (String)request.getSession().getAttribute("imgtext");
		if(!imgcheck.equalsIgnoreCase(imgtext)) {//不正确
			request.setAttribute("error", "验证码错误");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
		
		//获取表单数据username
		String username = request.getParameter("username");
		

		
		//校验用户信息是否正确(用户名只要不是itcast，用户信息就正确)
		if(!username.equals("itcast")) {//登录成功
			//附加项
			Cookie cookie = new Cookie("uname",username);//创建一个cookie，cookie中保存用户名
			cookie.setMaxAge(60*60*24);//设置cookie的生命为一天
			response.addCookie(cookie);//把cookie添加给客户端 
			
			//获取session对象
			HttpSession session = request.getSession();
			//把用户信息保存到session域中
			session.setAttribute("username", username);
			//重定向到succ1.jsp
			response.sendRedirect("/day11_3/xx/succ1.jsp");
		} else {//登录失败
			//保存错误信息到request域中
			request.setAttribute("error", "用户名错误");
			//转发回index.jsp
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}
}
