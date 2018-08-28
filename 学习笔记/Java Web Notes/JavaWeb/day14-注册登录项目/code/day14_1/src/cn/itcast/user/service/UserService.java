package cn.itcast.user.service;

import cn.itcast.user.dao.UserDao;
import cn.itcast.user.domain.User;

/***
 * 业务层，依赖数据层。完成一些业务功能，业务逻辑功能
 * @author Administrator
 *
 */
public class UserService {	
	private UserDao userDao = new UserDao();
	
	/***
	 * 注册功能
	 */
	public void regist(User user) throws UserException {
		/*
		 * 1.使用用户名去查询，如果被注册了，那么返回的就不是null，如果没有被注册，返回的是null
		 * 	2. 返回的不是null：抛出我们自定义的异常
		 * 	3.返回的是null：完成添加
		 * */
		
		//1.检查用户是否已经被注册
		User _user = userDao.findByUsername(user.getUsername());
		//2. 返回的不是null：抛出我们自定义的异常
		if(_user != null) throw new UserException("用户名：" + user.getUsername() + "，已经被注册");
		
		//3.返回的是null，完成添加
		userDao.add(user);
	}
	
	public User login(User form) throws UserException {
		/*
		 * 1.使用用户名出现数据库，得到返回的User
		 * 	2.返回为null：抛出异常，异常信息为（用户名不存在）
		 * 	3.返回不为null：获取查询出来的user的password。与form的password进行比较。
		 * 		4.如果不同：抛出异常。异常信息为（密码不正确）
		 * 		5.如果相同：返回查询到的user。返回的user包含用户的全部信息。而传递过来的user保存的只有用户名和密码
		 * */
		
		//1.使用用户名出现数据库，得到返回的User
		User user = userDao.findByUsername(form.getUsername());
		
		//2.返回为null：抛出异常，异常信息为（用户名不存在）
		if(user == null) throw new UserException("用户名不存在");
		
		//3.返回不为null,获取查询出来的user的password。与form的password进行比较。
		if(!form.getPassword().equals(user.getPassword())) {
			//4.如果不同：抛出异常。异常信息为（密码不正确）
			throw new UserException("密码不正确");
		}
		
		//5.如果相同：返回查询到的user
		return user;
		
	}
}
