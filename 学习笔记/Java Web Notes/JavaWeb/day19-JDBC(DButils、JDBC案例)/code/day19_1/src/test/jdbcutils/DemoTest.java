package test.jdbcutils;

import org.junit.Test;

/*
 * 测试转账
 * */
public class DemoTest {
	@Test
	public void fun1() {
		//得到Service
		DemoService service = new DemoService();
		//张三给李四转账2块
		service.zhuan("zs", "ls", 2);
	}
}
