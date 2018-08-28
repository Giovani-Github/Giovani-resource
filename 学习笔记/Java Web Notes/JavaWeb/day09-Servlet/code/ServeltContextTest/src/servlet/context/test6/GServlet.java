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
public class GServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		/*
		 * 1.先得到ClassLoader
		 * 	>先得到calss，在通过calss得到calssloader
		 * 2.在使用CalssLoader的getResourceAsStream方法，得到读取流对象InputStream。
		 * 3.在使用读取刘对象打印出a.txt里面的内容	
		 * 	> 使用commons-io-1.4.jar包里面，IOUtils类里面的toString方法获取a.txt里面的内容
		 * 	
		 * */
		
		ClassLoader cl = this.getClass().getClassLoader();
//		//相对于classes路径
//		InputStream input = cl.getResourceAsStream("a.txt");
		
		//相对于classes路径
//		InputStream input = cl.getResourceAsStream("servlet/context/test6/b.txt");
	
		//相对于classes路径
		InputStream input = cl.getResourceAsStream("../../index.jsp");
		
		String a = IOUtils.toString(input);
		System.out.println(a);
		
		
	}
}
