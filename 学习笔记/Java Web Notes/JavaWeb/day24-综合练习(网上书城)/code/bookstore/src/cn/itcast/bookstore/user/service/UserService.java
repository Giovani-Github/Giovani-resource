package cn.itcast.bookstore.user.service;

import sun.print.resources.serviceui;
import cn.itcast.bookstore.user.dao.UserDao;
import cn.itcast.bookstore.user.domain.User;

/*
 * User业务层
 * */
public class UserService {
	//依赖持久层
	private UserDao userDao = new UserDao();
	
	//注册功能
	public void regist(User form) throws UserException {
		User user = userDao.findByUsername(form.getUsername());
		//如果查询到了用户，表示用户名已经被注册
		if(user != null) {
			throw new UserException("用户名已经被注册");
		}
		
		user = userDao.findByEmail(form.getEmail());
		if(user != null) {
			throw new UserException("Email已经被注册");
		}
		
		//执行到这里，表示校验通过
		userDao.add(form);
	}
	
	//激活功能
	public void active(String code) throws UserException {
		//使用激活码去数据库中查，如果有这个用户，证明激活码正常。
		User user = userDao.findByCode(code);
		//如果没有，抛出异常
		if(user == null) {
			throw new UserException("激活码无效！");
		}
		
		//查看用户状态，如果状态为true，表示已经激活。无需再次激活
		if(user.isState()) {
			throw new UserException("已经激活，无需再次激活！");
		}
		
		//如果用户状态为false，即表示没有激活。修改状态为true
		userDao.updateState(user.getUid(), true);
	}
	
	// 登录功能
	public User login(User form) throws UserException {
		/*
		 * 1.使用username查询数据库，得到user对象
		 * 	> 如果user为null，抛出异常（用户不存在）
		 * 2.检验form的密码是否与user的密码相同
		 * 	> 如果不同，抛出异常（密码错误）
		 * 3.查看用户状态
		 * 	> 如果为false，即：未激活，抛出异常（还没有激活）
		 * 4.返回user
		 * */
		User user = userDao.findByUsername(form.getUsername());
		
		if(user == null) throw new UserException("用户不存在");
		if(!user.getPassword().equals(form.getPassword())) throw new UserException("密码错误");
		if(!user.isState()) throw new UserException("还没有激活");
		return user;
	}
}

