package my.book.dao;

import java.sql.SQLException;

import my.book.domain.Book;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.jdbc.TxQueryRunner;

public class BookDao {
	//简化jdbc操作的类
	private QueryRunner qr = new TxQueryRunner();
	
	public Object findAll() {
		String sql = "SELECT * FROM t_book";
		try {
			return qr.query(sql, new BeanListHandler<Book>(Book.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Object findByCategory(int category) {
		String sql = "SELECT * FROM t_book WHERE category=?";
		try {
			return qr.query(sql, new BeanListHandler<Book>(Book.class), category);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
