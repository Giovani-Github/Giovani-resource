package web.filter;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;


/*
 * 我们继承HttpServletRequestWrapper这个类
 * 这个类继承了HttpServletRequest，而且对它进行了装饰（帮我们完成了"一切拜托你"）
 * 我们只需要复写我们需要增强的方法即可
 * */
public class EncodingRequest extends HttpServletRequestWrapper {//是你
	private	HttpServletRequest request;//还有你 

	public EncodingRequest(HttpServletRequest request) {
		super(request);
		this.request = request;
	}

	//增强getParameter方法
	public String getParameter(String name) {
		String value = request.getParameter(name);
		
		//进行get请求编码处理
		try {
			value = new String(value.getBytes("iso-8859-1"), "utf-8");			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return value;
	}
}
