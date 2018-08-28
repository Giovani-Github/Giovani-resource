package test.dao;

import org.junit.Test;

import cn.itcast.user.dao.UserDaoImpl;
import cn.itcast.user.domain.User;

/***
 * dao层的UserDao测试
 * @author Administrator
 *
 */
public class UserDaoTest {
	/***
	 * 测试UserDao的add方法
	 */
	@Test
	public void testAdd() {
		UserDaoImpl userDao = new UserDaoImpl();
		User user = new User();
		user.setUsername("赵信");
		user.setPassword("554");
		
		userDao.add(user);
	}
	
	/***
	 * 测试UserDao的findByUser方法
	 */
	@Test
	public void testFindByUsername() {
		UserDaoImpl userDao = new UserDaoImpl();
		User user = userDao.findByUsername("赵信");
		System.out.println(user);
	}
}	
