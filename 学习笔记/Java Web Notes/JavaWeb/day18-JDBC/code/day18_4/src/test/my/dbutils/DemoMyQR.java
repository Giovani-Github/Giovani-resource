package test.my.dbutils;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import jdbc.utils.JdbcUtils;

import org.junit.Test;

public class DemoMyQR {
	/*
	 * 测试增删改
	 * */
	@Test
	public void fun1() {
		//获取数据库连接池对象
		DataSource dataSource = JdbcUtils.getDataSource();
		
		MyQueryRunner mqr = new MyQueryRunner(dataSource);
		
		//准备sql模板
		String sql = "INSERT INTO stu VALUES(?,?)";
		
		//要为sql模板添加的参数
		Object[] params = {"zs", 20};
		
		//使用MyQueryRunner执行
		mqr.updae(sql, params);
	}
	
	/*
	 * 测试查询
	 * */
	@Test
	public void fun2() {
		//获取数据库连接池对象
		DataSource dataSource = JdbcUtils.getDataSource();
		
		MyQueryRunner mqr = new MyQueryRunner(dataSource);
		
		//准备sql模板
		String sql = "SELECT * FROM stu";
		
		//实现RsHandler接口，把结果集转换成Stu的对象
		RsHandler<Stu> rh = new RsHandler<Stu>() {
			public Stu handle(ResultSet rs) throws SQLException {
				if(!rs.next()) return null;
				//要转换成的对象
				Stu stu = new Stu();
				
				//进行转换
				stu.setName(rs.getString("sname"));
				stu.setAge(rs.getInt("age"));
				return stu;
			}
		};
		
		//使用MyQueryRunner的quer方法，查询出stu对象
		Stu stu = (Stu) mqr.query(sql, rh);
		
		System.out.println(stu);
		
	}
}
