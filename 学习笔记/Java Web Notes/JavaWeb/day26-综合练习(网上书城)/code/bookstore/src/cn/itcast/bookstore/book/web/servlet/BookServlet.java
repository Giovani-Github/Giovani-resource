package cn.itcast.bookstore.book.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.bookstore.book.domain.Book;
import cn.itcast.bookstore.book.service.BookService;
import cn.itcast.servlet.BaseServlet;

public class BookServlet extends BaseServlet {
	//依赖service
	private BookService bookService = new BookService();
	
	//查询所有图书
	public String findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("bookList", bookService.findAll());
		return "f:/jsps/book/list.jsp";
	}
	
	//按分类查询图书
	public String findByCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cid = request.getParameter("cid");
		request.setAttribute("bookList", bookService.findByCategory(cid));
		return "f:/jsps/book/list.jsp";
	}
	
	//按图书id查找图书
	public String load(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取bid
		String bid = request.getParameter("bid");
		//调用service的load方法
		Book book = bookService.load(bid);
		//保存到request
		request.setAttribute("book", book);
		//转发到jsps/book/desc.jsp
		return "f:/jsps/book/desc.jsp";
	}
}
