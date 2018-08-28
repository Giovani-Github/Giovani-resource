package my.book.service;

import my.book.dao.BookDao;

public class BookService {
	private BookDao dao = new BookDao();
	
	public Object findAll() {
		return dao.findAll();
	}

	public Object findByCategory(int category) {
		return dao.findByCategory(category);
	}

}
