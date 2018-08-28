package cn.itcast.user.dao;

import cn.itcast.user.domain.User;

/*
 * 数据层：对数据进行操作的接口，dao中所有对数据进行操作的类都要实现这个接口
 * */
public interface UserDao {
	public void add(User user);
	public User findByUsername(String username);
}
