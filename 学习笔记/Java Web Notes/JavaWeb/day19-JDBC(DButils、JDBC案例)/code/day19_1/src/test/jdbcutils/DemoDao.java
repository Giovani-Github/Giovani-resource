package test.jdbcutils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jdbc.utils.JdbcUtils;

import org.apache.commons.dbutils.QueryRunner;

import dbutils.qr.TxQueryRunner;

/*
 * 数据层，进行转账事务的底层数据库表进行操作
 * */
public class DemoDao {
	/***
	 * @param name 对account表中，哪个name进行操作
	 * @param balance 减去或加上的金额
	 * @throws SQLException 
	 */
	public void setAccount(String name, double balance) throws SQLException {
//		//得到简化jdbc操作的对象，QueryRunner
//		QueryRunner qr = new QueryRunner();
//		//准备sql模板
//		String sql = "UPDATE account SET balance=balance+? WHERE name=?";
//		//准备要添加进sql模板的参数
//		Object[] params = {balance, name};
//		
//		/*
//		 * 为保证事务中使用的是同一个Connection，所以我们要自己提供Connection连接
//		 * 如果开启了事务，获取到的连接就是这个事务的Connection连接
//		 * */
//		Connection con = JdbcUtils.getConnection();
//		
//		//执行sql语句
//		qr.update(con, sql, params);
//		
//		/*
//		 * 在这里，我们不知道所获取到的Connection是不是事务专用的连接，所以不知道该不该释放
//		 * 所以我们交还给JdbcUtils，让它自己去处理是否释放的问题
//		 */
//		JdbcUtils.releaseConnection(con);
//		
//		/*
//		 * 上面这样写的话
//		 * 每次都要我们自己手动获取连接：Connection con = JdbcUtils.getConnection();
//		 * 手动进行释放连接：JdbcUtils.releaseConnection(con);
//		 * 这样很麻烦
//		 * 所以我们对DBUtils的QueryRunner类进行装饰，对它的方法进行增强
//		 * 写一个TxQueryRunner类
//		 * */
		
		
		
//----------------------------------------------------------------
		
		/*
		 * 使用我们装饰过的QueryRunner类TxQueryRunner
		 * 使代码更加简洁
		 * */
		
		//得到简化jdbc操作的对象，TxQueryRunner
		QueryRunner qr = new TxQueryRunner();
		//准备sql模板
		String sql = "UPDATE account SET balance=balance+? WHERE name=?";
		//准备要添加进sql模板的参数
		Object[] params = {balance, name};
		
		//执行sql语句，这里面会自动获取连接，并且释放连接
		//如果开启了事务，获取到的就是事务专用连接
		qr.update(sql, params);		
	}
}
