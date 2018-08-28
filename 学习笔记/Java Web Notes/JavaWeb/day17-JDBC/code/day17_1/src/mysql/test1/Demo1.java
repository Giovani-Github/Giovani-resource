package mysql.test1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class Demo1 {
	/*
	 * 异常：
	 * 	ClassNotFoundException:
	 *	> 没导驱动包
	 * 	> 驱动类名写错
	 * SQLException:
	 * 	> 检查url、username、password是否正确
	 *	> 检查是否开启了mysql服务器！
	 * */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		/*
		 * JDBC四大配置参数：
		 *  > driverClassName:驱动类名，com.mysql.jdbc.Driver 
		 *  > url: jdbc:mysql://localhost:3306/数据库名
		 *  > username: root
		 *  > password:123s
		 * */
		
		//加载驱动类
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/mydb1";
		String username = "root";
		String password = "123";
		
		//使用DriverManager类来得到Connection对象
		Connection con = DriverManager.getConnection(url, username, password);
		
		System.out.println(con);
		
		
		
		
	}
}
