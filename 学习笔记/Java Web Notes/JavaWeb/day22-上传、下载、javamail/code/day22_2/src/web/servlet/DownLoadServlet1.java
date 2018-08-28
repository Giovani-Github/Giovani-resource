package web.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import sun.misc.BASE64Encoder;

public class DownLoadServlet1 extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		/*
		 * 两个头，一个流
		 *  > Content-Type：你传递给客户端的文件是什么MIME类型，例如：image/pjpeg
		      * 通过文件名称调用ServletContext的getMimeType()方法，得到MIME类型！
		    > Content-Disposition：它的默认值为inline，表示在浏览器窗口中打开！attachment;filename=xxx,弹出一个窗口
			  * attachment：表示响应给客户端的附件。
		      * 在filename=后面跟随的是显示在下载框中的文件名称！
		    > 流：要下载的文件数据！
		      * 自己new一个输入流即可！
			  * 然后b把自己的流的数据写入到客户端的流中：
		 * */
		
		
		
		String fileDir = "D:/迅雷下载/02.mp4";//要下载的文件的路径
		String fileName = "李俊杰老师课表.mp4";//要下载的文件的名称
		
		/////////////////////////////////
		//解决在浏览器中显示文件名称乱码问题
		//通用方案，对个别字符没作用
		//fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");
		
		//另一个方案，使用函数filenameEncoding
		fileName = filenameEncoding(fileName, request);
		
		
		//////////////////////////////////
		
		String contentType = this.getServletContext().getMimeType(fileName);
		String contentDisposition = "attachment;filename=" + fileName;
		
		//两个头
		response.setHeader("Content-Type", contentType);
		response.setHeader("Content-Disposition", contentDisposition);
		
		//一个流，即：要下载的文件数据
		FileInputStream in = new FileInputStream(fileDir);
		
		//客户端的流
		ServletOutputStream out = response.getOutputStream();
		
		//把我们的流写入到客户端的流
		IOUtils.copy(in, out);
		
		//把自己的流关闭，而客户端的流，tomcat会处理
		in.close();
		
	}
	
	public static String filenameEncoding(String filename, HttpServletRequest request) throws IOException {
		String agent = request.getHeader("User-Agent"); //获取浏览器
		if (agent.contains("Firefox")) {
			BASE64Encoder base64Encoder = new BASE64Encoder();
			filename = "=?utf-8?B?"
					+ base64Encoder.encode(filename.getBytes("utf-8"))
					+ "?=";
		} else if(agent.contains("MSIE")) {
			filename = URLEncoder.encode(filename, "utf-8");
		} else {
			filename = URLEncoder.encode(filename, "utf-8");
		}
		return filename;
	}
}
