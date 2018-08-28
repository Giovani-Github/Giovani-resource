package test.dao;

import test.domain.User;


/*
 * 数据层DAO
 * 在这里，我们来查询数据，并且把查询到的数据保存到User中，返回给调用者（业务层）
 * 
 * 依赖数据库，通过查询数据库里的数据，然后封装到数据对象User中，再把数据对象User返回
 * */
public class UserDao {
	public User find() {
		return new User("张三","123");
	}
}
