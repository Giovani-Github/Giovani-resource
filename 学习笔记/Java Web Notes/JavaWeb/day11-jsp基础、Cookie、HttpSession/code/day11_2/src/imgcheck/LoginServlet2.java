package imgcheck;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * > 获取用户输入的验证码，请求参数！！！
    > 获取session中保存的真正的验证码文本，这是在打开login.jsp时已经保存到session中的
    > 比较用户输入的验证码与session中真正的验证码
    	<> 如果正确，xianshiture
    	<>如果错误，把错误信息保存到request域中，转发回longin.jsp

 * */
public class LoginServlet2 extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//获取用户输入的验证码，请求参数！！！
		String verify = (String)request.getParameter("verify");
		//获取session中保存的真正的验证码文本
		HttpSession session = request.getSession();
		String imgtext = (String)session.getAttribute("imgtext");
		
		//比较用户输入的验证码与session中真正的验证码
		if(verify.equals(imgtext)) {
			response.getWriter().print("true");
		} else {
			//把错误信息保存到request域中
			request.setAttribute("error", "验证码错误");
			//转发回longin.jsp
			request.getRequestDispatcher("/imgcheck/login.jsp").forward(request, response);
		}
	}
}
