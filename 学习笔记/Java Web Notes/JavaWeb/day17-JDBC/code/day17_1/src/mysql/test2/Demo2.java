package mysql.test2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;


public class Demo2 {
	
	/*
 * 发送SQL增、删、改语句
	 * */
	@Test
	public void fun1() throws ClassNotFoundException, SQLException {
		/*
		 * 一、得到Connection对象
		 *	1、准备四大参数
		 *	2、注册驱动
		 *	3、使用DriverManager类得到Connection对象
		 * 	
		 * 二、通过Connection对象得到Statement对象
		 * 	1、使用Connection对象的createStatement()方法，获得Statement对象
		 * 
		 * 三、使用Statement对象的executeUpdate(String sql)方法，发送DML、DDL语句
		 * 
		 * 四、关闭资源：先开的后关，后开的先关
		 * 	statement.close();
		 * 	connection.close();
		 * */
		
		/*一、1.准备四大参数
		 * > driverClassName:驱动类名，com.mysql.jdbc.Driver 
		 *  > url: jdbc:mysql://localhost:3306/数据库名
		 *  > username: root
		 *  > password:123s
		 * */
		String driverClassName = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/mydb1";
		String username = "root";
		String password = "123";
		
		/*
		 * 一、2.注册驱动
		 * */
		Class.forName(driverClassName);
		
		/*
		 * 一、3.得到Connection对象
		 * */
		Connection con = DriverManager.getConnection(url,username, password);
		
		/*
		 * 二、通过Connection对象得到Statement对象
		 * */
		Statement statement = con.createStatement();
		
		/*
		 * 三、使用Statement对象的executeUpdate(String sql)方法，发送DML、DDL语句
		 * */
		int i = statement.executeUpdate("INSERT INTO stu VALUES('ITCAST_0005', 'LiWu', 26, 'male')");
		
		System.out.println(i);
		
		/*
		 * 四、 关闭资源、倒关
		 * */
		statement.close();
		con.close();
		
	}
	
	/*
	 * 发送SQL，SELECT语句，即查询
	 * */
	@Test
	public void fun2() throws ClassNotFoundException, SQLException { 
		/*
		 * 一、得到Connection对象
		 *	1、准备四大参数
		 *	2、注册驱动
		 *	3、使用DriverManager类得到Connection对象
		 * 	
		 * 二、通过Connection对象得到Statement对象
		 * 	1、使用Connection对象的createStatement()方法，获得Statement对象
		 * 
		 * 三、得到查询结果
		 *	1.发送查询语句，使用Statement对象的executeQuery(String sql)方法，发送DQL语句,
		 *	得到查询结果集对象ResultSet 	
		 *
		 * 四、解析查询结果
		 * 	1.使用ResultSet的next()方法，把行光标定位到第一行
		 * 	2.使用ResultSet的getxxx(int 列数)、getxxx(String 列名称)方法，得到指定列的数据，首先要确定这一列的数据类型
		 * 		getObject(列数|列名称)、getString(列数|列名称)、getInt(列数|列名称)、getDouble(列数|列名称)
		 * 
		 * 五、关闭资源：先开的后关，后开的先关
		 * 	statement.close();
		 * 	connection.close();
		 * */
		
		/*一、1.准备四大参数
		 * 	> driverClassName:驱动类名，com.mysql.jdbc.Driver 
		 *  > url: jdbc:mysql://localhost:3306/数据库名
		 *  > username: root
		 *  > password:123s
		 * */
		
		String driverClassName = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/exam";
		String username = "root";
		String password = "123";
		
		/*
		 * 一、2.注册驱动 
		 * */
		Class.forName(driverClassName);
		
		/*
		 * 一、3.使用DriverManager类得到Connection对象
		 * */
		Connection con = DriverManager.getConnection(url, username, password);
		
		/*
		 * 二、使用Connection对象的createStatement()方法，获得Statement对象
		 * */
		Statement statement = con.createStatement();
		
		/*
		 * 三、1.发送查询语句，使用Statement对象的executeQuery(String sql)方法，发送DQL语句,
		 * 得到查询结果集对象ResultSet 	
		 * */
		ResultSet resultSet = statement.executeQuery("SELECT * FROM emp");
		
		/*  
		 * 四、解析查询结果
		 *  1.使用ResultSet的next()方法，把行光标定位到第一行，返回一个boolean，表示是否有下一行
		 * 	2.使用ResultSet的getxxx(int 列数)、getxxx(String 列名称)方法
		 * */
		while(resultSet.next()) {
			String ename = resultSet.getString("ename");//可以是列的名称
			String job = resultSet.getString(3);//也可以是列所在的位置
			int sal = resultSet.getInt("sal");
			System.out.println(ename + "\t" + job);
		}
		
		
	}
}
