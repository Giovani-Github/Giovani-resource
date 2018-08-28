package my.book.web.servlet;



import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import my.book.service.BookService;

import cn.itcast.servlet.BaseServlet;

public class BookServlet extends BaseServlet {
	private BookService service = new BookService();
	/*
	 * 查询所有
	 * */
	public String findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("booklist", service.findAll());
		return "f:/show.jsp";
	}
	
	/*
	 * 查询分类
	 * */
	public String findByCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int category = Integer.parseInt(request.getParameter("category"));
		request.setAttribute("booklist", service.findByCategory(category));
		return "f:/show.jsp";
	}
}
