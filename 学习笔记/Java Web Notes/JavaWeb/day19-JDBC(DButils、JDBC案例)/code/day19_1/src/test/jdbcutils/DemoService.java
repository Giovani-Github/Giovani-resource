package test.jdbcutils;

import java.sql.SQLException;

import jdbc.utils.JdbcUtils;

import org.junit.Test;

/*
 * 使用1.3版本的jdbcUtils，解决转账项目中，connection暴露在service层的问题
 * */
public class DemoService {
	/***
	 * 转账
	 * @param form 从谁转
	 * @param to 转给谁
	 * @param sum 转多少
	 */
	public void zhuan(String form, String to, double sum) {
		//依赖dao层
		DemoDao dao = new DemoDao();
		
		try {
			//开启事务，给事务专用的Connection赋值了，dao层调用getConnection的时候获取到的就是事务专用的Connection
			JdbcUtils.beginTransaction();
			
			//处理事务，zs给ls转账
			dao.setAccount(form, -sum);//给zs减去sum
			dao.setAccount(to, sum);//给ls加上sum		
			
			//提交事务
			JdbcUtils.commitTransaction();
		} catch (Exception e) {
			//回滚事务
			try {
				JdbcUtils.rollbackTransaction();
			} catch (SQLException e1) {
				throw new RuntimeException(e1);
			}
		}
	}
}
