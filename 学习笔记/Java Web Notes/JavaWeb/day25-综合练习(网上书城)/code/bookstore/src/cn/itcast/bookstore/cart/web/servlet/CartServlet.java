package cn.itcast.bookstore.cart.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.bookstore.book.domain.Book;
import cn.itcast.bookstore.book.service.BookService;
import cn.itcast.bookstore.cart.domain.Cart;
import cn.itcast.bookstore.cart.domain.CartItem;
import cn.itcast.servlet.BaseServlet;

public class CartServlet extends BaseServlet {
	//添加条目
	public String add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1.获取购物车
		 * 2.获取条目（在表单中,jsps/book/desc.jsp，图书id和数量）
		 * 3.添加条目到购物车中
		 * 4.转发到/jsps/cart/list.jsp
		 * */
		
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		
		//如果没有购物车（用户没有登录），直接转发到f:/jsps/cart/list.jsp
		if(cart == null) {
			return "f:/jsps/cart/list.jsp";
		}
		
		/*
		 * 得到条目
		 * 	>通过图书id，得到图书
		 * 	>得到数量
		 * */
		String bid = request.getParameter("bid");
		Book book = new BookService().load(bid);
		int count = Integer.parseInt(request.getParameter("count"));
		CartItem ci = new CartItem();
		ci.setBook(book);
		ci.setCount(count);
		
		cart.add(ci);
		return "f:/jsps/cart/list.jsp";
	}
	
	//清空条目
	public String clear(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1.得到购物车
		 * */
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		cart.clear();		
		return "f:/jsps/cart/list.jsp";
	}
	//删除条目
	public String delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1.得到购物车
		 * 2.得到商品id
		 * */
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		String bid = request.getParameter("bid");
		cart.delete(bid);
		return "f:/jsps/cart/list.jsp";
	}
}
