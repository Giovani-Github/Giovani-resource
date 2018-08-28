package jdbc.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/*
 * jdbc小工具1.2版本
 * */
public class JdbcUtils {
	//连接池对象
	private static DataSource dataSource = new ComboPooledDataSource();
	
	/*
	 * 处理多线程问题
	 * 当多个线程使用本类的时候，就会造成多线程并发问题：
	 * 	如：T1线程开启了事务，T2线程又关闭了事务。那么T1线程执行完它的事务的时候，要进行释放事务，就会出错。因为已经被T2线程抢先关闭了
	 * 所以这里我们使用ThreadLocal类来存放事务专用连接
	 * 	它内部有一个map，存放的时候使用当前线程做key，取的时候使用当前线程做key，删的时候也是使用当前线程做key
	 * 	这样我们获取的Connection只会是当前线程专属的事务连接
	 * */
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();//存放事务专用连接，每个线程存放一个事务专用连接，哪个线程放的连接，就只有哪个线程才能获取和删除
	
	
	/*
	 * 使用默认配置获取Connection
	 * */
	public static Connection getConnection() throws SQLException {
		//从t1中，取出我们当前线程专属的Connection
		Connection con = tl.get();

		/*
		 * 判断con是否为null
		 * 	不为null，表示开启了事务，返回的就得是事务专用的connection
		 * 	为null，表示没有开启事务，从连接池返回一个connection
		 * */
		if(con != null) return con;
		return dataSource.getConnection();

	}
	
	/*
	 * 返回连接池对象，供调用者手动配置
	 * */

	public static DataSource getDataSource() {
		return dataSource;
	}
	
	/**
	 * 开启事务
	 * 1.判断con是否为null
	 * 	如果为nul，表示还没有开启事务，从连接池中获取一个Connection为con赋值
	 * 	如果不为null，表示开启了事务，抛出异常
	 * @throws SQLException 
	 */
	public static void beginTransaction() throws SQLException {
		//从t1中，取出我们当前线程专属的Connection
		Connection con = tl.get();
		
		if(con != null) throw new RuntimeException("您已经开启了事务，无需重复开启！");
		
		//当前没有事务，获取到的就是普通连接
		con = getConnection();
		con.setAutoCommit(false);//开启事务
		
		//然后这个普通连接con就是我们事务专用的连接了，保存到tl中
		tl.set(con);
	}
	
	/**
	 * 提交事务
	 * 1.判断con是否为null
	 * 	如果不为null，表示开启了事务，进行事务提交
	 * 	如果为null，表示没有开启事务，抛出异常
	 * 2.调用con.close()方法关闭连接
	 * 3.con=null,表示事务已经结束，下次再getConnection()的时候,
	 * 	获取的就不是事务专用的connection，想要获取到事务专用的connection就必须先开启事务
	 * @throws SQLException 
	 */
	public static void commitTransaction() throws SQLException {
		//从t1中，取出我们当前线程专属的Connection
		Connection con = tl.get();
		
		if(con == null) throw new RuntimeException("您还没有开启事务，不能提交");
		
		con.commit();
		con.close();
		
		//把事务专用连接从tl中移除，只能移除当前线程自己的Con
		tl.remove();
	}
	
	/**
	 * 回滚事务
	 * 1.判断con是否为nul
	 *  如果不为null，表示开启了事务，进行事务回滚
	 * 	如果为null，表示没有开启事务，抛出异常
	 * 2.调用con.close()方法关闭连接
	 * 3.con=null,表示事务已经结束，下次再getConnection()的时候,
	 * 	获取的就不是事务专用的connection，想要获取到事务专用的connection就必须先开启事务
	 * @throws SQLException 
	 */
	public static  void rollbackTransaction() throws SQLException {
		//从t1中，取出我们当前线程专属的Connection
		Connection con = tl.get();
		
		if(con == null) throw new RuntimeException("您还没有开启事务，不能回滚");
	
		con.rollback();
		con.close();
		//把事务专用连接从tl中移除，只能移除当前线程自己的Con
		tl.remove();
	}
	
	/***
	 * 释放连接
	 * -------
	 * 因为使用JdbcUtils.getConnection()所得到的连接，有两种情况
	 * 	事务专用连接：如果得到的是事务专用的连接，那就不用我们来操心释放的问题，
	 * 		因为事务提交或回滚的时候，自动会释放连接
	 * 	普通连接：如果得到的是普通连接，那么就需要我们来释放了。
	 * 		因为getConnection()的调用者，是不知道它所得到的是不是事务连接，从而不知道要不要释放
	 * 		所以，把它得到的连接，传递回JdbcUtils，在JdbcUtils内部进行关闭即可。
	 * 		因为只有JdbcUtils自己清楚这个连接是不是事务专用连接，是否进行释放
	 * @param connection
	 * @throws SQLException 
	 */
	public static void releaseConnection(Connection connection) throws SQLException {
		//从t1中，取出我们当前线程专属的Connection
		Connection con = tl.get();
		
		/*
		 * 如果con == null，表示事务没有开启，
		 * 那么JdbcUtils.getConnection()所获取到的连接必然是普通连接
		 * 既然获取到的是普通连接，那么传递回来给我们自己释放的connection也就是普通连接
		 * 进行释放
		 * */
		if(con == null) connection.close();
		
		/*
		 * 如果con != null，表示开启了事务
		 * 但是con != connection，表示传递回来给我们释放的连接不是事务连接
		 * 进行释放
		 * */
		if(con != connection) connection.close();
	}
}
