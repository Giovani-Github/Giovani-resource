package cn.itcast.bookstore.category.service;

import java.util.List;

import cn.itcast.bookstore.category.dao.CategoryDao;
import cn.itcast.bookstore.category.domain.Category;

public class CategoryService {
	//依赖dao
	private CategoryDao categoryDao = new CategoryDao();

	//查询所有分类
	public List<Category> findAll() {
		return categoryDao.findAll();
	}
}
