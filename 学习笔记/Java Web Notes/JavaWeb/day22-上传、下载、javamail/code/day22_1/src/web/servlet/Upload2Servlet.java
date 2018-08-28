package web.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class Upload2Servlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//1.创建工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//2.创建 解析器
		ServletFileUpload sfu = new ServletFileUpload(factory);
		//3.使用解析器解析request，得到FileItem集合，一个FileItem为一个表单项
		try {
			List<FileItem> itemList = sfu.parseRequest(request);
			
			//我们的表单项在index2.jsp，里面只有两个表单项，普通的和文件表单项
			//获取普通表单项
			FileItem fi1 = itemList.get(0);
			String fi1Name = fi1.getFieldName();//当前表单项名称
			String fi1Value = fi1.getString("utf-8");//表单项的值
			System.out.println("普通表单项:" + fi1Name + "=" + fi1Value);
			
			//获取文件表单项
			FileItem fi2 = itemList.get(1);
			String fi2Name = fi2.getFieldName();//当前表单项的名称
			String fileName = fi2.getName();//上传文件的名称
			long fileSize = fi2.getSize();//上传文件的大小
			String mime = fi2.getContentType();//mime类型
			
			//上传的文件保存到硬盘
			File file = new File("e:/" + fileName);
			fi2.write(file);
			String filePath = file.getPath();//文件真实路径
			
			System.out.println("演示文件表单项：");
			System.out.println("\t" + "表单行名称：" + fi2Name + 
					"上传文件名称：" + fileName + 
					"上传文件大小：" + fileSize + 
					"保存路径：" + filePath);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
}
