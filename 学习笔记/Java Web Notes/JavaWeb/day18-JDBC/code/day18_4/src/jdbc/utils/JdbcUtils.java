package jdbc.utils;


import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtils {
	//连接池对象
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource();

	/*
	 * 使用默认配置获取Connection
	 * */
	public static Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
	
	/*
	 * 返回连接池对象，供调用者手动配置
	 * */

	public static DataSource getDataSource() {
		return dataSource;
	}
}
