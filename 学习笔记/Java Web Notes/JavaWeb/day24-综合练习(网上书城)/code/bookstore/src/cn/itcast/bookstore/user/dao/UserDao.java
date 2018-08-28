package cn.itcast.bookstore.user.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.itcast.bookstore.user.domain.User;
import cn.itcast.jdbc.TxQueryRunner;

//User持久层
public class UserDao {
	//简化jdbc操作的类
	private QueryRunner qr = new TxQueryRunner();
	
	//按用户名查询
	public User findByUsername(String username) {
		try {
			String sql = "SELECT * FROM tb_user WHERE username=?";
			User user = qr.query(sql, new BeanHandler<User>(User.class),username);
			return user;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	//按邮箱查询
	public User findByEmail(String email) {
		try {
			String sql = "SELECT * FROM tb_user WHERE email=?";
			User user = qr.query(sql, new BeanHandler<User>(User.class), email);
			return user;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//添加用户
	public void add(User user) {
		try {
			String sql = "INSERT INTO tb_user VALUES(?,?,?,?,?,?)";
			Object[] params = {user.getUid(), user.getUsername(), 
					user.getPassword(), user.getEmail(), user.getCode(), 
					user.isState()};
			qr.update(sql, params);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//按激活码查询
	public User findByCode(String code) {
		try {
			String sql = "SELECT * FROM tb_user WHERE code=?";
			return qr.query(sql, new BeanHandler<User>(User.class), code);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//修改指定用户的激活状态
	public void updateState(String uid, boolean state) {
		try {
			String sql = "UPDATE tb_user SET state=? WHERE uid=?";
			qr.update(sql, state, uid);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
