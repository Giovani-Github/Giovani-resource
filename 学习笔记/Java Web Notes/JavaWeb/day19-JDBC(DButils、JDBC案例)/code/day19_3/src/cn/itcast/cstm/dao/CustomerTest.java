package cn.itcast.cstm.dao;

import org.junit.Test;

import cn.itcast.commons.CommonUtils;
import cn.itcast.cstm.domain.Customer;

/*
 * 插入查询所用的测试数据
 * */
public class CustomerTest {
	@Test
	public void fun() {
		CustomerDao dao = new CustomerDao();
		for(int i=0; i<300; i++) {
			Customer c = new Customer();
			
			c.setCid(CommonUtils.uuid());
			c.setCname("catm_" + i);
			c.setBirthday("2014-07-13");
			c.setGender(i % 2==0?"男" : "女");
			c.setCellphone("139" + i);
			c.setEmail("cstm_" + i + "@163.com");
			c.setDescription("我是客户");
			
			dao.add(c);
		}
	}
}
