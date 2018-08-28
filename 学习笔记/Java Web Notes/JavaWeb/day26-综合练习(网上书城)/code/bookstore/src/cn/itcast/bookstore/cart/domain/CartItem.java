package cn.itcast.bookstore.cart.domain;

import java.math.BigDecimal;

import cn.itcast.bookstore.book.domain.Book;

//购物车条目类
public class CartItem {
	private Book book;//商品
	private int count;//数量
	
	//小计方法
	public double getSubtotal() {
		//图书单价乘数量
		//return book.getPrice() * count;
	
		//可能会出现二进制运算误差
		BigDecimal price = new BigDecimal(book.getPrice() + "");
		BigDecimal cnt = new BigDecimal(count + "");
		return price.multiply(cnt).doubleValue();//图书单价乘数量
	}
	
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
