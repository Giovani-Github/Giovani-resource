package test.my.dbutils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

/*
 * 模拟DBUtils的QueryRunner
 * */
public class MyQueryRunner<T> {
	//连接池对象，由用户给出
	private DataSource dataSource;

	public MyQueryRunner(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public MyQueryRunner() {
		super();
	}	
	
	/***
	 * 进行增删改操作
	 * @param sql 给出sql模板
	 * @param params 给出要设置进sql模板的参数
	 * @return 影响的行数
	 */
	public int updae(String sql, Object... params) {
		Connection con = null;//数据库连接对象
		PreparedStatement pstmt = null;//执行sql语句的对象
		
		try {
			//通过数据库连接池获取数据库连接对象
			con = dataSource.getConnection();
			//通过数据库连接对象，获取执行sql语句的对象
			pstmt = con.prepareStatement(sql);
			
			//为sql模板的添加参数
			initParams(pstmt, params);
			
			//执行sql语句，会返回一个影响行数
			return pstmt.executeUpdate();			
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if(con != null) con.close();
				if(pstmt != null) pstmt.close();
			} catch (Exception e2) {
				throw new RuntimeException(e2);
			}
		}
	}

	/***
	 * 为sql模板添加参数
	 * @param pstmt 执行sql语句的对象，sql模板在这个对象中
	 * @param params 要添加的参数
	 */
	private void initParams(PreparedStatement pstmt, Object... params) 
			throws SQLException {
		//传过来的参数有几个，就添加几个到sql模板
		for(int i = 0; i < params.length; i++) {
			pstmt.setObject(i+1, params[i]);
		}
	}
	
	public T query(String sql, RsHandler<T> rh, Object... params) {
		Connection con = null;//数据库连接对象
		PreparedStatement pstmt = null;//执行sql语句的对象
		ResultSet rs = null;//结果集对象
		
		try {
			//通过数据库连接池获取数据库连接对象
			con = dataSource.getConnection();
			//通过数据库连接对象，获取执行sql语句的对象
			pstmt = con.prepareStatement(sql);
			
			//为sql模板的添加参数
			initParams(pstmt, params);
			
			//执行sql语句，会返回一个结果集对象
			rs = pstmt.executeQuery();		
			
			//调用RsHandler的handle方法，把结果集转换成需要的对象
			return rh.handle(rs);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if(con != null) con.close();
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			} catch (Exception e2) {
				throw new RuntimeException(e2);
			}
		}
	}
}

//结果集处理器
//用来把结果集转换成需要的类型
interface RsHandler<T> {
	/***
	 * 把结果集转换成需要的类型
	 * 具体如何实现，由用户自己去实现这个接口的方法
	 * @param rs 结果集对象
	 * @return
	 * @throws SQLException
	 */
	public T handle(ResultSet rs) throws SQLException;
}
