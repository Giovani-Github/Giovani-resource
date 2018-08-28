package imgcheck;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.vcode.utils.VerifyCode;

/*
 * > 使用VerfiyCode来生成图形验证码
   > 把文本保存到session域中
   > 把图片响应给客户。
 * */
public class VerifyServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//使用VerfiyCode来生成图形验证码
		VerifyCode verifyCode = new VerifyCode();
		BufferedImage img = verifyCode.getImage(); 
		
		//把文本保存到session域中
		request.getSession().setAttribute("imgtext", verifyCode.getText());
		
		//把图片响应给客户。
		verifyCode.output(img, response.getOutputStream());	
		
	}
}
