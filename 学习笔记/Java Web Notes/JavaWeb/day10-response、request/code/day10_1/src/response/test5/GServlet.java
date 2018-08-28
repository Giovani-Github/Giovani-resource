package response.test5;

import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

/*
 * 向客户端发送图片
 * 
 * */
public class GServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		/*
		 * 1.FileInputStream流读取图片
		 * 2.将图片转换为字节数组
		 * 	> 使用commons-io-1.4.jar包的IOUtils.toByteArray(InputStream in)方法
		 * 	> 将与图片关联的FileInputStream的内容转换为字节，存到字节数组里
		 * 3.使用ServletOutputStream向客户端发送图片
		 * */
		
		FileInputStream in = new FileInputStream("e:/1.jpg");
		byte[] bytes = IOUtils.toByteArray(in);
		response.getOutputStream().write(bytes);
		
		/*
		 * 也可发送字符
		 * */
		
//		String s = "hello";
//		byte[] b = s.getBytes();
//		response.getOutputStream().write(b);
	}
}
