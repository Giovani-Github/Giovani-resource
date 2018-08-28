package test.service;

import test.dao.UserDao;
import test.domain.User;

/*
 * 业务层Service
 * 
 * 依赖数据层，需要通过数据层的对象UserDao来得到数据对象User，
 * 再把数据对象返回给Servlet层
 * */
public class UserService {
	UserDao userDao = new UserDao();
	
	public User find() {
		return userDao.find();
	}
}
