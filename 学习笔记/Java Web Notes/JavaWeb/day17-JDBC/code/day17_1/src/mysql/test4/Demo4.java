package mysql.test4;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

public class Demo4 {
	
	/*
	 * 使用JdbcUtils来获取Connection(数据库连接对象)
	 * */
	@Test
	public void fun1() {
		Connection con = null;
		
		try {
			con = JdbcUtils.getConnection();
			System.out.println(con);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if(con != null) con.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
