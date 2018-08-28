package my.book.web.filter;


import java.io.File;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class StaticFilter implements Filter {

	private FilterConfig fConfig;


	public void destroy() {
	}

	/*
	 * 页面静态化处理
	 * */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		/*
		 * 原理：
		 * 	请求访问这个资源时，判断资源对应的html是否存在，如果存在，直接重定向过去，不放行
		 * 		如果不存在：放行，把servlet访问数据库后，输出给客户端的数据保存到一个HTML中，重定向到这个html
		 * */
		
		/*
		 * 步骤：
		 * 	1.获取category参数
		 * 	category有四种可能：
		 * 	null --> 查询所有，null.html
		 * 	1 --> 查询se分类，1.html
		 * 	2 --> 查询ee分类，2.html
		 * 	3 --> 查询框架分类， 3.html
		 * 
		 * 	html保存路径：webroot/htmls/*.html
		 * 
		 * 	2.判断对应的html文件是否存在，如果存在，直接重定向
		 * */
		
		String category = request.getParameter("category");
		String htmlPage = category + ".html";//得到对应的文件名称
		String htmlPath = fConfig.getServletContext().getRealPath("/htmls");//文件保存的路径：d:/.../webroot/htmls
		File destFile = new File(htmlPath, htmlPage);//获取到File实例，但是还没有创建出文件
		
		//测试此抽象路径名表示的文件或目录是否存在。
		if(destFile.exists()) { //如果存在
			//重定向到这个文件
			res.sendRedirect(req.getContextPath() + "/htmls/" + htmlPage);
			return;
		}
		
		/*
		 * 3.如果对应的html页面不存在
		 * 	我们输出给客户端的数据就是show.jsp里面要输出的数据
		 * 	只需要要把jsp真身里面，out所绑定的流给替换成与html文件绑定的流即可
		 * 	因为out这个流是用response获取的，所以我们调包response
		 * 	自己写个response的装饰类，在getWriter（）的时候，返回一个与html文件绑定在一起的流即可
		 * */
		
		StaticResponse  sr = new StaticResponse(res, destFile.getAbsolutePath());//要调包的response
		
		/*
		 * 调包之后，Servlet用的就是我们调包的response了，
		 * 因为Servlet有个转发到show.jsp的操作，
		 * 所以show.jsp用的也是我们调用的response。
		 * 这时候，数据就不会输出到客户端了，而是输出到对应的html中
		 * */
		chain.doFilter(req, sr);
		
		//重定向到对应的html页面
		res.sendRedirect(req.getContextPath() + "/htmls/" + htmlPage);
	}


	public void init(FilterConfig fConfig) throws ServletException {
		this.fConfig = fConfig;
	}

}
