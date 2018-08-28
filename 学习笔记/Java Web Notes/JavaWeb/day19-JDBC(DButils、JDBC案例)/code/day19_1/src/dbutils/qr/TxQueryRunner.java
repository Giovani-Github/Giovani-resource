package dbutils.qr;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.utils.JdbcUtils;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

/*
 * 对dbutils的QueryRunner类进行装饰
 * 对它里面没有Connection参数的方法进行覆盖
 * 让这些方法自己在内部获取连接，并释放连接
 * 	获取:JdbcUtils.getConnection()
 * 		如果开启了事务JdbcUtils.beginTransaction()，那么获取到的就是事务专用连接
 * 		如果没有开启事务，就是普通连接
 * 	释放：JdbcUtils.releaseConnection(Connection)
 * 		把我们获取到的连接返回给JdbcUtils，让它在内部进行释放处理
 * 		因为我们不知道获取到的是事务连接还是普通连接，只有JdbcUtils自己猜清楚
 * */
public class TxQueryRunner extends QueryRunner{

	@Override
	public int[] batch(String sql, Object[][] params) throws SQLException {
		//获取连接
		Connection con = JdbcUtils.getConnection();
		//交给父类的带连接参数的本方法的重载方法进行执行
		int[] i = super.batch(con, sql, params);
		//释放连接
		JdbcUtils.releaseConnection(con);
		//返回重载方法所返回的值
		return i;
	}

	@Override
	public <T> T query(String sql, Object param, ResultSetHandler<T> rsh)
			throws SQLException {
		//获取连接
		Connection con = JdbcUtils.getConnection();
		//交给父类的带连接参数的本方法的重载方法进行执行
		T t = super.query(con, sql, param, rsh);
		//释放连接
		JdbcUtils.releaseConnection(con);
		//返回重载方法所返回的值
		return t;
	}

	@Override
	public <T> T query(String sql, Object[] params, ResultSetHandler<T> rsh)
			throws SQLException {
		//获取连接
		Connection con = JdbcUtils.getConnection();
		//交给父类的带连接参数的本方法的重载方法进行执行
		T t = super.query(con, sql, params, rsh);
		//释放连接
		JdbcUtils.releaseConnection(con);
		//返回重载方法所返回的值
		return t;
	}

	@Override
	public <T> T query(String sql, ResultSetHandler<T> rsh, Object... params)
			throws SQLException {
		//获取连接
		Connection con = JdbcUtils.getConnection();
		//交给父类的带连接参数的本方法的重载方法进行执行
		T t = super.query(con, sql, rsh, params);
		//释放连接
		JdbcUtils.releaseConnection(con);
		//返回重载方法所返回的值
		return t;
	}

	@Override
	public <T> T query(String sql, ResultSetHandler<T> rsh) throws SQLException {
		//获取连接
		Connection con = JdbcUtils.getConnection();
		//交给父类的带连接参数的本方法的重载方法进行执行
		T t = super.query(con, sql, rsh);
		//释放连接
		JdbcUtils.releaseConnection(con);
		//返回重载方法所返回的值
		return t;
	}

	@Override
	public int update(String sql) throws SQLException {
		//获取连接
		Connection con = JdbcUtils.getConnection();
		//交给父类的带连接参数的本方法的重载方法进行执行
		int i = super.update(con, sql);
		//释放连接
		JdbcUtils.releaseConnection(con);
		//返回重载方法所返回的值
		return i;
	}

	@Override
	public int update(String sql, Object param) throws SQLException {
		//获取连接
		Connection con = JdbcUtils.getConnection();
		//交给父类的带连接参数的本方法的重载方法进行执行
		int i = super.update(con, sql, param);
		//释放连接
		JdbcUtils.releaseConnection(con);
		//返回重载方法所返回的值
		return i;
	}

	@Override
	public int update(String sql, Object... params) throws SQLException {
		//获取连接
		Connection con = JdbcUtils.getConnection();
		//交给父类的带连接参数的本方法的重载方法进行执行
		int i = super.update(con, sql, params);
		//释放连接
		JdbcUtils.releaseConnection(con);
		//返回重载方法所返回的值
		return i;
	}
	
}
