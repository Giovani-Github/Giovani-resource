package test.dbcp;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;

/*
 * DBCP数据库连接池的使用
 * */
public class DbcpTest {
	@Test
	public void fun() throws Exception {
		//获取数据库连接池对象
		BasicDataSource ds = new BasicDataSource();
		
		//设置四大参数
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/mydb1");
		ds.setUsername("root");
		ds.setPassword("123");
		
		//设置池参数，可以不设置，连接池有默认的参数
		ds.setMaxActive(20);//最大连接数
		ds.setMaxIdle(10);//最大空闲连接数
		
		//从连接池获取数据库连接对象
		Connection con = ds.getConnection();
		System.out.println(con.getClass().getName());
		
		/*
		 * 连接池内部使用了四大参数创建了数据库连接对象！即mysql驱动提供的Connection
		 * 连接池使用mysql的连接对象进行了装饰，只对close()方法进行了增强
		 * 装饰之后的Connection的Close()方法，用来把当前连接归还给数据库连接池
		 * */
		con.close();
	}
}
