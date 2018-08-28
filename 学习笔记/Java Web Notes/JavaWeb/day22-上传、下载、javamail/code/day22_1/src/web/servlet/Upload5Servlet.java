package web.servlet;

import java.io.File;
import java.io.IOException;
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

public class Upload5Servlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;cahrset=utf-8");
		
		//上传三步
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload sfu = new ServletFileUpload(factory);
		
		/////////////////////////////////
		/*
		 * 上传文件大小限制
		 * 如果超出限制
		 * sfu.parseRequest(request)方法会抛出异常
		 * 我们在catch代码块里面进行处理
		 * */
		sfu.setFileSizeMax(100 * 1024);//单个文件大小限制为1kb
		sfu.setSizeMax(1024 * 1024);//所有文件加起来大小限制为1MB
		
		/////////////////////////////////
		
		
		
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

		
		} catch(FileUploadException fe) {
			if(fe instanceof FileUploadBase.FileSizeLimitExceededException) {//如果是这个异常时，说明单个文件超出大小限制
				request.setAttribute("msg", "文件大小超出1KB");
				request.getRequestDispatcher("/index5.jsp").forward(request, response);
			} else if(fe instanceof FileUploadBase.SizeLimitExceededException) {////如果是这个异常时，说明所有文件加起来超出大小限制
				request.setAttribute("msg", "所有文件大小之和超出1MB");
				request.getRequestDispatcher("/index5.jsp").forward(request, response);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
