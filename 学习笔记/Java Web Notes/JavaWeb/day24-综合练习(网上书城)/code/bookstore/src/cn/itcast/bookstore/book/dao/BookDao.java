package cn.itcast.bookstore.book.dao;


import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.bookstore.book.domain.Book;
import cn.itcast.jdbc.TxQueryRunner;

public class BookDao {
	//简化jdbc操作的类
	private QueryRunner qr = new TxQueryRunner();
	
	//查询所有图书
	public List<Book> findAll() {
		try {
			String sql = "SELECT * FROM book";
			List<Book> bookList = qr.query(sql, new BeanListHandler<Book>(Book.class));
			return bookList;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	//按分类查询所有图书
	public List<Book> findByCategory(String cid) {
		try {
			String sql = "SELECT * FROM book WHERE cid=?";
			return qr.query(sql, new BeanListHandler<Book>(Book.class), cid);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	//按图书id查找图书
	public Book load(String bid) {
		try {
			String sql = "SELECT * FROM book WHERE bid=?";
			return qr.query(sql, new BeanHandler<Book>(Book.class), bid);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
}
