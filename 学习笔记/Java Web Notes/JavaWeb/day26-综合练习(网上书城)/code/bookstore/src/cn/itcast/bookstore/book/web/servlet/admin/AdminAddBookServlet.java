package cn.itcast.bookstore.book.web.servlet.admin;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.itcast.bookstore.book.domain.Book;
import cn.itcast.bookstore.book.service.BookService;
import cn.itcast.bookstore.category.domain.Category;
import cn.itcast.bookstore.category.service.CategoryService;
import cn.itcast.commons.CommonUtils;


/**
 * Servlet implementation class AdminAddBookServlet
 */
public class AdminAddBookServlet extends HttpServlet {
	private BookService bookService = new BookService();
	private CategoryService categoryService = new CategoryService();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		/*
		 *	1.封装表单数据到Book对象中
		 *		*上传三步 
		 * */
		//创建工厂
		DiskFileItemFactory factory = new DiskFileItemFactory(15 * 1024, new File("F:/f/temp"));//设置缓存大小和缓存位置
		//创建解析器
		ServletFileUpload sfu = new ServletFileUpload(factory);
		
		//设置单个文件大小为最大30KB
		sfu.setFileSizeMax(30 * 1024);
		
		try {
	
			
			//使用解析器解析request，得到所有表单项
			List<FileItem> fileItemList = sfu.parseRequest(request);
			
			//循环遍历得到每个表单项（除了文件表单项以外），保存到map中，表单项名称做key，表单项的值做value
			Map<String, String> fileItemMap = new HashMap<String, String>();
			for(FileItem fileItem : fileItemList) {
				if(fileItem.isFormField()) {//判断是否为普通表单项
					fileItemMap.put(fileItem.getFieldName(), fileItem.getString("UTF-8"));
				}
			}
			//把map变成一个book对象
			Book book = CommonUtils.toBean(fileItemMap, Book.class);
			//为book指定bid
			book.setBid(CommonUtils.uuid());
			//把map变成一个category对象
			Category category = CommonUtils.toBean(fileItemMap, Category.class);
			//与book建立关系
			book.setCategory(category);

			/*
			 * 2.保存上传的文件
			 * 	保存的目录
			 * 	保存的文件名称
			 * */
			//保存的目录
			String sacepath = this.getServletContext().getRealPath("/book_img");
			//得到文件名称：添加uuid，避免文件冲突
			String filename = CommonUtils.uuid() + "_" +fileItemList.get(1).getName();
			
			//检查文件的扩展名
			if(!filename.toLowerCase().endsWith("jpg")) {
				request.setAttribute("msg", "文件扩展名必须为jpg");
				request.setAttribute("categoryList", categoryService.findAll());
				request.getRequestDispatcher("/adminjsps/admin/book/add.jsp").forward(request, response);
				return;
			}
			
			
			//使用保存的目录和文件名称创建目标文件
			File destFile = new File(sacepath, filename);
			//保存文件到目标文件位置
			fileItemList.get(1).write(destFile);
			
			//检查图片的尺寸
			Image image = new ImageIcon(destFile.getAbsolutePath()).getImage();
			if(image.getWidth(null) > 200 || image.getHeight(null) > 200) {
				destFile.delete();//删除这个图片
				request.setAttribute("msg", "图片尺寸超过200*200");
				request.setAttribute("categoryList", categoryService.findAll());
				request.getRequestDispatcher("/adminjsps/admin/book/add.jsp").forward(request, response);
				return;
			}
			
			//设置book对象的image，把图片的路径设置给book
			book.setImage("book_img/" + filename);
			
			//3.使用service，把book保存到数据库
			bookService.add(book);
			
			//4.返回到图书列表
			request.getRequestDispatcher("/admin/AdminBookServlet?method=findAll").forward(request,response);
		} catch (Exception e) {
			if(e instanceof FileUploadBase.FileSizeLimitExceededException) {
				request.setAttribute("msg", "文件大小超过20kb");
				request.setAttribute("categoryList", categoryService.findAll());
				request.getRequestDispatcher("/adminjsps/admin/book/add.jsp").forward(request, response);
			}
		}
		
		
	}
}
