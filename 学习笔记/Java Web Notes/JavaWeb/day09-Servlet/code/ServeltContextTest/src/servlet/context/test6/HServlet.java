package servlet.context.test6;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

/*
 * 演示获取类路径下的资源
 * 使用classLoader获取
 * */

public class HServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*
		 * 1.先得到Class
		 * 2.在使用Calss的getResourceAsStream方法，得到读取流对象InputStream。
		 * 3.在使用读取刘对象打印出a.txt里面的内容	
		 * 	> 使用commons-io-1.4.jar包里面，IOUtils类里面的toString方法获取a.txt里面的内容
		 * */
		
		Class c = this.getClass();
		
		//加“/”就是相对于classes路径下
		//InputStream input = c.getResourceAsStream("/a.txt");
		
		//没加“/”就是相对于当前class文件的路径下
		InputStream input = c.getResourceAsStream("../../../a.txt");
		
		String a = IOUtils.toString(input);
		System.out.println(a);
	}
}
