package cn.itcast.bookstore.category.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.bookstore.category.domain.Category;
import cn.itcast.jdbc.TxQueryRunner;

public class CategoryDao {
	//简化jdbc操作的类
	private QueryRunner qr = new TxQueryRunner();
	
	//查询所有分类
	public List<Category> findAll() {
		try {
			String sql = "SELECT * FROM category";
			List<Category> categoryList = qr.query(sql, new BeanListHandler<Category>(Category.class));
			return categoryList;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
