package web.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.itcast.commons.CommonUtils;

public class Upload6Servlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;cahrset=utf-8");
		
		//上传三步
		//设置缓存大小为20kb，临时目录为：f:/temp
		int size = 20 * 1024;
		File linshi = new File("f:\\temp");
		linshi.mkdirs();//生成这个目录
		DiskFileItemFactory factory = new DiskFileItemFactory(size, linshi);
		ServletFileUpload sfu = new ServletFileUpload(factory);


		try {
			List<FileItem> itemList = sfu.parseRequest(request);
			FileItem fi = itemList.get(1);//得到文件表单项
			String fileName = fi.getName();//得到文件名称
			//有的浏览器上传的文件名是绝对路径，这需要切割！C:\files\baibing.jpg
			int index = fileName.lastIndexOf("\\");
			if(index != -1) {
				fileName = fileName.substring(index+1);
			}
			//文件同名问题；我们需要为每个文件添加名称前缀，这个前缀要保证不能重复。uuid
			String saveFileName = CommonUtils.uuid() + "_" + fileName;//保存时的文件名称
			
			////////////////////////////////////////////////////////
			
			/*
			 * 文件需要保存到WEB-INF/files，即根目录
			 * 1.得到WEB-INF/files的真实路径
			 * 2.得到文件名的哈希
			 * 3.把哈希转换为16进制
			 * 4.取16进制的前两个字母，与跟目录连接在一起生成两层目录：root/a/b
			 * 5.把文件存放到这个两层目录下：root/a/b/a.txt
			 * */
			String root = this.getServletContext().getRealPath("/WEB-INF/files");//根目录
			int hash = fileName.hashCode();//文件名的哈希值
			String hex = Integer.toHexString(hash);//哈希值转换为十六进制
			char dir1 = hex.charAt(0);//十六进制的第一个字母
			char dir2 = hex.charAt(1);//十六进制的第二个字母
			//与根目录连接在一起，生成一个文件对象
			//这就是一个完整的文件保存的目录
			File dirFile = new File(root, dir1 + "\\" + dir2);
			//生成目录链：root/a/b... 如果目录存在就覆盖，不存在就创建。一层一层下去
			//把完整的目录创建出来
			dirFile.mkdirs();
			
			//把完整的目录与文件名连接起来，生成一个真正的文件对象，用来保存到完成目录下
			File file = new File(dirFile, saveFileName);
			
			//保存文件
			fi.write(file);

		
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
