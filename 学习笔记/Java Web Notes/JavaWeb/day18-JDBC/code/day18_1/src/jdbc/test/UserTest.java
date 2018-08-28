package jdbc.test;

import org.junit.Test;

/*
 * 测试转账
 * */
public class UserTest {
	@Test
	public void fun() {
		UserService service = new UserService();
		
		service.zhuan("zs", "ww", 100);
		
	}
}
