package cn.itcast.bookstore.book.dao;


import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.bookstore.book.domain.Book;
import cn.itcast.bookstore.category.domain.Category;
import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;

public class BookDao {
	//简化jdbc操作的类
	private QueryRunner qr = new TxQueryRunner();
	
	//查询所有图书
	public List<Book> findAll() {
		try {
			String sql = "SELECT * FROM book WHERE del=false";
			List<Book> bookList = qr.query(sql, new BeanListHandler<Book>(Book.class));
			return bookList;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	//按分类查询所有图书
	public List<Book> findByCategory(String cid) {
		try {
			String sql = "SELECT * FROM book WHERE cid=? AND del=false";
			return qr.query(sql, new BeanListHandler<Book>(Book.class), cid);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	//按图书id查找图书
	public Book load(String bid) {
		try {
			String sql = "SELECT * FROM book WHERE bid=?";
			Map<String, Object> map = qr.query(sql, new MapHandler(), bid);
			//使用一个map，映射成两个对象，并建立两个对象的关系
			Book book = CommonUtils.toBean(map, Book.class);
			Category category = CommonUtils.toBean(map, Category.class);
			book.setCategory(category);
			return book;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	//查看指定分类下的图书本数
	public int getCountByCid(String cid) {
		try {
			String sql = "SELECT COUNT(*) FROM book WHERE cid=? AND del=false";
			Number count = (Number)qr.query(sql,new ScalarHandler(), cid);
			return count.intValue();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	//添加图书
	public void add(Book book) {
		try {
			String sql = "INSERT INTO book VALUES(?,?,?,?,?,?,?)";
			Object[] params = {book.getBid(), book.getBname(), book.getPrice(), book.getAuthor(), book.getImage(),
					book.getCategory().getCid(), false};
			qr.update(sql, params);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//删除图书
	public void delete(String bid) {
		try {
			String sql = "UPDATE book SET del=true WHERE bid=?";
			qr.update(sql, bid);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	//编辑图书
	public void edit(Book book) {
		try {
			String sql = "UPDATE book SET bname=?, price=?, author=?, image=? WHERE bid=?";
			Object[] params = {book.getBname(), book.getPrice(), book.getAuthor(), 
					book.getImage(), book.getBid()};
			qr.update(sql, params);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
