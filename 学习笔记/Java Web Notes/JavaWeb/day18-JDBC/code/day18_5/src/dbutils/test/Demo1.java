package dbutils.test;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import jdbc.utils.JdbcUtils;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;


/*
 * 使用DBuitls的工具类QueryRunner来简化jdbc的操作，增删改查
 * */
public class Demo1 {
	/*
	 * 增删改
	 * */
	@Test
	public void fun1() throws SQLException {
		//获取DButils的QueryRunner类，给出数据库连接池对象，供它获取数据库连接
		QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
		
		//给出sql模板
		String sql = "INSERT INTO stu VALUES(?,?)";
		
		//给出要添加到sql模板的参数
		Object[] params = {"ls", 22};
		
		qr.update(sql, params);
	}
	
	//===========查操作，各种结果集处理器的使用================
	
	@Test
	public void fun2() throws SQLException {
		//获取DButils的QueryRunner类，给出数据库连接池对象，供它获取数据库连接
		QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
		
		//给出sql模板
		String sql = "SELECT * FROM stu WHERE sname=?";
		
		//给出要添加到sql模板的参数
		Object[] params = {"zs"};
		
		// 执行query()方法，需要给出结果集处理器，即ResultSetHandler的实现类对象
		// 我们给的是BeanHandler，它实现了ResultSetHandler
		// 构造器需要一个Class类型的参数，用来把一行结果转换成指定类型的javaBean对象
		// 数据库表的列名，必须与转换成的JavaBean的属性名称完全相同
		Stu stu = qr.query(sql, new BeanHandler(Stu.class), params);
		
		System.out.println(stu);
	}
	
	@Test
	public void fun3() throws SQLException {
		//获取DButils的QueryRunner类，给出数据库连接池对象，供它获取数据库连接
		QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
		
		//给出sql模板
		String sql = "SELECT * FROM stu";

		//我们给的是BeanListHandler，它实现了ResultSetHandler
		// 构造器也是需要一个Class类型的参数，用来把一行结果集转换成一个javabean，
		// 那么多行就是转换成List对象，一堆javabean
		// 数据库表的列名，必须与转换成的JavaBean的属性名称完全相同
		List<Stu> liststu = qr.query(sql, new BeanListHandler(Stu.class));
		
		System.out.println(liststu);
	}
	
	@Test
	public void fun4() throws SQLException {
		//获取DButils的QueryRunner类，给出数据库连接池对象，供它获取数据库连接
		QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
		
		//给出sql模板
		String sql = "SELECT * FROM stu WHERE sname=?";
		
		//给出要添加到sql模板的参数
		Object[] params = {"zs"};

		//我们给的是MapHandler，它实现了ResultSetHandler
		//把一行结果集转换Map对象
		// 数据库表的列名，必须与转换成的JavaBean的属性名称完全相同
		Map<String, Object> mapstu = qr.query(sql, new MapHandler(), params);
		
		System.out.println(mapstu);
	}
	
	@Test
	public void fun5() throws SQLException {
		//获取DButils的QueryRunner类，给出数据库连接池对象，供它获取数据库连接
		QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
		
		//给出sql模板
		String sql = "SELECT * FROM stu";
		

		//我们给的是MapListHandler，它实现了ResultSetHandler
		//把一行记录转换成一个Map，多行就是多个Map，即List<Map>！
		// 数据库表的列名，必须与转换成的JavaBean的属性名称完全相同
		List<Map<String, Object>> listmapstu = qr.query(sql, new MapListHandler());
		
		System.out.println(listmapstu);
		

	}
	
	@Test
	public void fun6() throws Exception {
		//获取DButils的QueryRunner类，给出数据库连接池对象，供它获取数据库连接
		QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
		
		//给出sql模板
		String sql = "SELECT count(*) FROM stu";
		

		//我们给的是ScalarHandler，它实现了ResultSetHandler
		// 通常用与select count(*) from t_stu语句！结果集是单行单列的！它返回一个Object
		// 使用select count(*) from t_stu语句返回的数据是数字类型，但是每个数据库对数字类型的描述可能不一样，例如有：Integer、Long、BigInteger
		// 所以，为了避免出现数字类型的转换错误，我们使用数字类型的父类型Number来存qr.query()查询出来的数据
		// 数据库表的列名，必须与转换成的JavaBean的属性名称完全相同
		Number number = (Number) qr.query(sql, new ScalarHandler());
		
		//再由我们自己转成需要的数字类型
		long c = number.longValue();
		System.out.println(c);
	}
}
