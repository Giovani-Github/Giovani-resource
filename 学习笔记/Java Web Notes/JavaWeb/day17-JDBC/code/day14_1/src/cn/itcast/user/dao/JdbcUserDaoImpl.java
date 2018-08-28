package cn.itcast.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.itcast.user.domain.User;

public class JdbcUserDaoImpl implements UserDao {

	//添加用户
	public void add(User user) {
		/*
		 * 1.得到数据库连接，使用jdbcUtils工具类来获得，
		 * 	修改配置文件dbconfig.properties的url，连接到mydb2数据库
		 * 2.准备sql模板
		 * 	> 因为是添加用户，所以使用的sql语句是DML
		 * 	> INSERT INTO user VALUES (?, ?)
		 * 3.通过Connection使用sql模板，得到PreparedStatement
		 * 4.为sql模板的？号赋值，即添加username和password到sql模板
		 * 5.执行sql语句
		 * 6.关闭资源
		 * */
		Connection con = null;
		PreparedStatement prtmt = null;
		
		try {
			//1.得到数据库连接，使用jdbcUtils工具类来获得
			con = JdbcUtils.getConnection();
			//2.准备sql模板
			String sql = "INSERT INTO user VALUES(?, ?)";
			//3.通过Connection使用sql模板，得到PreparedStatement
			prtmt = con.prepareStatement(sql);
			//4.为sql模板的？号赋值，即添加username和password到sql模板
			prtmt.setString(1, user.getUsername());
			prtmt.setString(2, user.getPassword());
			//5.执行sql语句
			prtmt.executeUpdate();
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if(prtmt != null) prtmt.close();
				if(con != null) con.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	//查询用户
	public User findByUsername(String username) {
		/*
		 * 1.得到数据库连接，使用jdbcUtils工具类来获得，
		 * 	修改配置文件dbconfig.properties的url，连接到mydb3数据库
		 * 2.准备sql模板
		 * 	> 因为是查询用户，所以使用的sql语句是DQL
		 * 	> SELECT * FROM user WHERE username=?
		 * 3.通过Connection使用sql模板，得到PreparedStatement
		 * 4.为sql模板的？号赋值，即添加username到sql模板
		 * 5.执行sql语句，得到结果集
		 * 6.结果集转换为User对象，返回
		 * 7.关闭资源
		 * */
		
		Connection con = null;
		PreparedStatement prtmt = null;
		ResultSet rs = null;
		
		try {
			//1.得到数据库连接，使用jdbcUtils工具类来获得
			con = JdbcUtils.getConnection();
			//2.准备sql模板
			String sql = "SELECT * FROM user WHERE username=?";
			//3.通过Connection使用sql模板，得到PreparedStatement
			prtmt = con.prepareStatement(sql);
			//4.为sql模板的？号赋值，即添加username和password到sql模板
			prtmt.setString(1, username);
			//5.执行sql语句，得到结果集
			rs = prtmt.executeQuery();
			//6.结果集转换为User对象，返回
			if(rs == null) return null;//如果没有结果集
			if(rs.next()) {
				User user = new User();
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				return user;
			} else {
				return null;//如果结果集没有数据，即这user表中没有找到指定条件的数据
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(prtmt != null) prtmt.close();
				if(con != null) con.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

}
