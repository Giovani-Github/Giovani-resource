package cn.itcast.bookstore.category.service;

import java.util.List;

import cn.itcast.bookstore.book.dao.BookDao;
import cn.itcast.bookstore.category.dao.CategoryDao;
import cn.itcast.bookstore.category.domain.Category;
import cn.itcast.bookstore.category.web.servlet.admin.CategoryException;

public class CategoryService {
	//依赖dao
	private CategoryDao categoryDao = new CategoryDao();
	private BookDao bookDao = new BookDao();

	//查询所有分类
	public List<Category> findAll() {
		return categoryDao.findAll();
	}

	//添加分类
	public void add(Category category) {
		categoryDao.add(category);		
	}

	//删除分类
	public void delete(String cid) throws CategoryException {
		/*
		 * 1.调用BookDao的方法，查看这个分类下面有多少本图书
		 * 2.如果图书本书大于0，就抛出异常，不给删除
		 * 3.否则删除此分类
		 * */		
		int count = bookDao.getCountByCid(cid);
		if(count > 0) {
			throw new CategoryException("该分类下还有图书，不允许删除");
		}
		categoryDao.delete(cid);
	}

	//加载分类，使用cid得到category
	public Category load(String cid) {
		return categoryDao.load(cid);
	}

	//修改分类名称
	public void edit(Category category) {
		categoryDao.edit(category);
	}
}
