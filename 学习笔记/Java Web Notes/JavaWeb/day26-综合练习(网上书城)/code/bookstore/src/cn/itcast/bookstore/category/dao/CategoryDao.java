package cn.itcast.bookstore.category.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
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

	//添加分类
	public void add(Category category) {
		try {
			String sql = "INSERT INTO category VALUE(? ,?)";
			qr.update(sql, category.getCid(), category.getCname());
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	//删除分类
	public void delete(String cid) {
		try {
			String sql = "DELETE FROM category WHERE cid=?";
			qr.update(sql, cid);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	//加载，使用cid得到category
	public Category load(String cid) {
		try {
			String sql = "SELECT * FROM category WHERE cid=?";
			Category category = qr.query(sql, new BeanHandler<Category>(Category.class), cid);
			return category;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	//修改分类名称
	public void edit(Category category) {
		try {
			String sql = "UPDATE category SET cname=? WHERE cid=?";
			qr.update(sql, category.getCname(), category.getCid());
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
