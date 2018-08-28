package cn.itcast.user.web.servlet;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.vcode.utils.VerifyCode;

public class VerifyCodeServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		/*
		 * 1.获取验证码图片
		 * 2.把验证码图片上的文本保存到session中
		 * 3.把图片响应到regist.jsp页面中
		 * */
		
		//1.获取验证码图片；itcast-tools-1.4.2.jar中的Verifycode的getImage
		VerifyCode verifyCode = new VerifyCode();
		BufferedImage bi = verifyCode.getImage();
		
		//2.把验证码图片上的文本保存到session中
		request.getSession().setAttribute("sessionVerifyCode", verifyCode.getText());
		
		//3.把图片响应到regist.jsp页面中
		verifyCode.output(bi, response.getOutputStream());
	}
}
