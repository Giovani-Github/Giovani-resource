package cn.itcast.user.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/*
 * jdbc小工具1.0版本
 * 实现一个获取Connection对象的方法
 * 	1.获取Connection对象需要四大参数，并且所使用的数据库不同，那么四大参数的值也不同
 * 	2.所以我们使用一个配置文件dbconfig.properties来保存四大参数，方便修改
 * */
public class JdbcUtils {
	private static Properties prop = new Properties();//配置文件
	
	/*
	 * 加载配置文件，和注册驱动只需要执行一次就可以了，
	 * 所以放在静态代码块中，让它在这个类在被加载时就执行且执行一次
	 * */
	static {
		//加载配置文件
		try {
			/*
			 * 获取读取流，
			 * 会在当前加载器所在的src下查找给定的资源，因为src目录下的非java文件，在bin目录下也复制一份
			 * 那么类加载器所加载的文件时class文件，而class文件时在bin目录下，
			 * 所以getResourceAsStream()方法会在bin目录下查找给定的资源，然后返回读取流
			 * */
			InputStream in = JdbcUtils.class.getClassLoader()
					.getResourceAsStream("dbconfig.properties");
			
			//加载配置文件到prop中
			prop.load(in);
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		//注册驱动
		try {
			//获取配置文件中的driverClassName的值
			String driverClassName = prop.getProperty("driverClassName");
			//注册驱动
			Class.forName(driverClassName);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	//获取Connection的方法
	public static Connection getConnection() throws SQLException {
		String url = prop.getProperty("url");
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		Connection con = DriverManager.getConnection(url, username, password);
		return con;
	}
}
