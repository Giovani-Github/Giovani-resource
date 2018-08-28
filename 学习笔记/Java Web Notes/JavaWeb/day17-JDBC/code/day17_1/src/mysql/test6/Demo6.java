package mysql.test6;

import java.sql.Connection;
import java.sql.PreparedStatement;

import mysql.test4.JdbcUtils;

import org.junit.Test;


/*
 * 批处理
 * */
public class Demo6 {
	
	@Test
	public void fun() throws Exception {
		//获取Connection
		Connection con = JdbcUtils.getConnection();
		//设置sql模板
		String sql = "INSERT INTO stu VALUES(?,?)";
		//获取PerpareStatement
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		//为模板添加参数
		for(int i = 0; i < 10000; i++) {
			//循环一次，就等于设置好了一天sql语句，然后放到集合中
			//循环一万次，就等于有一万条sql语句在集合中
			pstmt.setString(1, "zhang" + i);
			pstmt.setInt(2, i+1);
			
			//把这条sql语句添加到集合中
			pstmt.addBatch();
			
		}
		
		//循环执行完之后，就有一万条sql语句在集合中，我们来一次性执行
		long start = System.currentTimeMillis();
		pstmt.executeBatch();//一起执行
		long end = System.currentTimeMillis();
		
		System.out.println(end - start);
		
	}
}
