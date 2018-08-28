package test.service;

import org.junit.Test;

import cn.itcast.user.domain.User;
import cn.itcast.user.service.UserException;
import cn.itcast.user.service.UserService;

/***
 * 业务层的UserService测试
 * @author Administrator
 *
 */
public class UserServiceTest {
	/*
	 * 测试UserService的regist方法
	 * */
	
	@Test
	public void testRegist() {
		UserService service = new UserService();
		User user = new User();
		user.setUsername("王五");
		user.setPassword("sdfsad");
		try {
			service.regist(user);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
