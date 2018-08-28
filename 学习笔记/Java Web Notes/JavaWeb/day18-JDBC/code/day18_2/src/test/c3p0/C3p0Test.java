package test.c3p0;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3p0Test {
	/*
	 * 使用手动配置从连接池获取connection
	 * */
	@Test
	public void fun1() throws Exception {
		//获取连接池对象
		ComboPooledDataSource ds = new ComboPooledDataSource();
		
		//设置四大参数
		ds.setDriverClass("com.mysql.jdbc.Driver");
		ds.setJdbcUrl("jdbc:mysql://localhost:3306/mydb1");
		ds.setUser("root");
		ds.setPassword("123");
		
		//设置池参数，可以不设置，连接池有默认的参数(没设置c3p0-config.xml也是一样)
		
		//获取Connection
		Connection con = ds.getConnection();
		System.out.println(con);
		
		con.close();
	}
	
	/*
	 * 使用默认配置从连接池获取Connection
	 * */
	@Test
	public void fun2() throws Exception {
		/* 不用定配置文件名称，
		 * 因为配置文件名必须是c3p0-config.xml，这里使用的是默认配置。
		 * 会自动的去找配置文件并且读取
		 */
		ComboPooledDataSource ds = new ComboPooledDataSource();
		
		Connection con = ds.getConnection();
		System.out.println(con);
		
		con.close();
		
	}
	
	/*
	 * 使用命名配置从连接池获取Connection
	 * */
	@Test
	public void fun3() throws Exception {
		//使用c3p0-config.xml中名为oracle-config的配置。
		ComboPooledDataSource ds = new ComboPooledDataSource("oracle-config");
		
		Connection con = ds.getConnection();
		System.out.println(con);
		
		con.close();
		
	}
}
