package my.book.web.filter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class StaticResponse extends HttpServletResponseWrapper {
	private PrintWriter pw;
	
	/**
	 * 
	 * @param response
	 * @param path html文件的路径，这个路径与pw绑定在一起
	 */
	public StaticResponse(HttpServletResponse response, String path) {
		super(response);
		
		try {
			//这个流与html文件绑定，如果文件不存在，会创建之
			pw = new PrintWriter(path, "utf-8");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public PrintWriter getWriter() throws IOException {
		return pw;//返回与html文件绑定在一起的流
	}

}
