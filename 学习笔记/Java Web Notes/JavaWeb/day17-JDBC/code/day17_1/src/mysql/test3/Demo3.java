package mysql.test3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class Demo3 {
	/*
	 * 规范化代码
	 * */
	@Test
	public void fun1() {
		//四大参数
		String driverClassName = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/exam";
		String username = "root";
		String password = "123";
		
		Connection con = null;//声明引用
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			//获取Connection
			Class.forName(driverClassName);
			con = DriverManager.getConnection(url, username, password);
			
			//获取Statement
			stmt = con.createStatement();
			
			//进行查询操作，得到结果集
			rs = stmt.executeQuery("SELECT * FROM emp");
			
			//查看结果集有多少行
			rs.last();//把光标移到最后一行
			System.out.println(rs.getRow());//当前光标所在行
			rs.beforeFirst();//把光标放到第一行的上面一行
			
			//遍历结果集
			int count = rs.getMetaData().getColumnCount();//得到元数据，然后通过元数据得到结果集列数
			while(rs.next()) { //循环行
				for(int i = 1; i <= count; i++ ) {//循环列
					System.out.print(rs.getString(i));
					if(i < count) {//最后一列不缩进
						System.out.print("\t");
					}
				}
				System.out.println();
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally { //关闭资源，倒关
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(con != null) con.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}
}

